package park.exception;
/**
 * 当前车辆尚未退出停车场
 * @author whp
 * @date 2019年5月23日
 * @version 1.0
 */
public class CarInParkException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CarInParkException() {
		super();
		code = ExceptionConsts.ERR_CAR_IN_PARK;
	}

	public CarInParkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		code = ExceptionConsts.ERR_CAR_IN_PARK;
	}

	public CarInParkException(String message, Throwable cause) {
		super(message, cause);
		code = ExceptionConsts.ERR_CAR_IN_PARK;
	}

	public CarInParkException(String message) {
		super(message);
		code = ExceptionConsts.ERR_CAR_IN_PARK;
	}

	public CarInParkException(Throwable cause) {
		super(cause);
		code = ExceptionConsts.ERR_CAR_IN_PARK;
	}

}
