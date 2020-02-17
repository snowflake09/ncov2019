package com.dao.sys;

import java.util.List;

import com.condition.sys.RoleCondition;
import com.model.sys.Resource;
import com.model.sys.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	List<Resource> selectPermissionListByRoleId(Long id);

	List<Role> searchPage(RoleCondition roleCondition);

	Long getCount(RoleCondition roleCondition);

	List<Role> searchList();

}