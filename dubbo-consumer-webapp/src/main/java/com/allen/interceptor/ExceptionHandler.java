package com.allen.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) {
		ModelAndView result = new ModelAndView(); 
		result.addObject("executeStatus", 2);
		result.addObject("code", "00010100");
		result.addObject("msg", ex.getMessage());
		return result;
	}

}
