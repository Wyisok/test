package park.utils;

import java.util.List;

/**
 * 分页Bean  先给sEcho赋值，这是验证码
 * iDisplayEnd=iDisplayStart+iDisplayLength，
 * limit(iDisplayStart,iDisplayEnd)   
 * 查出来的数据再赋值给aaData, JSONObject.fromObject(DataTablePage dtp)传到前台
 * @author Admin
 * @param <T>
 */
public class DataTablePage<T> {
	private String sEcho ;//---前后台交互的一个验证数
	private int iTotalRecords;//---总记录数--数据库中的总记录数
	private int iTotalDisplayRecords;//---过滤后显示的总记录数，一般与总记录数相同--可忽略不重要
	private int iDisplayStart; //--数据起始索引，默认为0
	private int iDisplayLength; //--每页显示的行数
	private int iDisplayEnd; //--数据结束索引，计算获得=iDisplayStart+iDisplayLength
	private List<T> aaData;//----一页数据
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	public int getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public List<T> getAaData() {
		return aaData;
	}
	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}
	public int getiDisplayStart() {
		return iDisplayStart;
	}
	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	public int getiDisplayLength() {
		return iDisplayLength;
	}
	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	public int getiDisplayEnd() {
		return iDisplayEnd;
	}
	public void setiDisplayEnd(int iDisplayEnd) {
		this.iDisplayEnd = iDisplayEnd;
	}
}
