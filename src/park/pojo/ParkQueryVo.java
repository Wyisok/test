package park.pojo;

public class ParkQueryVo extends Park{
	private String dictItemName;
	private BaseDict baseDict;
	public String getDictItemName() {
		return dictItemName;
	}

	public void setDictItemName(String dictItemName) {
		this.dictItemName = dictItemName;
	}
	@Override
	public String toString() {
		return "ParkQueryVo [dictItemName=" + dictItemName + ", getParkId()=" + getParkId() + ", getParkName()="
				+ getParkName() + ", getParkSpotNum()=" + getParkSpotNum() + ", getAddress()=" + getAddress()
				+ ", getLngLat()=" + getLngLat() + ", getCharge()=" + getCharge() + ", getChargeType()="
				+ getChargeType() + ", getState()=" + getState() + "]";
	}

	public BaseDict getBaseDict() {
		return baseDict;
	}

	public void setBaseDict(BaseDict baseDict) {
		this.baseDict = baseDict;
	}
}
