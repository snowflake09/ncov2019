package com.dao.sys;

import java.util.List;

import com.condition.sys.ResourceCondition;
import com.model.sys.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

	List<Resource> searchByList(ResourceCondition resourceCondition);
}