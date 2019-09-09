package com.xyzq.zh.graph;

import org.junit.Test;

public class GraphTest {
	
	@Test
	public void testPrint1() {
		// 定义无向图各边的起点与终点值
		int[][] data = {{1, 2}, {2, 1}, {1, 5}, {5, 1}, {2, 3}, {3, 2},
						{2, 4}, {4, 2}, {3, 4}, {4, 3}, {3, 5}, {5, 3},
						{4, 5}, {5, 4}};
		System.out.println("data[7][0]=" + data[7][0]);
		System.out.println("data[7][1]=" + data[7][1]);
		System.out.println("data[10][0]=" + data[10][0]);
		System.out.println("data[10][1]=" + data[10][1]);
		
		// 声明矩阵arr
		int arr[][] = new int[6][6];
		
		for(int i = 0; i < 14; i++) {
			int tmpi = data[i][0];// 起始顶点
			int tmpj = data[i][1];// 起始终点
			arr[tmpi][tmpj] = 1;
		}
		System.out.println("无向图形矩阵：");
		for(int i = 1; i < 6; i++) {
			for(int j = 1; j < 6; j++) {
				// 打印矩阵内容
				System.out.print("[" + arr[i][j] + "] ");
			}
			System.out.println();
		}
		
	}
	
	@Test
	public void testPrint2() {
		// 定义有向图各边的起点与终点值
		int[][] data = {{1, 2}, {2, 1}, {2, 3}, {2, 4}, {4, 3}};
		
		// 声明矩阵arr
		int arr[][] = new int[5][5];
		
		for(int i = 0; i < 5; i++) {
			int tmpi = data[i][0];// 起始顶点
			int tmpj = data[i][1];// 起始终点
			arr[tmpi][tmpj] = 1;
		}
		System.out.println("有向图形矩阵：");
		for(int i = 1; i < 5; i++) {// 矩阵下标从1开始:{1, 2, 3, 4}
			for(int j = 1; j < 5; j++) {
				// 打印矩阵内容
				System.out.print("[" + arr[i][j] + "] ");
			}
			System.out.println();
		}
	}
	
}
