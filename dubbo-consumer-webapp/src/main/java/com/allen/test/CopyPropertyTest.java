package com.allen.test;

import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

public class CopyPropertyTest {

		
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		Bean1 bean1 = new Bean1();
		bean1.setId(1L);
		bean1.setName("allen");
		
		Bean2 bean2 = new Bean2();
		BeanUtils.copyProperties(bean2, bean1);
		System.out.println(bean2.toString());
	}
}


class Bean1{
    private Long id;
	
    private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

class Bean2{
private Long id;
	
	private String name;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Bean2 [userId=" + id + ", name=" + name + "]";
	}
}
