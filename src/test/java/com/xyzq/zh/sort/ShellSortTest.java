package com.xyzq.zh.sort;

import java.util.Arrays;

import org.junit.Test;

/**
 * 希尔排序测试案例
 * 
 * @author zhanghua
 *
 */
public class ShellSortTest {
	
	/**
	 * 第1次排序：jmp=8/2=4，下标：(0,4) (1,5) (2,6) (3,7)
	 * 第2次排序：jmp=(8/2)/2=2，下标：(0,2) (1,3) (2,4) (3,5) (4,6) (5,7)
	 * 第3次排序：jmp=((8/2)/2)/2=1，下标：(0,1) (1,2) (2,3) (3,4) (4,5) (5,6) (6,7)
	 * 第4次排序：jmp=0，跳出循环
	 */
	@Test
	public void testShell() {
		int[] data = {6, 5, 3, 2, 4, 8, 9, 1};
		System.out.println("希尔排序法：");
		System.out.println("原始数据为：" + Arrays.toString(data));
		
		ShellSort sort = new ShellSort(data);
		sort.shell();
	}
	
}
