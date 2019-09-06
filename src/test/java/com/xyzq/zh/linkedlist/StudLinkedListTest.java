package com.xyzq.zh.linkedlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import org.junit.Test;

public class StudLinkedListTest {
	
	@Test
	public void testPrint() throws Exception {
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		int num, score;
		String name;
		
		System.out.println("请输入5个学生数据：  ");
		StudLinkedList list = new StudLinkedList();
		for(int i = 1; i < 6; i++) {
			System.out.print("请输入学号： ");
			num = Integer.parseInt(buf.readLine());
			System.out.print("请输入姓名： ");
			name = buf.readLine();
			System.out.print("请输入成绩： ");
			score = Integer.parseInt(buf.readLine());
			list.insert(num, name, score);
			System.out.println("-----------");
		}
		System.out.println(" 学生成绩 ");
		System.out.println(" 学号姓名成绩 ===========");
		list.print();
	}
	
	/**
	 * 测试节点删除
	 * @throws IOException 
	 */
	@Test
	public void testDelete() throws IOException {
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		Random rand = new Random();
		StudLinkedList list = new StudLinkedList();
		int i, j ,findword = 0, data[][] = new int[12][2];
		String name[] = new String[]{"Allen", "Scott", "Marry", "Jon", "Mark", 
				"Ricky", "Lisa", "Jasica", "Hanson", "Amy", "Bob", "Jack"};
		
		System.out.println("学号成绩学号成绩学号成绩学号成绩\n");
		for(i = 0; i < 12; i++) {
			data[i][0] = i + 1;
			data[i][1] = Math.abs(rand.nextInt(50)) + 50;
			list.insert(data[i][0], name[i], data[i][1]);
		}
		
		for(i = 0; i < 3; i++) {
			for(j = 0; j < 4; j++) {
				System.out.print("[" + data[j*3 + i][0] + "]["+ data[j*3 + i][1] + "]  ");
			}
			System.out.println();
		}
		
		while(true) {
			System.out.println("请输入要删除成绩的学号，结束输入-1：  ");
			findword = Integer.parseInt(buf.readLine());
			if(findword == -1) {
				break;
			} else {
				Node current = new Node(list.first.data, list.first.name, list.first.score);
				current.next = list.first.next;
				while(current.data != findword) {
					current = current.next;
				}
				list.delete(current);
			}
			System.out.println("删除后成绩列表，请注意！要删除的成绩其学号必须在此列表中\n");
			list.print();
		}
		
	}
	
}
