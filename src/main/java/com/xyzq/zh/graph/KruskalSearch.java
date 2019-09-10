package com.xyzq.zh.graph;

/**
 * K氏搜索实现类
 * 
 * @author zhanghua
 *
 */
public class KruskalSearch {
	
	/**
	 * 遍历节点记录数组
	 */
	public int[] record;
	/**
	 * K氏法遍历节点
	 */
	public KruskalNode node;
	
	public KruskalSearch(int size, KruskalNode node) {
		this.record = new int[size + 1];
		this.node = node;
	}
	
	/**
	 * 建立最小成本生成树
	 */
	public void mintree() {
		boolean flag = true;
		int current = 0;
		while (node.next[current] != -1) {
			int result = findMinCost();
			record[node.from[result]]++;
			record[node.to[result]]++;
			if (record[node.from[result]] > 1 && record[node.to[result]] > 1) {// 路径遍历超过1次
				record[node.from[result]]--;
				record[node.to[result]]--;
				flag = false;// 舍弃该路径，回退状态
			} else {
				flag = true;
			}

			if (flag) {
				System.out.println(
						"起始顶点[" + node.from[result] + "] 终止顶点[" + node.to[result] + "] 路径长度[" + node.val[result] + "]");
			}

			current++;
		}
	}
	
	/**
	 * 查找链表中当前未被遍历过的最小路径
	 * 
	 * @return
	 */
	public int findMinCost() {
		int minval = 100;// 设置初始最小值
		int result = 0;
		int current = 0;// 遍历时当前节点的位置
		while(node.next[current] != -1) {// 未遍历到数组尾端
			if(node.val[current] < minval && node.find[current] == 0) {// 当前路径小于目前最小且未路径未被遍历过
				minval = node.val[current];
				result = current;
			}
			current++;
		}
		node.find[result] = 1;// 设置路径已走过
		return result;
	}

}
