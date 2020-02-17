package com.service.sys;

import com.condition.sys.ManageUserCondition;
import com.dto.pagetable.PageModel;
import com.dto.sys.ManageUserDto;
import com.dto.sys.RoleDto;

import java.util.List;

public interface ManageUserService {

	ManageUserDto findById(Long id);

	PageModel dataGrid(ManageUserCondition manageUserCondition);

	void save(ManageUserDto manageUserDto);

	void update(ManageUserDto manageUserDto);

	void delete(Long id);

	ManageUserDto login(ManageUserCondition manageUserCondition);

	List<ManageUserDto> getListManageUsers();
}
