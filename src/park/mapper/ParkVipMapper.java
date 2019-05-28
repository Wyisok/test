package park.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import park.pojo.ParkVip;

public interface ParkVipMapper {
	
	@Select("select * from TB_PARK_VIP where park_id = #{0}")
	List<ParkVip> selectByParkId(String parkId);
	@Select("select count(1) from TB_PARK_VIP where park_id = #{0}")
	int selectNum(String parkId);
	@Select("select * from TB_PARK_VIP where park_id = #{2}  limit #{0},#{1}")
	List<ParkVip> getPage(int getiDisplayStart, int i,String parkId);
	@Select("delete from TB_PARK_VIP where park_vip_id = #{0}")
	void deleteParkVipById(String parkVipId);
	@Insert("insert into TB_PARK_VIP values(#{parkVipId},#{parkId},#{parkVipType},#{discount},#{lengthOfTime},#{price},#{createTime},#{endTime})")
	void insertParkVip(ParkVip parkVip);
	
	void updateParkVip(ParkVip parkVip);
	@Select("select * from TB_PARK_VIP where park_vip_id = #{0}")
	ParkVip selectById(String parkVipId);
	
}
