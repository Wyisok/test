package park.utils;

import java.util.ArrayList;
import java.util.List;

import park.pojo.User;


public class Page{
	private Integer currentPageNo; //��ǰҳ��
	private Integer PageSize=5;      //ÿҳ��ʾ�ļ�¼��
	private Integer RecordNo;      //�ܼ�¼��
	private Integer PageNo;        //��ҳ��
	private List<User> userList=new ArrayList<User>(); //��ҳ��ʾ������
	public Integer getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(Integer currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public Integer getPageSize() {
		return PageSize;
	}
	public void setPageSize(Integer pageSize) {
		PageSize = pageSize;
	}
	public Integer getRecordNo() {
		return RecordNo;
	}
	public void setRecordNo(Integer recordNo) {
		RecordNo = recordNo;
	}
	public Integer getPageNo() {
		return PageNo;
	}
	public void setPageNo(Integer pageNo) {
		PageNo = pageNo;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}


	}