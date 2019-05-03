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

@Select("select * from USER where user_id=#{0}")
User selectById(String user_id);
@Insert("insert into USER values(#{userId} ,#{username},#{password},#{name},#{sex},#{telephone},#{email},#{balance},#{state},#{createTime})")
void add(User user);
//@Update("update user set username=#{name},password=#{password},name=#{name},sex=#{sex},telephone=#{telephone},email=#{email},balance=#{balance},state=#{state}  where user_id=#{userId}")
void update(User user);
@Delete("delete from USER where user_id=#{0}")
void deleteById(String user_id);

@Select ("select * from USER where username=#{username} and password=#{password} ")
User login(User user);






}
