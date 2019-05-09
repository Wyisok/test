package park.pojo;
/**
 * @author whp
 * @date 2019年5月3日
 * @version 1.0
 */
public class Role {
private String roleId;
private String roleName;
private String identity_id;

public String getRoleId() {
	return roleId;
}
public void setRoleId(String roleId) {
	this.roleId = roleId;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}

public String getIdentity_id() {
	return identity_id;
}
public void setIdentity_id(String identity_id) {
	this.identity_id = identity_id;
}
@Override
public String toString() {
	return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
}

}
