package com.service.ncov.impl;

import com.condition.ncov.NcovUsersCondition;
import com.condition.sys.ManageUserCondition;
import com.dao.ncov.NcovUsersMapper;
import com.dto.ncov.NcovUsersDto;
import com.dto.pagetable.PageModel;
import com.framework.exception.ServiceException;
import com.model.ncov.NcovUsersEntity;
import com.service.ncov.NcovUsersService;
import com.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NcovUsersServiceImpl implements NcovUsersService {


	@Autowired
	private NcovUsersMapper ncovUsersMapper;

	@Override
	public NcovUsersEntity findById(Integer id) {
		return  ncovUsersMapper.getByID(id);
	}

	@Override
	public PageModel dataGrid(NcovUsersCondition ncovUsersCondition) {
		ncovUsersCondition.setOffset((ncovUsersCondition.getPage()-1)*ncovUsersCondition.getRows());
		if (!StringUtils.isEmpty(ncovUsersCondition.getADDRESS()) && !StringUtils.isEmpty(ncovUsersCondition.getREGIONADDRESS()))
		{
			ncovUsersCondition.setREGIONADDRESS(ncovUsersCondition.getREGIONADDRESS()+"%"+ncovUsersCondition.getADDRESS());
		}
		if(StringUtils.isEmpty(ncovUsersCondition.getREGIONADDRESS()) && !StringUtils.isEmpty(ncovUsersCondition.getADDRESS()))
		{
			ncovUsersCondition.setREGIONADDRESS(ncovUsersCondition.getADDRESS());
		}
		List<NcovUsersEntity> ncovUsers = ncovUsersMapper.searchPage(ncovUsersCondition);
		List<NcovUsersDto> ncovUsersDtos = new ArrayList<>();
		for(NcovUsersEntity ncovUsersEntity:ncovUsers){
			NcovUsersDto ncovUsersDto = new NcovUsersDto();
			BeanUtil.copyProperties(ncovUsersEntity, ncovUsersDto);
			ncovUsersDtos.add(ncovUsersDto);
		}
		PageModel pageModel = new PageModel();
		pageModel.setPage(ncovUsersCondition.getPage());
		pageModel.setPageSize(ncovUsersCondition.getRows());
		pageModel.setTotal(ncovUsersMapper.getCount(ncovUsersCondition));
		pageModel.setRows(ncovUsersDtos);
		return pageModel;
	}
	@Override
	public PageModel dataNoGrid(NcovUsersCondition ncovUsersCondition) {
		ncovUsersCondition.setOffset((ncovUsersCondition.getPage()-1)*ncovUsersCondition.getRows());
		if (!StringUtils.isEmpty(ncovUsersCondition.getADDRESS()) && !StringUtils.isEmpty(ncovUsersCondition.getREGIONADDRESS()))
		{
			ncovUsersCondition.setREGIONADDRESS(ncovUsersCondition.getREGIONADDRESS()+"%"+ncovUsersCondition.getADDRESS());
		}
		if(StringUtils.isEmpty(ncovUsersCondition.getREGIONADDRESS()) && !StringUtils.isEmpty(ncovUsersCondition.getADDRESS()))
		{
			ncovUsersCondition.setREGIONADDRESS(ncovUsersCondition.getADDRESS());
		}
		List<NcovUsersEntity> ncovUsers = ncovUsersMapper.searchPageNo(ncovUsersCondition);
		List<NcovUsersDto> ncovUsersDtos = new ArrayList<>();
		for(NcovUsersEntity ncovUsersEntity:ncovUsers){
			NcovUsersDto ncovUsersDto = new NcovUsersDto();
			BeanUtil.copyProperties(ncovUsersEntity, ncovUsersDto);
			ncovUsersDtos.add(ncovUsersDto);
		}
		PageModel pageModel = new PageModel();
		pageModel.setPage(ncovUsersCondition.getPage());
		pageModel.setPageSize(ncovUsersCondition.getRows());
		pageModel.setTotal(ncovUsersMapper.getCount(ncovUsersCondition));
		pageModel.setRows(ncovUsersDtos);
		return pageModel;
	}

	@Override
	public void save(NcovUsersEntity ncovUsersEntity) {
		NcovUsersCondition ncovUsersCondition = new NcovUsersCondition();
		ncovUsersCondition.setWECHATID(ncovUsersEntity.getWECHATID());
		ncovUsersCondition.setREGIONID(ncovUsersEntity.getREGIONID());
		ncovUsersCondition.setCARDNO(ncovUsersEntity.getCARDNO());
		ncovUsersCondition.setUSERTYPE(ncovUsersEntity.getUSERTYPE());
		long j = ncovUsersMapper.getCount(ncovUsersCondition);
		if(j > 0 && ncovUsersCondition.getUSERTYPE() == 3){
			throw new ServiceException("该户名已经登记，请重新填写！");
		}
		ncovUsersEntity.setCREATEDATE(new Date());
		ncovUsersMapper.insert(ncovUsersEntity);
	}

	@Override
	public void update(NcovUsersEntity ncovUsersEntity) {
		ncovUsersMapper.updateById(ncovUsersEntity);
	}

	@Override
	public void delete(Long id) {
		ncovUsersMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<NcovUsersEntity> getByCondition(NcovUsersEntity ncovUsersEntity) {
		return ncovUsersMapper.getByCondition(ncovUsersEntity);
	}

	@Override
	public NcovUsersEntity findByWechatId(String weChatid) {
		return  ncovUsersMapper.findByWechatId(weChatid);
	}


}
