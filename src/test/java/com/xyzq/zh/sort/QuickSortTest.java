package com.xyzq.zh.sort;

import java.util.Arrays;

import org.junit.Test;

/**
 * 快速排序法测试案例
 * 
 * @author zhanghua
 *
 */
public class QuickSortTest {
	
	@Test
	public void testSort() {
		int[] data = {26, 17, 5, 33, 87, 53, 27, 49, 28, 78};
		System.out.println("快速排序法：");
		System.out.println("原始数据为：" + Arrays.toString(data));
		
		QuickSort sort = new QuickSort(data);
		sort.quick(0, data.length - 1);
		System.out.println("排序后结果为：" + Arrays.toString(data));
	}
	
}
