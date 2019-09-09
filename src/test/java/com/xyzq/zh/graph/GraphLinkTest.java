package com.xyzq.zh.graph;

import org.junit.Test;

public class GraphLinkTest {
	
	@Test
	public void testPrint() {
		// 图形数组声明
		int[][] data = {{1, 2}, {2, 1}, {1, 5}, {5, 1}, {2, 3}, {3, 2},
						{2, 4}, {4, 2}, {3, 4}, {4, 3}, {3, 5}, {5, 3},
						{4, 5}, {5, 4}};
		
		System.out.println("图形(a)的相邻表内容：");
		GraphLink[] linkArr = new GraphLink[6];
		for(int i = 1; i < 6; i++) {
			linkArr[i] = new GraphLink();
			System.out.print("顶点" + i + "=>");
			for(int j = 0; j < 14 ; j++) {
				if(data[j][0] == i) {
					linkArr[i].insert(data[j][1]);
				}
			}
			
			linkArr[i].print();
			System.out.println();
		}
		
	}
	
}
