package park.filter;

import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import park.service.MenuService;


public class URLPathMatchingFilter extends PathMatchingFilter {
	@Autowired
	MenuService menuService;

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		String requestURI = getPathWithinApplication(request);

		System.out.println("访问的地址" + requestURI);

		Subject subject = SecurityUtils.getSubject();
		// 如果没有登录，就跳转到登录页面
		if (!subject.isAuthenticated()) {
			WebUtils.issueRedirect(request, response, "/login");
			return false;
		}

		// 看看这个路径权限里有没有维护，如果没有维护，一律放行(也可以改为一律不放行)
		boolean needInterceptor = menuService.needInterceptor(requestURI);
		if (!needInterceptor) {
			return true;
		} else {
			boolean hasPermission =subject.isPermitted(requestURI);
			
//			System.out.println(subject.isPermitted(requestURI));
//			String userName = subject.getPrincipal().toString();
			
//			Set<String> permissionUrls = menuService.listPermissionURLs(userName);
//			for (String url : permissionUrls) {
//				// 这就表示当前用户有这个权限
//				if (url.equals(requestURI)) {
//					System.out.println("用户拥有该访问路径");
//					hasPermission = true;
//					break;
//				}
//			}
			if (hasPermission){
				return true;
			}else {
				UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径 " + requestURI + " 的权限");
				subject.getSession().setAttribute("ex", ex);

				WebUtils.issueRedirect(request, response, "/unauthorized");
				return false;
			}

		}

	}
}