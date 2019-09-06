package com.xyzq.zh.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

/**
 * 环形队列数据的存入和取出
 * 
 * @author zhanghua
 *
 */
public class CircleQueueTest {
	
	public int front = -1, rear = -1, max = 5;
	public int queue[] = new int[max];
	
	@Test
	public void testOpt() throws IOException {
		int val = 0;
		BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
		while(rear < max && val != -1) {
			System.out.print("请输入一个值存入队列， 要取出请输入0.（结束输入-1）：");
			val = Integer.parseInt(keyin.readLine());
			if(val == 0) {
				if(front == rear) {
					System.out.println("[队列已经空了]");
					break;
				} else if(++front == max) {// 环形队列头指针已经遍历一圈，将front指针的数值重置从0开始
					front = 0;
				}
				System.out.println("取出队列值[" + queue[front] + "]");
				queue[front] = 0;// 表示队列值被取出
			} else if(rear < max) {
				if(rear + 1 == front || (rear == max-1 && front <= 0)) {// 队列满
					System.out.println("[队列已经满了]");
					break;
				}
				if(++rear == max) {// 环形队列尾指针已经遍历一圈，将rear指针的数值重置从0开始
					rear = 0;
				}
				queue[rear] = val;// 存入新值
			}
		}
		
		System.out.println("队列剩余数据：");
		if(front == rear) {
			System.out.println("队列已经空了！");
		} else {
			while(front != rear) {
				if(++front == max) {
					front = 0;
				}
				System.out.print("[" + queue[front] + "]");
				queue[front] = 0;
			}
		}
	}
	
}
