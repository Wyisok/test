package park.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import park.pojo.Car;
import park.service.CarService;
/**
 * 车牌号前端的接口
 * @author whp
 * @date 2019年5月14日
 * @version 1.0
 */
@Controller
public class CarController {
	@Autowired
	private CarService carService;
	
	
	@RequestMapping("/getCarId")
	@ResponseBody
	public Set<Car> getCarIds(String userId){
		Set<Car> cars = carService.getCarIdByUserId(userId);
		System.out.println(cars);
		return cars;
	}
	
	@RequestMapping("/updateCarId")
	@ResponseBody
	public String updateCarId(@RequestParam(value="carIds[]")String arr[],String userId) {
		for(String carId : arr) {
			System.out.println(carId);
		}
		System.out.println(userId);
		carService.updateCarId(arr,userId);
		return "ok";
	}
	/**
	 * 获取所有的车牌号信息
	 * @author whp
	 * @return
	 */
	@RequestMapping("/getCarIds")
	@ResponseBody
	public List<Car> getCarIds(){
		List<Car> cars = carService.getAllCarId();
		System.out.println(cars);
		return cars;
	}
}
