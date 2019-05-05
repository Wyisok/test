package park.controller;

import java.util.List;
import java.util.Map;

import org.apache.catalina.tribes.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import park.pojo.Park;
import park.pojo.Role;
import park.pojo.User;
import park.pojo.UserRole;
import park.service.BaseDictService;
import park.service.ParkService;
import park.service.RoleService;
import park.utils.Page4DataTable;

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
	
	/**
	 * 获取一页数据
	 * @param allData
	 * @return
	 */
	@RequestMapping("/getParkTable")
	@ResponseBody
	public Page4DataTable<Park> getParkTable(Page4DataTable<Park> allData) {
		//List<DataTableBean> allData
		System.out.println(allData);
		parkService.getPageParks(allData);
		return allData;
	}
	/**
	 * 加盟停车场
	 * @param park
	 * @param user
	 * @param userRole
	 * @return
	 */
	@RequestMapping("/registerPark")
	public String registerPark(Park park,User user,UserRole userRole) {
		Role role = roleService.getRoleByName("停车场用户");
		//- TB_USER_ROLE 为当期那用户添加停车场员工角色
		parkService.addPark(park,user,userRole,role);
		return "redirect:login";
	}
	
	/**
	 * 表单 编辑事件
	 * 通过parkID查找park
	 * @param parkId
	 * @return
	 */
	@RequestMapping("/getPark")
	@ResponseBody
	public Park getParkById(@RequestParam String parkId) {
		Park park = parkService.getParkById(parkId);
		return park;
	}
}
