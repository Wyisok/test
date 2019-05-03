package park.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import park.pojo.UserRole;

public interface UserRoleMapper {
	int deleteByPrimaryKey(Long id);
	@Select("insert into TB_USER_ROLE values(null,#{userId},#{roleId},#{identityId})")
	void insert(UserRole record);
	@Select("select * from TB_USER_ROLE where user_id = #{0}")
	List<UserRole> selectByUserId(String userId);
	
	int insertSelective(UserRole record);

	UserRole selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserRole record);

	int updateByPrimaryKey(UserRole record);
}
