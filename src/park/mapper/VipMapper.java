package park.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import park.pojo.Vip;

public interface VipMapper {
	
	@Select("select * from TB_PARK_VIP where park_id = #{0}")
	List<Vip> selectByParkId(String parkId);
	
}
