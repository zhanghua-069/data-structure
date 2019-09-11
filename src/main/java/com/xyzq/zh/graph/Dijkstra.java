package com.xyzq.zh.graph;

/**
 * Dijkstra法获取单个顶点到全部顶点的最短距离
 * 
 * @author zhanghua
 *
 */
public class Dijkstra extends Adjacency {
	
	/**
	 * 记录顶点到相应下标的节点的最短距离（遍历过程中实时更新）
	 */
	private int[] cost;
	/**
	 * 记录顶点到相应下标的节点的最短距离是否已被计算过
	 */
	private int[] selected;
	
	
	// 初始化方法
	public Dijkstra(int[][] weightPath, int size) {
		super(weightPath, size);
		cost = new int[size];
		selected = new int[size];
	}
	
	/**
	 * 单点对全部顶点的最短距离
	 * 
	 * @param source
	 */
	public void shortestPath(int source) {
		int vertex = 1;
		
		// 初始化操作，记录顶点1（source=1）到各顶点的直接距离，selected数组中标识顶点1已被记录，顶点1到顶点1的距离为0
		// cost: [0, 0, 10, 99999, 99999, 99999, 99999]
		// selected: [0, 1, 0, 0, 0, 0, 0]
		for(int i = 1; i < graphMatrix.length; i++) {
			cost[i] = graphMatrix[source][i];
		}
		selected[source] = 1;
		cost[source] = 0;
		
		// 外层for循环作用为计数，记录计算顶点到除自身外的剩余节点最短距离的次数，所以循环次数为：i < graphMatrix.length - 1
		for(int i = 1; i < graphMatrix.length - 1; i++) {
			int distance = INFINITE;
			
			// 该层循环为记录顶点到其他节点的直接距离的最短值，并记录下对应的节点位置：selected[vertex] = 1
			for(int j = 1; j < graphMatrix.length; j++) {
				if(distance > cost[j] && selected[j] == 0) {
					vertex = j;
					distance = cost[j];
				}
			}
			selected[vertex] = 1;
			
			// 该层循环为重新标记顶点到其他节点的最短距离（通过当前已获得的最短距离节点后再计算），即更新cost数组中顶到到其他节点的最短距离
			// 依据Dijkstra法公式：D[I]=min(D[I], D[x]+A[x, I])
			for (int j = 1; j < graphMatrix.length; j++) {
				// cost[vertex]即上一个循环计算获得的distance
				/*if(cost[vertex] + graphMatrix[vertex][j] < cost[j] && selected[j] == 0) {
					cost[j] = cost[vertex] + graphMatrix[vertex][j];
				}*/
				
				// 将cost[vertex]替换为distance执行结果不变
				if(distance + graphMatrix[vertex][j] < cost[j] && selected[j] == 0) {
					cost[j] = distance + graphMatrix[vertex][j];
				}
			}
		}
		
		System.out.println("===========================");
		System.out.println("顶点1到各顶点最短距离的最终结果");
		System.out.println("===========================");
		for(int j = 1; j < graphMatrix.length; j++) {
			System.out.println("顶点1到顶点" + j + "的最短距离= " + cost[j]);
		}
	}

}
