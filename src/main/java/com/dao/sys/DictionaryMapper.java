package com.dao.sys;

import java.util.List;

import com.condition.sys.DistionaryCondition;
import com.model.sys.Dictionary;


public interface DictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);

	List<Dictionary> searchPage(DistionaryCondition distionaryCondition);

	Long getCount(DistionaryCondition distionaryCondition);

	List<Dictionary> searchList(DistionaryCondition distionaryCondition);
}