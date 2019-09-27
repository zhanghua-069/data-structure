package com.xyzq.zh.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.junit.Test;

/**
 * 插值查找测试案例
 * 
 * @author zhanghua
 *
 */
public class InterpolationSearchTest {
	
	@Test
	public void testSearch() throws Exception {
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		int val=1, size = 50;
		int data[] = new int[size];
		// 初始化一个数据经过由小到大排序的数组
		for(int i=0; i < size; i++) {
			data[i] = val;
			val += (int) ((Math.random() * 100) % 5 + 1);
		}
		
		InterpolationSearch search = new InterpolationSearch();
		while(val != -1) {// 执行插值查找
			System.out.println("请输入查找键值(1-" + data[data.length-1] + "), 输入-1离开: ");
			val = Integer.parseInt(buf.readLine());
			if(val == -1) {
				break;
			}
			
			int find = search.search(data, val);
			if(find == -1) {
				System.out.println("########## 没有找到[" + val + "] ##########");
			} else {
				System.out.println("在第" + (find+1) + "个位置找到 [" + data[find] + "]");
			}
			
		}
		
		int line=5, col = (size/line);
		System.out.println("数据内容：");
		for(int i=0; i<line; i++) {
			for(int j=0; j<col; j++) {
				System.out.print(i*col + (j+1) + "[" + data[i*col+j] + "] ");
			}
			System.out.println();
		}
	}
	
}
