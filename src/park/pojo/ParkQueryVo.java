package park.pojo;

public class ParkQueryVo extends Park{
	private String dictItemName;

	public String getDictItemName() {
		return dictItemName;
	}

	public void setDictItemName(String dictItemName) {
		this.dictItemName = dictItemName;
	}

	@Override
	public String toString() {
		return "ParkQueryVo [dictItemName=" + dictItemName + ", toString()=" + super.toString() + ", getParkId()="
				+ getParkId() + ", getParkName()=" + getParkName() + ", getParkSpotNum()=" + getParkSpotNum()
				+ ", getAddress()=" + getAddress() + ", getLngLat()=" + getLngLat() + ", getCharge()=" + getCharge()
				+ ", getChargeType()=" + getChargeType() + ", getState()=" + getState() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
	
}
