package com.xyzq.zh.graph;

/**
 * K氏法遍历节点
 * 
 * @author zhanghua
 *
 */
public class KruskalNode {
	
	/**
	 * 起始顶点存放数组
	 */
	public int from[];	
	/**
	 * 终止顶点存放数组
	 */
	public int to[];
	/**
	 * 路径长度数组
	 */
	public int val[];
	/**
	 * 存放最小成本树各节点遍历的次数
	 */
	public int find[];
	/**
	 * 存放链表下一个节点位置，-1：下一个节点为空，-2：未使用
	 */
	public int next[];
	
	public KruskalNode(int size) {
		this.from = new int[size];
		this.to = new int[size];
		this.val = new int[size];
		this.find = new int[size];
		this.next = new int[size];
		for(int i = 0; i < size; i++) {
			next[i] = -2;// -2表示未用节点
		}
	}
	
	/**
	 * 搜索可用节点位置
	 * 
	 * @return
	 */
	public int findFree() {
		for(int i = 0; i < next.length; i++) {
			if(next[i] == -2) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 建立链表
	 * 
	 * @param head		头节点位置
	 * @param free 		可用节点位置
	 * @param dataNum	路径长度
	 * @param fromNum	起始顶点
	 * @param toNum		终止顶点
	 */
	public void create(int head, int free, int dataNum, int fromNum, int toNum) {
		if(head == free) {
			val[head] = dataNum;
			from[head] = fromNum;
			to[head] = toNum;
			next[head] = -1;
		} else {
			int point = head;
			val[free] = dataNum;
			from[free] = fromNum;
			to[free] = toNum;
			next[free] = -1;
			
			while(next[point] != -1) {
				point = next[point];
			}
			next[point] = free;
		}
	}
	
	/**
	 * 打印链表数据
	 * 
	 * @param head
	 */
	public void print(int head) {
		int point = head;
		while(point != -1) {
			System.out.println("起始顶点[" + from[point] + "] 终止顶点[" + to[point] + "] 路径长度[" + val[point] + "]");
			point = next[point];
		}
	}
 	
}
