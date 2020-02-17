package com.controller.ncov;

import com.condition.ncov.NcovBuildingCondition;
import com.condition.ncov.NcovRegionCondition;
import com.dto.common.SessionInfo;
import com.dto.pagetable.PageModel;
import com.model.ncov.NcovRegionBuildingsEntity;
import com.model.ncov.NcovRegionEntity;
import com.service.ncov.NcovBuildingService;
import com.service.ncov.NcovRegionService;
import com.service.sys.ManageUserService;
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
@RequestMapping("/ncov/building/")
public class BuildingController {

	@Resource
	private NcovRegionService ncovRegionService;

	@Resource
	private NcovBuildingService ncovBuildingService;

	@Resource
	private ManageUserService manageUserService;

	@RequestMapping("listPage")
	public ModelAndView listPage(ModelAndView modelAndView){
		modelAndView.setViewName("ncov/building/buildingList");
		return modelAndView;
	}
	
	@RequestMapping("addPage")
	public ModelAndView addPage(ModelAndView modelAndView){
		List<NcovRegionEntity> regionDtos = ncovRegionService.getListNcovRegionList();
		modelAndView.addObject("regionDtos", regionDtos);
		modelAndView.setViewName("ncov/building/buildingAdd");
		return modelAndView;
	}
	
	@RequestMapping("editPage")
	public ModelAndView editPage(ModelAndView modelAndView,Long id){

		NcovRegionBuildingsEntity ncovRegionBuildingsEntity = ncovBuildingService.findById(id);
		modelAndView.addObject("ncovBuildingDto", ncovRegionBuildingsEntity);
		List<NcovRegionEntity> regionDtos = ncovRegionService.getListNcovRegionList();
		modelAndView.addObject("regionDtos", regionDtos);
		modelAndView.setViewName("ncov/building/buildingEdit");
		return modelAndView;
	}

	/**
	 * 获取楼号列表
	 * @param pageModel
	 * @param ncovBuildingCondition
	 * @return
	 * @throws Exception
	 * @author LiQiang
	 * @date 2016年11月15日
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public PageModel dataGrid(PageModel pageModel,HttpSession session,  NcovBuildingCondition ncovBuildingCondition){
		try {
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(CommonContants.SESSION_INFO);
			ncovBuildingCondition.setSessionid(sessionInfo.getId());
			return ncovBuildingService.dataGrid(ncovBuildingCondition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageModel;
	}
	
	/**
	 * 保存楼号信息
	 * @param ncovRegionBuildingsEntity
	 * @param session
	 * @return
	 * @throws Exception
	 * @author LiQiang
	 * @date 2016年11月15日
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Json save(NcovRegionBuildingsEntity ncovRegionBuildingsEntity, HttpSession session){
		Json j = new Json();
		//SessionInfo sessionInfo = (SessionInfo) session.getAttribute(CommonContants.SESSION_INFO);
		try {
			ncovBuildingService.save(ncovRegionBuildingsEntity);
			j.setSuccess(true);
			j.setMsg(CodeType.SUCCESS.getLabel());
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg(CodeType.FAIL.getLabel());
		}
		return j;
	}
	
	/**
	 * 编辑楼号
	 * @param ncovRegionBuildingsEntity
	 * @param session
	 * @return
	 * @throws Exception
	 * @author LiQiang
	 * @date 2016年11月15日
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(NcovRegionBuildingsEntity ncovRegionBuildingsEntity,  HttpSession session){
		Json j = new Json();
		//SessionInfo sessionInfo = (SessionInfo) session.getAttribute(CommonContants.SESSION_INFO);
		try {
			ncovBuildingService.update(ncovRegionBuildingsEntity);
			j.setSuccess(true);
			j.setMsg(CodeType.SUCCESS.getLabel());
		} catch (Exception e) {
			j.setMsg(CodeType.FAIL.getLabel());
		}
		return j;
	}
	
	/**
	 * 删除楼号信息
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
			ncovBuildingService.delete(id);
			j.setSuccess(true);
			j.setMsg(CodeType.SUCCESS.getLabel());
		} catch (Exception e) {
			j.setMsg(CodeType.FAIL.getLabel());
		}
		return j;
	}
	
	/**
	 * 获取楼号列表
	 * @param id
	 * @return
	 * @author LiQiang
	 * @date 2016年11月15日
	 */
	@RequestMapping(value="selectBulidingList")
	@ResponseBody
	public List<NcovRegionBuildingsEntity> selectBulidingList(Long id){
		return ncovBuildingService.getListNcovBuildingsByRegionids(id);
	}
	
}
