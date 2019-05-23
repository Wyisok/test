package park.exception;
/**
 * 车位数越界异常
 * @author whp
 * @date 2019年5月23日
 * @version 1.0
 */
public class ParkSpotNumOutOfBoundsException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8972564789593399777L;

	public ParkSpotNumOutOfBoundsException() {
		super();
		code = ExceptionConsts.ERR_PARKSPOTNUM_OUTOFBOUNDS;
	}

	public ParkSpotNumOutOfBoundsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		code = ExceptionConsts.ERR_PARKSPOTNUM_OUTOFBOUNDS;
	}

	public ParkSpotNumOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
		code = ExceptionConsts.ERR_PARKSPOTNUM_OUTOFBOUNDS;
	}

	public ParkSpotNumOutOfBoundsException(String message) {
		super(message);
		code = ExceptionConsts.ERR_PARKSPOTNUM_OUTOFBOUNDS;
	}

	public ParkSpotNumOutOfBoundsException(Throwable cause) {
		super(cause);
		code = ExceptionConsts.ERR_PARKSPOTNUM_OUTOFBOUNDS;
	}

	
}
