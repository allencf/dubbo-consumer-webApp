package com.allen.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;

public class ExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) {
		ModelAndView result = new ModelAndView(); 
		result.addObject("executeStatus", 2);
		result.addObject("code", "00010100");
		result.addObject("msg", ex.getMessage());
		
		try {
			JSONObject object = new JSONObject();
			object.put("executeStatus", 2);
			object.put("code", "00010100");
			object.put("msg", ex.getMessage());
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(object.toString());
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

}
