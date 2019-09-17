package com.xyzq.zh.sort;

import java.util.Arrays;

/**
 * 高级排序法-基数排序法
 * 
 * 基数排序：稳定排序法
 * 排序原理：按比较方向可分为最高位优先（MSD，从最左边的位数开始比较）和最低位优先（LSD，从最右边的位数开始比较）两种
 * 时间复杂度：O(nlogpk)；n：原始数据的个数，p是数据字符数（比较的位数），k是原始数据的最大值
 * 空间复杂度：O(n*p)
 * 适用场景：n很大，p固定或很小，此排序法将很有效率
 * 
 * @author zhanghua
 *
 */
public class RadixSort {
	
	private int[] data;
	
	public RadixSort(int[] data) {
		this.data = data;
	}
	
	/**
	 * 数据由小到大排序
	 * 排序方式：LSD，数据范围(0,1000)
	 */
	public void radix() {
		// 依次对个位、十位、百位的数值做排序
		for(int n=1; n <= 100; n=n*10) {
			// 定义暂存数组，[0~9位数][在对应的下标存放对应数据]
			int tmp[][] = new int[10][data.length];
			for(int i=0; i < data.length; i++) {
				int m = (data[i]/n)%10;// m为n位的数值
				tmp[m][i] = data[i];// 将data[i]暂存在tmp中
			}
			
			int k = 0;
			for(int i=0; i < 10; i++) {
				for(int j=0; j < data.length; j++) {
					if(tmp[i][j] != 0) {
						// 针对n位的数值排序，将排序结果放回data[]中
						data[k] = tmp[i][j];
						k++;
					}
				}
			}
			
			System.out.println("经过" + n + "位排序后：" + Arrays.toString(data));
		}
		
		System.out.println("排序后结果为：" + Arrays.toString(data));
	}
	
}
