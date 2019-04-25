package park.utils;

import java.util.ArrayList;
import java.util.List;

import park.pojo.User;


public class Page{
	private Integer currentPageNo; //当前页号
	private Integer PageSize=5;      //每页显示的记录数
	private Integer RecordNo;      //总记录数
	private Integer PageNo;        //总页号
	private List<User> userList=new ArrayList<User>(); //本页显示的内容
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