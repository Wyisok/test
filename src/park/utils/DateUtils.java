package park.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用于计算日期的一些方法
 * @author whp
 * @date 2019年5月22日
 * @version 1.0
 */
public class DateUtils {
	/**
	 * 计算两个日期之间相差了多少个小时
	 * @author whp
	 * @param start
	 * @param end
	 * @return
	 */
	public static long getHours(Date start,Date end) {
		long result = 0;
		long start_time = start.getTime();
		long end_time = end.getTime();
		long between = end_time - start_time;
		if(between<0) {
			return result;
		}
		long second = between/1000;
		long minutes = second/60;
		long hours = minutes/60;
		result = hours;
		
		return result;
	}
}
