package park.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;

@Component

public class AppPush {
	private static String appId;
	private static String appKey;
	private static String masterSecret;
	private static String url;
	
	@Value("${appId}")
	public  void setAppId(String appId) {
		AppPush.appId = appId;
	}
	
	@Value("${appKey}")
	public void setAppKey(String appKey) {
		AppPush.appKey = appKey;
	}
	
	@Value("${masterSecret}")
	public void setMasterSecret(String masterSecret) {
		AppPush.masterSecret = masterSecret;
	}

	@Value("${url}")
	public void setUrl(String url) {
		AppPush.url = url;
	}

//	private static IGtPush push;
//	static {
//		 IGtPush push = new IGtPush(url, appKey, masterSecret);
//	}

	/**
	 * 根据客户ClientID 向客户发送通知消息
	 * 
	 * @author whp
	 * @param cid     clientId
	 * @param title   通知标题
	 * @param content 通知内容
	 */
	public static void sendNotificationMessageByCid(String cid, String title, String content) {
		 IGtPush push = new IGtPush(url, appKey, masterSecret);
		SingleMessage message = new SingleMessage();
		NotificationTemplate template = new NotificationTemplate();
		template.setAppId(appId);
		template.setAppkey(appKey);
		template.setTransmissionType(1); // 这个Type为int型，填写1则自动启动app
		Style0 style = new Style0(); // 设置通知栏标题与内容
		style.setTitle(title);
		style.setText(content); // 配置通知栏图标
		style.setLogo("icon.png"); // 配置通知栏网络图标
		style.setLogoUrl("");// 网络图标地址
		// 设置通知是否响铃，震动，或者可清除
		style.setRing(true);
		style.setVibrate(true);
		style.setClearable(true);
		template.setStyle(style);
		template.setChannelLevel(4);// --锁屏显示级别，最高级别、
		// 设置消息的参数
		message.setData(template);
		message.setOffline(true);
		message.setOfflineExpireTime(24 * 3600 * 1000);// 离线有效时间，单位为毫秒，可选
		message.setPushNetWorkType(0);// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发

		Target target = new Target();// 设置推送目标，填入appid和clientId
		target.setAppId(appId);
		target.setClientId(cid);
		IPushResult ret;
		// 向单个用户发送消息
		try {
			ret = push.pushMessageToSingle(message, target);
		} catch (RequestException e) {
			e.printStackTrace();
			ret = push.pushMessageToSingle(message, target, e.getRequestId());
		}
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
		} else {
			System.out.println("服务器响应异常");
		}
	}

	/**
	 * 向当前APPID 群发通知消息消息
	 * 
	 * @author whp
	 * @param title
	 * @param content
	 */
	public static void sendNotificationMessage(String title, String content) {
		 IGtPush push = new IGtPush(url, appKey, masterSecret);
		NotificationTemplate template = new NotificationTemplate();
		template.setAppId(appId);
		template.setAppkey(appKey);
		template.setTransmissionType(1); // 这个Type为int型，填写1则自动启动app

		Style0 style = new Style0(); // 设置通知栏标题与内容
		style.setTitle(title);
		style.setText(content); // 配置通知栏图标
		style.setLogo("icon.png"); // 配置通知栏网络图标
		style.setLogoUrl("");// 网络图标地址
		// 设置通知是否响铃，震动，或者可清除
		style.setRing(true);
		style.setVibrate(true);
		style.setClearable(true);
		template.setStyle(style);
		template.setChannelLevel(4);// --锁屏显示级别，最高级别、

		AppMessage message = new AppMessage();// 设置消息的参数
		message.setData(template);
		message.setOffline(true);
		message.setOfflineExpireTime(24 * 3600 * 1000);// 离线有效时间，单位为毫秒，可选
		message.setPushNetWorkType(0);// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
		List<String> appIds = new ArrayList<String>();
		appIds.add(appId);
		message.setAppIdList(appIds);

		IPushResult ret = push.pushMessageToApp(message);// 发送
		System.out.println(ret.getResponse().toString());
	}

	/**
	 * 根据clientID向客户发送透传信息，不在通知栏提醒
	 * 
	 * @author whp
	 * @param cid
	 * @param msg
	 */
	public static void sendDataByCid(String cid, String msg) {
		 IGtPush push = new IGtPush(url, appKey, masterSecret);
		SingleMessage message = new SingleMessage();
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appId);
		template.setAppkey(appKey);
		template.setTransmissionContent(msg);// 设置消息内容
		template.setTransmissionType(2); // 这个Type为int型，填写1则自动启动app
		// 设置消息的参数
		message.setData(template);
		message.setOffline(true);
		message.setOfflineExpireTime(24 * 3600 * 1000);// 离线有效时间，单位为毫秒，可选
		message.setPushNetWorkType(0);// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发

		Target target = new Target();// 设置推送目标，填入appid和clientId
		target.setAppId(appId);
		target.setClientId(cid);
		IPushResult ret;
		// 向单个用户发送消息
		try {
			ret = push.pushMessageToSingle(message, target);
		} catch (RequestException e) {
			e.printStackTrace();
			ret = push.pushMessageToSingle(message, target, e.getRequestId());
		}
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
		} else {
			System.out.println("服务器响应异常");
		}
	}
	/**
	 * 向当前APPid 群发透传信息
	 * @author whp
	 * @param msg
	 */
	public static void sendData(String msg) {
		 IGtPush push = new IGtPush(url, appKey, masterSecret);
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appId);
		template.setAppkey(appKey);
		template.setTransmissionContent(msg);// 设置消息内容
		template.setTransmissionType(2); // 这个Type为int型，填写1则自动启动app
		AppMessage message = new AppMessage();// 设置消息的参数
		message.setData(template);
		message.setOffline(true);
		message.setOfflineExpireTime(24 * 3600 * 1000);// 离线有效时间，单位为毫秒，可选
		message.setPushNetWorkType(0);// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
		List<String> appIds = new ArrayList<String>();
		appIds.add(appId);
		message.setAppIdList(appIds);

		IPushResult ret = push.pushMessageToApp(message);// 发送
		System.out.println(ret.getResponse().toString());
	}
	
//	public static void main(String[] args) {
//		sendNotificationMessage("尊敬的客户", "欢");
//	}
	
	

}