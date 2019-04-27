package park.controller;

import java.util.ArrayList;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import park.pojo.User;
import park.service.UserService;
import park.utils.DataTablePage;
import park.utils.Page;


@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	/**
	 * 转发到tables.jsp页面
	 * @return
	 */
	@RequestMapping("/UserTable")
	public String getTable(){
		return "tables";
	}
	
	/**
	 * 获取用户分页信息
	 * @param allData
	 * @param dataTablePage
	 * @return
	 */
	@RequestMapping("/getUserTable")
	@ResponseBody
	public String getAll(@RequestParam String  allData, DataTablePage<User> dataTablePage){
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
		dataTablePage.setiDisplayEnd(iDisplayStart+iDisplayLength);
		userService.getAllUser(dataTablePage);
	
		//--最后得封装成jsonobject对象，才能显示出来
		JSONObject json = JSONObject.fromObject(dataTablePage);
		System.out.println(json);
		return json.toString();
	}

		
		
		
		
		
		
		
		
		
	
	/**
	 * 根据主键查询
	 */
	@RequestMapping("/getuser")
	@ResponseBody
	public ModelAndView getById(@RequestParam(value="userid")String userid){
		ModelAndView mv= new ModelAndView();
		System.out.println(userid);
		User user=userService.getById(userid);
		mv.addObject("user",user);
		mv.setViewName("tables");
		return mv;
	}
	
	
	
	
	/*public ModelAndView getAll(@RequestParam("id")Integer id){
		ModelAndView mv= new ModelAndView();
		Page page=userService.getAllUsers(id);
	    mv.addObject("page",page);
		mv.setViewName("userType/userList"); 
		return mv;
	}*/
	
	/**
	 * ����ɾ��
	 */
	@ResponseBody
	@RequestMapping(value="/userdelete")
	public ModelAndView deleteEmpById(@RequestParam("id")String id){
		ModelAndView mv=new ModelAndView();
		userService.detele(id);
		mv.setViewName("userType/userList");
		return mv;
	}
}
