package com.allen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.allen.dubbo.service.DubboTestService;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("consumer")
public class TestController extends BaseController{
	
	private final static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	//dubbo注解方式
	/*@Reference(version = "1.0.0")
	private DubboTestService dubboTestService;*/
	
	@Autowired
	private DubboTestService dubboTestService;
	
	@RequestMapping(value ="/test" , method = RequestMethod.POST)
	@ResponseBody
	public String testMethod(){
	    logger.info("开始调用testMethod方法------------------");
		
		JSONObject object = new JSONObject();
		object.put("executeStatus", 1);
		try {
			String msg = dubboTestService.sayHello();
			if(msg != null){
				object.put("msg", msg);
			}else{
				object.put("msg", "操作成功!!!");
			}
		} 
		catch (Exception e) {
			logger.info("操作异常,异常信息:" + e.getMessage(),e);
			object.put("msg", e.getMessage());
		}
		
		logger.info("结束调用testMethod方法----------------");
		return object.toString();	
	}
	
	@RequestMapping(value ="/test2" , method = RequestMethod.POST)
	@ResponseBody
	public String testMethod2(){
	    logger.info("开始调用testMethod方法------------------");
		
	    JSONObject object = new JSONObject();
		object.put("executeStatus", 1);
		try {
			String msg = dubboTestService.sayHello();
			if(msg != null){
				object.put("msg", msg);
			}else{
				object.put("msg", "操作成功!!!");
			}
		} 
		catch (Exception e) {
			logger.info("操作异常,异常信息:" + e.getMessage(),e);
			object.put("msg", e.getMessage());
		}
		
		logger.info("结束调用testMethod方法----------------");
		throw new RuntimeException("系统异常");		
		//return object.toString();	
	}
	
	
	public static void main(String[] args) {
		logger.info("test ================");
	}

}
