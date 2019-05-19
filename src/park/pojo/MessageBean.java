package park.pojo;

import java.util.Date;

/**
 * 发送消息的封装bean
 * @author whp
 * @date 2019年5月14日
 * @version 1.0
 */
public class MessageBean {
	/**  通知标题 */
	private String title;
	/** 通知内容 */
	private String content;
	/** 点击消息是否打开网页 */
	private boolean openWindow;
	/** 发送消息的时间 */
	private Date dateTime;
	/** 如果打开网页，那么打开的是哪个网页 */
	private String url;
	/** 打开网页需要的参数 */
	private Object obj;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isOpenWindow() {
		return openWindow;
	}
	public void setOpenWindow(boolean openWindow) {
		this.openWindow = openWindow;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	@Override
	public String toString() {
		return "MessageBean [title=" + title + ", content=" + content + ", openWindow=" + openWindow + ", date=" + dateTime
				+ ", url=" + url + ", obj=" + obj + "]";
	}
}
