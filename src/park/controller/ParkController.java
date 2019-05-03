package park.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import park.pojo.Park;
import park.pojo.Role;
import park.pojo.User;
import park.pojo.UserRole;
import park.service.BaseDictService;
import park.service.ParkService;
import park.service.RoleService;

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
	@ Autowired
	private RoleService roleService;
	
	/**
	 * 获取字典信息
	 */
	@Value("${park_charge_type}")
	private String parkChargeType;
	
	
	@RequestMapping("/getParkTable")
	public String getParkTable() {
		return null;
	}
	@RequestMapping("/registerPark")
	public String registerPark(Park park,User user,UserRole userRole) {
		Role role = roleService.getRoleByName("停车场用户");
		//- TB_USER_ROLE 为当期那用户添加停车场员工角色
		parkService.addPark(park,user,userRole,role);
		return "redirect:login";
	}
}
