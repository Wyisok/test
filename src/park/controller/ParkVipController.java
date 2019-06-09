package park.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import park.pojo.ParkVip;
import park.service.ParkVipService;
import park.utils.DateUtils;
import park.utils.JsonDate2String;
import park.utils.Page4DataTable;
import park.utils.UUIDUtils;

/**
 * 停车场优惠项
 * 
 * @author whp
 * @date 2019年5月27日
 * @version 1.0
 */
@Controller
public class ParkVipController {
	@Autowired
	private ParkVipService parkVipService;

	@Value("${park_vip_type}")
	private String parkVipType;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@ResponseBody
	@RequestMapping("/getParkVipTable")
	public JSONObject getParkVipTable(Page4DataTable<ParkVip> allData, String parkId) {
		System.out.println(allData);
		parkVipService.getAllVip(allData, parkId);
		JSONObject json = JSONObject.fromObject(allData, JsonDate2String.getDateStringJsonConfig());
		System.out.println(json);
		return json;
	}

	/**
	 * 添加优惠项
	 * @author whp
	 * @param parkVip
	 */
	@ResponseBody
	@RequestMapping("/addParkVip")
	public void addParkVip(ParkVip parkVip) {
		if(parkVip.getEndTime()!=null) {//-------------------------------------------一个简单验证，结束时间要比开始时间大
			System.out.println("endTime:"+parkVip.getEndTime());
			long hours = DateUtils.getHours(new Date(), parkVip.getEndTime());
			if(hours<0) {
				return;
			}
		}
		
		parkVip.setParkVipId(UUIDUtils.getUUID());
		System.out.println(parkVip);
		parkVipService.addParkVip(parkVip);
	}
	/**
	 * 移除优惠项
	 * @author whp
	 * @param parkVipId
	 */
	@ResponseBody
	@RequestMapping("deleteParkVip")
	public void deleteParkVip(String parkVipId) {
		parkVipService.removeVipById(parkVipId);
	}
	/**
	 * 根据停车场编号
	 * 获取停车场所有的优惠项目
	 */
	@ResponseBody
	@RequestMapping("getParkVip")
	public List<ParkVip> getParkVip(String parkId){
		List<ParkVip> parkVips = parkVipService.getParkVipByParkId(parkId);
		return parkVips;
	}
}
