package park.exception;
/**
 * 停车场没有空闲车位异常
 * @author whp
 * @date 2019年5月23日
 * @version 1.0
 */
public class ParkWithoutFreeSpotException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -956679994751799995L;

	public ParkWithoutFreeSpotException() {
		super();
		code = ExceptionConsts.ERR_PARK_NO_FREESPOT;
	}

	public ParkWithoutFreeSpotException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		code = ExceptionConsts.ERR_PARK_NO_FREESPOT;
	}

	public ParkWithoutFreeSpotException(String message, Throwable cause) {
		super(message, cause);
		code = ExceptionConsts.ERR_PARK_NO_FREESPOT;
	}

	public ParkWithoutFreeSpotException(String message) {
		super(message);
		code = ExceptionConsts.ERR_PARK_NO_FREESPOT;
	}

	public ParkWithoutFreeSpotException(Throwable cause) {
		super(cause);
		code = ExceptionConsts.ERR_PARK_NO_FREESPOT;
	}

	
}
