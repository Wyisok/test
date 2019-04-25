package park.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import park.pojo.User;
import park.service.UserService;
import park.utils.Page;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@RequestMapping("/users")
	@ResponseBody
	public ModelAndView getAll(@RequestParam("id")Integer id){
		ModelAndView mv= new ModelAndView();
		Page page=userService.getAllUsers(id);
	    mv.addObject("page",page);
		mv.setViewName("userType/userList"); 
		return mv;
	}
	/**
	 * 查询员工
	 */
	@RequestMapping(value="/user/{id}" ,method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView getEmp(@PathVariable("id")Integer id){
		ModelAndView mv=new ModelAndView();
		User user=userService.selectById(id);
		
		mv.addObject("user",user);
		mv.setViewName("userType/userList");
		return mv;
		
		
	}
	/**
	 * 单个删除
	 */
	@ResponseBody
	@RequestMapping(value="/userdelete")
	public ModelAndView deleteEmpById(@RequestParam("id")Integer id){
		ModelAndView mv=new ModelAndView();
		userService.detele(id);
		mv.setViewName("userType/userList");
		return mv;
	}
}
