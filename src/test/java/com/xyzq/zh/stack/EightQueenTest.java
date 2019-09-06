package com.xyzq.zh.stack;

import java.io.IOException;

import org.junit.Test;

public class EightQueenTest {
	
	private static final int SIZE = 8;
	/**
	 * 存放8皇后行的位置
	 */
	private static final int[] queen = new int[SIZE];
	/**
	 * 计算共有几组解的总和
	 */
	private int number = 0;
	
	@Test
	public void test() {
		decidePosition(0);
	}
	
	/**
	 * 按Enter键函数
	 */
	@SuppressWarnings("unused")
	private void PressEnter() {
		System.out.print("\n\n");
		System.out.println("...按下Enter键继续...");
		try {
			char tChar = (char) System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 决定皇后存放的位置
	 * @param col
	 */
	private void decidePosition(int col) {
		int row = 0;
		while(row < SIZE) {
			// 判断是否收到攻击
			if(!attack(row, col)) {
				queen[col] = row;
				if(col == 7) {
					printTable();
				} else {
					// 在此处发生递归，当迭代到7发现仍然没有合适的节点，则回退到上一个迭代方法的入口将row++继续往下尝试执行
					// 如此反复递归测试直到八皇后问题得解
					decidePosition(col + 1);
				}
			}
			row++;
		}
	}
	
	/**
	 * 测试在(row, col)上的皇后是否遭到攻击
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean attack(int row, int col) {
		boolean atk = false;
		int i = 0, offsetRow = 0, offsetCol = 0;
		while(!atk && i < col) {
			// 因为i<col，所以只需判断同一行或同一对角线上是否有两个皇后
			// offsetCol与offsetRow用来查找当前节点(row, col)的对角线上是否有已经存在的皇后节点(queen[i], i)
			offsetCol = Math.abs(i - col);
			offsetRow = Math.abs(queen[i] - row);
			if(offsetRow == 0 || offsetRow == offsetCol) {
				atk = true;
			} else {
				i ++;
			}
		}
		return atk;
		
	}
	
	/**
	 * 输出所要的结果
	 */
	private void printTable() {
		System.out.println();
		System.out.print("八皇后问题的第" + (number++) + "组解\n");
		for(int x = 0; x < SIZE; x++) {
			for(int y = 0; y < SIZE; y++) {
				if(x == queen[y]) {
					System.out.print("<*>");
				} else {
					System.out.print("<->");
				}
			}
			System.out.println("\t");
		}
		PressEnter();
	}

}
