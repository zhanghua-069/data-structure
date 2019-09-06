package com.xyzq.zh.queue;

/**
 * 使用链表实现队列
 * 
 * @author zhanghua
 *
 */
public class QueueByLinkedList {
	
	/**
	 * 队列节点类
	 */
	class QueueNode {
		int data;
		QueueNode next;
		
		public QueueNode(int data) {
			this.data = data;
			next = null;
		}
	}
	
	/**
	 * 队列前端指针
	 */
	public QueueNode front;
	/**
	 * 队列尾端指针
	 */
	public QueueNode rear;
	
	/**
	 * 初始队列为空
	 */
	public QueueByLinkedList() {
		front = null;
		rear = null;
	}
	
	/**
	 * 队列存入数据
	 * 
	 * @param value
	 * @return
	 */
	public boolean enqueue(int value) {
		QueueNode node = new QueueNode(value);
		// 判断队列是否为空
		if(rear == null) {
			// 新建节点作为第一个节点
			front = node;
		} else {
			// 将新建节点加入到队列尾端
			rear.next = node;
		}
		rear = node;// 将尾端指针指向新加入的节点
		return true;
	}
	
	/**
	 * 队列数据取出
	 * 
	 * @return
	 */
	public int dequeue() {
		int value = -1;
		// 判断队列是否为空
		if(front != null) {
			// 取数已到尾指针，将rear设为null，表示队列已空
			if(front == rear) {
				rear = null;
			}
			// 将队列数据取出
			value = front.data;
			// 将前端指针指向下一个节点，前端指针此时已到尾指针时，front将会被设为null
			front = front.next;
		}
		return value;
	}
	
}
