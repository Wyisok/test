package park.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 用户购买停车场的vip
 * @author whp
 * @date 2019年5月27日
 * @version 1.0
 */
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import park.exception.BalanceNotEnoughException;
import park.pojo.UserVipQueryVo;
import park.service.UserVipService;
import park.utils.JsonDate2String;
@Controller
public class UserVipController {
	@Autowired UserVipService userVipService;
	
	@ResponseBody
	@RequestMapping(value = "buyVip",produces = "text/plain;charset=utf-8")
	public String buyVip(String parkVipId,String userId) {
		try {
			userVipService.buyVip(userId, parkVipId);
		} catch (BalanceNotEnoughException e) {
			return e.getMessage();
		}
		return "购买成功";
	}
	/**
	 * 根据用户id获取用户vip信息
	 * @author whp
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getUserVip")
	public String getUserVip(String userId) {
		JSONArray json = JSONArray.fromObject(userVipService.getUserVipByUserId(userId), JsonDate2String.getDateStringJsonConfig());
		System.out.println(json);
		return json.toString();
	}
	
	
}
