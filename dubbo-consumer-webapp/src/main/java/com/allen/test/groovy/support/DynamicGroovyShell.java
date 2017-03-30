package com.allen.test.groovy.support;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * 动态的载入Groovy脚本
 * 动态载入脚本就更简单了,因为Groovy提供了一个方法evaluate,,和JavaScript的eval很像
 * 通过evaluate一个字符串就行了,evaluate还支持脚本文件和类文件哦....功能很强大的.
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2017年3月30日下午2:34:21
 */
public class DynamicGroovyShell {

		public Object doit() { 
			Binding bb = new Binding(); 
			bb.setVariable("test", "hello world!"); 
			GroovyShell gs = new GroovyShell(bb); 
			return gs.evaluate("println test"); 
		}
		
		/** 
		* @param args 
		*/ 
		public static void main(String[] args) { 
			DynamicGroovyShell te = new DynamicGroovyShell(); 
			te.doit();
		}

}
