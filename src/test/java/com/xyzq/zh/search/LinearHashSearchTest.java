package com.xyzq.zh.search;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

/**
 * 哈希查找-线性探测法
 * 
 * @author zhanghua
 *
 */
public class LinearHashSearchTest {
	
	@Test
	public void testSearch() {
		int indexBox = 10;// 哈希表最大长度
		int size = 7;
		int index[] = new int[indexBox];
		int data[] = new int[size];
		Random random = new Random();
		
		for(int i=0; i<size; i++) {// 数据表赋初始值
			data[i] = Math.abs(random.nextInt(20)) + 1;
		}
		for(int i=0; i<indexBox; i++) {// 清理哈希表
			index[i] = -1;
		}
		System.out.println("原始数组值：");
		System.out.println("  " + Arrays.toString(data));
		
		System.out.println("哈希表内容：");
		for(int i=0; i<size; i++) {// 建立哈希表
			createTable(data[i], index);
			// 打印单一元素的哈希表位置
			System.out.print("  " + data[i] + " =>");
			System.out.println(Arrays.toString(index));
		}
		System.out.println("完成哈希表：");
		System.out.println("  " + Arrays.toString(index));
		
	}
	
	/**
	 * 建立哈希表
	 * 
	 * @param num
	 * @param index
	 */
	private void createTable(int num, int[] index) {
		int tmp = num % index.length;
		while(index[tmp] != -1) {// 数据位置非空，往后找位置存放
			tmp = (tmp+1) % index.length;
		}
		index[tmp] = num;
	}
	
}
