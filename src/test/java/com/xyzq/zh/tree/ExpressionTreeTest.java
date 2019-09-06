package com.xyzq.zh.tree;

import org.junit.Test;

public class ExpressionTreeTest {
	
	@Test
	public void testCondition() {
		// 将二叉树以数组方式来声明
		char[] information1 = {' ', '+', '*', '%', '6', '3', '9', '5'};
		char[] information2 = {' ', '+', '+', '+', '*', '%', '/', '*', '1', '2', '3', '2', '6', '3', '2', '2'};
		ExpressionTree tree1 = new ExpressionTree(information1, 1);
		System.out.println("二叉运算树数值运算范例1：");
		System.out.println("=======================");
		System.out.print("转换成中序表达式：");
		tree1.inOrder(tree1.rootNode);
		System.out.print("\n转换成前序表达式：");
		tree1.preOrder(tree1.rootNode);
		System.out.print("\n转换成后序表达式：");
		tree1.postOrder(tree1.rootNode);
		// 计算二叉树表达式的运算结果
		System.out.print("\n此二叉运算树经过计算后所得到的结果值为：");
		System.out.println(tree1.answer(tree1.rootNode));
		System.out.println();
		
		ExpressionTree tree2 = new ExpressionTree(information2, 1);
		System.out.println("二叉运算树数值运算范例2：");
		System.out.println("=======================");
		System.out.print("转换成中序表达式：");
		tree2.inOrder(tree2.rootNode);
		System.out.print("\n转换成前序表达式：");
		tree2.preOrder(tree2.rootNode);
		System.out.print("\n转换成后序表达式：");
		tree2.postOrder(tree2.rootNode);
		// 计算二叉树表达式的运算结果
		System.out.print("\n此二叉运算树经过计算后所得到的结果值为：");
		System.out.println(tree2.answer(tree2.rootNode));
	}
	
}
