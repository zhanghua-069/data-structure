package com.xyzq.zh.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class StackByArrayTest {
	
	/**
	 * 测试堆栈的基本存取操作
	 * 
	 * @throws IOException
	 */
	@Test
	public void testStackOpt() throws IOException {
		StackByArray stack = new StackByArray(10);
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请依次输入10个数据：");
		for(int i=0; i < 10; i++) {
			int value = Integer.parseInt(buf.readLine());
			stack.push(value);
		}
		System.out.println("=======================");
		
		while(!stack.isEmpty()) {
			System.out.println("堆栈弹出的顺序为：" + stack.pop());
		}
	}
	
	/**
	 * 利用堆栈模拟扑克牌洗牌发牌过程
	 */
	@Test
	public void testCardGame() {
		int card[] = new int[52];
		StackByArray stack = new StackByArray(52);
		int i, j, k = 0, test, style;
		char ascVal = 'H';
		for(i = 0; i < 52; i++) {
			card[i] = i;
		}
		System.out.println("[洗牌中......请稍后！]");
		
		while(k < 30) {
			for(i = 0; i < 51; i++) {
				// j > i，card[j]为出现在card[i]之后的数
				for(j = i + 1; j < 52; j++) {
					if((int)(Math.random() * 5) == 2) {// 生成0~4间的int
						// 交换card[i]与card[j]的数值
						test = card[i];
						card[i] = card[j];
						card[j] = test;
					}
				}
			}
			k++;
		}
		
		i = 0;
		while(i < 52) {
			// 将52张牌推入堆栈
			stack.push(card[i]);
			i++;
		}
		System.out.println("[逆时针发牌]");
		System.out.println("[显示各家牌子]\n 东家\t  北家\t  西家\t  南家");
		System.out.println("=======================");
		
		while(!stack.isEmpty()) {
			int top = stack.getTop();
			int vaule = stack.pop();
			style = vaule / 13;
			// 计算牌的花色
			switch(style) {
				case 0: ascVal = 'C'; break; // 梅花
				case 1: ascVal = 'D'; break; // 方块
				case 2: ascVal = 'H'; break; // 红桃
				case 3: ascVal = 'S'; break; // 黑桃
			}
			System.out.print("[" + ascVal + (vaule % 13 + 1) + "]\t");
			if(top % 4 == 0) {
				System.out.println();
			}
		}
		
	}
	
}
