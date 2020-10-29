package com.allen.test.groovy.support;

import java.io.File;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;


/**
 * DynamicLoadGroovy的方法是通过定义好接口来动态载入,如果没有接口怎么办...办法还是有的,那就是传说中的反射..Groovy也支持反射哦
 * 通过反射,动态载入并使用Groovy类
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2017年3月30日下午2:24:39
 */
public class DynamicReflectGroovy {

	private GroovyObject groovyObject; 
	
	public Object getProperty(String name) { 
		return groovyObject.getProperty(name); 
	} 
	
	@SuppressWarnings({ "rawtypes", "resource" })
	public Object invokeScriptMethod(String scriptName, String methodName, Object[] args) { 
		ClassLoader parent = getClass().getClassLoader(); 
		GroovyClassLoader loader = new GroovyClassLoader(parent); 
		try { 
			Class groovyClass = loader.parseClass(new File(scriptName)); 
			//loader.parseClass("");
			groovyObject = (GroovyObject) groovyClass.newInstance(); 
			return groovyObject.invokeMethod(methodName, args); 
		} catch (Exception e) { 
			e.printStackTrace(); 
			return null; 
		} 
	} 
	
	public static void main(String[] args) { 
		DynamicReflectGroovy dynamicGroovy = new DynamicReflectGroovy(); 
		Object[] params = {new Integer(2)}; 
		Object result = dynamicGroovy.invokeScriptMethod("src/main/java/com/allen/test/groovy/temple/groovyfile/Foo.groovy", "run", params); 
		System.out.println(result); 
		 
	} 

}
