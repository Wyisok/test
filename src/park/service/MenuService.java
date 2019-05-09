package park.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import park.mapper.MenuMapper;
import park.pojo.Menu;
@Service
public class MenuService {
	@Autowired
	MenuMapper menuMapper;
	@Autowired
	RoleService roleService;

	public List<Menu> list() {
		return menuMapper.selectAll();
	}
	public Set<String> selectAllByUserName(String username){
		Set<String> result = new HashSet<>();
		List<Menu> list= menuMapper.selectAllByUserName(username);
		for(Menu m:list){
			result.add(m.getMenuName());
		}
		return result;
		
		
	}
	
	
	
	public boolean needInterceptor(String requestURI) {
		List<Menu> ps = list();
		for (Menu p : ps) {
			if (p.getMenuUrl().equals(requestURI))
				return true;
		}
		return false;
	}

	public Set<String> listPermissionURLs(String userName) {
		Set<String> result = new HashSet<>();
		List<Menu> list= menuMapper.selectAllByUserName(userName);
		for(Menu m:list){
			result.add(m.getMenuUrl());
			System.out.println("数据库拥有的地址"+m.getMenuUrl());
		}
		return result;
		
		
	}

}
