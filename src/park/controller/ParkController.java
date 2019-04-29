package park.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import park.pojo.BaseDict;
import park.pojo.Park;
import park.pojo.User;
import park.service.BaseDictService;
import park.service.ParkService;
import park.service.UserService;
import park.utils.UUIDUtils;


/**
* @author whp
* @date 2019年4月28日 上午10:56:01 
* @version 1.0 
*/
@Controller
public class ParkController {
	@Autowired
	private BaseDictService baseDictService;
	@Autowired
	private ParkService parkService;
	/**
	 * 获取字典信息
	 */
	@Value("${park_charge_type}")
	private String parkChargeType;
	
	
	
	/**
	 * 转发到register.jsp页面
	 * @return
	 */
	@RequestMapping("/register")
	public String getRegister(Model model){
		List<BaseDict> parkChargeTypes = baseDictService.getBaseDictByCode(parkChargeType);
		System.out.println(parkChargeTypes);
		model.addAttribute("parkChargeTypes",parkChargeTypes);
		return "register";
	}
	@RequestMapping("/getParkTable")
	public String getParkTable() {
		return null;
	}
	@RequestMapping("/registerPark")
	public String registerPark(Park park,User user) {
		String parkId = UUIDUtils.getUUID();
		String userId = UUIDUtils.getUUID();
		park.setParkId(parkId);
		user.setUserId(userId);
		
		//---state置为1
		park.setState(1);
		user.setState(1);
		//-------
		System.out.println(park);
		System.out.println(user);
		//--角色表  里添加   停车场员工角色，与当前注册的用户关联起来，同时将停车场id放进去 TB_USER_ROLE
		parkService.addPark(park,user);
		
		
		return "redirect:/login";
	}
}
