package park.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import park.mapper.ParkVipMapper;
import park.pojo.ParkVip;
import park.pojo.User;
import park.utils.Page4DataTable;

/**
 * 用于停车场发布一些优惠业务
 * 例如月票，年票，半年票。。。
 * @author whp
 * @date 2019年5月26日
 * @version 1.0
 */
@Service
public class ParkVipService {
	@Autowired private ParkVipMapper parkVipMapper;
	
	/**
	 * 根据编号获取优惠项目
	 * @author whp
	 * @param parkVipId
	 * @return
	 */
	public ParkVip getParkVipById(String parkVipId) {
		return parkVipMapper.selectById(parkVipId);
	}
	
	
	/**
	 * 获取停车场一页vip优惠项目
	 * @author whp
	 * @param allData
	 * @param parkId
	 */
	public void getAllVip(Page4DataTable<ParkVip> allData,String parkId) {
		int count = parkVipMapper.selectNum(parkId);
		allData.setiTotalRecords(count);
		allData.setiTotalDisplayRecords(count);
		List<ParkVip> page = parkVipMapper.getPage(allData.getiDisplayStart(),allData.getiDisplayStart()+allData.getiDisplayLength(),parkId);
		allData.setAaData(page);
	}
	/**
	 * 删除优惠项目
	 * @author whp
	 * @param parkVipId
	 */
	public void removeVipById(String parkVipId) {
		parkVipMapper.deleteParkVipById(parkVipId);
	}
	/**
	 * 添加优惠项目
	 * @author whp
	 * @param parkVip
	 */
	public void addParkVip(ParkVip parkVip) {
		parkVipMapper.insertParkVip(parkVip);
	}
	/**
	 * 修改优惠项目
	 * @author whp
	 * @param parkVip
	 */
	public void editParkVip(ParkVip parkVip) {
		parkVipMapper.updateParkVip(parkVip);
	}
	/**
	 * 根据停车场编号
	 * 获取优惠项目
	 * @author whp
	 * @param parkId
	 * @return
	 */
	public List<ParkVip> getParkVipByParkId(String parkId) {
		List<ParkVip> parkVips = parkVipMapper.selectByParkId(parkId);
		return parkVips;
	}
}
