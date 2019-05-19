package park.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import park.mapper.UserRoleMapper;
import park.pojo.User;
import park.pojo.UserRole;

@Service
public class UserRoleService {
	@Autowired 
	UserRoleMapper userRoleMapper;
	
	
	/**
	 * 批量设置角色信息
	 * @param user
	 * @param roleIds
	 */
	public void setRoles(User user,String[] roleIds) {
        //删除当前用户所有的角色
 
        //设置新的角色关系
        if(null!=roleIds)
            for (String rid : roleIds) {
                UserRole userRole = new UserRole();
                userRole.setRoleId(rid);
                userRole.setUserId(user.getUserId());
                userRoleMapper.insert(userRole);
            }
    }
	
	/**
	 * 通过用户id删除 该用户所有的角色信息
	 * @param userId
	 */
    public void deleteByUser(String userId) {
        
    }
    /**
     * 通过角色id删除 所有的角色信息
     * @param roleId
     */
    public void deleteByRole(String roleId) {
    }
}
