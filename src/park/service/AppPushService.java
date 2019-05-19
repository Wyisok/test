package park.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;
import park.pojo.MessageBean;
import park.pojo.Park;
import park.pojo.ParkSpot;
import park.pojo.User;
import park.utils.AppPush;
import park.utils.JsonDate2String;

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
	/**
	 * 向用户发送车位安排的消息
	 * @author whp
	 * @param user
	 * @param park
	 * @param parkSpot
	 */
	public void sendParkSpotArrange(User user, Park park, ParkSpot parkSpot) {
		//1. 当前停车场的收费金额与用户余额比较，如果余额不够，需要发送充值消息
		if(park.getCharge()>user.getBalance()) {
			sendRechargeMessage(user);
		}
		
		
		MessageBean mb = new MessageBean();
		mb.setDateTime(new Date());
		mb.setTitle("欢迎来到 "+park.getParkName());
		mb.setContent("已为您分配停车位:"+parkSpot.getSpotPlace()+parkSpot.getSpotNum()+"号，请及时就位。");
		JSONObject json = JSONObject.fromObject(mb, JsonDate2String.getDateStringJsonConfig("yyyy-MM-dd HH:mm:ss"));
		String clientId = user.getClientId();
		if(clientId==null) {
			return;//----------执行短信通知
		}
		System.out.println(json);
		AppPush.sendDataByCid(clientId, json.toString());
		AppPush.sendNotificationMessageByCid(clientId, mb.getTitle(), mb.getContent());
	}
	
	/**
	 * 充值提醒
	 * @author whp
	 * @param user
	 */
	public void sendRechargeMessage(User user) {
		MessageBean mb = new MessageBean();
		mb.setDateTime(new Date());
		mb.setTitle("尊敬的客户，您好!");
		mb.setContent("截至"+new Date().toLocaleString()+"，您的账户余额为"+user.getBalance()+"元，余额不足将会影响您的停车体验，建议您及时预存停车金额");
		JSONObject json = JSONObject.fromObject(mb, JsonDate2String.getDateStringJsonConfig("yyyy-MM-dd HH:mm:ss"));
		String clientId = user.getClientId();
		if(clientId==null) {
			return;//----------执行短信通知
		}
		System.out.println(json);
		AppPush.sendDataByCid(clientId, json.toString());
		AppPush.sendNotificationMessageByCid(clientId, mb.getTitle(), mb.getContent());
	}
	
	
}
