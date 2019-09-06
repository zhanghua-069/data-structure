package com.xyzq.zh.queue;

/**
 * 使用链表实现输入限制性的双向队列
 * 
 * @author zhanghua
 *
 */
public class DequeByLinkedList extends QueueByLinkedList {
	
	/**
	 * 双向队列的取数方法实现，action 1：从前端取数， 2：从尾端取数
	 * 
	 * @param action
	 * @return
	 */
	public int dequeue(int action) {
		// 从队列前端取数
		if(action ==1 && front != null) {
			if(front == rear) {// 当前队列只有一个节点
				rear = null;
			}
			int value = front.data;// 从前端指针取出队列数据
			front = front.next;// 将前端指针指向队列下一个元素
			return value;
		} 
		// 从队列尾端取数
		else if(action ==2 && rear != null) {
			QueueNode startNode = front;// 记录下前端节点的指针位置
			int value = rear.data;// 从尾指针获取队列数据
			
			QueueNode tempNode = front;
			// 从前端开始查找到尾节点的前一个节点（单向链表只能从头节点开始查找）
			while(front.next != rear && front.next != null) {
				front = front.next;
				tempNode = front;
			}
			front = startNode;// 将前端节点回位到一开始的头节点位置
			rear = tempNode;// 将尾端指针指向它的前一个节点
			
			if(front.next == null || rear.next == null) {
				// 当队列中仅剩一个节点时，取出数据后将front及rear指向null
				front = null;
				rear = null;
			}
			
			return value;
		}
		
		return -1;
	}
	
}
