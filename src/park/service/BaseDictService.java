package park.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import park.mapper.BaseDictMapper;
import park.pojo.BaseDict;

/**
 * 字典数据表业务逻辑实现
 * @author whp
 * @date 2019年4月29日
 * @version 1.0
 */
@Service
public class BaseDictService {
	@Autowired
	private BaseDictMapper baseDictMapper;
	/**
	 * 跟据字典编码查询字典列表
	 * @param code
	 * @return
	 */
	public List<BaseDict> getBaseDictByCode(String code) {
		return baseDictMapper.getBaseDictByCode(code);
	}
}
