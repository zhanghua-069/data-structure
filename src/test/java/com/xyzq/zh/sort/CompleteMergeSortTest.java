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
 * 完整的合并排序测试案例
 * 
 * @author zhanghua
 *
 */
public class CompleteMergeSortTest {
	
	@Test
	public void testSort() throws IOException {
		File file = ResourceUtils.getFile("classpath:sort/sort.txt");
		File file1 = ResourceUtils.getFile("classpath:sort/sort1.txt");
		File file2 = ResourceUtils.getFile("classpath:sort/sort2.txt");
		File source = ResourceUtils.getFile("classpath:sort/source.txt");
		
		if(!source.exists()) {
			System.out.println("开启数据文件失败");
		}
		if(!file.exists()) {
			System.out.println("开启合并文件失败");
		}
		if(!file1.exists()) {
			System.out.println("开启分割文件1失败");
		} 
		if(!file2.exists()) {
			System.out.println("开启分割文件2失败");
		} 
		
		// 直接合并排序测试
		System.out.println("文件分割中......");
		CompleteMergeSort sort = new CompleteMergeSort();
		sort.mergeAndSort(source, file1, file2, file);
		System.out.println("数据排序中......");
		System.out.println("数据处理完成！！");
		
		List<Character> slist = new ArrayList<Character>();
		List<Character> list = new ArrayList<Character>();
		List<Character> list1 = new ArrayList<Character>();
		List<Character> list2 = new ArrayList<Character>();
		int read;
		BufferedReader br = null;
		BufferedReader br1 =  null;
		BufferedReader br2 = null;
		BufferedReader bf = null;
		try {
			br = new BufferedReader(new FileReader(source));
			br1 = new BufferedReader(new FileReader(file1));
			br2 = new BufferedReader(new FileReader(file2));
			bf = new BufferedReader(new FileReader(file));
			
			while((read = br.read()) != -1) {
				slist.add((char)read);
			}
			System.out.println("原始文件 source.txt 数据内容为：" + slist);
			
			while((read = br1.read()) != -1) {
				list1.add((char)read);
			}
			System.out.println("分割文件 sort1.txt 数据内容为：" + list1);
			
			while((read = br2.read()) != -1) {
				list2.add((char)read);
			}
			System.out.println("分割文件 sort2.txt 数据内容为：" + list2);
			
			while((read = bf.read()) != -1) {
				list.add((char)read);
			}
			System.out.println("排序后 sort.txt 数据内容为：" + list);
			
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
			if(bf != null) {
				bf.close();
			}
		}
	}
	
}
