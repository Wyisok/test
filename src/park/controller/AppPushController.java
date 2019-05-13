package park.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import park.service.AppPushService;
import park.utils.AppPush;

@Controller
public class AppPushController {
	@Autowired
	private AppPushService aps;
	
	/**
	 * 向所有用户发送通知消息
	 * @author whp
	 * @param title
	 * @param content
	 */
	@RequestMapping("/sendNotify2All")
	public void sendNotify2All(String title, String content) {
		aps.sendNotify2All(title, content);
	}
	
}
