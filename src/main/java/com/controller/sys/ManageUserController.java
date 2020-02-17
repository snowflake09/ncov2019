package com.controller.sys;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.condition.sys.ManageUserCondition;
import com.dto.pagetable.PageModel;
import com.dto.sys.ManageUserDto;
import com.dto.sys.RoleDto;
import com.service.sys.ManageUserService;
import com.service.sys.RoleService;
import com.utils.easyui.Json;
import com.utils.em.CodeType;

@Controller
@RequestMapping("/sys/manageuser/")
public class ManageUserController {

	@Resource
	private ManageUserService manageUserService;
	@Resource
	private RoleService roleService;
	
	@RequestMapping("listPage")
	public ModelAndView listPage(ModelAndView modelAndView){
		modelAndView.setViewName("sys/user/userList");
		return modelAndView;
	}
	
	@RequestMapping("addPage")
	public ModelAndView addPage(ModelAndView modelAndView){
		List<RoleDto> roleDtos = roleService.getListRole();
		modelAndView.addObject("roleDtos", roleDtos);
		modelAndView.setViewName("sys/user/userAdd");
		return modelAndView;
	}
	
	@RequestMapping("editPage")
	public ModelAndView editPage(ModelAndView modelAndView,Long id){
		ManageUserDto manageUserDto = manageUserService.findById(id);
		modelAndView.addObject("dto", manageUserDto);
		List<RoleDto> roleDtos = roleService.getListRole();
		modelAndView.addObject("roleDtos", roleDtos);
		modelAndView.setViewName("sys/user/userEdit");
		return modelAndView;
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public PageModel dataGrid(PageModel pageModel,ManageUserCondition manageUserCondition){
		try {
			return manageUserService.dataGrid(manageUserCondition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageModel;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Json save(ManageUserDto manageUserDto, HttpSession session){
		Json j = new Json();
		//SessionInfo sessionInfo = (SessionInfo) session.getAttribute(CommonContants.SESSION_INFO);
		try {
			//dictionaryDto.setCreateUser(sessionInfo.getUsername());
			manageUserService.save(manageUserDto);
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
