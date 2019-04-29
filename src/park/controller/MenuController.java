package park.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {
	/**
	 * 转发到index.jsp页面
	 * @return
	 */
	@RequestMapping("")
	public String getIndex(){
		return "index";
	}
	/**
	 * 转发到tables.jsp页面
	 * @return
	 */
	@RequestMapping("/user")
	public String getTable(){
		return "tables";
	}
	/**
	 * 转发到login.jsp页面
	 * @return
	 */
	@RequestMapping("/login")
	public String getLogin(){
		return "login";
	}

}
