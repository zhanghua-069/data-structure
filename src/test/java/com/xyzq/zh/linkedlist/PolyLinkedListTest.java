package com.xyzq.zh.linkedlist;

import org.junit.Test;

public class PolyLinkedListTest {
	
	@Test
	public void testSumLink() {
		PolyLinkedList a = new PolyLinkedList();
		PolyLinkedList b = new PolyLinkedList();
		PolyLinkedList c = new PolyLinkedList();
		
		int data1[] = {8, 54, 7, 0, 1, 3, 0, 4, 2};// 多项式A的系数
		int data2[] = {-2, 6, 0, 0, 0, 5, 6, 8, 6, 9};// 多项式B的系数
		for(int i = 0; i < data1.length; i++) {
			// 建立多项式A
			a.createLink(data1[i], data1.length -i -1);
		}
		
		for(int i = 0; i < data2.length; i++) {
			// 建立多项式B
			b.createLink(data2[i], data2.length -i -1);
		}
		
		System.out.print("原始多项式：\nA=");
		a.printLink();
		System.out.print("B=");
		b.printLink();
		
		System.out.print("多项式相加结果：\nC=");
		c = a.sumLink(b);
		c.printLink();
	}
	
}
