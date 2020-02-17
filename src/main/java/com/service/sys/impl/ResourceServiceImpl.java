package com.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condition.sys.ResourceCondition;
import com.dao.sys.ResourceMapper;
import com.dto.sys.ResourceDto;
import com.model.sys.Resource;
import com.service.sys.ResourceService;
import com.utils.BeanUtil;
import com.utils.easyui.Tree;
import com.utils.em.ApproveStatusType;

@Service
public class ResourceServiceImpl implements ResourceService{

	@Autowired
	private ResourceMapper resourceMapper;
	
	@Override
	public List<Tree> getChildResource(ResourceCondition resourceCondition) {
		List<Resource> resources = resourceMapper.searchByList(resourceCondition);
		List<Tree> resourceDtos = new ArrayList<Tree>();
		for (Resource resource : resources) {
			Tree tree = new Tree();
			tree.setText(resource.getName());
			tree.setId(resource.getId());
			resourceCondition.setPid(resource.getId());
			tree.setState(this.getChildResource(resourceCondition).size() > 0 ? "closed" : "open");
			tree.setIconCls(resource.getIconcls());
			tree.setAttributes(resource.getUrl());
			resourceDtos.add(tree);
		}
		return resourceDtos;
	}

	@Override
	public List<ResourceDto> searchList(ResourceCondition resourceCondition) {
		List<Resource> resources = resourceMapper.searchByList(resourceCondition);
		List<ResourceDto> resourceDtos = new ArrayList<>();
		for(Resource resource:resources){
			ResourceDto resourceDto = new ResourceDto();
			BeanUtil.copyProperties(resource, resourceDto);
			resourceDto.setStatusLabel(ApproveStatusType.getLabel(resource.getStatus()));
			resourceDtos.add(resourceDto);
		}
		return resourceDtos;
	}

	@Override
	public ResourceDto findById(Long id) {
		Resource resource = resourceMapper.selectByPrimaryKey(id);
		ResourceDto resourceDto = new ResourceDto();
		BeanUtil.copyProperties(resource, resourceDto);
		if(resource.getPid()==0){
			resourceDto.setPid(null);
		}
		return resourceDto;
	}

	@Override
	public void save(ResourceDto resourceDto) {
		Resource resource = new Resource();
		BeanUtil.copyProperties(resourceDto, resource);
		if(resourceDto.getPid()==null){
			resource.setPid((long)0);
		}
		resourceMapper.insert(resource);
	}

	@Override
	public void update(ResourceDto resourceDto) {
		Resource resource = new Resource();
		BeanUtil.copyProperties(resourceDto, resource);
		resourceMapper.updateByPrimaryKeySelective(resource);
	}

	@Override
	public void delete(Long id) {
		ResourceCondition resourceCondition = new ResourceCondition();
		resourceCondition.setPid(id);
		List<Tree> childs = this.getChildResource(resourceCondition);
		if (childs.size()>0) {
			for (Tree tree : childs) {
				delete(tree.getId());
			}
		}
		resourceMapper.deleteByPrimaryKey(id);
	}

}
