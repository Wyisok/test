package park.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import park.pojo.RoleMenu;

public interface RoleMenuMapper {
@Select("select * from tb_role_menu")
List<RoleMenu> selectAll();
@Select("select * from tb_role_menu where id=#{0}")
RoleMenu selectById(Integer id);

}
