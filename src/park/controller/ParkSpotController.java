package park.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import park.pojo.Park;
import park.pojo.ParkSpot;
import park.service.BaseDictService;
import park.service.ParkSpotService;
import park.utils.Page4DataTable;


/**
 * @author whp
 * @date 2019年5月8日
 * @version 1.0
 */
@Controller
public class ParkSpotController {
	@Autowired
	private BaseDictService baseDictService;
	@Autowired
	private ParkSpotService parkSpotService;
	
	@Value("park_spot_type")
	private String parkSpotType;
	/**
	 * 根据停车场编号
	 * 获取一页车位
	 * @author whp
	 * @param allData
	 * @return
	 */
	@RequestMapping("/getParkSpotTable")
	@ResponseBody
	public Page4DataTable<ParkSpot> getParkSpotTable(Page4DataTable<ParkSpot> allData, String parkId){
		parkSpotService.getPageParkSpot(allData,parkId);
		return allData;
	}
	/**
	 * 获取可以添加的车位数
	 * @author whp
	 * @param model
	 * @param parkId
	 * @param parkSpotNum
	 * @return
	 */
	@RequestMapping("/getRemanentSpotNum")
	@ResponseBody
	public String getRSN(Model model,String parkId,String parkSpotNum) {
		int remanentSpotNum = parkSpotService.getRemanentSpotNum(parkId,parkSpotNum);
		return remanentSpotNum+"";
	}
	/**
	 * 批量添加车位
	 * @author whp
	 * @param parkId
	 * @param spotPlace
	 * @param spotAddNum
	 * @param spotType
	 * @return
	 */
	@RequestMapping("/addParkSpot")
	@ResponseBody
	public String addPS(String parkId, String spotPlace, String spotAddNum, String spotType) {
		parkSpotService.setParkSpotNum(parkId,spotPlace,  spotAddNum, spotType);
		return "ok";
	}
	
	@RequestMapping("/getParkSpot")
	@ResponseBody
	public ParkSpot getPS(String parkSpotId) {
		ParkSpot  ps = parkSpotService.getPSById(parkSpotId);
		return ps;
	}
}
