package com.service.ncov.impl;

import com.condition.ncov.NcovRegionCondition;
import com.condition.sys.ManageUserCondition;
import com.dao.ncov.NcovRegionMapper;
import com.dto.ncov.NcovRegionDto;
import com.dto.pagetable.PageModel;
import com.dto.sys.RoleDto;
import com.model.ncov.NcovRegionEntity;
import com.model.ncov.NcovUsersEntity;
import com.model.sys.Role;
import com.service.ncov.NcovRegionService;
import com.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NcovRegionServiceImpl implements NcovRegionService {


	@Autowired
	private NcovRegionMapper ncovRegionMapper;

	@Override
	public NcovRegionEntity findById(Long id) {
		return  ncovRegionMapper.getByID(id);
	}

	@Override
	public PageModel dataGrid(NcovRegionCondition ncovRegionCondition) {
		ncovRegionCondition.setOffset((ncovRegionCondition.getPage()-1)*ncovRegionCondition.getRows());
		List<NcovRegionEntity> covRegions = ncovRegionMapper.searchPage(ncovRegionCondition);
		List<NcovRegionDto> ncovRegionDtos = new ArrayList<>();
		for(NcovRegionEntity ncovRegionEntity:covRegions){
			NcovRegionDto ncovRegionDto = new NcovRegionDto();
			BeanUtil.copyProperties(ncovRegionEntity, ncovRegionDto);
			ncovRegionDtos.add(ncovRegionDto);
		}
		PageModel pageModel = new PageModel();
		pageModel.setPage(ncovRegionCondition.getPage());
		pageModel.setPageSize(ncovRegionCondition.getRows());
		pageModel.setTotal(ncovRegionMapper.getCount(ncovRegionCondition));
		pageModel.setRows(ncovRegionDtos);
		return pageModel;
	}

	@Override
	public void save(NcovRegionEntity ncovRegionEntity) {
		ncovRegionEntity.setCREATEDATE(new Date());
		ncovRegionMapper.insert(ncovRegionEntity);
	}

	@Override
	public void update(NcovRegionEntity ncovRegionEntity) {
		ncovRegionEntity.setUPDATEDATE(new Date());
		ncovRegionMapper.updateById(ncovRegionEntity);
	}

	@Override
	public void delete(Long id) {
		ncovRegionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<NcovRegionEntity> getListNcovRegionByUserids(Long id) {
		NcovRegionEntity ncovRegionEntity = new NcovRegionEntity();
		ncovRegionEntity.setMANAGEID(id);
		return ncovRegionMapper.getByCondition(ncovRegionEntity);
	}

	@Override
	public List<NcovRegionEntity> getListNcovRegionList() {
		return ncovRegionMapper.getRegionList();
	}

	@Override
	public List<Long> getIds(Long sessionid) {
		return ncovRegionMapper.getIds(sessionid);
	}

	@Override
	public NcovRegionEntity findByCode(String code) {
		return ncovRegionMapper.getByCode(code);
	}
}
