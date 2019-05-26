package park.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import park.exception.BalanceNotEnoughException;
import park.exception.CarNotInParkException;
import park.mapper.ParkMapper;
import park.mapper.ParkSpotMapper;
import park.mapper.ServiceInOutMapper;
import park.mapper.UserMapper;
import park.mapper.UserVipMapper;
import park.mapper.VipMapper;
import park.pojo.Park;
import park.pojo.ParkSpot;
import park.pojo.ServiceInOutPojo;
import park.pojo.User;
import park.pojo.UserVip;
import park.pojo.Vip;
import park.utils.DateUtils;
import park.utils.UUIDUtils;

/**
 * 车辆进出入停车场
 * 业务流水
 * @author whp
 * @date 2019年5月21日
 * @version 1.0
 */
@Service
public class ServiceInOut {
	@Autowired
	private AppPushService appPushService;
	@Autowired
	private ParkSpotService parkSpotService;
	@Autowired
	private ServiceInOutMapper serviceInOutMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ParkMapper parkMapper;
	@Autowired
	private VipMapper vipMapper;
	@Autowired
	private UserVipMapper userVipMapper;
	@Autowired
	private ParkSpotMapper  parkSpotMapper;
	
	
	/**
	 * 插入业务流水记录
	 * @author whp
	 * @param carId
	 * @param parkSpot
	 * @param user
	 */
	public void inParkRecord(String carId, ParkSpot parkSpot, User user) {
		ServiceInOutPojo sio = new ServiceInOutPojo();
		sio.setServiceId(UUIDUtils.getUUID());
		sio.setParkId(parkSpot.getParkId());
		sio.setParkSpotId(parkSpot.getParkSpotId());
		sio.setCarId(carId);
		sio.setUserId(user.getUserId());
		
		//1. 获取用户的vip编号
		UserVip userVip = userVipMapper.selectByParkIdAndUserId(parkSpot.getParkId(),user.getUserId());
		if (userVip!=null) {
			sio.setUserId(userVip.getUserVipId());
		}
		serviceInOutMapper.insert(sio);
	}
	/**
	 * 退出停车场业务流水
	 * 事务处理
	 * @author whp
	 * @param carId
	 * @throws BalanceNotEnoughException 
	 * @throws CarNotInParkException 
	 */
	public void outParkRecord(String carId) throws BalanceNotEnoughException, CarNotInParkException {
		//1. 根据carId和timeout为null即可获取当前车辆尚未完成的订单
		ServiceInOutPojo sio = getNowServiceInOutByCarId(carId);
		//--停车场没有当前车辆，则退出
		if(sio==null) {
			throw new CarNotInParkException("当前车辆不在当前停车场");
		}
		//2. 判断是否有vip，如果有，则根据VIP进行车价计算，income属性赋值
		double cost;
		String userVipId = sio.getUserVipId();
		if("".equals(userVipId) || userVipId==null) {//用户没有购买vip
			cost = getCount(sio);
		}else {
			//2.1 取出用户的优惠卡
			UserVip userVip = userVipMapper.selectById(userVipId);
			//2. 2 判断用户卡是否到期
			Date now = new Date();
			if(userVip.getEndTime().getTime()<now.getTime()) {//到期后使用普通付费模式
				cost = getCount(sio);
			}else {
				cost = 0;//--有vip则免费
			}
		}
		//3.1 用户钱包扣费，发送退出停车场通知 
		User user = userMapper.selectByCarId(carId);
		double balance = user.getBalance();
		//3.1.1 如果余额不足，则提醒用户余额不足，退出停车场失败
		if(balance<cost) {
			appPushService.sendRechargeMessage(user);
			throw new BalanceNotEnoughException("用户余额不足");
		}
		//3.1.2 用户钱包扣费
		balance -= cost;
		user.setBalance(balance);
		userMapper.update(user);//----------------------------------------------------------用户钱包信息更新
		//3.2  收入会转到停车场的总收入中，timeout赋值
		sio.setTimeOut(new Date());
		sio.setIncome(cost);// 当前订单的价格
		serviceInOutMapper.update(sio);//----------------------------------------------------------业务流水更新
		Park park = parkMapper.getParkById(sio.getParkId());
		double income = park.getIncome();
		income += cost;
		park.setIncome(income);//---------------------------平台完全免费
		parkMapper.updateParkInfo(park);//------------------------------------------------------停车场信息更新
		//3.3 车位更新为未占用
		ParkSpot parkSpot = parkSpotMapper.getParkSpotById(sio.getParkSpotId());
	
		parkSpotService.outParkUpdateSpot(parkSpot);//----------------------------------------------车位信息更新
	}
	
	/**
	 * 计算出用户停车的消费金额，当没有vip时；
	 * @author whp
	 * @param park
	 * @param sio 
	 * @return
	 */
	private double getCount(ServiceInOutPojo sio) {
		//2.1 计算停车费用--没有优惠
		//2.1.1 获取到停车场的收费模式，以及计费
		Park park = parkMapper.getParkById(sio.getParkId());
		double charge = park.getCharge();//收费金额
		String chargeType = park.getChargeType();//停车场收费类型 免费、标准收费、按次收费
		switch (chargeType) {
		case "免费":
			return 0;
		case "标准收费":
			//--计算停车使用了多少个小时
			Date timeIn = sio.getTimeIn();//进入停车场时间
			Date timeOut = new Date();//当前时间为退出停车场时间
			long hours = DateUtils.getHours(timeIn, timeOut);
			return hours*charge;
		case "按次收费":
			return charge;
		default:
			break;
		}
		return 0;
	}
	/**
	 * 根据carId和timeout为null即可获取当前车辆尚未完成的订单
	 * @author whp
	 * @param carId
	 * @return
	 */
	public ServiceInOutPojo getNowServiceInOutByCarId(String carId) {
		return serviceInOutMapper.selectNowSioByCarId(carId);
	}
	/**
	 * 根据userId 和timeOut不为null 查出当前用户所有的历史订单
	 * @author whp
	 * @param userId
	 * @return
	 */
	public List<ServiceInOutPojo> getPastServiceInOutByCarId(String userId) {
		return serviceInOutMapper.selectPastSioByCarId(userId);
	}
}
