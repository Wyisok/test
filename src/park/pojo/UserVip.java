package park.pojo;

import java.util.Date;
/**
 * 用户vip实体
 * @author whp
 * @date 2019年5月22日
 * @version 1.0
 */
public class UserVip {
	/** 用户购买的vip编号 */
	private String userVipId;
	/** 停车场编号 */
	private String parkId;
	private String userId;
	/** 用户vip购买时间 */
	private Date buyTime;
	/** 用户vip结束时间 */
	private Date endTime;
	public String getUserVipId() {
		return userVipId;
	}
	public void setUserVipId(String userVipId) {
		this.userVipId = userVipId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
	@Override
	public String toString() {
		return "UserVip [userVipId=" + userVipId + ", parkId=" + getParkId() + ", userId=" + userId + ", buyTime=" + buyTime
				+ ", endTime=" + endTime + "]";
	}
	
}
