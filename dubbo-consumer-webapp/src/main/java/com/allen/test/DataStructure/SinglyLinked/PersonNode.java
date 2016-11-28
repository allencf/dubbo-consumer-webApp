package com.allen.test.DataStructure.SinglyLinked;


/**
 * 定义一个链表的节点对象
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2016年11月28日上午10:40:18
 */
public class PersonNode {
	
	/**
	 * 节点的数据对象
	 */
	private Person person;
	
	/**
	 * 节点对下一个节点的引用
	 */
	private PersonNode nextNode;
	

	public Person getPerson() {
		return person;
	}
	
	public PersonNode(Person person, PersonNode nextNode) {
		this.person = person;
		this.nextNode = nextNode;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public PersonNode getNextNode() {
		return nextNode;
	}

	public void setNextNode(PersonNode nextNode) {
		this.nextNode = nextNode;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
