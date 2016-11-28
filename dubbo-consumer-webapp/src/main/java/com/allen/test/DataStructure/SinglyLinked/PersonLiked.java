package com.allen.test.DataStructure.SinglyLinked;


/**
 * Person单向链表
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2016年11月28日上午10:57:37
 */
public class PersonLiked {
	
	/**
	 * 头节点
	 */
	private PersonNode head;
	
	/**
	 * 节点数量
	 */
	private Integer nodeSize = 0;
	
	
	/**
	 * 修改次数
	 */
	private Integer updateCount = 0;
	
	
	/**
	 * 添加节点
	 * @param person
	 */
	public void addNode(Person person){
		if(head == null){
			head = new PersonNode(person, null);
		}else{
			head = new PersonNode(person, head);
		}
		nodeSize++;
		updateCount++;
	}
	
	
	/**
	 * 遍历链表并打印出来
	 */
	public void printAllNode(){
		for (PersonNode node = head; node != null; node = node.getNextNode()) {
			Person person = node.getPerson();
			if(person == null)
			continue;
			System.out.println(person.toString());
		}
	}
	

	public static void main(String[] args) {
		PersonLiked link = new PersonLiked();
		
		Person p1 = new Person(1L,"allen1","allen",23);
		Person p2 = new Person(2L,"viney2","viney",25);
		Person p3 = new Person(3L,"tom3","tome",27);
		
		link.addNode(p1);
		link.addNode(p2);
		link.addNode(p3);
		
		link.printAllNode();
	
	}

}
