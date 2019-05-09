package park.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import park.pojo.Role;

public interface RoleMapper {
	@Select("select * from ROLE")
	List<Role> selectAllRole();
	
	@Select("select * from ROLE where role_id = #{0}")
	Role selectByPrimaryKey(String roleId);
	
	List<Role> selectAllByUserName(String username);
	
    int deleteByPrimaryKey(String roleId);

    int insert(Role record);

    int insertSelective(Role record);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}
