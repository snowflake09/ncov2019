package com.controller.ncov;

import com.alibaba.fastjson.JSONObject;
import com.condition.common.CommonCondition;
import com.condition.ncov.NcovRegionCondition;
import com.condition.ncov.NcovUserRecordCondition;
import com.condition.ncov.NcovUsersCondition;
import com.condition.sys.ManageUserCondition;
import com.condition.sys.ResourceCondition;

import com.dto.common.SessionInfo;
import com.dto.pagetable.PageModel;
import com.dto.sys.ManageUserDto;
import com.dto.sys.ResourceDto;
import com.framework.security.IgnoreSecurity;
import com.model.ncov.NcovRegionBuildingsEntity;
import com.model.ncov.NcovRegionEntity;
import com.model.ncov.NcovUsersEntity;
import com.service.ncov.NcovBuildingService;
import com.service.ncov.NcovRegionService;
import com.service.ncov.NcovUserRecordService;
import com.service.ncov.NcovUsersService;
import com.service.sys.ManageUserService;
import com.service.sys.ResourceService;
import com.utils.BeanUtil;
import com.utils.CommonContants;
import com.utils.easyui.Json;
import com.utils.easyui.ResponseJson;
import com.utils.em.ApproveStatusType;
import com.utils.em.BusinessType;
import com.utils.em.CodeType;
import com.utils.wechatpay.WeChatPay;
import com.wechat.WechatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/ncov/web/")
public class MainController {

	@Resource
	private NcovRegionService ncovRegionService;

	@Resource
	private NcovUsersService ncovUsersService;

	@Resource
	private NcovUserRecordService ncovUserRecordService;


	@Resource
	private NcovBuildingService ncovBuildingService;


	@RequestMapping(value = "index")
	public ModelAndView index(NcovRegionCondition ncovRegionCondition, HttpServletRequest request, ModelAndView modelAndView)
	{
		String code= request.getParameter("code");
		String regionid= request.getParameter("regionid");
		ncovRegionCondition.setJsCode(code);
		if(!StringUtils.isEmpty(ncovRegionCondition.getJsCode())){
			JSONObject jsonObject = WechatUtils.getOpenId(ncovRegionCondition.getJsCode());
			if(!StringUtils.isEmpty(jsonObject.getString("openid")) && !StringUtils.isEmpty(regionid)){
				String openid = jsonObject.getString("openid");
				NcovRegionEntity ncovRegionEntity = ncovRegionService.findById(Long.parseLong(regionid));
				modelAndView.addObject("name", ncovRegionEntity.getNAME());
				NcovUsersEntity ncovUsersEntity = new NcovUsersEntity();
				ncovUsersEntity.setWECHATID(openid);
				ncovUsersEntity.setREGIONID(Long.parseLong(regionid));
				ncovUsersEntity.setUSERTYPE(3);
				List<NcovUsersEntity> ncovUsers = ncovUsersService.getByCondition(ncovUsersEntity);
				modelAndView.addObject("count", ncovUsers.size());
				modelAndView.addObject("regionid", regionid);
				modelAndView.addObject("openid", openid);
				modelAndView.setViewName("web/record");
			}else{
				modelAndView.setViewName("redirect:/auth/noWechat.html");
/*				modelAndView.addObject("name","金泽家园B区");
				String openid= request.getParameter("openid");
				modelAndView.addObject("regionid", regionid);
				modelAndView.addObject("openid", openid);
				modelAndView.setViewName("web/record");*/
			}
		}
		else {
		String openid = "oHtMx5pqAYxEP4md9pV1MPsilyiQ";
			regionid = "1";
			modelAndView.addObject("name", "金泽家园B区");
			modelAndView.addObject("regionid", regionid);
			modelAndView.addObject("openid", openid);
			modelAndView.setViewName("web/record");
/*			modelAndView.setViewName("redirect:/auth/noWechat.html");*/

		}
		return modelAndView;
	}

	@RequestMapping(value = "inadd")
	public ModelAndView inadd(HttpServletRequest request, ModelAndView modelAndView){
		String openid= request.getParameter("openid");
		String regionid= request.getParameter("regionid");
/*		openid = "oHtMx5pqAYxEP4md9pV1MPsilyiQ";
		regionid = "1";*/
		if(!StringUtils.isEmpty(openid) && !StringUtils.isEmpty(regionid)){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<NcovRegionBuildingsEntity>  Buildings = ncovBuildingService.getListNcovBuildingsByRegionids(Long.parseLong(regionid));
			modelAndView.addObject("Buildings", Buildings);
			modelAndView.addObject("now", df.format(new Date()));
			modelAndView.addObject("openid", openid);
			modelAndView.addObject("regionid", regionid);
			modelAndView.setViewName("web/inuseradd");
		}
		else{
			modelAndView.setViewName("redirect:/auth/noWechat.html");
		}
		return modelAndView;

	}
	@RequestMapping(value = "add")
	public ModelAndView add(HttpServletRequest request, ModelAndView modelAndView){
		String openid= request.getParameter("openid");
		String regionid= request.getParameter("regionid");
		if(!StringUtils.isEmpty(openid) && !StringUtils.isEmpty(regionid)){
			List<NcovRegionBuildingsEntity>  Buildings = ncovBuildingService.getListNcovBuildingsByRegionids(Long.parseLong(regionid));
			modelAndView.addObject("Buildings", Buildings);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			modelAndView.addObject("now", df.format(new Date()));
			modelAndView.addObject("openid", openid);
			modelAndView.addObject("regionid", regionid);
			modelAndView.setViewName("web/useradd");
		}
		else{
			modelAndView.setViewName("redirect:/auth/noWechat.html");
		}
		return modelAndView;

	}

