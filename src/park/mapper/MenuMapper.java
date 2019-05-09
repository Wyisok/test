package park.mapper;
 
import java.util.List;

import org.apache.ibatis.annotations.Select;

import park.pojo.Menu;
import park.pojo.User;

public interface MenuMapper {
@Select("select * from menu") 
List<Menu> selectAll();
@Select("select * from menu where menu_id=#{0}")
Menu selectById(String menuId);
List<Menu> selectAllByUserName(String username);
}
