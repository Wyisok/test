package park.pojo;
/**
 * 车牌号
 * @author whp
 * @date 2019年5月13日
 * @version 1.0
 */
public class Car {
	/** 车牌号 */
	private String carId;
	/** 用户id */
	private String userId;
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
	@Override
	public String toString() {
		return "Car [carId=" + carId + ", userId=" + userId + "]";
	}
}
