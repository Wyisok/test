package park.exception;
/**
 * 车位被占用异常
 * @author whp
 * @date 2019年5月23日
 * @version 1.0
 */
public class ParkSpotBeOccupyException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6661225744104424770L;

	public ParkSpotBeOccupyException() {
		super();
		code = ExceptionConsts.ERR_PARKSPOT_BE_OCCUPY;
	}

	public ParkSpotBeOccupyException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		code = ExceptionConsts.ERR_PARKSPOT_BE_OCCUPY;
	}

	public ParkSpotBeOccupyException(String message, Throwable cause) {
		super(message, cause);
		code = ExceptionConsts.ERR_PARKSPOT_BE_OCCUPY;
	}

	public ParkSpotBeOccupyException(String message) {
		super(message);
		code = ExceptionConsts.ERR_PARKSPOT_BE_OCCUPY;
	}

	public ParkSpotBeOccupyException(Throwable cause) {
		super(cause);
		code = ExceptionConsts.ERR_PARKSPOT_BE_OCCUPY;
	}

	 
}
