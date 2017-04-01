package com.allen.test.groovy;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.alibaba.dubbo.common.utils.IOUtils;
import com.allen.test.groovy.temple.IFoo;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;

/**
 * groovy 学习
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2017年3月28日下午5:41:49
 */
public class JavaTest {

	//https://my.oschina.net/davidzhang/blog/89654
	//http://www.open-open.com/lib/view/open1453261212042.html
	
	
	private static final String GROOVY_FILE_LOCATION = "src/main/java/com/allen/test/groovy/temple/groovyfile/Foo.groovy";
	
	/**
	 * 简答脚本执行   GroovyShell 执行
	 * @throws Exception
	 */
	public static void evalScriptText() throws Exception{  
	    //groovy.lang.Binding  
	    Binding binding = new Binding();  
	    GroovyShell shell = new GroovyShell(binding);  
	      
	    binding.setVariable("name", "zhangsan");  
	    shell.evaluate("println 'Hello World! I am ' + name;");  
	    //在script中,声明变量,不能使用def,否则scrope不一致.  
	    shell.evaluate("date = new Date();");  
	    Date date = (Date)binding.getVariable("date");  
	    System.out.println("Date:" + date.getTime());  
	    //以返回值的方式,获取script内部变量值,或者执行结果  
	    //一个shell实例中,所有变量值,将会在此"session"中传递下去."date"可以在此后的script中获取  
	    Long time = (Long)shell.evaluate("def time = date.getTime(); return time;");  
	    System.out.println("Time:" + time);  
	    binding.setVariable("list", new String[]{"A","B","C"});  
	    //invoke method  
	    String joinString = (String)shell.evaluate("def call(){return list.join(' - ')};call();");  
	    System.out.println("Array join:" + joinString);  
	    shell = null;  
	    binding = null;  
	}  
	
	
	/** 
	 * 伪main方法执行
	 * 当groovy脚本,为完整类结构时,可以通过执行main方法并传递参数的方式,启动脚本. 
	 */  
	public static void evalScriptAsMainMethod(){  
	    String[] args = new String[]{"Zhangsan","10"};//main(String[] args)  
	    Binding binding = new Binding(args);  
	    GroovyShell shell = new GroovyShell(binding);  
	    shell.evaluate("static void main(String[] args){ if(args.length != 2) return;println('Hello,I am ' + args[0] + ',age ' + args[1])}");  
	    shell = null;  
	    binding = null;  
	}  
	
	
	
	/** 
	 * 运行完整脚本 
	 * @throws Exception 
	 */  
	public static void evalScriptTextFull() throws Exception{  
	    StringBuffer buffer = new StringBuffer();  
	    //define API  
	    buffer.append("class User{")  
	            .append("String name;Integer age;")  
	            //.append("User(String name,Integer age){this.name = name;this.age = age};")  
	            .append("String sayHello(){return 'Hello,I am ' + name + ',age ' + age;}}\n");  
	    //Usage  
	    buffer.append("def user = new User(name:'zhangsan',age:1);")  
	            .append("user.sayHello();");  
	    //groovy.lang.Binding  
	    //System.out.println(buffer.toString());
	    Binding binding = new Binding();  
	    GroovyShell shell = new GroovyShell(binding);  
	    String message = (String)shell.evaluate(buffer.toString());  
	    System.out.println(message);  
	    //重写main方法,默认执行  
	    String mainMethod = "static void main(String[] args){def user = new User(name:'lisi',age:12);print(user.sayHello());}";  
	    shell.evaluate(mainMethod);  
	    shell = null;  
	}  
	
	
	/** 
	 * 以面向"过程"的方式运行脚本 
	 * @throws Exception 
	 */  
	public static void evalScript() throws Exception{  
	    Binding binding = new Binding();  
	    GroovyShell shell = new GroovyShell(binding);  
	    //直接方法调用  
	    //shell.parse(new File(//))  
	    Script script = shell.parse("def join(String[] list) {return list.join('--');}");  
	    String joinString = (String)script.invokeMethod("join", new String[]{"A1","B2","C3"});  
	    System.out.println(joinString);  
	    //脚本可以为任何格式,可以为main方法,也可以为普通方法  
	    //1) def call(){...};call();  
	    //2) call(){...};  
	    script = shell.parse("static void main(String[] args){i = i * 2;}");  
	    script.setProperty("i", new Integer(10));  
	    script.run();//运行,  
	    System.out.println(script.getProperty("i"));  
	    //the same as  
	    System.out.println(script.getBinding().getVariable("i"));  
	    script = null;  
	    shell = null;  
	}  
	
	
	/**
	 * GroovyClassLoad 加载
	 * @throws Exception
	 * http://www.blogjava.net/hsith/archive/2006/04/28/43790.html
	 */
	@SuppressWarnings({ "rawtypes", "unused", "resource" })
	public static void parseGroovyClassLoad() throws Exception{  
	    String script = "public Object run(Object foo) { return foo*10;}";
		GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());  
	    File sourceFile = new File(GROOVY_FILE_LOCATION);//文本内容的源代码   //new GroovyCodeSource(sourceFile)
	    Class testGroovyClass = classLoader.parseClass(script);  
	    GroovyObject instance = (GroovyObject)testGroovyClass.newInstance();//proxy  
	    Object[] arg = {new Integer(10)};
	    
