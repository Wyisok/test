package park.exception;
/**
 * 用户尚未注册
 * @author whp
 * @date 2019年5月23日
 * @version 1.0
 */
public class UserNoRegisterException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -240543778979610200L;

	public UserNoRegisterException() {
		super();
		code = ExceptionConsts.ERR_USER_NOT_REGISTER;
	}

	public UserNoRegisterException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		code = ExceptionConsts.ERR_USER_NOT_REGISTER;
	}

	public UserNoRegisterException(String message, Throwable cause) {
		super(message, cause);
		code = ExceptionConsts.ERR_USER_NOT_REGISTER;
	}

	public UserNoRegisterException(String message) {
		super(message);
		code = ExceptionConsts.ERR_USER_NOT_REGISTER;
	}

	public UserNoRegisterException(Throwable cause) {
		super(cause);
		code = ExceptionConsts.ERR_USER_NOT_REGISTER;
	}
	
	
}
