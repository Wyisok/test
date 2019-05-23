package park.mapper;

import org.apache.ibatis.annotations.Select;

import park.pojo.ServiceInOut;

/**
 * 业务流水数据库操作接口
 * @author whp
 * @date 2019年5月21日
 * @version 1.0
 */
public interface ServiceInOutMapper {
	
	void insert(ServiceInOut sio);
	void update(ServiceInOut sio);
	@Select("select * from TB_IN_OUT where car_id=#{0} and time_out='0000-00-00 00:00:00'")
	ServiceInOut selectByCarId(String carId);

	
}
