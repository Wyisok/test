package park.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import park.mapper.RoleMapper;
import park.mapper.UserRoleMapper;
import park.pojo.Role;
import park.pojo.UserRole;
/**
 * @author whp
 * @date 2019年5月3日
 * @version 1.0
 */
@Service
public class RoleService {
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	/**
	 * 获取所有的角色
	 * @return
	 */
	public List<Role> getAllRole() {
		return roleMapper.selectAllRole();
	}
	/**
	 * 根据用户名获取用户角色
	 */
	public Set<String> selectAllByUserName(String username){
		Set<String> result =new HashSet<>();
		List<Role> list=roleMapper.selectAllByUserName(username);
		if(list.contains(null)) {
			return null;
		}
		System.out.println(list);
		for(Role role:list){
			result.add(role.getRoleName());
		}
		return result;
	}
	
	/**
	 * 根据角色名称获取角色对象
	 * @param roleName
	 * @return
	 */
	public Role getRoleByName(String roleName) {
		List<Role> allRoles = getAllRole();
		Role result = null;
		for(Role role : allRoles) {
			if(role.getRoleName().equalsIgnoreCase(roleName)) {
				result = role;
				break;
			}
		}
		return result;
	}
	/**
	 * 根据用户id获取该用户的所有角色
	 * @param userId
	 * @return
	 */
	public List<Role> getRolesByUserId(String userId) {
		List<Role> roles = new ArrayList<Role>();
		List<UserRole> userRoles = userRoleMapper.selectByUserId(userId);
		// 获取所有的角色信息
		for(UserRole ur : userRoles) {
			Role role = roleMapper.selectByPrimaryKey(ur.getRoleId());
			roles.add(role);
		}
		
		return roles;
	}
}
