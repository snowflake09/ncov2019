package com.service.sys.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dto.sys.RoleDto;
import com.model.sys.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condition.sys.ManageUserCondition;
import com.dao.sys.ManageUserMapper;
import com.dto.pagetable.PageModel;
import com.dto.sys.ManageUserDto;
import com.framework.exception.ServiceException;
import com.model.sys.ManageUser;
import com.service.sys.ManageUserService;
import com.utils.BeanUtil;
import com.utils.MD5;

@Service
public class ManageUserServiceImpl implements ManageUserService{

	@Autowired
	private ManageUserMapper manageUserMapper;
	
	@Override
	public ManageUserDto findById(Long id) {
		ManageUser manageUser = manageUserMapper.selectByPrimaryKey(id);
		ManageUserDto manageUserDto = new ManageUserDto();
		BeanUtil.copyProperties(manageUser, manageUserDto);
		return manageUserDto;
	}

	@Override
	public PageModel dataGrid(ManageUserCondition manageUserCondition) {
		manageUserCondition.setOffset((manageUserCondition.getPage()-1)*manageUserCondition.getRows());
		List<ManageUser> manageUsers = manageUserMapper.searchPage(manageUserCondition);
		List<ManageUserDto> manageUserDtos = new ArrayList<>();
		for(ManageUser manageUser:manageUsers){
			ManageUserDto manageUserDto = new ManageUserDto();
			BeanUtil.copyProperties(manageUser, manageUserDto);
			manageUserDtos.add(manageUserDto);
		}
		PageModel pageModel = new PageModel();
		pageModel.setPage(manageUserCondition.getPage());
		pageModel.setPageSize(manageUserCondition.getRows());
		pageModel.setTotal(manageUserMapper.getCount(manageUserCondition));
		pageModel.setRows(manageUserDtos);
		return pageModel;
	}

	@Override
	public void save(ManageUserDto manageUserDto) {
		ManageUserCondition manageUserCondition = new ManageUserCondition();
		manageUserCondition.setLoginName(manageUserDto.getLoginName());
		if(manageUserMapper.findByCondition(manageUserCondition)!=null){
			throw new ServiceException("登录用户名已存在");
		}
		ManageUser manageUser = new ManageUser();
		BeanUtil.copyProperties(manageUserDto, manageUser);
		manageUser.setPassword(MD5.toMD5(manageUserDto.getPassword()));
		manageUser.setCreateDate(new Date());
		manageUserMapper.insert(manageUser);
	}

	@Override
	public void update(ManageUserDto manageUserDto) {
		ManageUserCondition manageUserCondition = new ManageUserCondition();
		manageUserCondition.setId(manageUserDto.getId());
		manageUserCondition.setLoginName(manageUserDto.getLoginName());
		if(manageUserMapper.findByCondition(manageUserCondition)!=null){
			throw new ServiceException("登录用户名已存在");
		}
		ManageUser manageUser = new ManageUser();
		BeanUtil.copyProperties(manageUserDto, manageUser);
		manageUser.setPassword(MD5.toMD5(manageUserDto.getPassword()));
		manageUser.setUpdateDate(new Date());
		manageUserMapper.updateByPrimaryKeySelective(manageUser);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public ManageUserDto login(ManageUserCondition manageUserCondition) {
		ManageUser manageUser = manageUserMapper.login(manageUserCondition);
		if(manageUser==null){
			throw new ServiceException("用户名或密码错误");
		}
		ManageUserDto manageUserDto = new ManageUserDto();
		BeanUtil.copyProperties(manageUser, manageUserDto);
		return manageUserDto;
	}

	@Override
	public List<ManageUserDto> getListManageUsers() {
		List<ManageUser> manageUsers = manageUserMapper.searchList();
		List<ManageUserDto> manageUserDtos = new ArrayList<>();
		for(ManageUser manageUser:manageUsers){
			ManageUserDto manageUserDto = new ManageUserDto();
			BeanUtil.copyProperties(manageUser, manageUserDto);
			manageUserDtos.add(manageUserDto);
		}
		return manageUserDtos;
	}
}
