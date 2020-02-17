package com.service.ncov;

import com.condition.ncov.NcovRegionCondition;
import com.dto.pagetable.PageModel;
import com.model.ncov.NcovRegionEntity;

import java.util.List;

public interface NcovRegionService {

	NcovRegionEntity findById(Long id);

	PageModel dataGrid(NcovRegionCondition ncovRegionCondition);

	void save(NcovRegionEntity ncovRegionEntity);

	void update(NcovRegionEntity ncovRegionEntity);

	void delete(Long id);

	List<NcovRegionEntity> getListNcovRegionByUserids(Long id);

	List<NcovRegionEntity> getListNcovRegionList();

	List<Long> getIds(Long sessionid);

	NcovRegionEntity findByCode(String code);
}
