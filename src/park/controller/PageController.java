package park.controller;

import java.util.List;

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
	
	
	/**
	 * 转发到index.jsp页面
	 * @return
	 */
	@RequestMapping("")
	public String getIndex(){
		return "index";
	}
	@RequestMapping("index")
	public String getIndex1() {
		return "index";
	}
	/**
	 * 转发到userTable.jsp页面
	 * @return
	 */
	@RequestMapping("/user")
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
		List<BaseDict> parkChargeTypes = baseDictService.getBaseDictByCode(parkChargeType);
		System.out.println(parkChargeTypes);
		model.addAttribute("parkChargeTypes",parkChargeTypes);
		return "register";
	}
	/**
	 * 转发到parkTable.jsp
	 * @return
	 */
	@RequestMapping("/park")
	public String getParkTable() {
		return "parkTable";
	}

}
