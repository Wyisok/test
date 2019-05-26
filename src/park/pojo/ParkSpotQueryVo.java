package park.pojo;
/**
 * 用于前台显示停车场中的车辆信息
 * @author whp
 * @date 2019年5月25日
 * @version 1.0
 */
public class ParkSpotQueryVo{
	private ParkSpot parkSpot;
	private Park park;
	private ServiceInOutPojo sio;
	

	public Park getPark() {
		return park;
	}

	public void setPark(Park park) {
		this.park = park;
	}

	public ServiceInOutPojo getSio() {
		return sio;
	}

	public void setSio(ServiceInOutPojo sio) {
		this.sio = sio;
	}

	public ParkSpot getParkSpot() {
		return parkSpot;
	}

	public void setParkSpot(ParkSpot parkSpot) {
		this.parkSpot = parkSpot;
	}

	@Override
	public String toString() {
		return "ParkSpotQueryVo [parkSpot=" + parkSpot + ", park=" + park + ", sio=" + sio + "]";
	}
	
}
