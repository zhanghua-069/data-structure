package com.xyzq.zh.graph;

/**
 * 图形相邻矩阵类声明
 * 
 * @author zhanghua
 *
 */
public class Adjacency {
	
	/**
	 * 最大距离常量
	 */
	public static final int INFINITE = 99999;
	/**
	 * 图形矩阵
	 */
	public int[][] graphMatrix;
	
	public Adjacency(int[][] weightPath, int size) {
		graphMatrix = new int[size][size];
		// i=1， j=1：weightPath的节点从1开始
		for(int i = 1; i < size; i++) {
			for(int j = 1; j < size; j++) {
				if(i != j) {
					graphMatrix[i][j] = INFINITE;
				}
			}
		}
		
		for(int i = 0; i < weightPath.length; i++) {
			int start = weightPath[i][0];
			int end = weightPath[i][1];
			graphMatrix[start][end] = weightPath[i][2];
		}
	}
	
	/**
	 * 打印图形矩阵
	 */
	public void printGraphMatrix() {
		for(int i = 1; i < graphMatrix.length; i++) {
			for(int j = 1; j < graphMatrix[i].length; j++) {
				if(graphMatrix[i][j] == INFINITE) {
					System.out.print(" x ");
				} else if(graphMatrix[i][j] == 0) {
					System.out.print(" 0 ");
				} else {
					System.out.print(graphMatrix[i][j] + " ");
				}
			}
			System.out.println();
		}
	}
	
}
