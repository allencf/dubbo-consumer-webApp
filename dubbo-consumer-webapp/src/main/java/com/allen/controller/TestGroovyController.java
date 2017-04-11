package com.allen.controller;

import java.io.IOException;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allen.test.Test;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
@RequestMapping("/groovy")
public class TestGroovyController {

	
	@SuppressWarnings({ "rawtypes", "resource" })
	@RequestMapping(value = "/testGroovy", method = RequestMethod.POST)
	@ResponseBody
	public JSON testGroovy(){
		try {
			GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
			Class c = classLoader.parseClass(loadGroovyScript());
			Object[] arg = {new Integer(10)};
			GroovyObject instance = (GroovyObject)c.newInstance();//proxy  
		    Object obj = instance.invokeMethod("run", arg);  
		    System.out.println(obj);  
		    instance = null;  
		    c = null;
		    JSONObject result = new JSONObject();
		    result.put("obj", obj);
		    return JSONSerializer.toJSON(result);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value = "/testJson" , method = RequestMethod.POST)
	@ResponseBody
	public JSON testJSON(){
		JSONObject object = new JSONObject();
		
		Test test = new Test();
		test.setUpdateTime(null);
		object.put("data", test);
		
		JSON json = JSONSerializer.toJSON(object);
		System.out.println(json);
		
		return json;
	}
	
	
	private String loadGroovyScript() throws IOException{
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("classpath*:/rule/groovy/Foo.groovy");
		for (Resource resource : resources) {
			String script = org.apache.commons.io.IOUtils.toString(resource.getInputStream(), "utf-8");
			if(StringUtils.isNotBlank(script))
				return script;
		}
		return null;
	}
	
	
	public static void main(String[] args) {


	}

}
