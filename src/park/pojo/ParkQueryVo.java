package park.pojo;

/**
 * 封装一些前台需要的信息
 * 一次性传过去
 * @author whp
 * @date 2019年5月19日
 * @version 1.0
 */
public class ParkQueryVo {
	/** 停车场 */
	private Park park;
	/** 空闲车位数 */
	private int freeSpotNum;
	public Park getPark() {
		return park;
	}
	public void setPark(Park park) {
		this.park = park;
	}
	public int getFreeSpotNum() {
		return freeSpotNum;
	}
	public void setFreeSpotNum(int freeSpotNum) {
		this.freeSpotNum = freeSpotNum;
	}
	@Override
	public String toString() {
		return "ParkQueryVo [park=" + park + ", freeSpotNum=" + freeSpotNum + "]";
	}
	
}
