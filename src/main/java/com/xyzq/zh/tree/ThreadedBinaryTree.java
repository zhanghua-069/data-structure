package com.xyzq.zh.tree;

/**
 * 线索二叉树
 * 
 * @author zhanghua
 *
 */
public class ThreadedBinaryTree {
	
	/**
	 * 节点类
	 */
	class ThreadNode {
		/**
		 * 节点数据
		 */
		int value;
		/**
		 * 左控制位
		 */
		int lbit;
		/**
		 * 右控制位
		 */
		int rbit;
		/**
		 * 左子树链接
		 */
		ThreadNode lchild;
		/**
		 * 右子树链接
		 */
		ThreadNode rchild;
		
		public ThreadNode(int value) {
			this.value = value;
			this.lbit = 0;
			this.rbit = 0;
			this.lchild = null;
			this.rchild = null;
		}
	}
	
	/**
	 * 线索二叉树根节点
	 */
	public ThreadNode rootNode;
	
	public ThreadedBinaryTree() {
		this.rootNode = null;
	}
	
	public ThreadedBinaryTree(int[] data) {
		for(int i = 0; i < data.length; i++) {
			addNode2Tree(data[i]);
		}
	}
	
	/**
	 * 将指定的值加入到二叉线索树
	 * 
	 * @param value
	 */
	public void addNode2Tree(int value) {
		// 新增节点
		ThreadNode newNode = new ThreadNode(value);
		// 二叉树的遍历节点的行进方向，0：根节点，1：左子树，2：右子树
		int pos = 0;
		// 加入根节点
		if(rootNode == null) {
			rootNode = newNode;
			rootNode.lbit = 0;
			rootNode.lchild = rootNode;
			rootNode.rbit = 1;
			rootNode.rchild = null;
		} else {
			// 设定线索二叉树遍历的开头节点所指向的节点
			ThreadNode current = rootNode.rchild;
			if(current == null) {
				rootNode.rchild = newNode;
				newNode.lchild = rootNode;
				newNode.rchild = rootNode;
			} else {
				// 对应rChild：指向中序遍历的后一个节点
				// 对应lChild：指向中序遍历的前一个节点
				ThreadNode parent = rootNode;
				ThreadNode previous = new ThreadNode(value);
				while(current != null) {
					if(current.value > value) {// 新增节点加入到左子树中
						if(pos != -1) {
							pos = -1;
							previous = parent;
						}
						parent = current;
						if(current.lbit == 1) {// 遍历左子树节点
							current = current.lchild;
						} else {// 跳出循环
							current = null;
						}
					} else {// 新增节点加入到右子树中
						if(pos != 1) {
							pos = 1;
							previous = parent;
						}
						parent = current;
						if(current.rbit == 1) {// 遍历右子树节点
							current = current.rchild;
						} else {
							current = null;
						}
					}
				}
				
				// 新节点加入动作
				if(parent.value > value) {
					parent.lbit = 1;
					parent.lchild = newNode;
					newNode.lchild = previous;
					newNode.rchild = parent;
				} else {
					parent.rbit = 1;
					parent.rchild = newNode;
					newNode.lchild = parent;
					newNode.rchild = previous;
				}
			}
		}
	}
	
	/**
	 * 线索二叉树中序遍历
	 */
	public void print() {
		ThreadNode tempNode = rootNode;
		do {
			// 二叉树中只有一个根节点时rootNode.rchild = rootNode 
			if(tempNode.rbit == 0) {// 往上层节点遍历
				tempNode = tempNode.rchild;
			} else {
				tempNode = tempNode.rchild;
				while(tempNode.lbit != 0) {// 往下层节点遍历
					tempNode = tempNode.lchild;
				}
			}
			
			if(tempNode != rootNode) {
				System.out.println("[" + tempNode.value + "]");
			}
		} while(tempNode != rootNode);
	}

}
