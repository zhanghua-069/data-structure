package com.xyzq.zh.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.junit.Test;

/**
 * 顺序查找测试案例
 * 
 * @author zhanghua
 *
 */
public class OrderSearchTest {
	
	@Test
	public void testSearch() throws Exception {
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		int val, size = 80;
		int data[] = new int[size];
		// 初始化数据
		for(int i=0; i < size; i++) {
			data[i] = (int) ((Math.random() * 150) % 150 + 1);
		}
		
		do {// 执行顺序查找
			int find = 0;
			System.out.println("请输入查找键值(1-150), 输入-1离开: ");
			val = Integer.parseInt(buf.readLine());
			for(int i=0; i<size; i++) {
				if(data[i] == val) {
					System.out.println("在第" + (i+1) + "个位置找到键值 [" + data[i] + "]");
					find++;
				}
			}
			
			if(find == 0 && val != -1) {
				System.out.println("########## 没有找到[" + val + "] ##########");
			}
			
		} while(val != -1);
		
		int line=10, col = (size/line);
		System.out.println("数据内容：");
		for(int i=0; i<line; i++) {
			for(int j=0; j<col; j++) {
				System.out.print(i*col + (j+1) + "[" + data[i*col+j] + "] ");
			}
			System.out.println();
		}
	}
	
}
