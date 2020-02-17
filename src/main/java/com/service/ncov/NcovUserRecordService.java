package com.service.ncov;

import com.condition.ncov.NcovUserRecordCondition;
import com.dto.pagetable.PageModel;
import com.model.ncov.NcovUserRecordEntity;
import com.model.ncov.NcovUsersEntity;

public interface NcovUserRecordService {

	NcovUserRecordEntity findById(Integer id);

	PageModel dataGrid(NcovUserRecordCondition ncovUserRecordCondition);

	void save(NcovUserRecordEntity ncovUserRecordEntity);

	void update(NcovUserRecordEntity ncovUserRecordEntity);

	void delete(Long id);

	void save(NcovUserRecordCondition ncovUserRecordCondition);

}
