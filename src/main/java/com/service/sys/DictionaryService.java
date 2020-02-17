package com.service.sys;

import java.util.List;

import com.condition.sys.DistionaryCondition;
import com.dto.pagetable.PageModel;
import com.dto.sys.DictionaryDto;
import com.utils.em.DictionaryType;


/**
 * 数据字典
 * 
 * @author LiQiang
 * @date 2016年11月11日
 */
public interface DictionaryService {

	/**
	 * 分页查询数据字典列表
	 * @param distionaryCondition
	 * @return
	 * @author LiQiang
	 * @date 2016年11月11日
	 */
	PageModel dataGrid(DistionaryCondition distionaryCondition);

	/**
	 * 保存字典
	 * @param dictionaryDto
	 * @author LiQiang
	 * @date 2016年11月12日
	 */
	void save(DictionaryDto dictionaryDto);

	/**
	 * 修改字典
	 * @param dictionaryDto
	 * @author LiQiang
	 * @date 2016年11月12日
	 */
	void update(DictionaryDto dictionaryDto);

	/**
	 * 查询字典
	 * @param id
	 * @return
	 * @author LiQiang
	 * @date 2016年11月12日
	 */
	DictionaryDto findById(Long id);

	List<DictionaryDto> getListByType(DictionaryType dictionaryType);

}
