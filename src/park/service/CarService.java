package park.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import park.mapper.CarMapper;
import park.pojo.Car;

/**
 * 车牌号业务处理
 * @author whp
 * @date 2019年5月13日
 * @version 1.0
 */
@Service
public class CarService {
	@Autowired
	private CarMapper carMapper;
	
	/**
	 * 通过用户id设置车牌号
	 * @author whp
	 * @param userId
	 */
	public void addCarIdByUserId(String userId, Set<Car> cars) {
		//设置用户id
		for(Car car : cars) {
			car.setUserId(userId);
		}
		System.out.println(cars);
		carMapper.insertCarIdByUserId(cars);
	}
	/**
	 * 根据用户id查找所有的车牌号
	 * @author whp
	 * @param userId
	 * @return
	 */
	public Set<Car> getCarIdByUserId(String userId) {
			List<Car> cars1 = carMapper.getCarIdByUserId(userId);
			Set<Car> cars = new HashSet<Car>(cars1);
			return cars;
	}
	/**
	 * 更新用户的车牌号信息
	 * @author whp
	 * @param arr
	 * @param userId
	 */
	public void updateCarId(String[] arr, String userId) {
		//1. 将数组转为Car的set集合
		Set<Car> cars = new HashSet<Car>();
		for(String carId : arr) {
			Car car = new Car();
			car.setCarId(carId);
			car.setUserId(userId);
			cars.add(car);
		}
		//2. 根据userId删除所有的车牌信息
		carMapper.deleteCarIdByUserId(userId);
		//3. 判断cars是否为空，若为空则返回
		if(cars.size()==0) {
			return;
		}
		//4. 将set集合中的车牌加入数据库
		carMapper.insertCarIdByUserId(cars);
		
	}
	/**
	 * 获取数据库中所有的车牌号
	 * @author whp
	 * @return
	 */
	public List<Car> getAllCarId() {
		List<Car> cars = carMapper.getAllCarId();
		return cars;
	}
}
