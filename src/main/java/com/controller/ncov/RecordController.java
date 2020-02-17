package com.controller.ncov;

import com.condition.ncov.NcovUserRecordCondition;
import com.condition.sys.DistionaryCondition;
import com.dto.common.SessionInfo;
import com.dto.pagetable.PageModel;
import com.dto.sys.DictionaryDto;
import com.model.ncov.NcovRegionBuildingsEntity;
import com.service.ncov.NcovBuildingService;
import com.service.ncov.NcovUserRecordService;
import com.service.sys.DictionaryService;
import com.utils.CommonContants;
import com.utils.easyui.Json;
import com.utils.em.CodeType;
import com.utils.em.DictionaryType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/ncov/record/")
public class RecordController {
	
	@Resource
	private DictionaryService dictionaryService;

	@Resource
	private NcovUserRecordService ncovUserRecordService;

	@Resource
	private NcovBuildingService ncovBuildingService;

	@RequestMapping("listPage")
	public ModelAndView listPage(ModelAndView modelAndView){
		List<NcovRegionBuildingsEntity>  Buildings = ncovBuildingService.getListNcovBuildings();
		modelAndView.addObject("Buildings", Buildings);
		modelAndView.setViewName("ncov/record/recordList");
		return modelAndView;
	}
	
	@RequestMapping("addPage")
	public ModelAndView addPage(ModelAndView modelAndView){
		modelAndView.addObject("dto", DictionaryType.getDictTypeList());
		modelAndView.setViewName("ncov/record/recordAdd");
		return modelAndView;
	}
	
	@RequestMapping("editPage")
	public ModelAndView editPage(ModelAndView modelAndView,Long id){
		DictionaryDto dictionaryDto = dictionaryService.findById(id);
		modelAndView.addObject("dictionaryDto", dictionaryDto);
		modelAndView.addObject("dtoType", DictionaryType.getDictTypeList());
		modelAndView.setViewName("ncov/record/recordEdit");
		return modelAndView;
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public PageModel dataGrid(PageModel pageModel, HttpSession session,  NcovUserRecordCondition ncovUserRecordCondition){
		try {
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(CommonContants.SESSION_INFO);
			ncovUserRecordCondition.setSessionid(sessionInfo.getId());
			return ncovUserRecordService.dataGrid(ncovUserRecordCondition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageModel;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Json save(DictionaryDto dictionaryDto, HttpSession session){
		Json j = new Json();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(CommonContants.SESSION_INFO);
		try {
			dictionaryDto.setCreateUser(sessionInfo.getUsername());
			dictionaryService.save(dictionaryDto);
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
	public Json edit(DictionaryDto dictionaryDto, HttpSession session){
		Json j = new Json();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(CommonContants.SESSION_INFO);
		try {
			dictionaryDto.setUpdateUser(sessionInfo.getUsername());
			dictionaryService.update(dictionaryDto);
			j.setSuccess(true);
			j.setMsg(CodeType.SUCCESS.getLabel());
		} catch (Exception e) {
			j.setMsg(CodeType.FAIL.getLabel());
		}
		return j;
	}
	
}
