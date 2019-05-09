package park.pojo;
/**
 * 车位
 * @author whp
 * @date 2019年5月8日
 * @version 1.0
 */
public class ParkSpot {
	/** 车位编号UUID */
	private String parkSpotId;
	/** 停车场uuid */
	private String parkId;
	/** 车位区号 */
	private String spotPlace;
	/** 车位编号A区13号 */
	private String spotNum;
	/** 车位类型 */
	private String spotType;
	/** 汽车编号 */
	private String carId;
	/** 车位空闲状态 */
	private Integer spotState;
	/** 车位停用状态 */
	private Integer state;
	
	
	
	public ParkSpot() {
		super();
	}
	
	public ParkSpot(String parkSpotId2, String parkId2, String spotPlace2, String string, String spotType2) {
		this.parkSpotId = parkSpotId2;
		this.spotPlace = spotPlace2;
		this.parkId = parkId2;
		this.spotNum = string;
		this.spotType = spotType2;
	}
	public String getParkSpotId() {
		return parkSpotId;
	}
	public void setParkSpotId(String parkSpotId) {
		this.parkSpotId = parkSpotId;
	}
	public String getParkId() {
		return parkId;
	}
	public void setParkId(String parkId) {
		this.parkId = parkId;
	}
	public String getSpotPlace() {
		return spotPlace;
	}
	public void setSpotPlace(String spotPlace) {
		this.spotPlace = spotPlace;
	}
	public String getSpotNum() {
		return spotNum;
	}
	public void setSpotNum(String spotNum) {
		this.spotNum = spotNum;
	}
	public String getSpotType() {
		return spotType;
	}
	public void setSpotType(String spotType) {
		this.spotType = spotType;
	}
	public String getCarId() {
		return carId;
	}
	public Integer getSpotState() {
		return spotState;
	}

	public void setSpotState(Integer spotState) {
		this.spotState = spotState;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}
	@Override
	public String toString() {
		return "ParkSpot [parkSpotId=" + parkSpotId + ", parkId=" + parkId + ", spotPlace=" + spotPlace + ", spotNum="
				+ spotNum + ", spotType=" + spotType + ", carId=" + carId + ", spotState=" + spotState + ", state="
				+ state + "]";
	}
}
