package com.xyzq.zh.sort;

import java.util.Arrays;

import org.junit.Test;

/**
 * 插入排序法测试案例
 * 
 * @author zhanghua
 *
 */
public class InsertionSortTest {
	
	@Test
	public void testInsert() {
		int[] data = {8, 7, 4, 6, 9, 2};
		System.out.println("插入排序法：");
		System.out.println("原始数据为：" + Arrays.toString(data));
		
		InsertionSort sort = new InsertionSort(data);
		sort.insert();
	}
	
}
