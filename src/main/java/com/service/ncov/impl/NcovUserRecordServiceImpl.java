package com.service.ncov.impl;

import com.condition.ncov.NcovUserRecordCondition;
import com.dao.ncov.NcovUserRecordMapper;
import com.dao.ncov.NcovUsersMapper;
import com.dto.ncov.NcovUserRecordDto;
import com.dto.pagetable.PageModel;
import com.model.ncov.NcovUserRecordEntity;
import com.model.ncov.NcovUsersEntity;
import com.service.ncov.NcovUserRecordService;
import com.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NcovUserRecordServiceImpl implements NcovUserRecordService {

	@Autowired
	private NcovUserRecordMapper ncovUserRecordMapper;


	@Autowired
	private NcovUsersMapper ncovUsersMapper;

	@Override
	public NcovUserRecordEntity findById(Integer id) {
		return  ncovUserRecordMapper.getByID(id);
	}

	@Override
	public PageModel dataGrid(NcovUserRecordCondition ncovUserRecordCondition) {
		ncovUserRecordCondition.setOffset((ncovUserRecordCondition.getPage()-1)*ncovUserRecordCondition.getRows());
		if (!StringUtils.isEmpty(ncovUserRecordCondition.getADDRESS()) && !StringUtils.isEmpty(ncovUserRecordCondition.getREGIONADDRESS()))
		{
			ncovUserRecordCondition.setREGIONADDRESS(ncovUserRecordCondition.getREGIONADDRESS()+"%"+ncovUserRecordCondition.getADDRESS());
		}
		if(StringUtils.isEmpty(ncovUserRecordCondition.getREGIONADDRESS()) && !StringUtils.isEmpty(ncovUserRecordCondition.getADDRESS()))
		{
			ncovUserRecordCondition.setREGIONADDRESS(ncovUserRecordCondition.getADDRESS());
		}
		List<NcovUserRecordEntity> ncovUserRecords = ncovUserRecordMapper.searchPage(ncovUserRecordCondition);
		List<NcovUserRecordDto> ncovUserRecordDtos = new ArrayList<>();
		for(NcovUserRecordEntity ncovUserRecordEntity:ncovUserRecords){
			NcovUserRecordDto ncovUserRecordDto = new NcovUserRecordDto();
			BeanUtil.copyProperties(ncovUserRecordEntity, ncovUserRecordDto);
			Long id = ncovUserRecordEntity.getREPORTUSERID();
			ncovUserRecordDto.setCARDNO(ncovUsersMapper.selectByPrimaryKey(id).getCARDNO());
			ncovUserRecordDto.setPHONE(ncovUsersMapper.selectByPrimaryKey(id).getPHONE());
			ncovUserRecordDto.setREGIONADDRESS(ncovUsersMapper.selectByPrimaryKey(id).getREGIONADDRESS());
			ncovUserRecordDtos.add(ncovUserRecordDto);
		}
		PageModel pageModel = new PageModel();
		pageModel.setPage(ncovUserRecordCondition.getPage());
		pageModel.setPageSize(ncovUserRecordCondition.getRows());
		pageModel.setTotal(ncovUserRecordMapper.getCount(ncovUserRecordCondition));
		pageModel.setRows(ncovUserRecordDtos);
		return pageModel;
	}

	@Override
	public void save(NcovUserRecordEntity ncovUserRecordEntity) {
		ncovUserRecordMapper.insert(ncovUserRecordEntity);
	}

	@Override
	public void update(NcovUserRecordEntity ncovUserRecordEntity) {
		ncovUserRecordMapper.updateById(ncovUserRecordEntity);
	}

	@Override
	public void delete(Long id) {
		ncovUserRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void save(NcovUserRecordCondition ncovUserRecordCondition) {
		NcovUserRecordEntity ncovUserRecordEntity = new NcovUserRecordEntity();
		ncovUserRecordEntity.setUSERNAME(ncovUserRecordCondition.getUSERNAME());
		ncovUserRecordEntity.setTEMPERATURE(ncovUserRecordCondition.getTEMPERATURE());
		ncovUserRecordEntity.setHEALTHY(ncovUserRecordCondition.getHEALTHY());
		ncovUserRecordEntity.setHEALTHNOTE(ncovUserRecordCondition.getHEALTHNOTE());
		ncovUserRecordEntity.setREPORTUSERID(ncovUserRecordCondition.getREPORTUSERID());
		ncovUserRecordEntity.setCREATEDATE(new Date());
		ncovUserRecordEntity.setUPDATEDATE(new Date());
		ncovUserRecordMapper.insert(ncovUserRecordEntity);
		NcovUsersEntity  ncovUsersEntity = ncovUsersMapper.selectByPrimaryKey(ncovUserRecordEntity.getREPORTUSERID());
		if (ncovUsersEntity != null){
			ncovUsersEntity.setUPDATEDATE(new Date());
			ncovUsersMapper.updateById(ncovUsersEntity);
		}
	}
}
