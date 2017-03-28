package com.allen.test.DataStructure.linkedImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;

/**
 * 模拟双向链表的实现
 * 双链表（双向链表）：双链表和单链表相比，多了一个指向尾指针（tail），
 * 双链表的每个结点都有一个头指针head和尾指针tail,双链表相比单链表更
 * 容易操作，双链表结点的首结点的head指向为null，tail指向下一个节点
 * 的tail;尾结点的head指向前一个结点的head，tail 指向为null，是双向的关系；
 * 
 * All rights Reserved, Designed By HQYG
 * Copyright:   Copyright(C) 2016
 * Company:     HQYG.
 * author:      cf
 * Createdate:  2017年3月28日上午10:06:01
 */
public class DoubleLinkedImpl<T> {
	
	private static final Logger logger = LoggerFactory.getLogger(DoubleLinkedImpl.class);
	
	//http://blog.csdn.net/a19881029/article/details/22898689
	//http://blog.csdn.net/Sun_Ru/article/details/51784058
	
	//头节点
	private Node<T> head;
	//尾节点
	private Node<T> trail;
	//计数器,计算元素的个数
	private int count;
	
    
	/**
	 * 构造函数 我们构造了一个带有头、尾节点的双向链表 头节点的Next指向尾节点 尾节点的pre指向头节点 链表长度起始为0。
	 */
    public DoubleLinkedImpl() {
		this.count = 0;
		this.head  = new Node<T>(null, trail, null);
		this.trail = new Node<T>(null, head, null);
	}
    
    
    /**
     * @description 添加元素方法
     * @param  item 需要添加的元素 
     */
    public void add(T item) {
    	logger.info("start execute add method,params :{}",JSON.toJSONString(item));
    	Node<T> node = new Node<T>(item, null, null);
    	trail.getPre().setNext(node);
        node.setPre(trail.getPre());
        node.setNext(trail);
        trail.setPre(node);
        count ++ ;
        logger.info("end execute add method");
    }


    public int size(){
    	return count;
    }
    

	public static void main(String[] args) {
	

	}

}
