package com.xyzq.zh.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 外部排序-直接合并排序
 * 
 * 适用场景：要排序的数据量太多或文件太大，无法直接在内存内排序，需要依赖外部存储设备
 * 适用访问方式：适用于顺序访问的文件
 * 排序步骤：
 * step1:将要排序的文件分为大小可加载到内存空间的小文件，再使用内部排序将各文件内的数据排序
 * step2：将第一步建立的小文两两合并，使用文件指针对两个文件内的各数据进行比较，将比较结果写入缓冲区，
 * 		    缓冲区满后写入磁盘，再继续比较后面的数据，直至所有文件比对完成
 * 
 * @author zhanghua
 *
 */
public class DirectMergeSort {
	
	/**
	 * 由小到大对file1，file2中的数据进行排序，排序结果写入file文件中
	 * 
	 * @param file
	 * @param file1
	 * @param file2
	 * @throws IOException 
	 */
	public void merge(File file, File file1, File file2) throws IOException {
		BufferedWriter bw = null;
		BufferedReader br1 =  null;
		BufferedReader br2 = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			br1 = new BufferedReader(new FileReader(file1));
			br2 = new BufferedReader(new FileReader(file2));
			int n1 = br1.read(); 
			int n2 = br2.read();
			
			while(n1 != -1 && n2 != -1) {// 判断是否已经到文件尾
				if(n1 <= n2) {
					bw.write((char)n1);// 若n1较小，则把n1存入file中
					n1 = br1.read();// 读取下一个n1的数据
				} else {
					bw.write((char)n2);// 若n2较小，则把n2存入file中
					n2 = br2.read();// 读取下一个n2的数据
				}
			}
			
			if(n2 != -1) {// n2仍未读取完成，将剩余的数据写入file中
				do {
					bw.write((char)n2);
				} while((n2 = br2.read()) != -1);
			} 
			
			if(n1 != -1) {// n1仍未读取完成，将剩余的数据写入file中
				do {
					bw.write((char)n1);
				} while((n1 = br1.read()) != -1);
			} 
			bw.close();
			
		} finally {
			if(br1 != null) {
				br1.close();
			}
			if(br2 != null) {
				br2.close();
			}
		}
	}
	
}
