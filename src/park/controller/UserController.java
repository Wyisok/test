package park.controller;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSONObject;
import park.pojo.Car;
import park.pojo.Park;
import park.pojo.Role;
import park.pojo.User;
import park.pojo.UserRole;
import park.service.CarService;
import park.service.ParkService;
import park.service.RoleService;
import park.service.UserRoleService;
import park.service.UserService;
import park.utils.JsonDate2String;
import park.utils.Page4DataTable;
import park.utils.UUIDUtils;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ParkService parkService;
	@Autowired
	private CarService carService;
	/**
	 * 获取用户分页信息
	 * 
	 * @param allData
	 * @param dataTablePage
	 * @return
	 */
	@RequestMapping("/getUserTable")
	@ResponseBody
	public JSONObject getAll(Page4DataTable<User> allData) {
		userService.getAllUser(allData);
		System.out.println(allData);
		// --最后得封装成jsonobject对象，才能显示出来
		JSONObject json = JSONObject.fromObject(allData, JsonDate2String.getDateStringJsonConfig());
		System.out.println(json);
		return json;
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
	 * 加盟停车场
	 * 
	 * @param park
	 * @param user
	 * @param userRole
	 * @return
	 */
	@RequestMapping("/registerPark")
	public String registerPark(Park park, User user, UserRole userRole) {
		Role role = roleService.getRoleByName("停车场用户");
		// - TB_USER_ROLE 为当期那用户添加停车场员工角色
		parkService.addPark(park, user, userRole, role);
		return "redirect:login";
	}
	
	
	/**
	 * 停车场用户登录
	 * 
	 * @param user
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/parkUserLogin")
	public String login(Model model,User user){
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try {
			subject.login(token);
			user = (User)subject.getPrincipal();
			Session session = subject.getSession();
			boolean isParkUser = subject.hasRole("停车场用户");
			if(isParkUser) {
				//获取到停车场的信息，然后加入session
				Park park = parkService.getParkByUserId(user.getUserId());
				if(park!=null) {
					session.setAttribute("park", park);
				}else {
					return "redirect:login";
				}
			}
			session.setAttribute("subject", subject);
			session.setAttribute("username",user.getUsername() );
			return "redirect:index";

		} catch (AuthenticationException e) {
			model.addAttribute("error", "用户名密码错误");
			return "login";
		}
	}
	
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

	
	/**
	 * app ajax  普通用户登录
	 * @author whp
	 * @param user1
	 * @return
	 */
	@RequestMapping("/userLogin")
	@ResponseBody
	public User appLogin(User user1) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user1.getUsername(), user1.getPassword());
		User user = null;
		try {
			subject.login(token);
			user = (User)subject.getPrincipal();
			System.out.println(user);
		} catch (AuthenticationException e) {
			return null;
		}
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
	public String userReg(User user, Car car) {
		try {
			String userId = UUIDUtils.getUUID();
			user.setUserId(userId);
			userService.register(user);
			Set<Car> cars = new HashSet<Car>();
			cars.add(car);
			carService.addCarIdByUserId(userId,cars);
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
	@RequestMapping("/saveClientId")
	@ResponseBody
	public String saveCid(String clientId,String userId) {
		System.out.println(clientId);
		System.out.println(userId);
		try {
			userService.bindCid2Uid(clientId,userId);
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
