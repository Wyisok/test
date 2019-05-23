package park.pojo;

import java.util.Date;

public class Vip {
/** 停车场的vip 有月票，年票，永久 */
private String vipId;
private String parkId;
private String parkVipId;
/** 价格 */
private double price;
/** 优惠折扣 */
private double discount;
private Date createTime;
private Date endTime;
public String getVipId() {
	return vipId;
}
public void setVipId(String vipId) {
	this.vipId = vipId;
}
public String getParkId() {
	return parkId;
}
public void setParkId(String parkId) {
	this.parkId = parkId;
}
public String getParkVipId() {
	return parkVipId;
}
public void setParkVipId(String parkVipId) {
	this.parkVipId = parkVipId;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public double getDiscount() {
	return discount;
}
public void setDiscount(double discount) {
	this.discount = discount;
}
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
public Date getEndTime() {
	return endTime;
}
public void setEndTime(Date endTime) {
	this.endTime = endTime;
}
@Override
public String toString() {
	return "Vip [vipId=" + vipId + ", parkId=" + parkId + ", parkVipId=" + parkVipId 
			+ ", price=" + price + ", discount=" + discount + ", createTime=" + createTime + ", endTime=" + endTime
			+ "]";
}


}
