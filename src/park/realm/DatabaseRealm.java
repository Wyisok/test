package park.realm;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import park.pojo.User;
import park.service.MenuService;
import park.service.RoleService;
import park.service.UserService;

public class DatabaseRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 能进入到这里，表示账号已经通过验证了
		User user = (User)principalCollection.getPrimaryPrincipal();
		String userName = user.getUsername();
		// 通过service获取角色和权限
		Set<String> roles = roleService.selectAllByUserName(userName);
		Set<String> permissions = menuService.selectAllByUserName(userName);
		// 授权对象
		SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
		// 把通过service获取到的角色和权限放进去
		s.setRoles(roles);
		s.setStringPermissions(permissions);
		return s;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
				//获取账号密码
				UsernamePasswordToken t = (UsernamePasswordToken) token;
				String userName= token.getPrincipal().toString();
				String password= new String( t.getPassword());
				System.out.println(userName+"*"+password);
				//获取数据库中的密码
				User user = userService.selectByUsername(userName);
				String passwordInDB = user.getPassword();
				//如果为空就是账号不存在，如果不相同就是密码错误,但是都抛出AuthenticationException，而不是抛出具体错误原因，免得给破解者提供帮助信息
				if(null==passwordInDB || !passwordInDB.equals(password)) {
					System.out.println("out");
					throw new AuthenticationException();
				}
				//认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
				SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(user,password,getName());
				System.out.println("认证成功");
				return a;
	}

}