package park.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import park.pojo.Park;
import park.pojo.User;


/* 
* @author wanghuping 
* @date 2019年4月28日 上午10:56:01 
* @version 1.0 
*/
@Controller
public class ParkController {
	@RequestMapping("/getParkTable")
	public String getParkTable() {
		return null;
	}
	@RequestMapping("/registerPark")
	public String registerPark(Park park,User user) {
		
		return "redirect:/login";
	}
}
