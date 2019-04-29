package park.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import park.pojo.BaseDict;

public interface BaseDictMapper {
    @Select("select * from TB_BASE_DICT where dict_type_code = #{0}")
	List<BaseDict> getBaseDictByCode(String code);
}
