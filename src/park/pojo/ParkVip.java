package park.pojo;

import java.util.Date;

public class ParkVip {
/** 优惠编号 UUID */
private String parkVipId;
private String parkId;
/** 停车场的vip 有月票，年票，永久 */
private String parkVipType;
/** 价格 */
private double price;
/** 优惠折扣 */
private double discount;
/** 优惠时长 */
private int lengthOfTime;
/** 创建时间 数据库默认生成 */
private Date createTime;
private Date endTime;


public String getParkVipId() {
	return parkVipId;
}
public void setParkVipId(String parkVipId) {
	this.parkVipId = parkVipId;
}
public String getParkId() {
	return parkId;
}
public void setParkId(String parkId) {
	this.parkId = parkId;
}
public String getParkVipType() {
	return parkVipType;
}
public void setParkVipType(String parkVipType) {
	this.parkVipType = parkVipType;
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
public int getLengthOfTime() {
	return lengthOfTime;
}
public void setLengthOfTime(int lengthOfTime) {
	this.lengthOfTime = lengthOfTime;
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
	return "ParkVip [parkVipId=" + parkVipId + ", parkId=" + parkId + ", parkVipType=" + parkVipType + ", price="
			+ price + ", discount=" + discount + ", lengthOfTime=" + lengthOfTime + ", createTime=" + createTime
			+ ", endTime=" + endTime + "]";
}



}
