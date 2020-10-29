package com.allen.test.DataStructure.linkedImpl;

/**
 * 链表的节点对象
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2017年3月28日上午10:50:40
 */
public class Node<T> {
	
	//节点的数据内容
	private T data;
	//下一个节点
	private Node<T> next;
	//上一个节点(这个属性只有双向链表才可用)
	private Node<T> pre;
	
	public Node(T data, Node<T> next, Node<T> pre) {
		this.data = data;
		this.next = next;
		this.pre = pre;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public Node<T> getPre() {
		return pre;
	}

	public void setPre(Node<T> pre) {
		this.pre = pre;
	}


	public static void main(String[] args) {

	}

}
