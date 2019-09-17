package com.xyzq.zh.sort;

/**
 * 高级排序法-堆积排序法
 * 
 * 堆积排序：不稳定排序法
 * 排序原理：选择排序法的改进版，减少选择排序中的比较次数，进而减少排序时间；利用堆积树（heap tree）进行排序
 * 时间复杂度：所有情况下都为O(nlogn)
 * 空间复杂度：O(1)
 * 
 * @author zhanghua
 *
 */
public class HeapSort {
	
	private int[] data;
	private int process = 1;
	
	public HeapSort(int[] data) {
		this.data = data;
	}
	
	/**
	 * 由小到大排序
	 */
	public void heap() {
		// 建立初始堆积树
		// 在完全二叉树中i(0~length/2)必然有子节点
		for(int i = (data.length/2); i > 0; i--) {
			createHeap(i, data.length -1);
		}
		
		System.out.print("堆积树内容：");
		print();
		System.out.println();
		
		// 初始堆积树的根节点为最大值，所以i从data.length - 2开始以保证i+1为当前的堆积树最后一个叶子节点
		for(int i = data.length - 2; i > 0; i--) {
			// 将根节点的值与当前堆积树的最后一个叶子节点交换
			int tmp = data[i+1];
			data[i+1] = data[1];
			data[1] = tmp;
			
			// 将剩余待排序节点生成堆积树，保证根节点为最大值
			createHeap(1, i);
			System.out.print("[处理过程" + (process++) + "]=> ");
			print();
		}
		
		System.out.print("排序后结果为：");
		print();
	}
	
	/**
	 * 由下往上逐一比较建立堆积树节点
	 * 步骤：
	 * step1：由下往上，由右往左找到最底部的叶子节点，判断是否有兄弟节点
	 * step2：若有兄弟节点，先取左右子节点中较大的那一个与父节点比较，若子节点大于父节点，则交换相互位置
	 * step3：以步骤2中的父节点为新的子节点继续往上先兄弟后父节点比较，直至根节点位置
	 * step4：完成右子树的遍历后再进行左子树的遍历，最终生成堆积树
	 * 
	 * @param root	起始父节点下标
	 * @param size
	 */
	public void createHeap(int root, int size) {
		int tmp = data[root];// 起始父节点
		int j = 2 * root;// 定位到父节点的左子节点
		int post = 0;// 标识比较过程是否结束
		while(j <= size && post == 0) {
			// 从上往下查找子树，结束条件post=1或已查找到叶子节点(j>size)
			if(j < size) {
				if(data[j] < data[j+1]) {// 比较父节点下左右子节点的大小，取较大的那一个与父节点做比较
					j++;
				}
			}
			
			// tmp=data[root]，data[j]=data[2*root]
			// 完全二叉树中，tmp为data[j]与data[j+1]的根节点
			if(tmp >= data[j]) {// 父节点较大，结束比较过程
				post = 1;
			} else {// 父节点较小
				data[j/2] = data[j];// 将叶子节点的值赋值给父节点
				j = 2*j;// 继续用新的父节点与子树的各节点比较
			}
		}
		
		data[j/2] = tmp;// 将起始父节点插入到最终的子节点位置
	}
	
	public void print() {
		for(int i = 1; i < data.length; i++) {
			System.out.print("[" + data[i] + "] ");
		}
		System.out.println();
	}
	
}
