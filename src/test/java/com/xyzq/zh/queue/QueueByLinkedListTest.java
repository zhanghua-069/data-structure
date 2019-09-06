package com.xyzq.zh.queue;

import org.junit.Test;

/**
 * 链表实现队列基本操作检查
 * 
 * @author zhanghua
 *
 */
public class QueueByLinkedListTest {
	
	@Test
	public void testOpt() {
		QueueByLinkedList queue = new QueueByLinkedList();
		System.out.println("用链表来实现队列");
		System.out.println("==============================");
		
		System.out.println("在队列前端加入第1个数据，此数据值为1");
		queue.enqueue(1);
		System.out.println("在队列前端加入第2个数据，此数据值为3");
		queue.enqueue(3);
		System.out.println("在队列前端加入第3个数据，此数据值为5");
		queue.enqueue(5);
		System.out.println("在队列前端加入第4个数据，此数据值为7");
		queue.enqueue(7);
		System.out.println("在队列前端加入第5个数据，此数据值为9");
		queue.enqueue(9);
		System.out.println("==============================");
		
		while(queue.front != null) {
			System.out.println("从队列前端依次取出的数据值为：" + queue.dequeue());
		}
	}
	
	/**
	 * 双向队列的存取数操作
	 */
	@Test
	public void testDequeOpt() {
		DequeByLinkedList queue = new DequeByLinkedList();
		System.out.println("以链表来实现双向队列");
		System.out.println("==============================");
		System.out.println("在队列前端加入第1个数据，此数据值为1");
		queue.enqueue(1);
		System.out.println("在队列前端加入第2个数据，此数据值为3");
		queue.enqueue(3);
		System.out.println("在队列前端加入第3个数据，此数据值为5");
		queue.enqueue(5);
		System.out.println("在队列前端加入第4个数据，此数据值为7");
		queue.enqueue(7);
		System.out.println("在队列前端加入第5个数据，此数据值为9");
		queue.enqueue(9);
		System.out.println("==============================");
		
		System.out.println("从双向队列前端依次取出的数据值为：" + queue.dequeue(1));
		System.out.println("从双向队列尾端依次取出的数据值为：" + queue.dequeue(2));
		System.out.println("从双向队列前端依次取出的数据值为：" + queue.dequeue(1));
		System.out.println("从双向队列尾端依次取出的数据值为：" + queue.dequeue(2));
		System.out.println("从双向队列前端依次取出的数据值为：" + queue.dequeue(1));
//		System.out.println("从双向队列尾端依次取出的数据值为：" + queue.dequeue(2));
	}
	
}
