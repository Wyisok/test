package park.mapper;
 
import java.util.List;

import org.apache.ibatis.annotations.Select;

import park.pojo.Menu;

public interface MenuMapper {
@Select("select * from MENU") 
List<Menu> selectAll();
@Select("select * from MENU where menu_id=#{0}")
Menu selectById(String menuId);

List<Menu> selectAllByUserName(String username);
}
