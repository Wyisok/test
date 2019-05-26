package park.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import park.pojo.Car;
import park.pojo.ParkSpotQueryVo;
import park.pojo.ServiceInOutPojo;
import park.pojo.User;
import park.service.CarService;
import park.service.ParkService;
import park.service.ParkSpotService;
import park.service.ServiceInOut;
import park.utils.JsonDate2String;

/**
 * 业务流水信息前端控制器
 * 
 * @author whp
 * @date 2019年5月25日
 * @version 1.0
 */
@Controller
public class ServiceInOutController {
	@Autowired
	private ServiceInOut serviceInOut;
	@Autowired
	private CarService carService;
	@Autowired
	private ParkService parkService;
	@Autowired
	private ParkSpotService parkSpotService;

	/**
	 * 获取当前用户正在停泊的车辆信息
	 * 
	 * @author whp
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getCarState")
	@ResponseBody
	public String getCarState(String userId) {
		// 1. 查找当前用户所有的车辆
		Set<Car> cars = carService.getCarIdByUserId(userId);
		if (cars.size() == 0) {
			return null;
		}
		// 2. 查找此用户车辆中正在停车的所有业务流水信息
		List<ServiceInOutPojo> sios = new ArrayList<ServiceInOutPojo>();
		for (Car car : cars) {
			ServiceInOutPojo sio = serviceInOut.getNowServiceInOutByCarId(car.getCarId());
			if (sio != null) {
				sios.add(sio);
			}
		}
		if (sios.size() == 0) {
			return null;
		}
		// 3. 将所有的业务流水信息包装成parkSpotQueryVo,显示到前台
		List<ParkSpotQueryVo> carInfos = new ArrayList<ParkSpotQueryVo>();
		for (ServiceInOutPojo sio : sios) {
			ParkSpotQueryVo carInfo = new ParkSpotQueryVo();
			carInfo.setSio(sio);
			carInfo.setPark(parkService.getParkById(sio.getParkId()));
			carInfo.setParkSpot(parkSpotService.getPSById(sio.getParkSpotId()));
			carInfos.add(carInfo);
		}
		// ------------carInfos中有日期属性，所以需要转换，否则显示一大串数字
		JSONArray json = JSONArray.fromObject(carInfos, JsonDate2String.getDateStringJsonConfig("yyyy-MM-dd HH:mm:ss"));
		System.out.println(json);
		return json.toString();
	}

	/**
	 * 获取到当前用户所有的停车记录
	 * 
	 * @author whp
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getCarRecord")
	@ResponseBody
	public String getCarRecord(String userId) {
		// 1. 查找当前用户所有的车辆
		Set<Car> cars = carService.getCarIdByUserId(userId);
		if (cars.size() == 0) {
			return null;
		}
		// 2. 查找这些车辆的已完成订单，根据timeIn日期排序
		List<ServiceInOutPojo> sios = serviceInOut.getPastServiceInOutByCarId(userId);
		if (sios.size() == 0) {
			return null;
		}
		// 3. 将所有的业务流水信息包装成parkSpotQueryVo,显示到前台
		List<ParkSpotQueryVo> carInfos = new ArrayList<ParkSpotQueryVo>();
		for (ServiceInOutPojo sio : sios) {
			ParkSpotQueryVo carInfo = new ParkSpotQueryVo();
			carInfo.setSio(sio);
			carInfo.setPark(parkService.getParkById(sio.getParkId()));
			carInfo.setParkSpot(parkSpotService.getPSById(sio.getParkSpotId()));
			carInfos.add(carInfo);
		}
		// ------------carInfos中有日期属性，所以需要转换，否则显示一大串数字
		JSONArray json = JSONArray.fromObject(carInfos, JsonDate2String.getDateStringJsonConfig("yyyy-MM-dd HH:mm:ss"));
		System.out.println(json);
		return json.toString();
	}
}
