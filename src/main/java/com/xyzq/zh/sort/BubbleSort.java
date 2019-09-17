package com.xyzq.zh.sort;

import java.util.Arrays;

/**
 * 简单排序法-改进版冒泡排序法
 * 
 * 冒泡排序：稳定排序法
 * 比较次数：n(n-1)/2
 * 时间复杂度：O(n^2)，最好情况的时间复杂度O(n)
 * 空间复杂度：O(1)
 * 适用于数据量小或有部分数据已经排序过的情况
 * 
 * @author zhanghua
 *
 */
public class BubbleSort {
	
	private int[] data;
	
	public BubbleSort(int[] data) {
		this.data = data;
	}
	
	/**
	 * 可中断冒泡排序：由小到大排序
	 */
	public void bubble() {
		for(int i = data.length - 1; i > 0; i--) {
			int flag = 0;// 用来判断是否执行过交换动作
			for(int j = 0; j < i; j++) {
				if(data[j] > data[j+1]) {
					int tmp = data[j];
					data[j] = data[j+1];
					data[j+1] = tmp;
					flag++;
				}
			}
			
			if(flag == 0) {
				// 当执行完一次扫描就判断是否做过交换动作，若没有交换过数据，表示数组已完成排序，故可直接跳出循环
				break;
			}
			
			System.out.println("第" + (data.length - i) + "次排序后的结果是：" + Arrays.toString(data));
		}
		
		System.out.println("排序后结果为：" + Arrays.toString(data));
	}
	
}
