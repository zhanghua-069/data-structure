package com.xyzq.zh.graph;

import org.junit.Test;

public class AdjacencyTest {
	
	@Test
	public void testDijkstra() {
		int weightPath[][] = { {1, 2, 10}, {2, 3, 20}, {2, 4, 25}, 
							   {3, 5, 18}, {4, 5, 22}, {4, 6, 95}, 
							   {5, 6, 77} };
		System.out.println("===========================");
		System.out.println("此范例图形的相邻矩阵如下：");
		System.out.println("===========================");
		Dijkstra dijkstra = new Dijkstra(weightPath, 7);
		dijkstra.printGraphMatrix();
		dijkstra.shortestPath(1);
	}
	
	@Test
	public void testFloyd() {
		int weightPath[][] = { {1, 2, 10}, {2, 3, 20}, {2, 4, 25}, 
				   {3, 5, 18}, {4, 5, 22}, {4, 6, 95}, 
				   {5, 6, 77} };
		System.out.println("===========================");
		System.out.println("此范例图形的相邻矩阵如下：");
		System.out.println("===========================");
		Floyd floyd = new Floyd(weightPath, 7);
		floyd.printGraphMatrix();
		
		System.out.println("===========================");
		System.out.println("所有顶点两两之间的最短距离：");
		System.out.println("===========================");
		floyd.shortestPath();
	}
	
}
