package com.service.ncov;

import com.condition.ncov.NcovUsersCondition;
import com.dto.pagetable.PageModel;
import com.model.ncov.NcovUsersEntity;

import java.util.List;

public interface NcovUsersService {

	NcovUsersEntity findById(Integer id);

	PageModel dataGrid(NcovUsersCondition ncovUsersCondition);

	PageModel dataNoGrid(NcovUsersCondition ncovUsersCondition);

	void save(NcovUsersEntity ncovUsersEntity);

	void update(NcovUsersEntity ncovUsersEntity);

	void delete(Long id);

	List<NcovUsersEntity> getByCondition(NcovUsersEntity ncovUsersEntity);

	NcovUsersEntity findByWechatId(String weChaid);

}
