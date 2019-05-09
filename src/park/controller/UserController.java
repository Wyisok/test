package park.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import park.pojo.Park;
import park.pojo.Role;
import park.pojo.User;
import park.service.ParkService;
import park.service.RoleService;
import park.service.UserRoleService;
import park.service.UserService;
import park.utils.DataTablePage;
import park.utils.JsonDate2String;
import park.utils.JsonDateValueProcessor;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	UserRoleService userRoleService;
	@Autowired
	RoleService roleService;
	@Autowired
	ParkService parkService;
	/**
	 * 获取用户分页信息
	 * 
	 * @param allData
	 * @param dataTablePage
	 * @return
	 */
	@RequestMapping("/getUserTable")
	@ResponseBody
	public String getAll(@RequestParam String allData, DataTablePage<User> dataTablePage) {
		System.out.println("getusertables执行");
		JSONArray jsonArray = JSONArray.fromObject(allData);
		String sEcho = null; // 记录操作的次数,从前端取的数 ， 一会返回这个参数，两者相同
		int iDisplayStart = 0; // 起始索引
		int iDisplayLength = 0; // 每页显示的行数，[10,25,50,100]
		System.out.println(jsonArray);
		// 这里获取从前台传递过来的参数，
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject obj = (JSONObject) jsonArray.get(i);
			if ("sEcho".equals(obj.get("name"))) {
				sEcho = obj.getString("value");
			}
			if ("iDisplayStart".equals(obj.get("name"))) {
				iDisplayStart = obj.getInt("value");
			}
			if ("iDisplayLength".equals(obj.get("name"))) {
				iDisplayLength = obj.getInt("value");
			}
		}
		dataTablePage.setsEcho(sEcho);
		dataTablePage.setiDisplayStart(iDisplayStart);
		dataTablePage.setiDisplayLength(iDisplayLength);
		dataTablePage.setiDisplayEnd(iDisplayStart + iDisplayLength);
		userService.getAllUser(dataTablePage);

		// --最后得封装成jsonobject对象，才能显示出来
		JSONObject json = JSONObject.fromObject(dataTablePage, JsonDate2String.getDateStringJsonConfig());
		System.out.println(json);
		return json.toString();
	}

	/**
	 * 根据主键查询
	 */
	@RequestMapping("/getuser")
	@ResponseBody
	public User getById(@RequestParam(value = "userId") String userid) {
		User user = userService.getById(userid);
		return user;
	}

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/parkUserLogin")
	public String login(Model model,User user){
		Subject subject = SecurityUtils.getSubject();
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try {
			subject.login(token);
			Session session = subject.getSession();
			session.setAttribute("subject", subject);
			return "redirect:index";

		} catch (AuthenticationException e) {
			model.addAttribute("error", "用户名密码错误");
			return "login";
		}
	}
	/*public ModelAndView login(User user, HttpServletResponse response, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		User user2 = userService.login(user);
		
		if (user2 != null) {
			//获取当前用户的角色信息
			List<Role> roles = roleService.getRolesByUserId(user2.getUserId());
			for(Role role : roles) {
				if("停车场用户".equalsIgnoreCase(role.getRoleName())) {
					*//** 获取到当前用户所在的停车场 *//*
					Park park = parkService.getParkByUserId(user2.getUserId());
					session.setAttribute("park", park);
				}
				if("普通用户".equalsIgnoreCase(role.getRoleName())) {
					mv.addObject("msg", "用户名或密码错误");
					mv.setViewName("login");
				}
			}
			
			System.out.println("登录ok");
			session.setAttribute("user", user2);
			
			Cookie c = new Cookie("username", URLEncoder.encode(user.getUsername(), "UTF-8"));
			c.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(c);
			mv.setViewName("redirect:index");
		} else {
			mv.addObject("msg", "用户名或密码错误");
			mv.setViewName("login");
		}
		return mv;
	}*/

	/**
	 * 用户注册
	 */
//	@RequestMapping("/registerUser")
//	@ResponseBody
//	public ModelAndView register(User user) {
//		ModelAndView mv = new ModelAndView();
//		userService.register(user);
//		mv.addObject("msg", "注册成功");
//		mv.setViewName("login");
//		return mv;
//	}
	
	
	
	/**
	 * 更新用户
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String update(User user) {
		System.out.println(user);
		userService.update(user);
		return "ok1";
	}

	/**
	 * 删除用户
	 */
	@RequestMapping("/userdelete")
	@ResponseBody
	public String deleteEmpById(@RequestParam("userId") String id) {
		userService.detele(id);
		return "ok2";
	}

	/*
	 * public ModelAndView getAll(@RequestParam("id")Integer id){ ModelAndView
	 * mv= new ModelAndView(); Page page=userService.getAllUsers(id);
	 * mv.addObject("page",page); mv.setViewName("userType/userList"); return
	 * mv; }
	 */
	
	
	
	/**
	 * app ajax  普通用户登录
	 * @author whp
	 * @param user1
	 * @return
	 */
	@RequestMapping("/userLogin")
	@ResponseBody
	public User appLogin(User user1) {
		User user = userService.login(user1);

		return user;
	}
	/**
	 * app ajax 普通用户注册
	 * @author whp
	 * @param user
	 * @return
	 */
	@RequestMapping("/userReg")
	@ResponseBody
	public String userReg(User user) {
		try {
			userService.register(user);
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	/**
	 * app ajax 普通用户更新
	 * @author whp
	 * @param user
	 * @return
	 */
	@RequestMapping("/userUpdate")
	@ResponseBody
	public String userUpdate(@RequestBody User user) {
		try {
			System.out.println(user);
			userService.update(user);
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
}
