package park.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import park.pojo.BaseDict;
/**
 * @author whp
 * @date 2019年5月13日
 * @version 1.0
 */
public interface BaseDictMapper {
	/**
	 * 根据字典编号，查出所有的字典子项
	 * @author whp
	 * @param code
	 * @return
	 */
    @Select("select * from TB_BASE_DICT where dict_type_code = #{0}")
	List<BaseDict> getBaseDictByCode(String code);
    
    
}
