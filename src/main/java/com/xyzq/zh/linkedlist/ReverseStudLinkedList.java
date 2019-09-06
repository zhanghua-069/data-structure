package com.xyzq.zh.linkedlist;

import java.util.Random;

/**
 * 单向链表反转
 * 
 * @author zhanghua
 *
 */
public class ReverseStudLinkedList extends StudLinkedList {
	
	public void reversePrint() {
		Node current = first;
		Node before = null;
		System.out.println("反转后的链表数据：");
		while(current != null) {
			last = before;
			before = current;
			current = current.next;
			before.next = last;
		}
		current = before;
		
		while(current != null) {
			System.out.println("[" + current.data + " " + current.name + " " + current.score + "]");
			current = current.next;
		}
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		Random rand = new Random();
		ReverseStudLinkedList list = new ReverseStudLinkedList();
		int i, j, data[][] = new int[12][2];
		String[] name = new String[]{"Allen", "Scott", "Marry", "Jon", "Mark", "Ricky", 
				"Lisa", "Jasica", "Hanson", "Amy", "Bob", "Jack"};
		System.out.println("学号成绩学号成绩学号成绩学号成绩\n ");
		for(i = 0; i < 12; i++) {
			data[i][0] = i + 1;
			data[i][1] = Math.abs(rand.nextInt(50)) + 50;
			list.insert(data[i][0], name[i], data[i][1]);
		}
		
		for(i = 0; i < 3; i++) {
			for(j = 0; j < 4; j++) {
				System.out.print("[" + data[j*3 + i][0] + "][" + data[j*3 + i][1] + "]  ");
			}
			System.out.println();
		}
		
		list.reversePrint();
		
	}
	
}
