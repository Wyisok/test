package park.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import park.mapper.UserMapper;
import park.pojo.User;
import park.utils.Page;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	/**
	 * 查询所有用户
	 */
	public Page getAllUsers(int currentPageNo){
		
		Page page=new Page();
		
		//封装当前页号
		page.setCurrentPageNo(currentPageNo);
		//封装总记录数
		Integer allRecord = userMapper.selectAll();
		page.setRecordNo(allRecord);
		//封装总页数
		Integer allPageNo=null;
		if(page.getRecordNo()%page.getPageSize()==0){
			allPageNo=page.getRecordNo()/page.getPageSize();
		}else{
			allPageNo=page.getRecordNo()/page.getPageSize()+1;
		}
		page.setPageNo(allPageNo);
		System.out.println("总页数"+page.getPageNo());
		//判断
		if(page.getCurrentPageNo()<=0){
			page.setCurrentPageNo(1);                          //把当前页设置为1
		}else if(page.getCurrentPageNo()>page.getPageNo()){
			page.setCurrentPageNo(page.getPageNo());          //把当前页设置为最大页数
		}
		
		
		//封装本页显示的内容
		Integer start=(page.getCurrentPageNo()-1)*page.getPageSize();
		Integer end=page.getCurrentPageNo()*page.getPageSize()+1;
		List<User> emplist=userMapper.getAll(start, end);
		page.setUserList(emplist);
		return page;
	}
	/**
	 * 根据id查询用户
	 */
	public User selectById(Integer user_id){
		return userMapper.selectById(user_id);
	}
	/**
	 * 添加用户
	 * @return 
	 */
	public void addUser(User user){
		userMapper.add(user);
	}
	/**
	 * 更新用户
	 */
	public void update(User user){
		userMapper.update(user);
	}
	/**
	 * 删除用户
	 */
	public void detele(Integer user_id){
	userMapper.deleteById(user_id);
	}
	
}
