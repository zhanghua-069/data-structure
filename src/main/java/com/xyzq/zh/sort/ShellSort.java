package com.xyzq.zh.sort;

import java.util.Arrays;

/**
 * 希尔排序法
 * 
 * 希尔排序：稳定排序法
 * 排序原理：基于插入排序，减少数据搬移次数
 * 时间复杂度：O(n^(3/2))
 * 空间复杂度：O(1)
 * 适用场景：适用于大部分数据已经过排序
 * 
 * @author zhanghua
 *
 */
public class ShellSort {
	
	private int[] data;
	
	public ShellSort(int[] data) {
		this.data = data;
	}
	
	/**
	 * 从小到大排序
	 */
	public void shell() {
		int jmp = data.length / 2;// 设定间隔位移量
		int k = 1;// 排序计数
		while(jmp != 0) {
			for(int i = jmp; i < data.length; i++) {
				int tmp = data[i];
				int j = i - jmp;// 定位与data[i]比较的元素
				while(j >= 0 && tmp < data[j]) {// 实现为插入排序法
					data[j+jmp] = data[j];
					j = j - jmp;
				}
				data[jmp + j] = tmp;// jmp+j=i，即比较交换data[i]与data[i-jmp]
			}
			
			System.out.println("第" + (k++) + "次排序后的结果是：" + Arrays.toString(data));
			jmp = jmp/2;// 不断除2直至jmp=0，最后一次为相邻的两个数两两比较
		}
		
		System.out.println("排序后结果为：" + Arrays.toString(data));
	}

}
