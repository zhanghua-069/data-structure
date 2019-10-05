package com.xyzq.zh.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import org.junit.Test;

/**
 * 哈希查找-再哈希（利用链表）测试案例
 * 
 * @author zhanghua
 *
 */
public class LinkedHashSearchTest {
	
	@Test
	public void testSearch() throws IOException {
		int indexBox = 7;// 哈希表最大长度
		int size = 13;
		int data[] = new int[size];
		HashNode[] indexTable = new HashNode[indexBox];
		Random random = new Random();
		
		for(int i=0; i<indexBox; i++) {// 清理哈希表
			indexTable[i] = new HashNode(-1);
		}
		
		System.out.println("原始数据：");
		for(int i=0; i<size; i++) {// 数据表赋初始值
			data[i] = Math.abs(random.nextInt(30)) + 1;
			System.out.print("[" + data[i] + "]");
			if(i%8 == 7) {
				System.out.println();
			}
		}
		System.out.println();
		
		LinkedHashSearch linkedHash = new LinkedHashSearch(indexTable);
		for(int i=0; i<size; i++) {
			linkedHash.createTable(data[i]);
		}
		
		// 测试哈希查找功能
		BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.print("请输入查找数据(1-30), 结束请输入-1：");
			int num = Integer.parseInt(keyin.readLine());
			if(num == -1) {
				break;
			}
			int count = linkedHash.findNum(num);
			if(count == -1) {
				System.out.println("########## 没有找到" + num + " ##########");
			} else {
				System.out.println("找到" + num + ", 共找了 " + count + " 次！");
			} 
		}
		
		// 打印哈希表
		System.out.println("\n 哈希表：");
		linkedHash.printTable();
		
	}
	
}
