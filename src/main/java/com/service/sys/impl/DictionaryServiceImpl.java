package com.service.sys.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condition.sys.DistionaryCondition;
import com.dao.sys.DictionaryMapper;
import com.dto.pagetable.PageModel;
import com.dto.sys.DictionaryDto;
import com.model.sys.Dictionary;
import com.service.sys.DictionaryService;
import com.utils.BeanUtil;
import com.utils.em.ApproveStatusType;
import com.utils.em.DictionaryType;

@Service
public class DictionaryServiceImpl implements DictionaryService{

	@Autowired
	private DictionaryMapper dictionaryMapper;
	
	@Override
	public PageModel dataGrid(DistionaryCondition distionaryCondition) {
		distionaryCondition.setOffset((distionaryCondition.getPage()-1)*distionaryCondition.getRows());
		List<Dictionary> dictionaries = dictionaryMapper.searchPage(distionaryCondition);
		List<DictionaryDto> dictionaryDtos = new ArrayList<>();
		for(Dictionary dictionary:dictionaries){
			DictionaryDto dictionaryDto = new DictionaryDto();
			BeanUtil.copyProperties(dictionary, dictionaryDto);
			dictionaryDto.setTypeLabel(DictionaryType.getLabel(dictionary.getType()));
			dictionaryDtos.add(dictionaryDto);
		}
		PageModel pageModel = new PageModel();
		pageModel.setPage(distionaryCondition.getPage());
		pageModel.setPageSize(distionaryCondition.getRows());
		pageModel.setTotal(dictionaryMapper.getCount(distionaryCondition));
		pageModel.setRows(dictionaryDtos);
		return pageModel;
	}

	@Override
	public void save(DictionaryDto dictionaryDto) {
		Dictionary dictionary = new Dictionary();
		BeanUtil.copyProperties(dictionaryDto, dictionary);
		dictionary.setCreateDate(new Date());
		dictionaryMapper.insert(dictionary);
	}

	@Override
	public void update(DictionaryDto dictionaryDto) {
		Dictionary dictionary = new Dictionary();
		BeanUtil.copyProperties(dictionaryDto, dictionary);
		dictionary.setUpdateDate(new Date());
		dictionaryMapper.updateByPrimaryKeySelective(dictionary);
	}

	@Override
	public DictionaryDto findById(Long id) {
		Dictionary dictionary = dictionaryMapper.selectByPrimaryKey(id);
		DictionaryDto dictionaryDto = new DictionaryDto();
		BeanUtil.copyProperties(dictionary, dictionaryDto);
		return dictionaryDto;
	}

	@Override
	public List<DictionaryDto> getListByType(DictionaryType dictionaryType) {
		String type = dictionaryType.getValue();
		DistionaryCondition distionaryCondition = new DistionaryCondition();
		distionaryCondition.setType(type);
		distionaryCondition.setStatus(ApproveStatusType.ENABLE.getValue());
		List<Dictionary> dictionaries = dictionaryMapper.searchList(distionaryCondition);
		List<DictionaryDto> dictionaryDtos = new ArrayList<>();
		for(Dictionary dictionary:dictionaries){
			DictionaryDto dictionaryDto = new DictionaryDto();
			BeanUtil.copyProperties(dictionary, dictionaryDto);
			dictionaryDtos.add(dictionaryDto);
		}
		return dictionaryDtos;
	}

}
