package park.pojo;
/**
 * 用户vip查询，
 * 前台显示-app
 * @author whp
 * @date 2019年5月28日
 * @version 1.0
 */
public class UserVipQueryVo {
	Park park;
	UserVip userVip;
	
	public Park getPark() {
		return park;
	}
	public void setPark(Park park) {
		this.park = park;
	}
	public UserVip getUserVip() {
		return userVip;
	}
	public void setUserVip(UserVip userVip) {
		this.userVip = userVip;
	}
	@Override
	public String toString() {
		return "UserVipQueryVo [park=" + park + ", userVip=" + userVip + "]";
	}
}
