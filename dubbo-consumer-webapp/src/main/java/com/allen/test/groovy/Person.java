package com.allen.test.groovy;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = -7898970346255065804L;
	
	private String name;
	private String address;
	private Integer age;
	    
    public Person(String name, String addr, Integer age){
        this.name = name;
        this.address = addr;
        this.age = age;
    }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", address=" + address + ", age=" + age + "]";
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
