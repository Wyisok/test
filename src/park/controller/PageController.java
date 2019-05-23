package park.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import park.pojo.BaseDict;
import park.service.BaseDictService;

@Controller
public class PageController {
	@Autowired
	private BaseDictService baseDictService;
	/**
	 * 获取字典信息
	 */
	@Value("${park_charge_type}")
	private String parkChargeType;
	@Value("${park_spot_type}")
	private String parkSpotType;
	@Value("${park_vip_type}")
	private String parkVipType;
	/**
	 * 转发到index.jsp页面
	 * @return
	 */
	@RequestMapping("")
	public String getIndex(HttpSession session){
		Subject user = (Subject) session.getAttribute("subject");
		if(user == null) {
			return "login";
		}
		return "redirect:/index";
	}
	@RequestMapping("index")
	public String getIndex1(HttpSession session) {
		Subject user = (Subject) session.getAttribute("subject");
		String username=(String) session.getAttribute("username");
		System.out.println("session中的user"+user);
		if(user == null) {
			return "login";
		}
		return "redirect:/showMenu?username="+username;
		
	}
	/**
	 * 转发到userTable.jsp页面
	 * @return
	 */
	@RequestMapping("/usertable")
	public String getUserTable(){
		return "userTable";
	}
	/**
	 * 转发到login.jsp页面
	 * @return
	 */
	@RequestMapping("/login")
	public String getLogin(){
		return "login";
	}
	/**
	 * 转发到register.jsp页面
	 * @return
	 */
	@RequestMapping("/register")
	public String getRegister(Model model){
		loadParkChargeTypes(model);
		return "register";
	}
	/**
	 * 转发到parkTable.jsp
	 * @return
	 */
	@RequestMapping("/park")
	public String getParkTable(Model model) {
		System.out.println("/park执行");
		loadParkChargeTypes(model);
		return "parkTable";
	}
	@RequestMapping("/parkSpot")
	public String getParkSpotTable(Model model) {
		loadParkSpotTypes(model);
		return "parkSpotTable";
	}
	
	
	/**
	 * 无权限跳转界面
	 */
	@RequestMapping("/unauthorized")
	public String noPerms() {
		return "unauthorized";
	}
	
	/**
	 * 给转发的页面加载 停车场收费类型信息
	 * @author whp
	 * @param model
	 */
	public void loadParkChargeTypes(Model model) {
		List<BaseDict> parkChargeTypes = baseDictService.getBaseDictByCode(parkChargeType);
		model.addAttribute("parkChargeTypes",parkChargeTypes);
	}
	/**
	 * 给转发的页面加载 车位类型信息
	 * @author whp
	 * @param model
	 */
	public void loadParkSpotTypes(Model model) {
		List<BaseDict> parkSpotTypes = baseDictService.getBaseDictByCode(parkSpotType);
		model.addAttribute("parkSpotTypes", parkSpotTypes);
	}
	/**
	 * 加载 停车场优惠类型信息
	 * @author whp
	 * @param model
	 */
	public void loadParkVipTypes(Model model) {
		List<BaseDict> parkVipTypes = baseDictService.getBaseDictByCode(parkVipType);
		model.addAttribute("ParkVipTypes",parkVipTypes);
	}
}
