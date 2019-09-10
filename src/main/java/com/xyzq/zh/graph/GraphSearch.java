package com.xyzq.zh.graph;

/**
 * 图形遍历
 * 
 * @author zhanghua
 *
 */
public class GraphSearch {
	
	public int run[];
	public GraphLink[] linkArr;
	
	/**
	 * 先广后深遍历队列
	 */
	public GraphQueue queue;
	
	/**
	 * 对象实例化
	 * 
	 * @param size
	 * @param linkArr
	 * @param queue
	 */
	public GraphSearch(int size, GraphLink[] linkArr, GraphQueue queue) {
		this.run = new int[size];
		this.linkArr = linkArr;
		this.queue = queue;
	}
	
	
	/**
	 * 先深后广搜索法（Deepth-First Search, DFS）
	 * 
	 * @param current
	 */
	public void dfs(int current) {
		run[current] = 1;
		System.out.print("[" + current + "]");
		
		while(linkArr[current].first != null) {
			int value = linkArr[current].first.x;
			if(run[value] == 0) {// 顶点尚未遍历，就进行dfs的递归调用
				dfs(value);
			} else {// 顶点已遍历，顺延到该顶点的下一个邻接点
				linkArr[current].first = linkArr[current].first.next;
			}
		}
		
	}
	
	/**
	 * 先广后深遍历法（Breadth-First Search, BFS）
	 * 
	 * @param current
	 */
	public void bfs(int current) {
		queue.enqueue(current);// 将第一个顶点存入队列
		run[current] = 1;// 将遍历过的顶点设为1
		System.out.print("[" + current + "]");
		
		while(!queue.isEmpty()) {
			current = queue.dequeue();// 将顶点从队列中取出
			while(linkArr[current].first != null) {
				int value = linkArr[current].first.x;
				if(run[value] == 0) {
					queue.enqueue(value);
					run[value] = 1;// 记录已遍历过
					System.out.print("[" + value + "]");
				} else {
					linkArr[current].first = linkArr[current].first.next;
				}
			}
			
		}
		
	}
	
}
