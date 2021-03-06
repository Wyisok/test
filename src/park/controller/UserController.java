package park.controller;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;
import park.exception.ParkWithoutFreeSpotException;
import park.pojo.BaseDict;
import park.pojo.Car;
import park.pojo.Menu;
import park.pojo.Park;
import park.pojo.ParkSpot;
import park.pojo.Role;
import park.pojo.User;
import park.pojo.UserRole;
import park.service.BaseDictService;
import park.service.CarService;
import park.service.MenuService;
import park.service.ParkService;
import park.service.ParkSpotService;
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
	@Autowired
	private ParkSpotService parkSpotService;
	@Autowired
	MenuService menuService;
	@Autowired
	PageController pageController;
	
	
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
		// --最后得封装成jsonobject对象，才能显示出来
		JSONObject json = JSONObject.fromObject(allData, JsonDate2String.getDateStringJsonConfig());
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
	 * 停车场用户注册
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
			session.setAttribute("username",user.getUsername() );
			
			List<Menu> list1=menuService.selectByUserName(user.getUsername());
		   session.setAttribute("menu", list1);
		   
			session.setAttribute("subject", subject);
			if(isParkUser) {
				//获取到停车场的信息，然后加入session
				Park park = parkService.getParkByUserId(user.getUserId());
				
				try {
					List<ParkSpot> list=parkSpotService.getFreeParkSpots(park);
					int i=list.size();
					session.setAttribute("sum", i);
				} catch (ParkWithoutFreeSpotException e) {
					e.printStackTrace();
				}
				
				if(park!=null) {
					session.setAttribute("park", park);
				}else {
					return "redirect:login";
				}
				return "index";//------------停车场用户到首页
			}else {
				//重定向到park
				pageController.loadParkChargeTypes(model);
				return "parkTable";//-----------管理员到停车场管理页面
			}
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
	/**
	 * app 用户保存客户端id
	 * @author whp
	 * @param clientId
	 * @param userId
	 * @return
	 */
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
			userService.update(user);
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	/**
	 * 获取用户余额
	 * @author whp
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getBalance")
	@ResponseBody
	public String getBalance(String userId) {
		double balance1 = userService.getBalance(userId);
		DecimalFormat df = new DecimalFormat(",###.##");//----截取两个小数，
		String balance = df.format(balance1);
		return balance;
	}
	
	@RequestMapping("/recharge")
	@ResponseBody
	public String recharge(String userId,String charge) {
		User user = userService.getById(userId);
		double balance = user.getBalance();
		balance += Double.parseDouble(charge);
		user.setBalance(balance);
		userService.update(user);
		return "ok";
	}
	
}
