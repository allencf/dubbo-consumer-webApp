package com.allen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class BaseController {
	
	private final static Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	
	@ExceptionHandler({Exception.class})
	@ResponseBody
	public JSON exceptionHandler(Exception e){
		JSONObject result = new JSONObject();
		result.put("executeStatus", 1);
		result.put("code", "00000000");
		result.put("msg", e.getMessage());
		return result;
	}

}
