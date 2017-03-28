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
	
	
	
	static void main (def args) {
		println "hello world"
	}
	

}


