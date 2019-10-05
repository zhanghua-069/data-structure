package com.xyzq.zh.search;

/**
 * 哈希查找-再哈希（利用链表）
 * 程序目的：使用哈希法快速的建立和查找数据
 * 
 * @author zhanghua
 *
 */
public class LinkedHashSearch {
	
	public HashNode indexTable[];
	
	public LinkedHashSearch() {
		
	}
	
	public LinkedHashSearch(HashNode indexTable[]) {
		this.indexTable = indexTable;
	}
	
	/**
	 * 建立哈希表
	 * 
	 * @param val
	 */
	public void createTable(int val) {
		HashNode newNode = new HashNode(val);
		int hash = val % indexTable.length;
		HashNode current = indexTable[hash];
		
		if(current.next == null) {
			current.next = newNode;
		} else {
			while(current.next !=  null) {
				current = current.next;
			}
			
			// 将节点加在链表的最后
			current.next = newNode;
		}
	}
	
	/**
	 * 打印哈希表
	 */
	public void printTable() {
		for(int i=0; i < indexTable.length; i++) {
			HashNode head = indexTable[i].next;// 找到起始节点
			int num = 0;
			System.out.print("  " + i + ":  ");
			while(head != null) {
				System.out.print("[" + head.val + "]-");
				num++;
				if(num % 8 == 7) {// 控制输出长度
					System.out.println();
				}
				head = head.next;
			}
			System.out.println();// 迭代换行
		}
	}
	
	/**
	 * 哈希法查找目标数据
	 * 
	 * @param num
	 * @return
	 */
	public int findNum(int num) {
		int count = 0;
		int hash = num % indexTable.length;
		HashNode head = indexTable[hash].next;
		while(head != null) {
			count++;
			if(head.val == num) {
				return count;
			} else {
				head = head.next;
			}
		}
		
		return -1;
	}
	
}
