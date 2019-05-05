package park.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import park.mapper.ParkMapper;
import park.mapper.RoleMapper;
import park.mapper.UserMapper;
import park.mapper.UserRoleMapper;
import park.pojo.Park;
import park.pojo.ParkQueryVo;
import park.pojo.Role;
import park.pojo.User;
import park.pojo.UserRole;
import park.utils.DataTablePage;
import park.utils.Page4DataTable;
import park.utils.UUIDUtils;

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
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleMapper roleMapper;
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
	 * @param allData
	 */
	public void getPageParks(Page4DataTable<ParkQueryVo> allData) {
		int count = parkMapper.getParkNum();
		allData.setiTotalRecords(count);
		allData.setiTotalDisplayRecords(count);
		List<ParkQueryVo> page = parkMapper.getPageParks(allData.getiDisplayStart(),allData.getiDisplayStart()+allData.getiDisplayLength());
		System.out.println(page);
		allData.setAaData(page);
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
	 * 添加停车场后，自动为当前停车场分配一个员工用户
	 * 
	 * @param park
	 * @param user 
	 * @param role 
	 * @param userRole 
	 */
	public void addPark(Park park, User user, UserRole userRole, Role role) {
		String parkId = UUIDUtils.getUUID();
		String userId = UUIDUtils.getUUID();
		park.setParkId(parkId);
		user.setUserId(userId);
		//--停车场审核员经过审核停车场信息后-将state置为1
		park.setState(1);
		user.setState(1);
		//--将用户与角色关联起来
		userRole.setRoleId(role.getRoleId());
		userRole.setUserId(userId);
		userRole.setIdentityId(parkId);
		
		System.out.println(park);
		System.out.println(user);
		
		parkMapper.insertPark(park);
		userMapper.add(user);
		/** 为用户添加停车场员工角色 *   */
		userRoleMapper.insert(userRole);
	}
	
	
	/**
	 * 通过用户id获取该用户所在的停车场
	 * @param userId
	 * @return
	 */
	public Park getParkByUserId(String userId) {
		Park park = null;
		List<UserRole> userRoles = userRoleMapper.selectByUserId(userId);
		String identityId = null;
		for(UserRole ur : userRoles) {
			identityId = ur.getIdentityId();
		}
		if(identityId!=null) {
			park = parkMapper.getParkById(identityId);
		}
		return park;
	}
}
