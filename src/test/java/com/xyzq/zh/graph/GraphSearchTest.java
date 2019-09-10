package com.xyzq.zh.graph;

import org.junit.Test;

public class GraphSearchTest {
	
	@Test
	public void testDfs() {
		// 图形数组声明
		int[][] data = {{1, 2}, {2, 1}, {1, 3}, {3, 1}, {2, 4}, {4, 2},
						{2, 5}, {5, 2}, {3, 6}, {6, 3}, {3, 7}, {7, 3},
						{4, 5}, {5, 4}, {6, 7}, {7, 6}, {5, 8}, {8, 5}, 
						{6, 8}, {8, 6}};
		
		System.out.println("图形的相邻表内容：");
		GraphLink[] linkArr = new GraphLink[9];
		for(int i = 1; i < 9; i++) {
			linkArr[i] = new GraphLink();
			System.out.print("顶点" + i + "=>");
			for(int j = 0; j < 20 ; j++) {
				if(data[j][0] == i) {
					linkArr[i].insert(data[j][1]);
				}
			}
			
			linkArr[i].print();
		}
		
		System.out.println("先深后广遍历顶点：");
		GraphSearch graphSearch = new GraphSearch(9, linkArr, null);
		graphSearch.dfs(1);
	}
	
	@Test
	public void testBfs() {
		// 图形数组声明
		int[][] data = {{1, 2}, {2, 1}, {1, 3}, {3, 1}, {2, 4}, {4, 2},
						{2, 5}, {5, 2}, {3, 6}, {6, 3}, {3, 7}, {7, 3},
						{4, 5}, {5, 4}, {6, 7}, {7, 6}, {5, 8}, {8, 5}, 
						{6, 8}, {8, 6}};
		
		System.out.println("图形的相邻表内容：");
		GraphLink[] linkArr = new GraphLink[9];
		for(int i = 1; i < 9; i++) {
			linkArr[i] = new GraphLink();
			System.out.print("顶点" + i + "=>");
			for(int j = 0; j < 20 ; j++) {
				if(data[j][0] == i) {
					linkArr[i].insert(data[j][1]);
				}
			}
			
			linkArr[i].print();
		}
		
		System.out.println("先广后深遍历顶点：");
		GraphQueue queue = new GraphQueue(10);
		GraphSearch search = new GraphSearch(9, linkArr, queue);
		search.bfs(1);
	}
	
}
