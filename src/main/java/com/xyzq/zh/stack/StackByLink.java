package com.xyzq.zh.stack;

/**
 * 堆栈的链表实现
 * 
 * @author zhanghua
 *
 */
public class StackByLink {
	
	/**
	 * 定义栈内节点
	 * @author zhanghua
	 *
	 */
	class Node {
		int data;
		Node next;
		
		public Node(int data) {
			this.data = data;
			this.next = null;
		}
	}
	
	/**
	 * 指向栈底部的指针
	 */
	public Node front;
	/**
	 * 指向栈顶端的指针
	 */
	public Node top;
	
	/**
	 * 判断堆栈是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return front == null;
	}
	
	/**
	 * 打印堆栈内容
	 */
	public void printOfStack() {
		Node current = front;
		while(current != null) {
			System.out.print("[" + current.data + "]");
			current = current.next;
		}
		System.out.println();
	}
	
	/**
	 * 栈顶压入数据
	 * 
	 * @param data
	 */
	public void push(int data) {
		Node newNode = new Node(data);	
		if(isEmpty()) {
			front = newNode;
			top = newNode;
		} else {
			top.next = newNode;
			top = newNode;
		}
	}
	
	/**
	 * 栈顶删除数据
	 */
	public void pop() {
		if(isEmpty()) {
			System.out.println("===目前为空栈===");
		} else {
			Node newNode = front;
			if(newNode == top) {
				front = null;
				top = null;
				System.out.println("===目前为空栈===");
			} else {
				while(newNode.next != top) {
					newNode = newNode.next;
				}
				newNode.next = top.next;
				top = newNode;
			}
		}
	}
	
}
