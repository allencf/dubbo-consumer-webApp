package com.allen.test.groovy.temple.groovyfile

import java.util.List

import com.allen.test.groovy.temple.IFoo

import groovy.util.IFileNameFinder

class Foo implements IFoo {

	@Override
	public Object run(Object foo) {
		println "hello world"
		return foo*10;
	}


	static main(args) {
	
	}

}
