package park.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import park.mapper.UserMapper;
import park.pojo.User;
import park.utils.Page4DataTable;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;

	/**
	 * 查询全部
	 */
	public List<User> getAllUser() {
		return userMapper.getAll();
	}

	/**
	 * ����û�
	 * 
	 * @return
	 */
	public void addUser(User user) {
		UUID uuid = UUID.randomUUID();
		user.setUserId(uuid.toString());
		userMapper.add(user);
	}

	/**
	 * 更新用户
	 */
	public void update(User user) {
		userMapper.update(user);
	}

	/**
	 * 删除用户
	 */
	public void detele(String user_id) {
		userMapper.deleteById(user_id);
	}

	/**
	 * 根据id查询用户
	 * 
	 * @param userid
	 * @return
	 */
	public User getById(String userId) {

		return userMapper.selectById(userId);
	}

	public void getAllUser(Page4DataTable<User> allData) {
		int count = userMapper.selectAll();
		allData.setiTotalRecords(count);
		allData.setiTotalDisplayRecords(count);
		List<User> page = userMapper.getPage(allData.getiDisplayStart(),allData.getiDisplayStart()+allData.getiDisplayLength());
		allData.setAaData(page);
	}

	/**
	 * 根据用户名查询
	 */
	public User selectByUsername(String username) {
		return userMapper.selectByUsername(username);
	}

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 */
	public User login(User user) {
		return userMapper.login(user);
	}

	/**
	 * 用户注册
	 */
	public void register(User user) {
		userMapper.add(user);
	}
	
	/**
	 * 将客户端id绑定到 用户对象中
	 * 用于定向发送消息
	 * @author whp
	 * @param clientId
	 * @param userId
	 */
	public void bindCid2Uid(String clientId, String userId) {
		//1. 获取用户
		User user = userMapper.selectById(userId);
		//2. 设置
		user.setClientId(clientId);
		//3.更新
		userMapper.update(user);
	}
	/**
	 * 根据车牌号获取所属用户
	 * @author whp
	 * @param carId
	 * @return
	 */
	public User getUserByCarId(String carId) {
		User user = userMapper.selectByCarId(carId);
		return user;
	}
	/**
	 * 获取用户余额
	 * @author whp
	 * @param userId
	 * @return
	 */
	public double getBalance(String userId) {
		User user = getById(userId);
		return user.getBalance();
	}

}
