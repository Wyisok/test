package park.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import park.pojo.Park;
import park.pojo.ParkQueryVo;
import park.pojo.Role;
import park.pojo.User;
import park.pojo.UserRole;
import park.service.BaseDictService;
import park.service.ParkService;
import park.service.RoleService;
import park.utils.Page4DataTable;

/**
 * @author whp
 * @date 2019年4月28日 上午10:56:01
 * @version 1.0
 */
@Controller
public class ParkController {
	@Autowired
	private BaseDictService baseDictService;
	@Autowired
	private ParkService parkService;
	@Autowired
	private RoleService roleService;

	/**
	 * 获取字典信息
	 */
	@Value("${park_charge_type}")
	private String parkChargeType;

	/**
	 * 获取一页数据
	 * @param allData
	 * @return
	 */
	@RequestMapping("/getParkTable")
	@ResponseBody
	public Page4DataTable<Park> getParkTable(Page4DataTable<Park> allData) {
		// List<DataTableBean> allData
		System.out.println(allData);
		parkService.getPageParks(allData);
		return allData;
	}



	/**
	 * 表单 编辑事件 通过parkID查找park
	 * 
	 * @param parkId
	 * @return
	 */
	@RequestMapping("/getPark")
	@ResponseBody
	public Park getParkById(@RequestParam String parkId) {
		Park park = parkService.getParkById(parkId);
		return park;
	}

	/**
	 * app 获取所有的停车场信息
	 * 
	 * @author whp
	 * @return
	 */
	@RequestMapping("/getParks")
	@ResponseBody
	public List<Park> getParks() {
		List<Park> parks = parkService.getAllParks();
		System.out.println(parks);
		return parks;
	}

	/**
	 * 删除停车场 通过id
	 * 
	 * @author whp
	 * @param parkId
	 */
	@RequestMapping("/delPark")
	@ResponseBody
	public String delPark(@RequestParam String parkId) {
		parkService.delParkById(parkId);
		return "ok";
	}
	/**
	 * 停车场信息更新
	 * @author whp
	 * @param park
	 * @return
	 */
	@RequestMapping("/updatePark")
	@ResponseBody
	public String updatePark(Park park) {
		System.out.println(park);
		parkService.updatePark(park);
		return "ok";
	}
	
	@RequestMapping("/getParkDetails")
	@ResponseBody
	public ParkQueryVo getParkDetails(String parkId) {
			ParkQueryVo  parkVo = parkService.getParkQueryVoById(parkId);
			System.out.println(parkVo);
			return parkVo;
	}

}
