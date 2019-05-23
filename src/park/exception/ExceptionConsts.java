package park.exception;
/**
 * 异常常量表
 * 避免硬编码.使用静态常量替代.
 * @author whp
 * @date 2019年5月22日
 * @version 1.0
 */
public class ExceptionConsts {
	/**
	 * 用户名密码错误
	 */
	public static final int ERR_NAME_OR_PWD = 1201;
	/**
	 * 用户余额不足
	 */
	public static final int ERR_BALANCE_NOT_ENOUGH= 1202;
	/**
	 * 用户尚未注册
	 */
	public static final int ERR_USER_NOT_REGISTER = 1203;
	/**
	 * 当前汽车不在当前停车场
	 */
	public static final int ERR_CAR_NOT_IN_PARK= 1301;
	/**
	 * 当前汽车尚未退出停车场
	 */
	public static final int ERR_CAR_IN_PARK = 1302;
	/**
	 * 停车场没有空闲车位
	 */
	public static final int ERR_PARK_NO_FREESPOT = 1401;
	/**
	 * 车位被占用异常
	 */
	public static final int ERR_PARKSPOT_BE_OCCUPY = 1501;
	/**
	 * 车位数越界异常
	 */
	public static final int ERR_PARKSPOTNUM_OUTOFBOUNDS = 1502;
	
}
