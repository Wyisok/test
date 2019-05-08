package park.mapper;
/* 
* @author wanghuping 
* @date 2019年4月28日 下午9:35:27 
* @version 1.0 
*/

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import park.pojo.Park;
import park.pojo.ParkQueryVo;

public interface ParkMapper {
	/**
	 * 获取总停车场数
	 * @return
	 */
	@Select("select count(1) from PARK")
	int getParkNum();
	/**
	 * 获取所有的停车场
	 * @return
	 */
	@Select("select *  from PARK")
	List<Park> getAllParks();
	/**
	 * 表格获取一页停车场数据
	 * @param start
	 * @param end
	 * @return
	 */
	@Select("select * from PARK  limit #{0},#{1}")
	List<Park> getPageParks(int start, int end);
	/**
	 * 根据parkId获取停车场
	 * @param parkId
	 * @return
	 */
	@Select("select * from PARK where park_id = #{0}")
	Park getParkById(String parkId);
	
	@Insert("insert into PARK values(#{parkId},#{parkName},#{parkSpotNum},#{address},#{lngLat},#{charge},#{chargeType},#{state})")
	void insertPark(Park park);
	
	@Delete("delete from PARK where park_id = #{0}")
	void deleteParkById(String parkId);
	
	//@Select("select * from PARK p left join TB_BASE_DICT t on p.charge_type=t.dict_id where park_id = #{0}")
//	ParkQueryVo getParkQueryVoById(String parkId);
}
