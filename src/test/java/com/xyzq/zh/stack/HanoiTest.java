package com.xyzq.zh.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

/**
 * 递归解决汉诺塔问题
 * 
 * @author zhanghua
 *
 */
public class HanoiTest {
	
	@Test
	public void testHanoi() throws IOException {
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请输入盘子的数量：  ");
		int n = Integer.parseInt(buf.readLine());
		hanoi(n, 1, 2, 3);
	}
	
	/**
	 * 汉诺塔的问题的结论，即当有n个盘子时：
	 * step1：将n-1个盘子从木桩1移动到木桩2。
	 * step2：将第n个最大的盘子，从木桩1移动到木桩3。
	 * step3：将n-1个盘子，从木桩2移动到木桩3。
	 * 
	 * @param n
	 * @param p1
	 * @param p2
	 * @param p3
	 */
	public void hanoi(int n, int p1, int p2, int p3) {
		if(n == 1) {
			// step2：将第n个最大的盘子，从木桩1移动到木桩3。
			System.out.println("盘子从 " + p1 + "移到 " + p3);
		} else {
			// step1：将n-1个盘子从木桩1移动到木桩2。
			hanoi(n-1, p1, p3, p2);
			System.out.println("盘子从 " + p1 + "移到 " + p3);
			// step3：将n-1个盘子，从木桩2移动到木桩3。
			hanoi(n-1, p2, p1, p3);
		}
	}
	
}
