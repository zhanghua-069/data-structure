package com.xyzq.zh.sort;

import java.util.Arrays;

/**
 * 插入排序法
 * 
 * 插入排序：稳定排序法
 * 排序原理：将数组中的元素逐一与已排序好的数据做比较，再将该数据插入适当的位置
 * 比较次数：n(n-1)/2
 * 时间复杂度：O(n^2)，最好情况的时间复杂度O(n)
 * 空间复杂度：O(1)
 * 适用场景：适用于大部分数据已经过排序或已排序数据库新增数据后进行排序的情况
 * 
 * @author zhanghua
 *
 */
public class InsertionSort {
	
	private int[] data;
	
	public InsertionSort(int[] data) {
		this.data = data;
	}
	
	/**
	 * 由小到大排序
	 */
	public void insert() {
		for(int i = 1; i < data.length; i++) {// 扫描循环次数为SIZE-1
			int tmp = data[i];// 记录当前要排序的数据，与之前已经过排序的数据比较
			int j = i - 1;// 定位比较的元素（当前要排序数据之前的已完成排序的内容）
			while(j >= 0 && tmp < data[j]) {
				data[j+1] = data[j];// 将所有元素往后推一个位置
				j--;
			}
			
			data[j+1] = tmp;// 将待排序数据插入指定的位置
			System.out.println("第" + i + "次排序后的结果是：" + Arrays.toString(data));
		}
		
		System.out.println("排序后结果为：" + Arrays.toString(data));
	}
	
}
