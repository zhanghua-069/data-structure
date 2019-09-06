package com.xyzq.zh.tree;

/**
 * 链表方式实现二叉树
 * 
 * @author zhanghua
 * 
 */
public class BinaryTreeByLink {
	
	class TreeNode {
		int value;
		/**
		 * 左节点
		 */
		TreeNode left;
		/**
		 * 右节点
		 */
		TreeNode right;
		
		public TreeNode(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}
	
	/**
	 * 二叉树根节点
	 */
	public TreeNode rootNode;
	
	public int count = 1;
	
	public BinaryTreeByLink() {
		
	}
	
	public BinaryTreeByLink(int[] data) {
		for(int i = 0; i < data.length; i++) {
			addNode2Tree(data[i]);
		}
	}
	
	/**
	 * 将指定的值加入到二叉树的适当的节点
	 * 
	 * @param value
	 */
	public void addNode2Tree(int value) {
		if(rootNode == null) {// 建立根节点
			rootNode = new TreeNode(value);
		} else {
			TreeNode currentNode = rootNode;
			while(true) {
				if(value < currentNode.value) {// 在左子树加入节点
					if(currentNode.left == null) {
						currentNode.left = new TreeNode(value);
						return;
					} else {
						currentNode = currentNode.left;
					}
				} else {// 在右子树加入节点
					if(currentNode.right == null) {
						currentNode.right = new TreeNode(value);
						return;
					} else {
						currentNode = currentNode.right;
					}
				}
			}
			
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
			System.out.print("[" + node.value + "]");
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
			System.out.print("[" + node.value + "]");
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
			System.out.print("[" + node.value + "]");
		}
	}
	
	/**
	 * 二叉树搜索
	 * 
	 * @param node
	 * @param value
	 * @return
	 */
	public boolean findTree(TreeNode node, int value) {
		if(node == null) {
			return false;
		} else if(node.value == value) {
			System.out.println("共搜索" + count + "次");
			return true;
		} else if(node.value > value) {
			// 搜索左子树
			count++;
			return findTree(node.left, value);
		} else {
			// 搜索右子树
			count++;
			return findTree(node.right, value);
		}
	}

}
