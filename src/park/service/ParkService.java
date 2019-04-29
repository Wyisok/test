package park.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import park.mapper.ParkMapper;
import park.mapper.UserMapper;
import park.pojo.Park;
import park.pojo.User;
import park.utils.DataTablePage;

/* 
* @author wanghuping 
* @date 2019年4月28日 下午10:25:49 
* @version 1.0 
*/
@Service
public class ParkService {

	@Autowired
	private ParkMapper parkMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 获取总停车场数
	 * @return
	 */
	public int getParkNum() {
		return parkMapper.getParkNum();
	}
	/**
	 * 获取所有的停车场
	 * @return
	 */
	public List<Park> getAllParks() {
		return parkMapper.getAllParks();
	}
	/**
	 * dataTables表格插件，获取一页数据
	 * @param dataTablePage
	 */
	public void getPageParks(DataTablePage<Park> dataTablePage) {
		int count = parkMapper.getParkNum();
		dataTablePage.setiTotalRecords(count);
		dataTablePage.setiTotalDisplayRecords(count);
		List<Park> page = parkMapper.getPageParks(dataTablePage.getiDisplayStart(), dataTablePage.getiDisplayEnd());
		dataTablePage.setAaData(page);
	}
	/**
	 * 根据parkId获取停车场
	 * @param parkId
	 * @return
	 */
	public Park getParkById(String parkId) {
		return parkMapper.getParkById(parkId);
	}
	/**
	 * 添加停车场
	 * @param park
	 * @param user 
	 */
	public void addPark(Park park, User user) {
		parkMapper.insertPark(park);
		userMapper.add(user);
	}
}
