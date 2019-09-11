package com.xyzq.zh.graph;

/**
 * Floyd算法，计算所有顶点两两直接的最短距离
 * 
 * @author xyzq
 *
 */
public class Floyd extends Adjacency {
	
	/**
	 * 记录顶点两两之间的最短距离
	 */
	private int[][] cost;
	private int capacity;

	public Floyd(int[][] weightPath, int size) {
		super(weightPath, size);
		cost = new int[size][];
		capacity = graphMatrix.length;
		for(int i = 0; i < capacity; i ++) {
			cost[i] = new int[size];
		}
	}
	
	/**
	 * 获取所有顶点两两之间的最短距离
	 */
	public void shortestPath() {
		for(int i = 1; i < graphMatrix.length; i++) {
			for(int j = i; j < graphMatrix.length; j++) {
				cost[i][j] = cost[j][i] = graphMatrix[i][j];
			}
		}
		
		for(int k = 1; k < graphMatrix.length; k++) {
			for(int i = 1; i < graphMatrix.length; i++) {
				for(int j = 1; j < graphMatrix.length; j++) {
					if(cost[i][k] + cost[k][j] < cost[i][j]) {
						cost[i][j] = cost[i][k] + cost[k][j];
					}
				}
			}
		}
		
		System.out.print("顶点             ");
		for(int i = 1; i < graphMatrix.length; i++) {
			System.out.print("vex" + i + " ");
		}
		System.out.println();
		
		for(int i = 1; i < graphMatrix.length; i++) {
			System.out.print("vex" + i + "  ");
			for(int j = 1; j < graphMatrix.length; j++) {
				// 调整显示时数组元素间距的位置
				if(cost[i][j] < 10) {
					System.out.print(" ");
				}
				if(cost[i][j] < 100) {
					System.out.print(" ");
				}
				System.out.print(" " + cost[i][j] + " ");
			} 
			System.out.println();
		}
		
	}

}
