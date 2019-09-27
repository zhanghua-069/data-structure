package com.xyzq.zh.search;

/**
 * 插值查找法（插补查找法）
 * 
 * 二分查找法的改进版，按照数据的位置分布，利用公式预测数据所在的位置，再以二分法的方式渐渐逼近
 * 公式：Mid=low + (key-data[low]) / (data[high]-data[low]) * (high-low)
 * 时间复杂度：取决于数据分布的情况，平均而言优于O(log n)
 * 使用前提：数据需先经过排序
 * 
 * @author zhanghua
 *
 */
public class InterpolationSearch {
	
	public int search(int data[], int val) {
		int low = 0, mid, high = data.length-1;
		System.out.println("查找处理中......");
		while (low <= high && val != -1) {
			// 插值法公式：mid = low + tmp
			if(data[high] == data[low]) {
				mid = high;
			} else {
				int tmp = (val - data[low]) * (high - low) / (data[high] - data[low]);
				mid = low + tmp;
			}
			
			if(mid >= data.length || mid <= -1) {
				return -1;
			}
			if(val < data[low] || val > data[high]) {
				return -1;
			}
			
			if(val < data[mid]) {
				System.out.println(val + " 介于位置" + (low + 1) + "[" + data[low] + "]及中间值 " + (mid + 1) + "[" + data[mid]
						+ "], 往左半边继续查找");
				high = mid - 1;
			} else if(val > data[mid]) {
				System.out.println(val + " 介于中间值位置" + (mid + 1) + "[" + data[mid] + "]及 " + (high + 1) + "["
						+ data[high] + "], 往右半边继续查找");
				low = mid + 1;
			} else {
				return mid;
			}
		}
		
		return -1;
	}
	
}
