package park.exception;
/**
 * 余额不足异常
 * @author whp
 * @date 2019年5月22日
 * @version 1.0
 */
public class BalanceNotEnoughException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4249008621326208537L;

	public BalanceNotEnoughException() {
		super();
		code = ExceptionConsts.ERR_BALANCE_NOT_ENOUGH;
	}

	public BalanceNotEnoughException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		code = ExceptionConsts.ERR_BALANCE_NOT_ENOUGH;
	}

	public BalanceNotEnoughException(String message, Throwable cause) {
		super(message, cause);
		code = ExceptionConsts.ERR_BALANCE_NOT_ENOUGH;
	}

	public BalanceNotEnoughException(String message) {
		super(message);
		code = ExceptionConsts.ERR_BALANCE_NOT_ENOUGH;
	}

	public BalanceNotEnoughException(Throwable cause) {
		super(cause);
		code = ExceptionConsts.ERR_BALANCE_NOT_ENOUGH;
	}

	
}
