package park.mapper;

import java.util.Date;

public class UserVip {
	private String vip_id;
	private String user_id;
	private Date buy_time;
	private Date end_time;
	public String getVip_id() {
		return vip_id;
	}
	public void setVip_id(String vip_id) {
		this.vip_id = vip_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getBuy_time() {
		return buy_time;
	}
	public void setBuy_time(Date buy_time) {
		this.buy_time = buy_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
}
