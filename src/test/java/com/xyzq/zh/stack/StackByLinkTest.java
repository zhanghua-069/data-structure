package com.xyzq.zh.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class StackByLinkTest {
	
	/**
	 * 模拟栈的基本操作
	 * @throws IOException 
	 */
	@Test
	public void testStackOpt() throws IOException {
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		StackByLink stack = new StackByLink();
		
		while(true) {
			System.out.print("(0)结束(1)在堆栈中加入数据(2)弹出堆栈数据：");
			int choice = Integer.parseInt(buf.readLine());
			if(choice == 2) {
				stack.pop();
				System.out.println("数据弹出后的堆栈内容：");
				stack.printOfStack();
			} else if(choice == 1) {
				System.out.println("请输入要加入堆栈的数据：");
				int data = Integer.parseInt(buf.readLine());
				stack.push(data);
				System.out.println("数据加入后的堆栈内容：");
				stack.printOfStack();
			} else if(choice == 0) {
				break;
			} else {
				System.out.println("输入错误！！");
			}
			
		}
	}
	
}
