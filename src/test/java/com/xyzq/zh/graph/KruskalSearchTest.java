package com.xyzq.zh.graph;

import java.util.Arrays;

import org.junit.Test;

public class KruskalSearchTest {
	
	@Test
	public void testFindmin() {
		int data[][] = {{1, 2, 6}, {1, 6, 12}, {1, 5, 10}, {2, 3, 3}, {2, 4, 5}, {2, 6, 8}, 
						{3, 4, 7}, {4, 6, 11}, {4, 5, 9}, {5, 6, 16}};
		KruskalNode kruskalNode = new KruskalNode(20);
		int head = 0;
		System.out.println("建立图形表：");
		for(int i = 0; i < 10; i++) {
			for(int j = 1; j <= 6; j++) {
				if(data[i][0] == j) {
					int fromNum = data[i][0];
					int toNum = data[i][1];
					int dataNum = data[i][2];
					int free = kruskalNode.findFree();
					
					// 建立链表
					kruskalNode.create(head, free, dataNum, fromNum, toNum);
					break;// 跳出当层循环
				}
			}
		}
		
		kruskalNode.print(head);
		
		System.out.println("建立最小成本生成树");
		KruskalSearch search = new KruskalSearch(6, kruskalNode);
		search.mintree();
		
		System.out.println("node.from: " + Arrays.toString(kruskalNode.from));
		System.out.println("node.to: " + Arrays.toString(kruskalNode.to));
		System.out.println("node.val: " + Arrays.toString(kruskalNode.val));
		System.out.println("node.next: " + Arrays.toString(kruskalNode.next));
		System.out.println("search.record: " + Arrays.toString(search.record));
	}
	
}
