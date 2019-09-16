package com.xyzq.zh.sort;

import java.util.Arrays;

/**
 * 选择排序法
 * 
 * 排序原理：以最大或最小值直接与最前方未排序的键值交换，数据排列顺序很有可能被改变，属于不稳定排序法
 * 比较次数：n(n-1)/2
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 * 适用于数据量小或有部分数据已经排序过的情况
 * 
 * @author zhanghua
 *
 */
public class SelectionSort {
	
	private int[] data;
	
	public SelectionSort(int[] data) {
		this.data = data;
	}
	
	/**
	 * 数据由小到大排序
	 */
	public void select() {
		for(int i = 0; i < data.length - 1; i++) {
			for(int j = i+1; j < data.length; j++) {
				if(data[i] > data[j]) {
					int tmp = data[i];
					data[i] = data[j];
					data[j] = tmp;
				}
			}
			
			System.out.println("第" + (i+1) + "次排序后的结果是：" + Arrays.toString(data));
		}
		
		System.out.println("排序后结果为：" + Arrays.toString(data));
	}
	
}
