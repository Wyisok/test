package park.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import park.mapper.UserMapper;
import park.pojo.User;
import park.utils.DataTablePage;
import park.utils.Page;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	/**
	 * 查询全部
	 */
	public List<User> getAllUser(){
		return userMapper.getAll();
	}
	
	/*public Page getAllUsers(int currentPageNo){
		
		Page page=new Page();
		
		//��װ��ǰҳ��
		page.setCurrentPageNo(currentPageNo);
		//��װ�ܼ�¼��
		Integer allRecord = userMapper.selectAll();
		page.setRecordNo(allRecord);
		//��װ��ҳ��
		Integer allPageNo=null;
		if(page.getRecordNo()%page.getPageSize()==0){
			allPageNo=page.getRecordNo()/page.getPageSize();
		}else{
			allPageNo=page.getRecordNo()/page.getPageSize()+1;
		}
		page.setPageNo(allPageNo);
		System.out.println("��ҳ��"+page.getPageNo());
		//�ж�
		if(page.getCurrentPageNo()<=0){
			page.setCurrentPageNo(1);                          //�ѵ�ǰҳ����Ϊ1
		}else if(page.getCurrentPageNo()>page.getPageNo()){
			page.setCurrentPageNo(page.getPageNo());          //�ѵ�ǰҳ����Ϊ���ҳ��
		}
		
		
		//��װ��ҳ��ʾ������
		Integer start=(page.getCurrentPageNo()-1)*page.getPageSize();
		Integer end=page.getCurrentPageNo()*page.getPageSize()+1;
		List<User> emplist=userMapper.getAll(start, end);
		page.setUserList(emplist);
		return page;
	}*/
	
	/**
	 * ����û�
	 * @return 
	 */
	public void addUser(User user){
		userMapper.add(user);
	}
	/**
	 * �����û�
	 */
	public void update(User user){
		String a=UUID.randomUUID().toString();
		user.setUserId(a);
		System.out.println(a);
		userMapper.update(user);
	}
	/**
	 * ɾ���û�
	 */
	public void detele(String user_id){
	userMapper.deleteById(user_id);
	}

	public User getById(String userid) {
		
		return userMapper.selectById(userid);
	}

	public void getAllUser(DataTablePage<User> dataTablePage) {
		int count = userMapper.selectAll();
		dataTablePage.setiTotalRecords(count);
		dataTablePage.setiTotalDisplayRecords(count);
		List<User> page = userMapper.getPage(dataTablePage.getiDisplayStart(), dataTablePage.getiDisplayEnd());
		dataTablePage.setAaData(page);
		
	}
	
}
