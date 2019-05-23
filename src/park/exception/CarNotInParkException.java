package park.exception;
/**
 * 汽车不在停车场异常
 * @author whp
 * @date 2019年5月22日
 * @version 1.0
 */
public class CarNotInParkException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -451316400804245368L;

	public CarNotInParkException() {
		super();
		code = ExceptionConsts.ERR_CAR_NOT_IN_PARK;
	}

	public CarNotInParkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		code = ExceptionConsts.ERR_CAR_NOT_IN_PARK;
	}

	public CarNotInParkException(String message, Throwable cause) {
		super(message, cause);
		code = ExceptionConsts.ERR_CAR_NOT_IN_PARK;
	}

	public CarNotInParkException(String message) {
		super(message);
		code = ExceptionConsts.ERR_CAR_NOT_IN_PARK;
	}

	public CarNotInParkException(Throwable cause) {
		super(cause);
		code = ExceptionConsts.ERR_CAR_NOT_IN_PARK;
	}

	
}
