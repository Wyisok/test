package park.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import park.pojo.UserVip;

public interface UserVipMapper {

	@Select("select * from TB_USER_VIP where user_vip_id = #{0}")
	UserVip selectById(String userVipId);
	@Delete("delete from TB_USER_VIP where user_vip_id = #{0}")
	void deleteById(String userVipId);
	@Select("select * from TB_USER_VIP where park_id = #{0} and user_id = #{1}")
	UserVip selectByParkIdAndUserId(String parkId, String userId);

}
