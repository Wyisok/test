package park.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import park.pojo.User;

public interface UserMapper {
@Select("select count(*) from USER") 
int selectAll();
@Select("select * from USER")
List<User> getAll();
@Select("select * from USER limit #{0},#{1}")
List<User> getPage(int start , int end);

@Select("select * from user where userId=#{0}")
User selectById(String user_id);
@Insert("insert into user values(#{default} ,#{username},#{password},#{name},#{sex},#{telephone},#{email},#{balance},#{state},#{createTime},#{parkId})")
void add(User user);
@Update("update user set name=#{name} where user_id=#{userId}")
void update(User user);
@Delete("delete from user where userId=#{0}")
void deleteById(String user_id);



}
