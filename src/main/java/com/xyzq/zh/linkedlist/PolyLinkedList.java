package com.xyzq.zh.linkedlist;

public class PolyLinkedList {
	
	class PolyNode {
		/**
		 * 系数
		 */
		int coef; 
		/**
		 * 指数
		 */
		int exp; 
		
		PolyNode next;
		
		public PolyNode(int coef, int exp) {
			this.coef = coef;
			this.exp = exp;
			this.next = null;
		}
	}
	
	/**
	 * 头节点
	 */
	public PolyNode first;
	
	/**
	 * 尾节点
	 */
	public PolyNode last;
	
	public boolean isEmpty() {
		return this.first == null;
	}
	
	public void createLink(int coef, int exp) {
		PolyNode newNode = new PolyNode(coef, exp);
		if(this.isEmpty()) {
			first = newNode;
			last = newNode;
		} else {
			last.next = newNode;
			last = newNode;
		}
	}
	
	public void printLink() {
		PolyNode current = first;
		while(current != null) {
			if(current.exp == 1 && current.coef != 0) {
				// X^1时不显示指数
				System.out.print(current.coef + "X + ");
			} else if(current.exp != 0 && current.coef != 0) {
				// 指数大于1时显示指数与系数
				System.out.print(current.coef + "X^" + current.exp + " + ");
			} else if(current.coef != 0) {
				// X^0时只显示系数
				System.out.print(current.coef);
			} 
			
			current = current.next;
		}
		System.out.println();
	}
	
	/**
	 * 链表的多项式相加
	 * 
	 * @param b
	 * @return
	 */
	public PolyLinkedList sumLink(PolyLinkedList b) {
		@SuppressWarnings("unused")
		int i = 0, maxnumber;
		/**
		 * 多项式计算结果系数存放数组
		 */
		int sum[] = new int[10];
		/**
		 * 多项式计算结果指数存放数组
		 */
		int tmpexp[] = new int[10];
		PolyLinkedList tmpLinkedList = new PolyLinkedList();
		PolyLinkedList a = this;
		PolyNode ptr = b.first;
		
		while(a.first != null) {
			b.first = ptr;
			while(b.first != null) {
				if(a.first.exp == b.first.exp) {
					// 指数相等，系数相加
					sum[i] = a.first.coef + b.first.coef;
					tmpexp[i] = a.first.exp;
					a.first = a.first.next;
					b.first = b.first.next;
					i++;
					
				} else if(b.first.exp > a.first.exp) {
					// b指数较大,指定系数给c
					sum[i] = b.first.coef;
					tmpexp[i] = b.first.exp;
					b.first = b.first.next;
					i++;
					
				} else if(b.first.exp < a.first.exp) {
					// a指数较大,指定系数给c
					sum[i] = a.first.coef;
					tmpexp[i] = a.first.exp;
					a.first = a.first.next;
					i++;
					
				}
			}
		}
		
		// 相加算法1
		/*maxnumber = i - 1;
		for(int j = 0; j < maxnumber + 1; j++) {
			tmpLinkedList.createLink(sum[j],  maxnumber - j);
		}*/
		
		// 相加算法2
		for(int j = 0; j < i; j++) {
			tmpLinkedList.createLink(sum[j],  tmpexp[j]);
		}
		
		return tmpLinkedList;
	}
	
}
