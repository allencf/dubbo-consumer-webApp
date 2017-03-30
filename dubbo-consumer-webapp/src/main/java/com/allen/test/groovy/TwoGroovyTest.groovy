package com.allen.test.groovy


class TwoGroovyTest {
	
	def helloWithoutParam(){
		println "start to call helloWithoutParam!"
		return "success, helloWithoutParam";
	}
	
	
	def helloWithParam(person, id){
		println "start to call helloWithParam, param{person:" + person + ", id:" + id + "}";
		return "success, helloWithParam";
	}
	
	//用闭包定义一个方法 var1为参数 ,->后面是执行语句（当然参数不是必须的）
	def methodA = {
		var1-> println "this is methodA"
	}
	
	def methodB = {
		var1-> println "this is methodB"
	}
	
	//String.MetaClass.addMethodA = methodA;
	//String.MetaClass.addMethod = methodA;
	def String s = "a";
	//s.addMedthod();
	
	def run(){
		println "$test hello world"
	}
	
	
	static void main (def args) {
		println "hello world"
	}
	

}


