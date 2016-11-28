package com.allen.test.DataStructure.SinglyLinked;

import java.io.Serializable;

/**
 * 定义一个Person对象
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2016年11月28日上午10:42:59
 */
public class Person implements Serializable{
	
	private static final long serialVersionUID = 9082423726809541612L;

	private Long id;
	
	private String personNo;
	
	private String name;
	
	private Integer age;

	public Long getId() {
		return id;
	}

	public String getPersonNo() {
		return personNo;
	}
	
	public void setPersonNo(String personNo) {
		this.personNo = personNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", personNo=" + personNo + ", name=" + name + ", age=" + age + "]";
	}
	
	public Person() {
	}

	public Person(Long id, String personNo, String name, Integer age) {
		this.id = id;
		this.personNo = personNo;
		this.name = name;
		this.age = age;
	}

	public static void main(String[] args) {
		Person p = new Person();
		p.setId(12L);
		p.setAge(23);
		p.setName("allen");
		System.out.println(p.toString());
	}
	
	

}
