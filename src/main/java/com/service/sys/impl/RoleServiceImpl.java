package com.service.sys.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.condition.sys.RoleCondition;
import com.dao.sys.RoleMapper;
import com.dao.sys.RoleResourceMapper;
import com.dto.pagetable.PageModel;
import com.dto.sys.RoleDto;
import com.dto.sys.RoleResourceDto;
import com.model.sys.Resource;
import com.model.sys.Role;
import com.model.sys.RoleResource;
import com.service.sys.RoleService;
import com.utils.BeanUtil;
import com.utils.easyui.Tree;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleResourceMapper roleResourceMapper;
	
	@Override
	public PageModel dataGrid(RoleCondition roleCondition) {
		roleCondition.setOffset((roleCondition.getPage()-1)*roleCondition.getRows());
		List<Role> roles = roleMapper.searchPage(roleCondition);
		List<RoleDto> roleDtos = new ArrayList<>();
		for(Role role:roles){
			RoleDto roleDto = new RoleDto();
			BeanUtil.copyProperties(role, roleDto);
			roleDtos.add(roleDto);
		}
		PageModel pageModel = new PageModel();
		pageModel.setPage(roleCondition.getPage());
		pageModel.setPageSize(roleCondition.getRows());
		pageModel.setTotal(roleMapper.getCount(roleCondition));
		pageModel.setRows(roleDtos);
		return pageModel;
	}

	@Override
	public void update(RoleDto roleDto) {
		Role role = new Role();
		BeanUtil.copyProperties(roleDto, role);
		role.setUpdateDate(new Date());
		roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public void save(RoleDto roleDto) {
		Role role = new Role();
		BeanUtil.copyProperties(roleDto, role);
		role.setCreateDate(new Date());
		roleMapper.insert(role);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveGrant(RoleResourceDto roleResourceDto) {
		roleResourceMapper.deletePermissionByRoleId(roleResourceDto.getRoleId());
		if(!StringUtils.isEmpty(roleResourceDto.getPermissionIds())){
			String[] permissionIds = roleResourceDto.getPermissionIds().split(",");
			for(int i=0;i<permissionIds.length;i++){
				RoleResource roleResource = new RoleResource();
				roleResource.setRoleId(roleResourceDto.getRoleId());
				roleResource.setResourceId(Long.valueOf(permissionIds[i]));
				roleResourceMapper.insert(roleResource);
			}
		}
	}

	@Override
	public List<Tree> selectPermissionListByRoleId(Long id) {
		List<Resource> resources = roleMapper.selectPermissionListByRoleId(id);
		List<Tree> trees = new ArrayList<Tree>();

		for (Resource resource : resources) {
			Tree tree = new Tree();
			BeanUtil.copyProperties(resource, tree);
			tree.setIconCls(resource.getIconcls());
			tree.setText(resource.getName());
			if(tree.isChecked()){
				tree.setFlag(true);
			
			}else{
				tree.setFlag(false);
			}
			//resourceDto.setChecked(resource.getChecked() > 0 ? true : false);
			trees.add(tree);
		}
		return trees;
	}

	@Override
	public RoleDto findById(Long id) {
		Role role = roleMapper.selectByPrimaryKey(id);
		RoleDto roleDto = new RoleDto();
		BeanUtil.copyProperties(role, roleDto);
		return roleDto;
	}

	@Override
	public List<RoleDto> getListRole() {
		List<Role> roles = roleMapper.searchList();
		List<RoleDto> roleDtos = new ArrayList<>();
		for(Role role:roles){
			RoleDto roleDto = new RoleDto();
			BeanUtil.copyProperties(role, roleDto);
			roleDtos.add(roleDto);
		}
		return roleDtos;
	}

}
