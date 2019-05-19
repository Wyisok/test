package park.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import park.pojo.Park;
import park.pojo.ParkSpot;
import park.pojo.User;
import park.service.AppPushService;
import park.service.ParkService;
import park.service.ParkSpotService;
import park.service.UserService;

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
		User user = userService.getUserByCarId(carId);
		if(user==null) {
			return "register";
		}
		//2. 验证 停车场是否有空闲车位
		Park park = parkService.getParkById(parkId);
		List<ParkSpot> parkSpots = parkSpotService.getFreePSByParkId(parkId);
		if(parkSpots==null || parkSpots.size()==0) {
			return "full";
		}
		System.out.println(parkSpots);
		ParkSpot parkSpot = parkSpots.get(0);
		//3. 判断用户是否在线，如果在线则用app通知，不在线用短信通知，判断cid是否存在
		appPushService.sendParkSpotArrange(user,park,parkSpot);
		return "ok";
	}
}
