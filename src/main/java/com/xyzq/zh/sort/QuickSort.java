package com.xyzq.zh.sort;

import java.util.Arrays;

/**
 * 高级排序法-快速排序法
 * 
 * 快速排序：不稳定排序法
 * 排序原理：选取虚拟中间值，将小于中间值的数据放在左边，大于中间值的数据放在右边
 * 时间复杂度：最快及平均情况下为O(nlog2n)，最坏情况为O(n^2)
 * 空间复杂度：最佳情况为O(log2n)，最坏情况为O(n)
 * 评价：是平均运行时间最快的排序法，目前公认的最佳排序算法
 * 
 * @author zhanghua
 *
 */
public class QuickSort {
	
	private int[] data;
	private int process = 0;
	
	public QuickSort(int[] data) {
		this.data = data;
	}
	
	/**
	 * 由小到大排序
	 * 
	 * @param left	虚拟中间值的位置
	 * @param right	
	 */
	public void quick(int left, int right) {
		if(left < right) {
			int lindex = left + 1;// 由左往右data[i]>=data[left]的位置
			int rindex = right;// 由右往左data[j]<=data[left]的位置
			
			// 排序
			while(true) {
				System.out.println("[处理过程" + (process++) + "]=> " + Arrays.toString(data));
				
				// 由左往右找出第一个键值大于data[left]者
				for(int i = left + 1; i <= right; i++) {
					if(data[i] >= data[left]) {
						lindex = i;
						break;
					}
					lindex++;
				}
				
				// 由右往左找出第一个键值小于data[left]者
				for(int j = right; j >= left+1; j--) {
					if(data[j] <= data[left]) {
						rindex = j;
						break;
					}
					rindex--;
				}
				
				// 如果lindex<rindex,交换data[lindex]与data[rindex]的位置
				if(lindex < rindex) {
					int tmp = data[lindex];
					data[lindex] = data[rindex];
					data[rindex] = tmp;
				} else {
					break;// 否则跳出循环
				}
			}
			
			// 如果lindex >= rindex，则将data[left]于data[rindex]交换
			if(lindex >= rindex) {
				int tmp = data[left];
				data[left] = data[rindex];
				data[rindex] = tmp;
				
				// 并以rindex为基准点将数据分为左右两部分，并以递归方式分别为左右两半进行排序，直至完成排序
				quick(left, rindex - 1);// 左侧半部分排序
				quick(rindex + 1, right);// 右侧半部分排序
			}
			
		}
	}
	
}