	@RequestMapping(value = "outadd")
	public ModelAndView outadd(HttpServletRequest request, ModelAndView modelAndView){
		String openid= request.getParameter("openid");
		String regionid= request.getParameter("regionid");
		if(!StringUtils.isEmpty(openid) && !StringUtils.isEmpty(regionid)){
			List<NcovRegionBuildingsEntity>  Buildings = ncovBuildingService.getListNcovBuildingsByRegionids(Long.parseLong(regionid));
			modelAndView.addObject("Buildings", Buildings);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			modelAndView.addObject("now", df.format(new Date()));
			modelAndView.addObject("openid", openid);
			modelAndView.addObject("regionid", regionid);
			modelAndView.setViewName("web/outuseradd");
		}
		else{
			modelAndView.setViewName("redirect:/auth/noWechat.html");
		}
		return modelAndView;
	}

	@RequestMapping(value = "healthadd")
	public ModelAndView healthadd(HttpServletRequest request, ModelAndView modelAndView){

		String openid= request.getParameter("openid");
		String regionid= request.getParameter("regionid");
		NcovUsersEntity ncovUsersEntity = new NcovUsersEntity();
		ncovUsersEntity.setWECHATID(openid);
		ncovUsersEntity.setREGIONID(Long.parseLong(regionid));
		ncovUsersEntity.setUSERTYPE(3);
		List<NcovUsersEntity> ncovUsers = ncovUsersService.getByCondition(ncovUsersEntity);
		if(!StringUtils.isEmpty(openid) && !StringUtils.isEmpty(regionid) && ncovUsers.size()>0){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			modelAndView.addObject("now", df.format(new Date()));
			modelAndView.addObject("openid", openid);
			modelAndView.addObject("regionid", regionid);
			modelAndView.addObject("users", ncovUsers);
			modelAndView.addObject("userid", ncovUsers.get(0).getID());
			modelAndView.addObject("count", ncovUsers.size());
			modelAndView.addObject("current", 0);
			modelAndView.setViewName("web/healthadd");
		}
		else{
			modelAndView.setViewName("redirect:/auth/noWechat.html");
		}
		return modelAndView;
	}

	/**
	 * 登录提交
	 * @param ncovUsersCondition
	 * @param request
	 * @return
	 * @author LiQiang
	 * @date 2016年11月16日
	 */
	@RequestMapping(value = "nextRecord")
	@ResponseBody
	public Map<String, Object> nextRecord(NcovUsersCondition ncovUsersCondition, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			PageModel pageModel = ncovUsersService.dataGrid(ncovUsersCondition);
			resultMap.put("status", 200);
			resultMap.put("message", "登录成功");
			resultMap.put("user", pageModel.getRows().get(0));
			resultMap.put("back_url", "/ncov/web/healthadd");
		}catch (Exception e) {
			resultMap.put("status", 500);
			resultMap.put("message", "帐号或密码错误");
		}
		return resultMap;
	}

	/**
	 * 退出
	 * 
	 * @return
	 */
	@RequestMapping(value = "logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "login";
	}
	@RequestMapping("/addinuser")
	@ResponseBody
	public Json addinuser(NcovUsersEntity ncovUsersEntity, HttpSession session, HttpServletRequest request){
		Json j = new Json();

		try {
			ncovUsersService.save(ncovUsersEntity);
			j.setSuccess(true);
			j.setMsg("登记成功，请登记下一位！");
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	@RequestMapping("/addhealth")
	@ResponseBody
	public Json addhealth(NcovUserRecordCondition ncovUserRecordCondition, HttpSession session, HttpServletRequest request){
		Json j = new Json();
		try {
			ncovUserRecordService.save(ncovUserRecordCondition);
			j.setSuccess(true);
			j.setMsg("登记成功，请登记下一位！");
		} catch (Exception e) {
			j.setSuccess(false);
			e.printStackTrace();
			j.setMsg("操作失败，请以管理员联系！");
		}
		return j;
	}
	@IgnoreSecurity
	@RequestMapping(value = "getrecord")
	@ResponseBody
	public ResponseJson getrecord(NcovUsersCondition ncovUsersCondition, HttpServletRequest request){
		ResponseJson json = new ResponseJson();
		if (ncovUsersCondition.getPage() >= ncovUsersCondition.getOffset()){
			ncovUsersCondition.setRows(1);
		}
		PageModel pageModel = ncovUsersService.dataGrid(ncovUsersCondition);
		json.success(pageModel.getRows().get(0), CodeType.SUCCESS.getLabel());
		return json;
	}
	@IgnoreSecurity
	@RequestMapping("/getuserid")
	@ResponseBody
	public ResponseJson edit(HttpSession session, HttpServletRequest request){
		ResponseJson json = new ResponseJson();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(CommonContants.SESSION_INFO);
		json.success(sessionInfo.getId(),CodeType.SUCCESS.getLabel());
		return json;
	}
	@IgnoreSecurity
	@RequestMapping("/getuser")
	@ResponseBody
	public ResponseJson getuser(String wechatid){
		ResponseJson json = new ResponseJson();
		NcovUsersEntity NcovUsersEntity = ncovUsersService.findByWechatId(wechatid);
		if (NcovUsersEntity == null){
			json.failure(301,"Null");
		}else {
			json.success(NcovUsersEntity,CodeType.SUCCESS.getLabel());
		}

		return json;
	}
}
