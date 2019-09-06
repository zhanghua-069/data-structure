package com.xyzq.zh.tree;

/**
 * 二叉运算树链表表示法
 * 
 * @author zhanghua
 *
 */
public class ExpressionTree extends BinaryTreeByLink {

	public ExpressionTree(int[] data) {
		super(data);
	}
	
	public ExpressionTree(char[] data, int index) {
		this.rootNode = create(data, index);
	}
	
	/**
	 * 将二叉树的数组表示法转化为链表表示法，返回二叉树根节点
	 * 
	 * @param data
	 * @param index
	 * @return
	 */
	public TreeNode create(char[] data, int index) {
		if(index >= data.length) {// 作为递归调用的出口条件（遍历至叶子节点）
			return null;
		} else {
			TreeNode tempNode = new TreeNode(data[index]);
			// 递归生成左子树
			tempNode.left = create(data, 2 * index);
			// 递归生成右子树
			tempNode.right = create(data, 2 * index + 1);
			return tempNode;
		}
	}
	
	/**
	 * 表达式运算方法
	 * 
	 * @param oprator
	 * @param num1
	 * @param num2
	 * @return
	 */
	public int condition(char oprator, int num1, int num2) {
		switch(oprator) {
		case '*' : return num1 * num2;
		case '/' : return num1 / num2;
		case '+' : return num1 + num2;
		case '-' : return num1 - num2;
		case '%' : return num1 % num2;
		}
		return -1;
	}
	
	/**
	 * 传入根节点，用来计算此二叉树的值
	 * 
	 * @param node
	 * @return
	 */
	public int answer(TreeNode node) {
		if(node.left == null && node.right == null) {
			// 递归调用的出口条件，将节点的值转换成数值后返回
			return Character.getNumericValue((char)node.value);
		} else {
			// 计算左子树表达式的值
			int num1 = answer(node.left);
			// 计算右子树表达式的值
			int num2 = answer(node.right);
			return condition((char)node.value, num1, num2);
		}
	}
	
	/**
	 * 二叉树中序遍历
	 * 递归查找：左子树 -> 树根 -> 右子树
	 * 
	 * @param node
	 */
	public void inOrder(TreeNode node) {
		if(node != null) {
			inOrder(node.left);
			System.out.print((char)node.value);
			inOrder(node.right);
		}
	}
	
	/**
	 * 二叉树前序遍历
	 * 递归查找：树根 -> 左子树 -> 右子树
	 * 
	 * @param node
	 */
	public void preOrder(TreeNode node) {
		if(node != null) {
			System.out.print((char)node.value);
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	/**
	 * 二叉树后序遍历
	 * 递归查找：左子树 -> 右子树 -> 树根
	 * 
	 * @param node
	 */
	public void postOrder(TreeNode node) {
		if(node != null) {
			postOrder(node.left);
			postOrder(node.right);
			System.out.print((char)node.value);
		}
	}

}
