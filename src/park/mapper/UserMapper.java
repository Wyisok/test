package park.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import park.pojo.User;

public interface UserMapper {
@Select("select count(*) from user") 
int selectAll();
@Select("select * from user limit #{0},#{1}")
List<User> getAll(Integer start, Integer end);

@Select("select * from user where user_id=#{0}")
User selectById(Integer user_id);
@Insert("insert into user values(#{default} ,#{user_name},#{password},#{name},#{sex},#{telephone},#{email},#{balance},#{state},#{create_time},#{park_id})")
void add(User user);
@Update("update user set name=#{name} where user_id=#{user_id}")
void update(User user);
@Delete("delete from user where user_id=#{0}")
void deleteById(Integer user_id);



}
