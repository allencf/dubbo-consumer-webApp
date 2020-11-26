package com.allen.test.sf;

import org.apache.commons.lang3.ArrayUtils;

public class LianBiao {
	
	static int i = 1;
	
	
	public class ListNode {
		
		private int      value;
		
		private ListNode next;
		
		public ListNode() {
			
		}
		
		public ListNode(int value,ListNode node) {
			this.value = value;
			this.next  = node;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public ListNode getNext() {
			return next;
		}

		public void setNext(ListNode next) {
			this.next = next;
		}
		
	}
	
	
	
	public ListNode createListNode(int[] arr) {
		if (ArrayUtils.isEmpty(arr)) {
			return null;
		}
		ListNode head = null;
		ListNode next = null;
		for (int i = 0; i < arr.length; i++) {
			int value = arr[i];
			if (head == null) {
				head = new ListNode();
				next = new ListNode();
				head.value = value;
				head.next = next;
			} else {
				next.value = value;
				if (i == arr.length -1) {
					next.next = null;
				}else {
					ListNode node = new ListNode(); 
					next.next = node;
					next = node;
				}
			}
		}
		return head;
		
	}
	
	
	public void afterInsert(ListNode node,int data) {
		if (i <= 10) {
			ListNode newnode = new ListNode();
			//node.value    = data;
			newnode.value = data;
			newnode.next  = node.next;
			node.next = newnode;
			afterInsert(node, ++ i);
		}
	}
	
	public void beforeInsert(ListNode node ,int data) {
		if (i <= 10) {
			ListNode newnode = new ListNode();
			node.value = data;
			node.next  = newnode;
			newnode.next = null;
			beforeInsert(newnode, ++ i);
		}
	}
	
	
	/**
	 * 获取链表的倒数第N个节点
	 * @param head 头结点
	 * @param n    倒数第N个节点
	 * @return
	 */
	public ListNode getListNode(ListNode head,int n) {
		if (head == null || n == 0) {
			return null;
		}
		ListNode left  = head;
		ListNode right = head;
		for (int i = 1; i < n; i++) {
			right = right.next;
			if (right == null) {
				return null;
			}
		}
		while(right.next != null){
			right = right.next;
			left = left.next;
		}
		return left;
	}
	
	/**
	 * 删除链表位置为第N个的节点
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode deleteListNode(ListNode head,int n) {
		if (head == null || n <=0) {
			return null;
		}
		//int length = getNodeLength(head);
		ListNode pre = head;
		for (int i = 2; i < n; i++) {
			pre = pre.next;
		}
		pre.next = pre.next.next;
		return head;
	}
	
	/**
	 * 删除链表的倒数第N个元素
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode deleteListNode2(ListNode head,int n) {
		if (head == null || n <=0) {
			return null;
		}
		int length = getNodeLength(head);
		ListNode pre = head;
		for (int i = 2; i <= length -n ; i++) {
			pre = pre.next;
		}
		pre.next = pre.next.next;
		return head;
	}
	
	/**
	 * 获取链表的长度
	 * @param head
	 * @return
	 */
	public int getNodeLength(ListNode head) {
		if (head == null) {
			return 0;
		}
		int length = 1;
		while (head.next != null) {
			length ++;
			head = head.next;
		}
		return length;
	}
	
	

	public static void main(String[] args) {
		LianBiao lianbiao = new LianBiao();
		ListNode node = lianbiao.new ListNode();
		//lianbiao.afterInsert(node, 1);
		//lianbiao.beforeInsert(node, 1);
		int[] arr = {3,2,5,6,9,4};
		//生成一个链表
		node = lianbiao.createListNode(arr);
		//删除链表的第N个元素
		//node = lianbiao.deleteListNode(node, 6);
		//删除链表的倒数第N个元素
		node = lianbiao.deleteListNode2(node, 3);
		while(node !=null){
			System.out.println("value:" + node.value);
			node = node.next;
		}
		//System.out.println(lianbiao.getListNode(node,1).value);
	}

}
