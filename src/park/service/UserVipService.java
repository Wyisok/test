package park.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import park.exception.BalanceNotEnoughException;
import park.mapper.ParkMapper;
import park.mapper.ParkVipMapper;
import park.mapper.UserMapper;
import park.mapper.UserVipMapper;
import park.pojo.ParkVip;
import park.pojo.User;
import park.pojo.UserVip;
import park.pojo.UserVipQueryVo;
import park.utils.DateUtils;
import park.utils.UUIDUtils;

@Service
public class UserVipService {
	@Autowired private UserVipMapper userVipMapper;
	@Autowired private ParkVipMapper parkVipMapper;
	@Autowired private UserMapper userMapper;
	@Autowired private ParkMapper parkMapper;
	
	/**
	 * 用户购买vip
	 * @author whp
	 * @param userId
	 * @param parkVipId
	 * @throws BalanceNotEnoughException 
	 */
	public void buyVip(String userId,String parkVipId) throws BalanceNotEnoughException {
		//1. 获取到对应的parkVip
		ParkVip parkVip = parkVipMapper.selectById(parkVipId);
		//2. 获取到用户, 用户钱包扣费，更新
		User user = userMapper.selectById(userId);
		double balance = user.getBalance();
		if(balance<parkVip.getPrice()) {
			throw new BalanceNotEnoughException("用户余额不足");
		}
		balance = balance - parkVip.getPrice();
		user.setBalance(balance);
		userMapper.update(user);
		//3. 创建用户vip，加载相应的天数
		Date endTime = DateUtils.getEndTime(new Date(System.currentTimeMillis()), parkVip.getLengthOfTime());
		UserVip userVip = new UserVip();
		userVip.setUserVipId(UUIDUtils.getUUID());
		userVip.setUserId(user.getUserId());
		userVip.setParkId(parkVip.getParkId());
		userVip.setEndTime(endTime);
		userVipMapper.	insertUserVip(userVip);
	}
	/**
	 * 根据用户id查出该用户所有的vip
	 * @author whp
	 * @param userId
	 * @return
	 */
	public List<UserVipQueryVo> getUserVipByUserId(String userId){
		List<UserVip> userVips = userVipMapper.selectUserVipByUserId(userId);
		List<UserVipQueryVo> uvs = new ArrayList<UserVipQueryVo>();
		for(UserVip uv : userVips) {
			String parkId = uv.getParkId();
			
			UserVipQueryVo uvq = new UserVipQueryVo();
			uvq.setPark(parkMapper.getParkById(parkId));
			uvq.setUserVip(uv);
			
			uvs.add(uvq);
		}
		return uvs;
	}
}
