package com.webService.common;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.model.ncov.NcovRegionEntity;
import com.service.ncov.NcovRegionService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.condition.common.CommonCondition;
import com.framework.security.IgnoreSecurity;
import com.utils.CacheUtils;
import com.utils.alipay.AliPay;
import com.utils.easyui.ResponseJson;
import com.utils.em.CodeType;
import com.utils.em.PaymentType;
import com.utils.wechatpay.WeChatPay;

@Controller
@RequestMapping("/webService/common/")
public class CommonController {
	@Resource
	private NcovRegionService ncovRegionService;
	/**
	 * 获取访问浏览器
	 * @param request
	 * @param model
	 * @return
	 * @author LiQiang
	 * @date 2016年12月22日
	 */
	@IgnoreSecurity
	@RequestMapping("getBrowser")
	public ModelAndView getBrowser(HttpServletRequest request,ModelAndView model){
		String deviceId = request.getParameter("deviceId");
		String userAgent = request.getHeader("user-agent");
		if (userAgent != null && userAgent.contains("AlipayClient")) {
	        String address = "redirect:/auth/alipay.html?deviceId="+deviceId+"&paymentMode="+PaymentType.ALIPAY.getValue()+"&appId="+AliPay.APP_ID;
	        return new ModelAndView(address);
		}else if (userAgent != null && userAgent.contains("MicroMessenger")) {
	        return new ModelAndView("redirect:/auth/wechat.html?deviceId="+deviceId+"&paymentMode="+PaymentType.WECHAT.getValue()+"&appId="+WeChatPay.APP_ID);
		}else{
			model.setViewName("redirect:/auth/noAuth.html");
		}
		return model;
	}

	/**
	 * 获取访问浏览器
	 * @param request
	 * @param model
	 * @return
	 * @author LiQiang
	 * @date 2016年12月22日
	 */
	@IgnoreSecurity
	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request,ModelAndView model){
		String code= request.getParameter("code");
		NcovRegionEntity ncovRegionEntity =ncovRegionService.findByCode(code);
		if(!StringUtils.isEmpty(ncovRegionEntity)) {
			Long regionid = ncovRegionEntity.getID();
			return new ModelAndView("redirect:/auth/login.html?regionid=" + regionid + "&appId=" + WeChatPay.APP_ID);
		}
		else {
			return new ModelAndView("redirect:/auth/noAuth.html");
		}
	}
}
