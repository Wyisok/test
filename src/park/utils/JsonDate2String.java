package park.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
/**
 * 将传给前台的json中的date属性的值转为字符串类型
 * @author whp
 * @date 2019年4月29日
 * @version 1.0
 */
public class JsonDate2String {
	private static JsonConfig jsonConfig=null;
	static {
		jsonConfig = new JsonConfig();
	}
	/**
	 * 日期格式 自定义 yyyy-MM-dd HH:mm:ss
	 * @param datePattern
	 * @return
	 */
	public static JsonConfig getDateStringJsonConfig(String datePattern) {
		JsonDate2String.DateJsonValueProcessor pro = new JsonDate2String().new DateJsonValueProcessor(datePattern);
		jsonConfig.registerJsonValueProcessor(Date.class, pro);
		return jsonConfig;
	}
	/**
	 * 日期格式 yyyy-MM-dd
	 * @return
	 */
	public static JsonConfig getDateStringJsonConfig() {
		JsonDate2String.DateJsonValueProcessor pro = new JsonDate2String().new DateJsonValueProcessor();
		jsonConfig.registerJsonValueProcessor(Date.class, pro);
		return jsonConfig;
	}
	
	
	
	class DateJsonValueProcessor implements JsonValueProcessor {
		private  String datePattern = "yyyy-MM-dd";//默认将Date转成的我们需要的样式
		public String getDatePattern() {
			return datePattern;
		}
		public void setDatePattern(String datePattern) {
			this.datePattern = datePattern;
		}
		public DateJsonValueProcessor() {
			super();
		}
		public DateJsonValueProcessor(String datePattern) {//这个构造方法的作用就是我们在创建对象还可以改变默认样式
			super();
			this.datePattern = datePattern;
		}
		@Override
		public Object processArrayValue(Object value, JsonConfig jsonConfig) {
			try {							   //所以这个方法的实现和下面那个一毛一样 你可以合并
				if(value instanceof Date){			//我之所以没合并是为了做实验 验证强调的点
					SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
					Date date = (Date)value;
					return sdf.format(date);
				}
				return value == null ? "" : value.toString();
			} catch (Exception e) {
				return "";
			}
		}

		@Override
		public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
			try {
				if(value instanceof Date){
					SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
					Date date = (Date)value;
					return sdf.format(date);
				}
				return value == null ? "" : value.toString();
			} catch (Exception e) {
				return "";
			}
		}
	}
}
