package com.allen.test.groovy.support;

import java.io.File;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.allen.test.groovy.temple.IFoo;
import groovy.lang.GroovyClassLoader;


/**
 * 
 * 动态载入Groovy类，创建接口实例，调用接口中定义的方法
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2017年3月30日下午2:11:46
 */
public class DynamicLoadGroovy {
	
	//http://blog.csdn.net/wangwei_cq/article/details/9833835
	
	private static final Logger logger = LoggerFactory.getLogger(DynamicLoadGroovy.class);
	
	
	/**
	 * 加载groovy文件
	 * @param filePath 文件路径
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	public static <T> T loadGroovy(Class<T> classType, String filePath){
		if(classType == null) {
			logger.info("classType is null");
			return null;
		}
		if(StringUtils.isBlank(filePath)) {
			logger.info("file path is null");
			return null;
		}
		try {
			ClassLoader c = new DynamicLoadGroovy().getClass().getClassLoader();
			GroovyClassLoader gc = new GroovyClassLoader(c);
			Class<T> groovyClass = gc.parseClass(new File(filePath));
			T t = groovyClass.newInstance();
			return t;
		} catch (Exception e) {
			logger.error("loadGroovy error",e);
			return null;
		} finally {
			
		}
	}
	
	

	public static void main(String[] args) {
		IFoo foo = (IFoo) loadGroovy(IFoo.class, "src/main/java/com/allen/test/groovy/temple/groovyfile/Foo.groovy");
		System.out.println(foo.run(new Integer(2)));

	}

}
