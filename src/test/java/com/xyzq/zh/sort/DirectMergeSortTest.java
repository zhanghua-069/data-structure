package com.xyzq.zh.sort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.util.ResourceUtils;

/**
 * 直接排序测试案例
 * 
 * @author zhanghua
 *
 */
public class DirectMergeSortTest {
	
	@Test
	public void testSort() throws IOException {
		File file = ResourceUtils.getFile("classpath:sort/data.txt");
		File file1 = ResourceUtils.getFile("classpath:sort/data1.txt");
		File file2 = ResourceUtils.getFile("classpath:sort/data2.txt");
		
		if(!file.exists()) {
			System.out.println("开启主文件失败");
		}
		if(!file1.exists()) {
			System.out.println("开启数据文件1失败");
		} 
		if(!file2.exists()) {
			System.out.println("开启数据文件2失败");
		} 
		
		// 直接合并排序测试
		System.out.println("数据排序中......");
		DirectMergeSort sort = new DirectMergeSort();
		sort.merge(file, file1, file2);
		System.out.println("数据处理完成！！");
		
		List<Character> list = new ArrayList<Character>();
		List<Character> list1 = new ArrayList<Character>();
		List<Character> list2 = new ArrayList<Character>();
		int read;
		BufferedReader br = null;
		BufferedReader br1 =  null;
		BufferedReader br2 = null;
		try {
			br = new BufferedReader(new FileReader(file));
			br1 = new BufferedReader(new FileReader(file1));
			br2 = new BufferedReader(new FileReader(file2));
			while((read = br1.read()) != -1) {
				list1.add((char)read);
			}
			System.out.println("data1.txt 数据内容为：" + list1);
			
			while((read = br2.read()) != -1) {
				list2.add((char)read);
			}
			System.out.println("data2.txt 数据内容为：" + list2);
			
			while((read = br.read()) != -1) {
				list.add((char)read);
			}
			System.out.println("排序后 data.txt 数据内容为：" + list);
			
		} finally {
			if(br != null) {
				br.close();
			}
			if(br1 != null) {
				br1.close();
			}
			if(br2 != null) {
				br2.close();
			}
		}
	}
	
}
