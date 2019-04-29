package park.pojo;
/**
 * 字典表
 * @author whp
 * @date 2019年4月29日
 * @version 1.0
 */
public class BaseDict {
	/** 字典id */
	private String dictId;
	/** 字典名称 */
	private String dictTypeName;
	/** 字典编号 */
	private String dictTypeCode;
	/** 字典子项名称 */
	private String dictItemName;
	/** 字典子项编号 */
	private String dictItemCode;
	/** 字典子项排序号 */
	private int dictSort;
	/** 字典描述 */
	private String description;
	/** 是否启用 */
	private int state;
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public String getDictTypeName() {
		return dictTypeName;
	}
	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
	}
	public String getDictTypeCode() {
		return dictTypeCode;
	}
	public void setDictTypeCode(String dictTypeCode) {
		this.dictTypeCode = dictTypeCode;
	}
	public String getDictItemName() {
		return dictItemName;
	}
	public void setDictItemName(String dictItemName) {
		this.dictItemName = dictItemName;
	}
	public String getDictItemCode() {
		return dictItemCode;
	}
	public void setDictItemCode(String dictItemCode) {
		this.dictItemCode = dictItemCode;
	}
	public int getDictSort() {
		return dictSort;
	}
	public void setDictSort(int dictSort) {
		this.dictSort = dictSort;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "BaseDict [dictId=" + dictId + ", dictTypeName=" + dictTypeName + ", dictTypeCode=" + dictTypeCode
				+ ", dictItemName=" + dictItemName + ", dictItemCode=" + dictItemCode + ", dictSort=" + dictSort
				+ ", description=" + description + ", state=" + state + "]";
	}

}
