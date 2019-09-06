package com.xyzq.zh.tree;

import org.junit.Test;

public class ThreadedBinaryTreeTest {
	
	/**
	 * 线索二叉树遍历测试
	 */
	@Test
	public void testPrint() {
		System.out.println("线索二叉树经建立后，以中序追踪能有排序的效果");
		System.out.println("除了第一个数字作为线索二叉树的开头节点外");
		int data1[] = {0, 10, 20, 30, 100, 399, 453, 43, 237, 373, 655};
		ThreadedBinaryTree tree1 = new ThreadedBinaryTree(data1);
		System.out.println("=======================");
		System.out.println("范例 1 ");
		System.out.println("数字由小到大的排列顺序结果为：");
		tree1.print();
		
		// 测试范例2
		int data2[] = {0, 101, 118, 87, 12, 765, 65};
		ThreadedBinaryTree tree2 = new ThreadedBinaryTree(data2);
		System.out.println("=======================");
		System.out.println("范例 2 ");
		System.out.println("数字由小到大的排列顺序结果为：");
		tree2.print();
	}
	
}
