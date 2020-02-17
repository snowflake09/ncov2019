package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.condition.sys.ManageUserCondition;
import com.condition.sys.ResourceCondition;
import com.dto.common.SessionInfo;
import com.dto.sys.ManageUserDto;
import com.dto.sys.ResourceDto;
import com.service.sys.ManageUserService;
import com.service.sys.ResourceService;
import com.utils.BeanUtil;
import com.utils.CommonContants;
import com.utils.em.ApproveStatusType;


@Controller
public class LoginController {
	
	@Resource
	private ManageUserService manageUserService;
	@Resource
	private ResourceService resourceService;

	@RequestMapping(value = "index")
	public String index(HttpServletRequest request){
		return "index";
	}


	@RequestMapping(value = "login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	/**
	 * 登录提交
	 * @param manageUserCondition
	 * @param request
	 * @return
	 * @author LiQiang
	 * @date 2016年11月16日
	 */
	@RequestMapping(value = "submitLogin")
	@ResponseBody
	public Map<String, Object> submitLogin(ManageUserCondition manageUserCondition,HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			ManageUserDto manageUserDto = manageUserService.login(manageUserCondition);
			resultMap.put("status", 200);
			resultMap.put("message", "登录成功");
			resultMap.put("back_url", "/index");
			HttpSession session = request.getSession();
			SessionInfo sessionInfo = new SessionInfo();
			BeanUtil.copyProperties(manageUserDto, sessionInfo);
			
			ResourceCondition resourceCondition = new ResourceCondition();
			resourceCondition.setStatus(ApproveStatusType.ENABLE.getValue());
			resourceCondition.setUserId(manageUserDto.getId());
			List<ResourceDto> resourceDtos = resourceService.searchList(resourceCondition);
			List<String> list = new ArrayList<>();
			for(ResourceDto resourceDto:resourceDtos){
				list.add(resourceDto.getUrl());
			}
			sessionInfo.setResourceList(list);
			session.setAttribute(CommonContants.SESSION_INFO, sessionInfo);
			session.setMaxInactiveInterval(3600);
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

}
