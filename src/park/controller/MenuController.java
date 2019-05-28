package park.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import park.pojo.Menu;
import park.pojo.Role;
import park.service.MenuService;
import park.service.UserRoleService;

@Controller
public class MenuController {
	@Autowired
	MenuService menuService;
	@Autowired
	UserRoleService userRoleService;
	
	@RequestMapping("/showMenu")
	public String getMenu(Model model,@RequestParam String username){
		System.out.println(username);
		List<Menu> list=menuService.selectByUserName(username);
		//Integer roleId=userRoleService.selectRoleIdByUserName(username);
		System.out.println("用户信息"+list);
		model.addAttribute("menu",list);
		model.addAttribute("username",username);
		return "index";
	}
}
