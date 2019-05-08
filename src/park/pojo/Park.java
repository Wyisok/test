package park.pojo;

/* 
* @author wanghuping 
* @date 2019年4月28日 下午9:45:37 
* @version 1.0 
*/
public class Park {
	private String parkId;
	/**
	 * 停车场名称
	 */
	private String parkName;
	/**
	 * 车位数量
	 */
	private int parkSpotNum;
	/**
	 * 停车场地址
	 */
	private String address;
	/**
	 * 停车场经纬度
	 */
	private String lngLat;
	/**
	 * charge=0; charge=一次性价格； charge=每小时多少钱； charge=每次多少钱
	 */
	private double charge;
	/**
	 * 免费、一次性收费、按期收费、标准收费、按次收费
	 */
	private String chargeType;
	/**
	 * 是否启用
	 */
	private int state;

	
	
	@Override
	public String toString() {
		return "Park [parkId=" + parkId + ", parkName=" + parkName + ", parkSpotNum=" + parkSpotNum + ", address="
				+ address + ", lngLat=" + lngLat + ", charge=" + charge + ", chargeType=" + chargeType + ", state="
				+ state + "]";
	}

	public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public int getParkSpotNum() {
		return parkSpotNum;
	}

	public void setParkSpotNum(int parkSpotNum) {
		this.parkSpotNum = parkSpotNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLngLat() {
		return lngLat;
	}

	public void setLngLat(String lngLat) {
		this.lngLat = lngLat;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
