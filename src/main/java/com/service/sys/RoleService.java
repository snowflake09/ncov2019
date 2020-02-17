package com.service.sys;

import java.util.List;

import com.condition.sys.RoleCondition;
import com.dto.pagetable.PageModel;
import com.dto.sys.RoleDto;
import com.dto.sys.RoleResourceDto;
import com.utils.easyui.Tree;

public interface RoleService {
	
	PageModel dataGrid(RoleCondition roleCondition);

	void update(RoleDto roleDto);

	void save(RoleDto roleDto);

	void delete(Long id);

	void saveGrant(RoleResourceDto roleResourceDto);

	List<Tree> selectPermissionListByRoleId(Long id);

	RoleDto findById(Long id);

	List<RoleDto> getListRole();

}
