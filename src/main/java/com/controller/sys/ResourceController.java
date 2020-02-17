package com.controller.sys;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.condition.sys.ResourceCondition;
import com.dto.common.SessionInfo;
import com.dto.sys.ResourceDto;
import com.service.sys.DictionaryService;
import com.service.sys.ResourceService;
import com.utils.CommonContants;
import com.utils.em.ApproveStatusType;
import com.utils.em.CodeType;
import com.utils.em.DictionaryType;
import com.utils.easyui.Json;
import com.utils.easyui.Tree;

@Controller
@RequestMapping("/sys/resource/")
public class ResourceController {

	@Resource
	private ResourceService resourceService;
	@Resource
	private DictionaryService dictionaryService;

	/**
	 * 导航页
	 * 
	 * @return
	 */
	@RequestMapping(value = "navigation")
	public String navigation() {
		return "navigation";
	}
	
	@RequestMapping("addPage")
	public ModelAndView addPage(ModelAndView modelAndView){
		modelAndView.addObject("dto", dictionaryService.getListByType(DictionaryType.RESOURCE_ICON));
		modelAndView.setViewName("sys/resource/resourceAdd");
		return modelAndView;
	}
	
	@RequestMapping("editPage")
	public ModelAndView editPage(ModelAndView modelAndView,Long id){
		ResourceDto resourceDto = resourceService.findById(id);
		modelAndView.addObject("dto", dictionaryService.getListByType(DictionaryType.RESOURCE_ICON));
		modelAndView.addObject("resourceDto", resourceDto);
		modelAndView.setViewName("sys/resource/resourceEdit");
		return modelAndView;
	}
	
	/**
	 * 资源管理页
	 * @return
	 * @author LiQiang
	 * @date 2016年11月10日
	 */
	@RequestMapping(value = "listPage")
	public ModelAndView listPage() {
		return new ModelAndView("sys/resource/resourceList");
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Json save(ResourceDto resourceDto, HttpSession session){
		Json j = new Json();
		//SessionInfo sessionInfo = (SessionInfo) session.getAttribute(CommonContants.SESSION_INFO);
		try {
			//dictionaryDto.setCreateUser(sessionInfo.getUsername());
			resourceService.save(resourceDto);
			j.setSuccess(true);
			j.setMsg(CodeType.SUCCESS.getLabel());
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg(CodeType.FAIL.getLabel());
		}
		return j;
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(ResourceDto resourceDto, HttpSession session){
		Json j = new Json();
		//SessionInfo sessionInfo = (SessionInfo) session.getAttribute(CommonContants.SESSION_INFO);
		try {
			//dictionaryDto.setUpdateUser(sessionInfo.getUsername());
			if(resourceDto.getId().equals(resourceDto.getPid())){
				j.setMsg("不能设置自己下级节点");
			}else{
				resourceService.update(resourceDto);
				j.setSuccess(true);
				j.setMsg(CodeType.SUCCESS.getLabel());
			}
		} catch (Exception e) {
			j.setMsg(CodeType.FAIL.getLabel());
		}
		return j;
	}

	/**
	 * 获取子菜单
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getChildResource")
	@ResponseBody
	public List<Tree> getChildResource(Long pid,HttpSession session) {
		ResourceCondition resourceCondition = new ResourceCondition();
		resourceCondition.setPid(pid);
		resourceCondition.setStatus(ApproveStatusType.ENABLE.getValue());
		resourceCondition.setResourceType(0);
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(CommonContants.SESSION_INFO);
		resourceCondition.setUserId(sessionInfo.getId());
		return resourceService.getChildResource(resourceCondition);
	}
	
	/**
	 * 获取列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "treeGrid")
	@ResponseBody
	public List<ResourceDto> treeGrid(HttpServletRequest request,ResourceCondition resourceCondition) {
		return resourceService.searchList(resourceCondition);
	}
	
	/**
	 * 下拉树
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "comboTree")
	@ResponseBody
	public List<Tree> comboTree(HttpServletRequest request,ResourceCondition resourceCondition) {
		resourceCondition.setStatus(1);
		List<ResourceDto> resourceDtos = resourceService.searchList(resourceCondition);
		List<Tree> trees = new ArrayList<>();
		for(ResourceDto resourceDto:resourceDtos){
			Tree tree = new Tree();
			tree.setId(resourceDto.getId());
			tree.setText(resourceDto.getName());
			tree.setPid(resourceDto.getPid());
			tree.setIconCls(resourceDto.getIconcls());
			trees.add(tree);
		}
		return trees;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Json delete(HttpServletRequest request,Long id){
		Json json = new Json();
		json.setMsg(CodeType.SUCCESS.getLabel());
		json.setSuccess(true);
		try {
			resourceService.delete(id);
		} catch (Exception e) {
			json.setMsg("删除失败，可能被其他表关联");
			json.setSuccess(false);
		}
		return json;
	}

}
