package com.allen.test.DataStructure.linkedImpl;


/**
 * 模拟单向链表
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2016年11月14日下午5:12:20
 */
public class SingleLinkedImpl {
	
	//头节点
	Node head;
	
	/**
	 * 定义一个链表节点对象
	 * All rights Reserved, Designed By HQYG
	 * Copyright:   Copyright(C) 2016
	 * Company:     HQYG.
	 * author:      cf
	 * Createdate:  2016年11月14日下午5:12:41
	 */
	class Node{
		
		Node nextNode;
		String content;
		
		public Node getNextNode() {
			return nextNode;
		}
		
		public void setNextNode(Node nextNode) {
			this.nextNode = nextNode;
		}
		
		public String getContent() {
			return content;
		}
		
		public void setContent(String content) {
			this.content = content;
		}

		public Node(String content) {
			super();
			this.content = content;
		}

		@Override
		public String toString() {
			return "Node [nextNode=" + nextNode + ", content=" + content + "]";
		}
	}

	
	/**
	 * 向链表中添加节点
	 * @param content
	 */
	public void addNode(String content){
		Node node = new Node(content);
		if(head == null){
			head = node;
			return;
		}
		
		Node temp = head;
		while(temp.nextNode != null){
			temp = temp.nextNode;
		}
		temp.nextNode = node;
		System.out.println("head node :" + head.toString());
	}
	
	
	//打印出链表信息
	public void printList(){
		Node headNode = head;
		while(headNode != null){
			System.out.println("node content:" + headNode.getContent() );
			if(headNode.getNextNode() != null) {
				System.out.println("next node content:" + headNode.getNextNode().getContent());
			}
			headNode = headNode.nextNode;
		}
	}
	
	
	public static void main(String[] args) {
		SingleLinkedImpl link = new SingleLinkedImpl();
		link.addNode("1");
		link.addNode("2");
		link.addNode("3");
		link.addNode("4");
		link.addNode("5");
		link.printList();
		
		//System.out.println("link head data:" + link.head.getContent());
		if(link.head.getNextNode() != null){
			//System.out.println("link head nextNode content:" + link.head.getNextNode().getContent());
		}
		//LinkedList<E>

	}

}
