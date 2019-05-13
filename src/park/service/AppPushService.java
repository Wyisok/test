package park.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import park.utils.AppPush;

@Service
public class AppPushService {
	
	/**
	 * 向所有已经登录过的用户发送消息
	 * @author whp
	 * @param userId
	 * @param title
	 * @param content
	 */
	public void sendNotify2All(String title, String content) {
		AppPush.sendData(content);
		AppPush.sendNotificationMessage(title, content);
	}
}
