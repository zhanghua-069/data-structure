package com.xyzq.zh.tree;

import java.util.Arrays;

import org.junit.Test;

/**
 * 数组方式实现二叉树
 * 
 * @author zhanghua
 *
 */
public class BinaryTreeByArrayTest {
	
	@Test
	public void test() {
		int data[] = {6, 3, 5, 9, 7, 8, 4, 2};
		int btree[] = new int[16];
		
		System.out.println("原始数组的内容：");
		System.out.println(Arrays.toString(data));
		
		for(int i = 0; i < data.length; i++) {
			// level从1开始
			int level = 1;
			// 比较树根与原始数组内的值
			while(btree[level] != 0) {
				// 如果数组内的值大于树根，则往右子树比较
				if(data[i] > btree[level]) {
					level = level * 2 + 1;
				} else {// 如果数组内的值小于或等于树根，则往左子树比较
					level = level * 2;
				}
			}
			// 将数组中的值放入二叉树
			btree[level] = data[i];
		}
		
		System.out.println("二叉树内容：");
		for(int i = 1; i < btree.length; i++) {
			System.out.print("[" + btree[i] + "] ");
		}
	}
	
}
