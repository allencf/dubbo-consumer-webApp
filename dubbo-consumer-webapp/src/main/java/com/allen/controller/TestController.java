/*package com.allen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.allen.commons.event.annotation.ClassEvent;
import com.allen.commons.event.annotation.MethodEvent;
import com.allen.dubbo.service.DubboTestService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;


@Controller
@RequestMapping("consumer")
@ClassEvent
public class TestController {
	
	private final static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	private static ClassPathXmlApplicationContext context;
	
	@Value("${zookeeper.registry.address}")
	String zkAddress;
	
	//dubbo注解方式
	@Reference(version = "1.0.0")
	private DubboTestService dubboTestService;
	
	@Autowired
	private DubboTestService dubboTestService;
	
	@MethodEvent
	@RequestMapping(value ="/test" , method = RequestMethod.POST)
	@ResponseBody
	public JSON testMethod(){
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
			object.put("param", zkAddress);
		} 
		catch (Exception e) {
			logger.info("操作异常,异常信息:" + e.getMessage(),e);
			object.put("msg", e.getMessage());
		}
		
		logger.info("结束调用testMethod方法----------------");
		return JSONSerializer.toJSON(object.toString());	
	}
	
	@RequestMapping(value ="/test2" , method = RequestMethod.POST)
	//@ResponseBody
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
		context = new ClassPathXmlApplicationContext("classpath:application-context.xml");
		context.start();
		DubboTestService testService = (DubboTestService) context.getBean("dubboTestService");
		String msg = testService.sayHello();
		System.out.println(msg);
		logger.info("test ================");
	}

}
*/