package com.controller.ncov;

import com.condition.ncov.NcovUsersCondition;
import com.dto.common.SessionInfo;
import com.dto.pagetable.PageModel;
import com.dto.sys.ManageUserDto;
import com.dto.sys.RoleDto;
import com.model.ncov.NcovRegionBuildingsEntity;
import com.model.ncov.NcovUsersEntity;
import com.service.ncov.NcovBuildingService;
import com.service.ncov.NcovUsersService;
import com.service.sys.ManageUserService;
import com.service.sys.RoleService;
import com.utils.CommonContants;
import com.utils.easyui.Json;
import com.utils.em.CodeType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/ncov/outuser/")
public class OutUserController {

	@Resource
	private ManageUserService manageUserService;
	@Resource
	private RoleService roleService;

	@Resource
	private NcovUsersService ncovUsersService;

	@Resource
	private NcovBuildingService ncovBuildingService;

	@RequestMapping("listPage")
	public ModelAndView listPage(ModelAndView modelAndView){
		List<NcovRegionBuildingsEntity>  Buildings = ncovBuildingService.getListNcovBuildings();
		modelAndView.addObject("Buildings", Buildings);
		modelAndView.setViewName("ncov/outuser/userList");
		return modelAndView;
	}
	
	@RequestMapping("addPage")
	public ModelAndView addPage(ModelAndView modelAndView){
		List<RoleDto> roleDtos = roleService.getListRole();
		modelAndView.addObject("roleDtos", roleDtos);
		modelAndView.setViewName("ncov/outuser/userAdd");
		return modelAndView;
	}
	
	@RequestMapping("editPage")
	public ModelAndView editPage(ModelAndView modelAndView,Long id){
		ManageUserDto manageUserDto = manageUserService.findById(id);
		modelAndView.addObject("dto", manageUserDto);
		List<RoleDto> roleDtos = roleService.getListRole();
		modelAndView.addObject("roleDtos", roleDtos);
		modelAndView.setViewName("ncov/outuser/userEdit");
		return modelAndView;
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public PageModel dataGrid(HttpServletRequest request, PageModel pageModel,HttpSession session,  NcovUsersCondition ncovUsersCondition){
		try {
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(CommonContants.SESSION_INFO);
			ncovUsersCondition.setSessionid(sessionInfo.getId());
			ncovUsersCondition.setUSERTYPE(Integer.parseInt(request.getParameter("usertype")));
			return ncovUsersService.dataGrid(ncovUsersCondition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageModel;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Json save(NcovUsersEntity ncovUsersEntity, HttpSession session){
		Json j = new Json();
		try {
			ncovUsersService.save(ncovUsersEntity);
			j.setSuccess(true);
			j.setMsg(CodeType.SUCCESS.getLabel());
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(ManageUserDto manageUserDto, HttpSession session){
		Json j = new Json();
		//SessionInfo sessionInfo = (SessionInfo) session.getAttribute(CommonContants.SESSION_INFO);
		try {
			//dictionaryDto.setUpdateUser(sessionInfo.getUsername());
			manageUserService.update(manageUserDto);
			j.setSuccess(true);
			j.setMsg(CodeType.SUCCESS.getLabel());
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Long id){
		Json j = new Json();
		try {
			manageUserService.delete(id);
			j.setSuccess(true);
			j.setMsg(CodeType.SUCCESS.getLabel());
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg(CodeType.FAIL.getLabel());
		}
		return j;
	}
	
}
