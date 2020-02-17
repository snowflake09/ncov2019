package com.service.sys;

import java.util.List;

import com.condition.sys.ResourceCondition;
import com.dto.sys.ResourceDto;
import com.utils.easyui.Tree;

public interface ResourceService{

	/**
	 * 获取子集菜单
	 * @param resourceCondition
	 * @return
	 */
	List<Tree> getChildResource(ResourceCondition resourceCondition);

	/**
	 * 获取资源列表
	 * @param resourceCondition
	 * @return
	 * @author LiQiang
	 * @date 2016年11月10日
	 */
	List<ResourceDto> searchList(ResourceCondition resourceCondition);

	ResourceDto findById(Long id);

	void save(ResourceDto resourceDto);

	void update(ResourceDto resourceDto);

	void delete(Long id);

}
