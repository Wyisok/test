package park.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import park.pojo.Car;

/**
 * @author whp
 * @date 2019年5月13日
 * @version 1.0
 */
public interface CarMapper {
	List<Car> getCarIdByUserId(String userId);

	void insertCarIdByUserId(@Param("set") Set<Car> cars);

	void deleteCarIdByUserId(String userId);
	
	@Select("select * from TB_CAR")
	List<Car> getAllCarId();

}
