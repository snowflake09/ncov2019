package com.service.ncov.impl;

import com.condition.ncov.NcovBuildingCondition;
import com.dao.ncov.NcovRegionBuildingsMapper;
import com.dao.ncov.NcovRegionMapper;
import com.dto.ncov.NcovRegionBuildingsDto;
import com.dto.ncov.NcovRegionDto;
import com.dto.pagetable.PageModel;
import com.model.ncov.NcovRegionBuildingsEntity;
import com.model.ncov.NcovRegionEntity;
import com.service.ncov.NcovBuildingService;
import com.service.ncov.NcovRegionService;
import com.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NcovBuildingServiceImpl implements NcovBuildingService {


	@Autowired
	private NcovRegionBuildingsMapper ncovRegionBuildingsMapper;

	@Resource
	private NcovRegionMapper ncovRegionMapper;

	@Override
	public NcovRegionBuildingsEntity findById(Long id) {
		return ncovRegionBuildingsMapper.getByID(id);
	}

	@Override
	public PageModel dataGrid(NcovBuildingCondition ncovBuildingCondition) {
		ncovBuildingCondition.setOffset((ncovBuildingCondition.getPage()-1)*ncovBuildingCondition.getRows());
		List<NcovRegionBuildingsEntity> covBuildings = ncovRegionBuildingsMapper.searchPage(ncovBuildingCondition);
		List<NcovRegionBuildingsDto> covBuildingDtos = new ArrayList<>();
		for(NcovRegionBuildingsEntity ncovBuildingEntity:covBuildings){
			NcovRegionEntity NcovRegionEntity = ncovRegionMapper.getByID(ncovBuildingEntity.getREGIONID().longValue());
			NcovRegionBuildingsDto ncovBuildingDto = new NcovRegionBuildingsDto();
			BeanUtil.copyProperties(ncovBuildingEntity, ncovBuildingDto);
			ncovBuildingDto.setNAME(NcovRegionEntity.getNAME());
			covBuildingDtos.add(ncovBuildingDto);
		}
		PageModel pageModel = new PageModel();
		pageModel.setPage(ncovBuildingCondition.getPage());
		pageModel.setPageSize(ncovBuildingCondition.getRows());
		pageModel.setTotal(ncovRegionBuildingsMapper.getCount(ncovBuildingCondition));
		pageModel.setRows(covBuildingDtos);
		return pageModel;
	}

	@Override
	public void save(NcovRegionBuildingsEntity ncovRegionBuildingsEntity) {
		ncovRegionBuildingsEntity.setCREATETIME(new Date());
		ncovRegionBuildingsMapper.insert(ncovRegionBuildingsEntity);
	}

	@Override
	public void update(NcovRegionBuildingsEntity ncovRegionBuildingsEntity) {
		ncovRegionBuildingsEntity.setUPDATETIME(new Date());
		ncovRegionBuildingsMapper.updateById(ncovRegionBuildingsEntity);
	}

	@Override
	public void delete(Long id) {
		ncovRegionBuildingsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<NcovRegionBuildingsEntity> getListNcovBuildingsByRegionids(Long id) {
		NcovRegionBuildingsEntity ncovRegionBuildingsEntity = new NcovRegionBuildingsEntity();
		ncovRegionBuildingsEntity.setREGIONID(id.intValue());
		return ncovRegionBuildingsMapper.getByCondition(ncovRegionBuildingsEntity);
	}

	@Override
	public List<NcovRegionBuildingsEntity> getListNcovBuildings() {
		return ncovRegionBuildingsMapper.getBuildingsList();
	}
}
