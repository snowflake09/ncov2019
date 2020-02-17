package com.service.ncov;

import com.condition.ncov.NcovBuildingCondition;
import com.condition.ncov.NcovRegionCondition;
import com.dto.pagetable.PageModel;
import com.model.ncov.NcovRegionBuildingsEntity;
import com.model.ncov.NcovRegionEntity;

import java.util.List;

public interface NcovBuildingService {

	NcovRegionBuildingsEntity findById(Long id);

	PageModel dataGrid(NcovBuildingCondition ncovBuildingCondition);

	void save(NcovRegionBuildingsEntity ncovRegionBuildingsEntity);

	void update(NcovRegionBuildingsEntity ncovRegionBuildingsEntity);

	void delete(Long id);

	List<NcovRegionBuildingsEntity> getListNcovBuildingsByRegionids(Long id);

	List<NcovRegionBuildingsEntity> getListNcovBuildings();
}
