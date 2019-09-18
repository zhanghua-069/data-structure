package com.xyzq.zh.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 外部排序-完整实现的合并排序法
 * 步骤：拆分-内部排序-合并
 * 
 * @author zhanghua
 *
 */
public class CompleteMergeSort {
	
	/**
	 * 分割源文件并进行合并排序
	 * 
	 * @param source	源文件
	 * @param file1		分割文件1		
	 * @param file2		分割文件2
	 * @param file		合并排序后文件
	 * @throws IOException
	 */
	public void mergeAndSort(File source, File file1, File file2, File file) throws IOException {
		BufferedReader br =  null;
		BufferedReader br1 =  null;
		BufferedWriter bw1 =  null;
		BufferedWriter bw2 = null;
		int count = 0, read, i;
		try {
			br = new BufferedReader(new FileReader(source));
			bw1 = new BufferedWriter(new FileWriter(file1));
			bw2 = new BufferedWriter(new FileWriter(file2));
			while((read = br.read()) != -1) {// 统计源文件的字符数
				count++;
			}
			
			br1 = new BufferedReader(new FileReader(source));
			// 读取源文件前1/2数据写入分割文件1中
			for(i = 0; i < count/2; i++) {
				bw1.write((char)br1.read());
			}
			// 【注意】 必须将close()直接写在write完之后，否则数据无法写入文件中
			bw1.close();
			// 使用冒泡排序法对分割文件1进行内部排序
			bubble(file1, i);
			
			// 读取源文件剩余的一半数据写入分割文件2中，此处需将计数器i重置为0
			i = 0;
			while((read = br1.read()) != -1) {
				bw2.write((char)read);
				i++;
			} 
			bw2.close();
			// 使用冒泡排序法对分割文件2进行内部排序
			bubble(file2, i);
			
			// 对已完成内部排序的分割文件1和2进行合并排序
			merge(file, file1, file2);
			
		} finally {
			if(br1 != null) {
				br1.close();
			}
			if(br != null) {
				br.close();
			}
		}
	}
	
	/**
	 * 对file中的内容进行冒泡排序
	 * 
	 * @param file	目标文件
	 * @param size	目标文件数据长度
	 * @throws IOException
	 */
	public void bubble(File file, int size) throws IOException {
		int data[] = new int[size];
		BufferedReader read =  null;
		BufferedWriter write = null;
		try {
			read = new BufferedReader(new FileReader(file));
			for(int i = 0; i < size; i++) {
				data[i] = read.read();// 将文件中数据读入data[]中
			}
			
			write = new BufferedWriter(new FileWriter(file));
			// 对目标文件的数据使用冒泡法进行排序
			for(int i = size-1; i > 0; i--) {
				int flag = 0;
				for(int j = 0; j < i; j++) {
					if(data[j+1] < data[j]) {
						int tmp = data[j];
						data[j] = data[j+1];
						data[j+1] = tmp;
						flag++;
					}
				}
				
				// 可中断的冒泡排序
				if(flag == 0) {
					break;
				}
			}
			
			// 将已排序好的数据重新回写到目标文件中
			for(int i = 0; i < size; i++) {
				write.write((char)data[i]);
			}
			// 关闭写入流，否则无法保证数据写入文件中
			write.close();
			
		} finally {
			if(read != null) {
				read.close();
			}
		}
	}
	
	/**
	 * 由小到大对file1，file2中的数据进行排序，排序结果写入file文件中
	 * 
	 * @param file
	 * @param file1
	 * @param file2
	 * @throws IOException 
	 */
	public void merge(File file, File file1, File file2) throws IOException {
		BufferedWriter br = null;
		BufferedReader br1 =  null;
		BufferedReader br2 = null;
		try {
			br = new BufferedWriter(new FileWriter(file));
			br1 = new BufferedReader(new FileReader(file1));
			br2 = new BufferedReader(new FileReader(file2));
			// n1，n2读取的内容为ASCII
			int n1 = br1.read(); 
			int n2 = br2.read();
			
			while(n1 != -1 && n2 != -1) {// 判断是否已经到文件尾
				if(n1 <= n2) {
					br.write((char)n1);// 若n1较小，则把n1存入file中
					n1 = br1.read();// 读取下一个n1的数据
				} else {
					br.write((char)n2);// 若n2较小，则把n2存入file中
					n2 = br2.read();// 读取下一个n2的数据
				}
			}
			
			if(n2 != -1) {// n2仍未读取完成，将剩余的数据写入file中
				do {
					br.write((char)n2);
				} while((n2 = br2.read()) != -1);
			} 
			
			if(n1 != -1) {// n1仍未读取完成，将剩余的数据写入file中
				do {
					br.write((char)n1);
				} while((n1 = br1.read()) != -1);
			} 
			br.flush();
			
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
