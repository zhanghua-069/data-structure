package com.xyzq.zh.graph;

/**
 * 使用相邻表表示图形
 * 
 * @author zhanghua
 *
 */
public class GraphLink {
	
	class Node {
		int x;
		Node next;
		
		public Node(int x) {
			this.x = x;
			this.next = null;
		}
		
	}
	
	public Node first;
	public Node last;
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void insert(int x) {
		Node newNode = new Node(x);
		if(this.isEmpty()) {
			first = newNode;
			last = newNode;
		} else {
			last.next = newNode;
			last = newNode;
		}
	}
	
	public void print() {
		Node current = first;
		while(current != null) {
			System.out.print("[" + current.x + "]");
			current = current.next;
		}
		System.out.println();
	}
	
}
