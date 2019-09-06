package com.xyzq.zh.linkedlist;

import java.util.Random;

public class ConcatStudLinkedList extends StudLinkedList {
	
	/**
	 * 环形链表串联
	 * 
	 * @param stuList
	 * @return
	 */
	public StudLinkedList concat(StudLinkedList stuList) {
		this.last.next = stuList.first;
		this.last = stuList.last;
		
		return this;
	}
	
	public static void main(String[] args) {
		Random rand = new Random();
		ConcatStudLinkedList list1 = new ConcatStudLinkedList();
		StudLinkedList list2 = new StudLinkedList();
		int i, j, data[][] = new int[12][2];
		String name1[] = new String[]{"Allen", "Scott", "Marry", "Jon", "Mark", "Ricky", "Michael", "Tom"};
		String name2[] = new String[]{"Lisa", "Jasica", "Hanson", "Amy", "Bob", "Jack", "John", "Andy"};
		System.out.println("学号成绩学号成绩学号成绩学号成绩\n");
		for(i = 0; i < 8; i++) {
			data[i][0] = i + 1;
			data[i][1] = Math.abs(rand.nextInt(50)) + 50;
			list1.insert(data[i][0], name1[i], data[i][1]);
		}
		for(i = 0; i < 2; i++) {
			for(j = 0; j < 4; j++) {
				System.out.print("[" + data[j + i*4][0] + "][" + data[j + i*4][1] + "]  ");
			}
			System.out.println();
		}
		
		for(i = 0; i < 8; i++) {
			data[i][0] = i + 9;
			data[i][1] = Math.abs(rand.nextInt(50)) + 50;
			list2.insert(data[i][0], name2[i], data[i][1]);
		}
		for(i = 0; i < 2; i++) {
			for(j = 0; j < 4; j++) {
				System.out.print("[" + data[j + i*4][0] + "][" + data[j + i*4][1] + "]  ");
			}
			System.out.println();
		}
		
		list1.concat(list2);
		list1.print();
	}
	
}
