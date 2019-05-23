package park.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import park.exception.BalanceNotEnoughException;
import park.exception.CarNotInParkException;
import park.exception.CarInParkException;
import park.exception.ParkSpotBeOccupyException;
import park.exception.ParkWithoutFreeSpotException;
import park.exception.UserNoRegisterException;
import park.pojo.Park;
import park.pojo.ParkSpot;
import park.pojo.ServiceInOut;
import park.pojo.User;
import park.service.AppPushService;
import park.service.InOutService;
import park.service.ParkService;
import park.service.ParkSpotService;
import park.service.UserService;
import park.utils.UUIDUtils;

@Controller
public class HardwareInterface {
	
	@Autowired
	private ParkService parkService;
	@Autowired
	private UserService userService;
	@Autowired
	private ParkSpotService parkSpotService;
	@Autowired
	private AppPushService appPushService;
	@Autowired
	private InOutService inOutService;
	/**
	 * 进入停车场业务
	 * @author whp
	 * @param parkId
	 * @param carId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/enterPark")
	public String enterPark(String parkId,String carId) {
		System.out.println(parkId + "*" + carId);
		//1. 验证 车牌号是否被注册 取出用户对象
		User user = userService.getUserByCarId(carId);//-----------------------------------用户
		//--------------------------------------------------------验证
		//1. 验证用户
		if(user==null) {
			try {
				throw new UserNoRegisterException("用户尚未注册");
			} catch (UserNoRegisterException e) {
				return e.getMessage();
			}
		}
		//2. 验证车辆
		ServiceInOut sio = inOutService.getServiceInOutByCarId(carId);
		if(sio!=null) {
			try {
				throw new CarInParkException("当前车辆尚未退出停车场");
			} catch (CarInParkException e) {
				return e.getMessage();
			}
		}
		
		Park park = parkService.getParkById(parkId);//--------------------------------------停车场
		//2. 验证 停车场是否有空闲车位
		List<ParkSpot> parkSpots;
		try {
			parkSpots = parkSpotService.getFreeParkSpots(park);
		} catch (ParkWithoutFreeSpotException e) {
			return e.getMessage();
		}
		
		ParkSpot parkSpot = parkSpots.get(0);//----------------------------------------------车位
		//当前车位状态更新
		try {
			parkSpotService.enterParkUpdateSpot(parkSpot,carId);
		} catch (ParkSpotBeOccupyException e) {
			return e.getMessage();
		}
		
		
		//3. 发送消息通知
		try {
			appPushService.sendParkSpotArrange(user,park,parkSpot);
		} catch (ParkSpotBeOccupyException e) {
			return e.getMessage();
		}
		//4 业务流水表--进入停车场业务信息
		inOutService.inParkRecord(carId,parkSpot,user);
		return "ok";
	}
	
	/**
	 * 退出停车场业务
	 * @author whp
	 * @param parkId
	 * @param carId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/exitPark")
	public String outPark(String carId) {
		System.out.println(carId);
		// 业务流水订单处理,对尚未完成的业务流水信息进行完善
			try {
				inOutService.outParkRecord(carId);
			} catch (BalanceNotEnoughException e) {
				return e.getMessage();
			} catch (CarNotInParkException e) {
				return e.getMessage();
			}
		return "ok";
	}
	
	
}
