package com.xyzq.zh.sort;

import java.util.Arrays;

import org.junit.Test;

/**
 * 选择排序法测试案例
 * 
 * @author zhanghua
 *
 */
public class SelectionSortTest {
	
	@Test
	public void testSelect() {
		int[] data = {9, 7, 5, 3, 4, 6};
		System.out.println("选择排序法：");
		System.out.println("原始数据为：" + Arrays.toString(data));
		
		SelectionSort sort = new SelectionSort(data);
		sort.select();
	}

}