	    Object obj = instance.invokeMethod("run", arg);  
	    System.out.println(obj);  
	    //Date date = (Date)instance.invokeMethod("getDate", time);  
	    //System.out.println(date.getTime());  
	    //here  
	    instance = null;  
	    testGroovyClass = null;  
	}  
	
	
	/**
	 * 如何加载已经编译的groovy文件(.class)
	 * @throws Exception
	 */
	public static void load() throws Exception {  
	    GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());  
	    BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\TestGroovy.class"));  
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();  
	    for(;;){  
	        int i = bis.read();  
	        if( i == -1){  
	            break;  
	        }  
	        bos.write(i);  
	    }  
	    Class testGroovyClass = classLoader.defineClass(null, bos.toByteArray());  
	    //instance of proxy-class  
	    //if interface API is in the classpath,you can do such as:  
	    //MyObject instance = (MyObject)testGroovyClass.newInstance()  
	    GroovyObject instance = (GroovyObject)testGroovyClass.newInstance();  
	    Long time = (Long)instance.invokeMethod("getTime", new Date());  
	    System.out.println(time);  
	    Date date = (Date)instance.invokeMethod("getDate", time);  
	    System.out.println(date.getTime());  
	      
	    //here  
	    bis.close();  
	    bos.close();  
	    instance = null;  
	    testGroovyClass = null;  
	}  
	
	
	/**
	 * scriptEngine 加载
	 * @throws Exception
	 */
	public static void evalScriptEngine() throws Exception{  
	    ScriptEngineManager factory = new ScriptEngineManager();  
	    //每次生成一个engine实例  
	    ScriptEngine engine = factory.getEngineByName("groovy");  
	    System.out.println(engine.toString());  
	    assert engine != null;  
	    //javax.script.Bindings  
	    Bindings binding = engine.createBindings();  
	    binding.put("date", new Date());  
	    //如果script文本来自文件,请首先获取文件内容  
	    engine.eval("def getTime(){return date.getTime();}",binding);  
	    engine.eval("def sayHello(name,age){return 'Hello,I am ' + name + ',age' + age;}");  
	    Long time = (Long)((Invocable)engine).invokeFunction("getTime", null);  
	    System.out.println(time);  
	    String message = (String)((Invocable)engine).invokeFunction("sayHello", "zhangsan",new Integer(12));  
	    System.out.println(message);  
	}  
	
	
	/**
	 * 不带参数的方法调用
	 */
	@SuppressWarnings("rawtypes")
	public static void testGroovy2(){
	    try {  
	    	GroovyScriptEngine groovyScripEngine = new GroovyScriptEngine("");
	        Class scriptClass = groovyScripEngine.loadScriptByName("src/main/java/com/allen/test/groovy/TwoGroovyTest.groovy");
	        GroovyObject scriptInstance = (GroovyObject)scriptClass.newInstance();
	        Object ret = scriptInstance.invokeMethod("helloWithoutParam", null);
	        System.out.println("testGroovy2:" + ret);
	    } catch (Exception e) {  
	        e.printStackTrace();  
	        System.out.println("Exception e="+e.toString());  
	    } 
	}
	
	
	/**
	 * 带参数的方法调用
	 */
	@SuppressWarnings({ "rawtypes" })
	public static void testGroovy3(){
	    try {  
	        Person person = new Person("allen", "shenzhen", 18);
	        GroovyScriptEngine groovyScriptEngine = new GroovyScriptEngine("");
	        Class scriptClass = groovyScriptEngine.loadScriptByName("src/main/java/com/allen/test/groovy/TwoGroovyTest.groovy");
	        GroovyObject scriptInstance = (GroovyObject)scriptClass.newInstance();
	        Object ret = scriptInstance.invokeMethod("helloWithParam", new Object[]{person,"lxi"}); 
	        System.out.println("testGroovy3:" + ret);
	    } catch (Exception e) {  
	        e.printStackTrace();  
	        System.out.println("Exception e="+e.toString());  
	    } 
	}
	
	
	
	public static void testLoad(String location) {
		ResourcePatternResolver resource = new PathMatchingResourcePatternResolver();
		try {
			Resource[] resources = resource.getResources(location);
			for (Resource r : resources) {
				System.out.println("filename: " + r.getFilename());
				System.out.println("filecontent" + com.alibaba.fastjson.util.IOUtils.toString(r.getInputStream()));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void main(String[] args) throws Exception{
		//GroovyShell执行
		//evalScriptText();
		
		//GroovyShell伪main方法执行
		//evalScriptAsMainMethod();
		
		//evalScriptTextFull();
		//evalScript();
		
		//testGroovy2();
		//testGroovy3();
		//直接调用编译成class后的对象
		//TwoGroovyTest test = new TwoGroovyTest();
		//test.helloWithoutParam();
		
		/*Binding bind = new Binding();
		bind.setVariable("test", "allen");
		TwoGroovyTest script = new TwoGroovyTest();
		
		Map<String, Object> map = new ConcurrentHashMap<>();*/
		
		//parseGroovyClassLoad();
		testLoad("f:\\groovy");
		
	}

}
