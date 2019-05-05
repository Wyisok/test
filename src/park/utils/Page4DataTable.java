package park.utils;

import java.util.List;
/**
 * 对datatables插件前台数据请求的封装，
 * 以及响应的封装
 * @author whp
 * @date 2019年5月4日
 * @version 1.0
 * @param <T>
 */
public class Page4DataTable<T> {
	/** 验证码*/
	private String sEcho;
	/** 总列数 */
	private int iColumns;
	/** sColumns=,,,,,,,, */
	private String sColumns;
	/** 起始索引 */
	private int iDisplayStart;
	/** 一页的数据条数 */
	private int iDisplayLength;
	/** 总记录数--数据库中的总记录数  */
	private int iTotalRecords;
	/** 过滤后显示的总记录数，一般与总记录数相同--可忽略不重要  */
	private int iTotalDisplayRecords;
	/**  一页数据 */
	private List<T> aaData;
	
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
	public String getsEcho() {
		return sEcho;
	}
	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	public int getiColumns() {
		return iColumns;
	}
	public void setiColumns(int iColumns) {
		this.iColumns = iColumns;
	}
	public String getsColumns() {
		return sColumns;
	}
	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
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
	@Override
	public String toString() {
		return "Page [sEcho=" + sEcho + ", iColumns=" + iColumns + ", sColumns=" + sColumns + ", iDisplayStart="
				+ iDisplayStart + ", iDisplayLength=" + iDisplayLength + ", iTotalRecords=" + iTotalRecords
				+ ", iTotalDisplayRecords=" + iTotalDisplayRecords + ", aaData=" + aaData + "]";
	}
	
}
