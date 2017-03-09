package com.allen.test.groovy

import java.lang.annotation.Repeatable

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter

/**
 *
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2017年3月9日上午10:41:06
 */
class OneGroovyTest {
	
	def name
	
	def age 
	
	String toString(){
		"$name,$age"	
	}
	
	
	def var = "hello , viney"
	
	void repeat(var){
		for(i=1;i<5;i++){
			println var
		}
	}
	
	def ot = new OneGroovyTest('name':'viney','age':19);
	//ot.toString();
	//ot.repeat(var)
	
	//repeat(var2)
	
	static void main(def args){
	  
		 println "hello world"
		 
		 println "groovy"
		 
		 //定义变量,用def关键字
		 def var = "hi ,allen"
		 
		 println var
		 
		//打印出变量类型
		 println var.class
		 
		 def var2 = """hi,
		 allen
		 hello"""
		 
		println var2
		
		var2 = 100
		
		println var2
		println var2.class
		
		def collection = ["1","2","3"];
		println collection[4]
		
		def OneGroovyTest test1 = new OneGroovyTest();
		test1.name = "allen"
		test1.age  =18
		println test1
		
		def OneGroovyTest test2 = new OneGroovyTest('name':'allen','age':28);
		println test2
		
		//repeat(test2);
		
    }

}


