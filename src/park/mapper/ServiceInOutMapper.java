package park.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import park.pojo.ServiceInOutPojo;

/**
 * 业务流水数据库操作接口
 * @author whp
 * @date 2019年5月21日
 * @version 1.0
 */
public interface ServiceInOutMapper {
	
	void insert(ServiceInOutPojo sio);
	void update(ServiceInOutPojo sio);
	@Select("select * from TB_IN_OUT where car_id=#{0} and time_out='0000-00-00 00:00:00'")
	ServiceInOutPojo selectNowSioByCarId(String carId);
	@Select("select * from TB_IN_OUT where user_id=#{0} and time_out!='0000-00-00 00:00:00' ORDER BY time_in DESC")
	List<ServiceInOutPojo> selectPastSioByCarId(String userId);

	
}
