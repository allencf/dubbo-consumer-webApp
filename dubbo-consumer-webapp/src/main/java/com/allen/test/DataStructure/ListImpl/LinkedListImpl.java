package com.allen.test.DataStructure.ListImpl;

public class LinkedListImpl {
	
	//http://www.cnblogs.com/ysw-go/p/5383498.html
	
	//定义一个头节点
	Node head;
	//定义一个尾节点
	Node trail;
	//计数器,计算元素的数量
	int count;
	
	public void addVal(Object obj) {
		Node node = new Node();
		node.content = obj;
		//当链表为空时,设置head为node,trail也为node
		if(head == null) {
			head = node;
			trail = node;
		}
		
		trail.next = node;
		trail = node;
		count ++ ;
		return ;
	}
	
	//节点内部类
	class Node{
		Object content;
		Node next;
	}
	
	public int size(){
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
