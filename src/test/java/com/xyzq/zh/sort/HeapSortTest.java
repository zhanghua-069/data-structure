package com.xyzq.zh.sort;

import org.junit.Test;

/**
 * 堆积排序测试案例
 * 
 * @author zhanghua
 *
 */
public class HeapSortTest {
	
	@Test
	public void testSort() {
		int[] data = {0, 5, 6, 4, 8, 3, 2, 7, 1};
		HeapSort sort = new HeapSort(data);
		System.out.println("堆积排序法：");
		System.out.print("原始数据为：");
		sort.print();
		
		sort.heap();
	}
	
}
