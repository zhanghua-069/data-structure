package com.xyzq.zh.sort;

import java.util.Arrays;

import org.junit.Test;

/**
 * 基数排序测试案例
 * 
 * @author zhanghua
 *
 */
public class RadixSortTest {
	
	@Test
	public void testSort() {
		int[] data = {993, 994, 5, 919, 115, 599, 758, 512, 735, 486};
		System.out.println("基数排序法：");
		System.out.println("原始数据为：" + Arrays.toString(data));
		
		RadixSort sort = new RadixSort(data);
		sort.radix();
	}
	
}
