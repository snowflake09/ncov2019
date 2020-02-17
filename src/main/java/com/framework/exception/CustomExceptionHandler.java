package com.framework.exception;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.utils.easyui.ResponseJson;
import com.utils.em.CodeType;

public class CustomExceptionHandler implements HandlerExceptionResolver {

	private static Logger logger = LogManager.getLogger(CustomExceptionHandler.class.getName());

	@Override
	@ResponseBody
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		logger.error("exception : " ,ex);
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Object a = handlerMethod.getMethodAnnotation(ResponseBody.class);
		if (a == null) {
			Map<String, Object> model = new HashMap<String, Object>();
			if (ex instanceof ServiceException) {
				model.put("ex", ex.getMessage());
			}else {
				model.put("ex", "提交内容有误");
			}
			return new ModelAndView("/error", model);
		} else {
			try {
				ResponseJson json = new ResponseJson();
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html");
				PrintWriter writer = response.getWriter();
				if (ex instanceof ServiceException) {
					if(ex.getCause()!=null){
						json.failure(Integer.parseInt(ex.getCause().getMessage()), ex.getMessage());
					}else{
						json.failure(CodeType.FAIL.getValue(), ex.getMessage());
					}
				}else {
					json.failure(CodeType.FAIL.getValue(), "提交内容有误");
				}
				writer.write(JSON.toJSONString(json));
				writer.flush();
			} catch (Exception e) {
				
			}
			return new ModelAndView();
		}

	}

}
