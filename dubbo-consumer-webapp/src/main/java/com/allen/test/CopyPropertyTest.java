package com.allen.test;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.beanutils.BeanUtils;

public class CopyPropertyTest {

		
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		Bean1 bean1 = new Bean1();
		bean1.setId(1L);
		bean1.setName("allen");
		
		Bean2 bean2 = new Bean2();
		BeanUtils.copyProperties(bean2, bean1);
		System.out.println(bean2.toString());
		
		
		ReentrantLock look = new ReentrantLock();
		Condition c1 = look.newCondition();
		
		//加锁
		look.lock();
		
		try {
			//阻塞线程
			c1.await();
			
			//响应中断
			look.lockInterruptibly();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//唤醒线程
			c1.signal();
			
			//解锁
			look.unlock();
		}
		
		
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
