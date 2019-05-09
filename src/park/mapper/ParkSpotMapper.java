package park.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import park.pojo.ParkSpot;

/**
 * 车位
 * @author whp
 * @date 2019年5月8日
 * @version 1.0
 */
public interface ParkSpotMapper {
	
	@Select("select count(1) from PARK_SPOT")
	int getParkSpotNum();
	
	@Select("select * from PARK_SPOT limit #{0},#{1}")
	List<ParkSpot> getPageParkSpots(int getiDisplayStart, int i);
	
	@Select("select count(1) from PARK_SPOT where park_id=#{0}")
	int getParkSpotNumByParkId(String parkId);

	@Select("select * from PARK_SPOT where park_id=#{2} ORDER BY spot_place,spot_num ASC limit #{0},#{1}")
	List<ParkSpot> getPageParkSpotsByParkId(int getiDisplayStart, int i, String parkId);
	
	@Select("select MAX(spot_num) from PARK_SPOT where park_id=#{1} and spot_place=#{0}")
	Integer getMaxSpotNum(String spotPlace,String parkId);

	void insertSpotByBatch(List<ParkSpot> list);
	@Select("select * from PARK_SPOT where park_spot_id = #{0}")
	ParkSpot getParkSpotById(String parkSpotId);
	
}
