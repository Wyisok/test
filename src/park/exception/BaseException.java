package park.exception;
/**
 * 是软件系统的异常继承根，
 * 所有的异常都继承自这个类
 * @author whp
 * @date 2019年5月22日
 * @version 1.0
 */

import java.io.Serializable;

public class BaseException extends Exception implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2245992383626694410L;
	/**
	 * 错误编号
	 */
	protected int code;
	
	public BaseException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public BaseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public BaseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public BaseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	public int getCode() {
		return code;
	}
	public String toString() {
		return getCode() + ":" + super.toString();
	}
	
}
