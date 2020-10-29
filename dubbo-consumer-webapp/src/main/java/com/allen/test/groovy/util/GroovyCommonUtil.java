package com.allen.test.groovy.util;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import groovy.lang.GroovyObject;
import groovy.util.GroovyScriptEngine;

/**
 * GroovyCommonUtil操作的一个工具类
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2017年3月10日上午9:08:44
 */
public class GroovyCommonUtil {
	
	//eclipse安装groovy插件
	//http://blog.csdn.net/qq_27645299/article/details/50250723
	//进入https://github.com/groovy/groovy-eclipse/wiki查找和自己eclipse对应版本的groovy

	
	private static final Logger log = LoggerFactory.getLogger(GroovyCommonUtil.class);
    //该变量用于指明groovy脚本所在的父目录
    static String root[]=new String[]{"bin/groovy/"};  
    static GroovyScriptEngine groovyScriptEngine;  
    
    static{
        try {
            groovyScriptEngine=new GroovyScriptEngine(root);
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }
    
    /**
     *  用于调用指定Groovy脚本中的指定方法 
     * @param scriptName    脚本名称
     * @param methodName    方法名称
     * @param params        方法参数
     * @return
     */
	@SuppressWarnings({ "rawtypes"})
	public Object invokeMethod(String scriptName, String methodName, Object... params) throws Exception{
	    Object ret = null;
	    Class scriptClass = null;
	    GroovyObject scriptInstance = null;
	    
	    try {
	        scriptClass = groovyScriptEngine.loadScriptByName(scriptName);
	        scriptInstance = (GroovyObject)scriptClass.newInstance();
	    } catch (Exception e1) {
	        log.warn("加载脚本["+scriptName+"]出现异常", e1);
	        throw new Exception("加载脚本"+scriptName+"失败");
	    }
	
	    try {
	        ret = (String)scriptInstance.invokeMethod(methodName, params);
	    } catch (IllegalArgumentException e) {
	        log.warn("执行方法" + methodName + "参数出现异常, 参数为" + params, e);
	        throw new Exception("调用方法[" + methodName + "]失败，因参数不合法");
	    } catch(Exception e){
	        log.warn("执行方法" + methodName + "出现异常", e);
	        throw new Exception("调用方法[" + methodName + "]失败");
	    }
	    return ret;
	}
	
	
	public static void main(String[] args) {
		

	}

}
