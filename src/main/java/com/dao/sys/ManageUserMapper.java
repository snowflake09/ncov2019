package com.dao.sys;

import java.util.List;

import com.condition.sys.ManageUserCondition;
import com.model.sys.ManageUser;
import com.model.sys.Role;

public interface ManageUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ManageUser record);

    int insertSelective(ManageUser record);

    ManageUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManageUser record);

    int updateByPrimaryKey(ManageUser record);

	List<ManageUser> searchPage(ManageUserCondition manageUserCondition);

	Long getCount(ManageUserCondition manageUserCondition);

	ManageUser findByCondition(ManageUserCondition manageUserCondition);

	ManageUser login(ManageUserCondition manageUserCondition);

    List<ManageUser> searchList();
}