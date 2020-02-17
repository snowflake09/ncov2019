package com.framework.interceptors;

import com.service.biz.TokenService;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dto.common.SessionInfo;
import com.framework.exception.ServiceException;
import com.framework.security.IgnoreSecurity;
import com.framework.security.NeedSign;
import com.utils.CommonContants;
import com.utils.MD5;
import com.utils.OkhttpRequest;
import com.utils.em.CodeType;
import com.utils.em.UserType;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * 权限拦截器
 */
public class SecurityInterceptor implements HandlerInterceptor {

	@Resource
	private TokenService tokenService;
	
	private List<String> excludeUrls;// 不需要拦截的资源

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
	
	private List<String> sessionExcludeUrls;//登录后不拦截的资源

	public List<String> getSessionExcludeUrls() {
		return sessionExcludeUrls;
	}

	public void setSessionExcludeUrls(List<String> sessionExcludeUrls) {
		this.sessionExcludeUrls = sessionExcludeUrls;
	}

	/**
	 * 完成页面的render后调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) throws Exception {
	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 在调用controller具体方法前拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(CommonContants.SESSION_INFO);
		
		/**
		 * 不拦截的地址
		 */
		if (excludeUrls.contains(url)) {
			return true;
		}
		
		/**
		 * 后台首页URL
		 */
		if (url.indexOf("/console") > -1) {
			return true;
		}
		
		/**
		 * 微信端
		 */
		if (url.indexOf("/wechat") > -1) {
			return true;
		}
		/**
		 * 首页UR
		 */
		if (url.indexOf("/ncov/web") > -1) {
			return true;
		}

		/**
		 * 移动端接口请求的token校验
		 */
		if (url.indexOf("/webService/") > -1) {
			//移动端接口数据安全性校验-检查
			this.checkToken(request, object ,url);
			return true;
		}

		/** 内部访问数据，不做拦截 **/
		if (sessionInfo != null) {
			if (sessionExcludeUrls.contains(url)) {
				return true;
			}
			if (!sessionInfo.getResourceList().contains(url)) {// 如果当前用户没有访问此资源的权限
				request.setAttribute("msg", "您没有访问此资源的权限！<br/>请联系超管赋予您<br/>[" + url + "]<br/>的资源访问权限！");
				request.getRequestDispatcher("/error/noSecurity.jsp").forward(request, response);
				return false;
			}
		} else {
			request.getRequestDispatcher("/error/sessionTimeout.jsp").forward(request, response);
			return false;
		}
		return true;
	}

	public void checkToken(HttpServletRequest request, Object handler,String url) {
		// 从切点上获取目标方法
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		//若目标需要验证验签
		if(method.isAnnotationPresent(NeedSign.class)){
			checkSign(request);
		}
		// 若目标方法忽略了安全性检查，则直接调用目标方法
		if (!method.isAnnotationPresent(IgnoreSecurity.class)) {
			// 从 request header 中获取当前 token
			String token = request.getHeader(CommonContants.DEFAULT_TOKEN_NAME);
			String type = request.getHeader(CommonContants.DEFAULT_TYPE_NAME);
			int userType = 0;
			if(!StringUtils.isEmpty(type)){
				userType = Integer.parseInt(type);
			}
			// 查询访问权限
			if (url.indexOf("/webService/common/") == -1) {
				if(userType==UserType.BUSINESS.getValue()||userType==UserType.SHOP.getValue()){
					if(url.indexOf("/webService/business/")==-1){
						throw new ServiceException(CodeType.NO_AUTH.getLabel(),new Throwable(CodeType.NO_AUTH.getValue()+""));
					}
				}else if(userType==UserType.CUSTOMER.getValue()){
					if(url.indexOf("/webService/customer/")==-1){
						throw new ServiceException(CodeType.NO_AUTH.getLabel(),new Throwable(CodeType.NO_AUTH.getValue()+""));
					}
				}else{
					throw new ServiceException(CodeType.NO_AUTH.getLabel(),new Throwable(CodeType.NO_AUTH.getValue()+""));
				}
			}
			// 检查 token 有效性
			if (!tokenService.checkToken(token,userType)) {
				throw new ServiceException(CodeType.TOKEN_INVALID.getLabel(),new Throwable(CodeType.TOKEN_INVALID.getValue()+""));
			}
		}
	}
	
	/**
	 * url请求参数验签验证
	 * @param request
	 * @author LiQiang
	 * @date 2017年3月27日
	 */
	public void checkSign(HttpServletRequest request){
		Map<String,String> params = OkhttpRequest.getParameter(request);
		//排除微信自动添加的参数
		if(!StringUtils.isEmpty(params.get("state"))){
			params.remove("code");
			params.remove("state");
		}
		if(!MD5.checkSign(params)){
			throw new ServiceException(CodeType.ILLEGAL_VISIT.getLabel());
		}
	}
	
}
