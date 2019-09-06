package com.xyzq.zh.stack;

import org.junit.Test;

/**
 * 老鼠走迷宫问题
 * 
 * @author zhanghua
 *
 */
public class MazeTest {
	
	/**
	 * 出口的X坐标
	 */
	public static final int EXITX = 8;
	/**
	 * 出口的Y坐标
	 */
	public static final int EXITY = 10;
	
	/**
	 * 迷宫数组
	 */
	public static final int MAZE[][] = new int[][] {{1,1,1,1,1,1,1,1,1,1,1,1}
												   ,{1,0,0,0,1,1,1,1,1,1,1,1}
												   ,{1,1,1,0,1,1,0,0,0,0,1,1}
												   ,{1,1,1,0,1,1,0,1,1,0,1,1}
												   ,{1,1,1,0,0,0,0,1,1,0,1,1}
												   ,{1,1,1,0,1,1,0,1,1,0,1,1}
												   ,{1,1,1,0,1,1,0,1,1,0,1,1}
												   ,{1,1,1,1,1,1,0,1,1,0,1,1}
												   ,{1,1,0,0,0,0,0,0,1,0,0,1}
												   ,{1,1,1,1,1,1,1,1,1,1,1,1}};
	
	@Test
	public void testExit() {
		int x = 1, y = 1;
		// 老鼠走过的路径节点
		TraceRecord path = new TraceRecord();
		
		System.out.println("[迷宫的路径(0的部分)]");
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 12; j++) {
				System.out.print(MAZE[i][j]);
			}
			System.out.println();
		}
		
		// 老鼠走迷宫（先将下一个节点放入path，再将该节点标记为2（已走过））
		while(x <= EXITX && y <= EXITY) {
			MAZE[x][y] = 2;
			// 路线选择 上、下、左、右
			if(MAZE[x-1][y] == 0) {
				x--;
				path.insert(x, y);
			} else if(MAZE[x+1][y] == 0) {
				x++;
				path.insert(x, y);
			} else if(MAZE[x][y-1] == 0) {
				y--;
				path.insert(x, y);
			} else if(MAZE[x][y+1] == 0) {
				y++;
				path.insert(x, y);
			} else if(checkExit(x, y, EXITX, EXITY) == 1) {
				// 走出迷宫
				break;
			} else {
				// 走到死胡同往回走，标记当前节点为2（已走过），并从链表path中删除（last节点），再会退到上一个节点（新的last节点）
				MAZE[x][y] = 2;
				path.delete();
				x = path.last.x;
				y = path.last.y;
			}
		}
		
		System.out.println("[老鼠走过的路径(2的部分)]");
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 12; j++) {
				System.out.print(MAZE[i][j]);
			}
			System.out.println();
		}
	}
	
	private int checkExit(int x, int y, int ex, int ey) {
		if(x == ex && y == ey) {
			if(MAZE[x-1][y] == 1 || MAZE[x+1][y] == 1 || MAZE[x][y-1] == 1 || MAZE[x][y+1] == 2) {
				return 1;
			}
			if(MAZE[x-1][y] == 1 || MAZE[x+1][y] == 1 || MAZE[x][y-1] == 2 || MAZE[x][y+1] == 1) {
				return 1;
			}
			if(MAZE[x-1][y] == 1 || MAZE[x+1][y] == 2 || MAZE[x][y-1] == 1 || MAZE[x][y+1] == 1) {
				return 1;
			}
			if(MAZE[x-1][y] == 2 || MAZE[x+1][y] == 1 || MAZE[x][y-1] == 1 || MAZE[x][y+1] == 1) {
				return 1;
			}
		}
		return 0;
	}
	
	class Node {
		int x;
		int y;
		Node next;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
			this.next = null;
		}
	}
	
	class TraceRecord {
		public Node first;
		public Node last;
		
		public boolean isEmpty() {
			return first == null;
		}
		
		public void insert(int x, int y) {
			Node newNode = new Node(x, y);
			if(this.isEmpty()) {
				first = newNode;
				last = newNode;
			} else {
				last.next = newNode;
				last = newNode;
			}
		}
		
		public void delete() {
			if(isEmpty()) {
				System.out.println("[队列已经空了]\n");
			} else {
				Node newNode = first;
				while(newNode.next != last) {
					newNode = newNode.next;
				}
				newNode.next = last.next;
				last = newNode;
			}
		}
		
	}

}
