package com.xyzq.zh.linkedlist;

/**
 * 定义内部类“节点”
 * 
 * @author zhanghua
 *
 */
public class Node {
	int data;
	int score;
	String name;
	Node next;
	
	public Node(int data, String name, int score) {
		this.data = data;
		this.name = name;
		this.score = score;
		// 从链表尾部增加节点，默认next指向null（新增节点为新的last节点）
		this.next = null;
	}
}
