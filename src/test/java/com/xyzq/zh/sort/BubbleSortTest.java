package com.xyzq.zh.sort;

import java.util.Arrays;

import org.junit.Test;

/**
 * 冒泡排序测试案例
 * 冒泡排序：稳定排序法
 * 比较次数：n(n-1)/2
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 * 
 * @author zhanghua
 *
 */
public class BubbleSortTest {
	
	@Test
	public void test() {
		int[] data = {6, 5, 9, 7, 2, 8};
		System.out.println("冒泡排序法：");
		System.out.println("原始数据为：" + Arrays.toString(data));
		
		for(int i = data.length - 1; i > 0; i--) {
			for(int j = 0; j < i; j++) {
				if(data[j] > data[j+1]) {
					int tmp = data[j];
					data[j] = data[j+1];
					data[j+1] = tmp;
				}
			}
			
			System.out.println("第" + (data.length - i) + "次排序后的结果是：" + Arrays.toString(data));
		}
		
		System.out.println("排序后结果为：" + Arrays.toString(data));
	}
	
	@Test
	public void testBubble() {
		int[] data = {4, 6, 2, 7, 8, 9};
		System.out.println("改良冒泡排序法：");
		System.out.println("原始数据为：" + Arrays.toString(data));
		
		BubbleSort bubbleSort = new BubbleSort(data);
		bubbleSort.bubble();
	}
	
}
