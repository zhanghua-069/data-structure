package com.xyzq.zh.search;

/**
 * 二分查找法
 * 
 * 查找过程：将事先排序好的数据分割成两等分，再比较键值与中间值的大小，如果键值小于中间值，可确定要找的数据在前半段，
 * 否则在后半段；如此分割数次直到找到或确定不存在为止
 * 时间复杂度：因为每次查找都会比上一次少一半的范围，时间复杂度为O(log n)
 * 使用前提：数据事先经过排序，且数据量必须能直接在内存中执行
 * 适用场景：适用于不需要增删的静态数据
 * 
 * @author zhanghua
 *
 */
public class BinarySearch {

	public int search(int data[], int val) {
		int low = 0, high = data.length - 1, mid;
		System.out.println("查找处理中......");
		while (low <= high && val != -1) {
			mid = (low + high) / 2;
			if (val < data[mid]) {// 目标数据小于中间值
				System.out.println(val + " 介于位置" + (low + 1) + "[" + data[low] + "]及中间值 " + (mid + 1) + "[" + data[mid]
						+ "], 往左半边继续查找");
				high = mid - 1;
			} else if (val > data[mid]) {// 目标数据大于中间值
				System.out.println(val + " 介于中间值位置" + (mid + 1) + "[" + data[mid] + "]及 " + (high + 1) + "["
						+ data[high] + "], 往右半边继续查找");
				low = mid + 1;
			} else {
				return mid;
			}
		}

		return -1;
	}

}
