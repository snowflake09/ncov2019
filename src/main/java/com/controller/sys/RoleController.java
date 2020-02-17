package com.controller.sys;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.condition.sys.RoleCondition;
import com.dto.pagetable.PageModel;
import com.dto.sys.RoleDto;
import com.dto.sys.RoleResourceDto;
import com.service.sys.RoleService;
import com.utils.easyui.Json;
import com.utils.easyui.Tree;
import com.utils.em.CodeType;


@Controller
@RequestMapping("/sys/role/")
public class RoleController {
	
	@Resource
	private RoleService roleService;

	@RequestMapping("listPage")
	public ModelAndView listPage(ModelAndView modelAndView){
		modelAndView.setViewName("sys/role/roleList");
		return modelAndView;
	}
	
	@RequestMapping("addPage")
	public ModelAndView addPage(ModelAndView modelAndView){
		modelAndView.setViewName("sys/role/roleAdd");
		return modelAndView;
	}
	
	@RequestMapping("editPage")
	public ModelAndView editPage(ModelAndView modelAndView,Long id){
		RoleDto roleDto = roleService.findById(id);
		modelAndView.addObject("roleDto", roleDto);
		modelAndView.setViewName("sys/role/roleEdit");
		return modelAndView;
	}
	
	@RequestMapping("grantPage")
	public ModelAndView grantPage(ModelAndView modelAndView,Long id){
		modelAndView.setViewName("sys/role/grantPage");
		return modelAndView;
	}
	
	/**
	 * 获取角色列表
	 * @param pageModel
	 * @param roleDto
	 * @return
	 * @throws Exception
	 * @author LiQiang
	 * @date 2016年11月15日
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public PageModel dataGrid(PageModel pageModel,RoleCondition roleCondition){
		try {
			return roleService.dataGrid(roleCondition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageModel;
	}
	
	/**
	 * 保存角色
	 * @param roleDto
	 * @param session
	 * @return
	 * @throws Exception
	 * @author LiQiang
	 * @date 2016年11月15日
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Json save(RoleDto roleDto, HttpSession session){
		Json j = new Json();
		//SessionInfo sessionInfo = (SessionInfo) session.getAttribute(CommonContants.SESSION_INFO);
		try {
			//dictionaryDto.setCreateUser(sessionInfo.getUsername());
			roleService.save(roleDto);
			j.setSuccess(true);
			j.setMsg(CodeType.SUCCESS.getLabel());
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg(CodeType.FAIL.getLabel());
		}
		return j;
	}
	
	/**
	 * 编辑角色
	 * @param roleDto
	 * @param session
	 * @return
	 * @throws Exception
	 * @author LiQiang
	 * @date 2016年11月15日
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(RoleDto roleDto, HttpSession session){
		Json j = new Json();
		//SessionInfo sessionInfo = (SessionInfo) session.getAttribute(CommonContants.SESSION_INFO);
		try {
			//dictionaryDto.setUpdateUser(sessionInfo.getUsername());
			roleService.update(roleDto);
			j.setSuccess(true);
			j.setMsg(CodeType.SUCCESS.getLabel());
		} catch (Exception e) {
			j.setMsg(CodeType.FAIL.getLabel());
		}
		return j;
	}
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 * @throws Exception
	 * @author LiQiang
	 * @date 2016年11月15日
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Long id){
		Json j = new Json();
		try {
			roleService.delete(id);
			j.setSuccess(true);
			j.setMsg(CodeType.SUCCESS.getLabel());
		} catch (Exception e) {
			j.setMsg(CodeType.FAIL.getLabel());
		}
		return j;
	}
	
	/**
	 * 获取权限列表
	 * @param id
	 * @return
	 * @author LiQiang
	 * @date 2016年11月15日
	 */
	@RequestMapping(value="selectPermissionList")
	@ResponseBody
	public List<Tree> selectPermissionList(Long id){
		return roleService.selectPermissionListByRoleId(id);
	}
	
	/**
	 * 给角色授权
	 * @param roleResourceDto
	 * @return
	 * @throws Exception
	 * @author LiQiang
	 * @date 2016年11月15日
	 */
	@RequestMapping("/grant")
	@ResponseBody
	public Json grant(RoleResourceDto roleResourceDto){
		Json j = new Json();
		try {
			roleService.saveGrant(roleResourceDto);
			j.setSuccess(true);
			j.setMsg(CodeType.SUCCESS.getLabel());
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg(CodeType.FAIL.getLabel());
		}
		return j;
	}
	
}
