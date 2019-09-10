package com.xyzq.zh.graph;

/**
 * 图形先广后深遍历队列
 * 
 * @author zhanghua
 *
 */
public class GraphQueue {
	
	/**
	 * 指向队列前端
	 */
	public int front = -1;
	/**
	 * 指向队列尾端
	 */
	public int rear = -1;
	
	public int[] queue;
	
	public GraphQueue(int size) {
		this.queue = new int[size];
	}
	
	/**
	 * 入队列
	 * @param value
	 */
	public void enqueue(int value) {
		if(rear >= queue.length) {// 队列已满
			return;
		} 
		queue[++rear] = value;
	}
	
	/**
	 * 出队列
	 * @return
	 */
	public int dequeue() {
		if(isEmpty()) {
			return -1;
		}
		return queue[++front];
	}
	
	public boolean isEmpty() {
		return front == rear;
	}
	
}
