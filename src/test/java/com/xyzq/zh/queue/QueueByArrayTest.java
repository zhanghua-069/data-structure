package com.xyzq.zh.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

/**
 * 数组实现队列存取
 * 
 * @author zhanghua
 *
 */
public class QueueByArrayTest {
	
	/**
	 * front与rear预设为-1，表示队列为空
	 */
	private int front = -1, rear = -1, max = 20;
	
	private int queue[] = new int[max];
	
	@Test
	public void testOpt() throws IOException {
		int m = 0;
		BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
		while(rear < max -1 && m != 3) {
			System.out.println("[1]存入一个数值[2]取出一个数值[3]结束");
			m = Integer.parseInt(keyin.readLine());
			switch(m) {
			case 1: 
				// 存入数值
				System.out.println("[请输入数值]：");
				int val = Integer.parseInt(keyin.readLine());
				queue[++rear] = val;
				break;
			case 2:
				// 取出数值
				if(rear > front) {
					System.out.println("[取出数值为]：[" + queue[++front] + "]");
					// 表示数值已被取出
					queue[front] = 0;
				} else {
					System.out.println("[队列已经空了]");
				}
				break;
			default:
				// 输出空，跳过
				System.out.println();
				break;
			}
		}
		
		if(rear == max - 1) {
			System.out.println("[队列已经满了]");
		}
		System.out.println("[目前队列中的数据]：");
		if(front >= rear) {
			System.out.println("没有\n[队列已经空了]");
		}
		while(rear > front) {
			System.out.print("[" + queue[++front] + "]");
		}
		System.out.println();
		
	}
	
}
