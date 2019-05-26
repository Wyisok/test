package park.pojo;
/**
 * 业务流水实体
 * @author whp
 * @date 2019年5月21日
 * @version 1.0
 */

import java.util.Date;

public class ServiceInOutPojo {
	/** uuid*/
	private String serviceId;
	private String parkId;
	private String parkSpotId;
	private String carId;
	private String userId;
	/** 用户vipid*/
	private String userVipId;
	/** 用户进入车位时间*/
	private Date timeIn;
	/** 用户退出车位时间*/
	private Date timeOut;
	/** 收入*/
	private double income;
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
	public String getParkSpotId() {
		return parkSpotId;
	}
	public void setParkSpotId(String parkSpotId) {
		this.parkSpotId = parkSpotId;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserVipId() {
		return userVipId;
	}
	public void setUserVipId(String userVipId) {
		this.userVipId = userVipId;
	}
	public Date getTimeIn() {
		return timeIn;
	}
	public void setTimeIn(Date timeIn) {
		this.timeIn = timeIn;
	}
	public Date getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(Date timeOut) {
		this.timeOut = timeOut;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	@Override
	public String toString() {
		return "ServiceInOut [serviceId=" + serviceId + ", parkId=" + parkId + ", parkSpotId=" + parkSpotId + ", carId="
				+ carId + ", userId=" + userId + ", userVipId=" + userVipId + ", timeIn=" + timeIn + ", timeOut="
				+ timeOut + ", income=" + income + "]";
	}
	
}
