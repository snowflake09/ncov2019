package com.controller.ncov;

import com.condition.ncov.NcovRegionCondition;
import com.dto.common.SessionInfo;
import com.dto.pagetable.PageModel;
import com.model.ncov.NcovRegionEntity;
import com.service.ncov.NcovRegionService;
import com.service.sys.ManageUserService;
import com.utils.CommonContants;
import com.utils.FileUpLoad;
import com.utils.QRCode;
import com.utils.RecordNo;
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
@RequestMapping("/ncov/region/")
public class RegionController {

	@Resource
	private NcovRegionService ncovRegionService;

	@Resource
	private ManageUserService manageUserService;

	@RequestMapping("listPage")
	public ModelAndView listPage(ModelAndView modelAndView){
		modelAndView.setViewName("ncov/region/regionList");
		return modelAndView;
	}
	
	@RequestMapping("addPage")
	public ModelAndView addPage(ModelAndView modelAndView){
		modelAndView.addObject("userDtos", manageUserService.getListManageUsers());
		modelAndView.setViewName("ncov/region/regionAdd");
		return modelAndView;
	}
	
	@RequestMapping("editPage")
	public ModelAndView editPage(ModelAndView modelAndView,Long id){
		NcovRegionEntity ncovRegionEntity = ncovRegionService.findById(id);
		modelAndView.addObject("ncovRegionDto", ncovRegionEntity);
		modelAndView.addObject("userDtos", manageUserService.getListManageUsers());
		modelAndView.setViewName("ncov/region/regionEdit");
		return modelAndView;
	}

	/**
	 * 获取社区列表
	 * @param pageModel
	 * @param ncovRegionCondition
	 * @return
	 * @throws Exception
	 * @author LiQiang
	 * @date 2016年11月15日
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public PageModel dataGrid(PageModel pageModel,HttpSession session, NcovRegionCondition ncovRegionCondition){
		try {
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(CommonContants.SESSION_INFO);
			ncovRegionCondition.setSessionid(sessionInfo.getId());
			return ncovRegionService.dataGrid(ncovRegionCondition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageModel;
	}
	
	/**
	 * 保存社区信息
	 * @param ncovRegionEntity
	 * @param session
	 * @return
	 * @throws Exception
	 * @author LiQiang
	 * @date 2016年11月15日
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Json save(NcovRegionEntity ncovRegionEntity, HttpSession session, HttpServletRequest request){
		Json j = new Json();
		//SessionInfo sessionInfo = (SessionInfo) session.getAttribute(CommonContants.SESSION_INFO);
		try {
			ncovRegionEntity.setMANAGENAME(manageUserService.findById(ncovRegionEntity.getMANAGEID()).getLoginName());
			String code = RecordNo.create("ZZ", RecordNo.YEAR_MONTH+RecordNo.DAY_HOUR);
			String QrPath = QRCode.encodeIsText(code, "http://" + request.getServerName()+ request.getContextPath()+"/webService/common/login?code="+code, FileUpLoad.getRootPath(request), "ymkj/ncovQr");
			QrPath = request.getContextPath()+QrPath;
			ncovRegionEntity.setCODE(code);
			ncovRegionEntity.setREGIONQRPATH(QrPath);
			ncovRegionService.save(ncovRegionEntity);
			j.setSuccess(true);
			j.setMsg(CodeType.SUCCESS.getLabel());
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg(CodeType.FAIL.getLabel());
		}
		return j;
	}
	
	/**
	 * 编辑社区
	 * @param ncovRegionEntity
	 * @param session
	 * @return
	 * @throws Exception
	 * @author LiQiang
	 * @date 2016年11月15日
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(NcovRegionEntity ncovRegionEntity,  HttpSession session){
		Json j = new Json();
		//SessionInfo sessionInfo = (SessionInfo) session.getAttribute(CommonContants.SESSION_INFO);
		try {
			ncovRegionEntity.setMANAGENAME(manageUserService.findById(ncovRegionEntity.getMANAGEID()).getLoginName());
			ncovRegionService.update(ncovRegionEntity);
			j.setSuccess(true);
			j.setMsg(CodeType.SUCCESS.getLabel());
		} catch (Exception e) {
			j.setMsg(CodeType.FAIL.getLabel());
		}
		return j;
	}
	
	/**
	 * 删除社区信息
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
			ncovRegionService.delete(id);
			j.setSuccess(true);
			j.setMsg(CodeType.SUCCESS.getLabel());
		} catch (Exception e) {
			j.setMsg(CodeType.FAIL.getLabel());
		}
		return j;
	}
	
	/**
	 * 获取社区列表
	 * @param id
	 * @return
	 * @author LiQiang
	 * @date 2016年11月15日
	 */
	@RequestMapping(value="selectRegionList")
	@ResponseBody
	public List<NcovRegionEntity> selectRegionList(Long id){
		return ncovRegionService.getListNcovRegionByUserids(id);
	}
	
}
