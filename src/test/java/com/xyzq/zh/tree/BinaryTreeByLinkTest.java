package com.xyzq.zh.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.junit.Test;

public class BinaryTreeByLinkTest {
	
	@Test
	public void testAdd() throws IOException {
		int[] content = new int[10];
		BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请连续输入" + content.length + "个数字");
		for(int i = 0; i < content.length; i++) {
			System.out.print("请输入第" + (i+1) + "个数字：");
			int data = Integer.parseInt(keyin.readLine());
			content[i] = data;
		}
		
		new BinaryTreeByLink(content);
		System.out.println("以链表方式建立二叉树，成功！！");
	}
	
	/**
	 * 二叉树遍历
	 */
	@Test
	public void testOrder() {
		int arr[] = {7, 4, 1, 5, 16, 8, 11, 12, 15, 9, 2};// 原始数组
		System.out.println("原始数组内容：");
		System.out.println(Arrays.toString(arr));
		
		BinaryTreeByLink binaryTree = new BinaryTreeByLink(arr);
		System.out.println("[二叉树的内容]");
		System.out.println("前序遍历结果：");
		binaryTree.preOrder(binaryTree.rootNode);
		System.out.println();
		
		System.out.println("中序遍历结果：");
		binaryTree.inOrder(binaryTree.rootNode);
		System.out.println();
		
		System.out.println("后序遍历结果：");
		binaryTree.postOrder(binaryTree.rootNode);
	}
	
	/**
	 * 二叉排序树
	 */
	@Test
	public void testSort() throws IOException {
		BinaryTreeByLink tree = new BinaryTreeByLink();
		BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请输入数据，结束请输入-1：  ");
		while(true) {
			int value = Integer.parseInt(keyin.readLine());
			if(value == -1) {
				break;
			}
			tree.addNode2Tree(value);
		}
		System.out.println("=======================");
		System.out.println("排序完成结果：");
		tree.inOrder(tree.rootNode);
	}
	
	@Test
	public void testFindTree() throws IOException {
		int arr[] = {7, 4, 1, 5, 16, 8, 11, 12, 15, 9, 2};// 原始数组
		System.out.println("原始数组内容：");
		System.out.println(Arrays.toString(arr));
		
		BinaryTreeByLink binaryTree = new BinaryTreeByLink(arr);
		System.out.print("请输入搜索值：  ");
		BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
		int value = Integer.parseInt(keyin.readLine());
		if(binaryTree.findTree(binaryTree.rootNode, value)) {
			System.out.println("您要找的值 [" + value + "] 已找到！！");
		} else {
			System.out.println("抱歉，没有找到");
		}
	}
	
}
