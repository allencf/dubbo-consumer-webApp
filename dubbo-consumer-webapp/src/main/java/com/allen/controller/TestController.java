package com.allen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("consumer")
public class TestController {
	
	
	@RequestMapping(value ="/test" , method = RequestMethod.POST)
	@ResponseBody
	public String testMethod(){
	    JSONObject object = new JSONObject();
		object.put("executeStatus", 1);
		object.put("msg", "操作成功!!!");
		return object.toString();	
	}
	
	public static void main(String[] args) {
		
	}

}
