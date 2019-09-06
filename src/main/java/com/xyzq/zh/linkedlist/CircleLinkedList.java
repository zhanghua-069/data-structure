package com.xyzq.zh.linkedlist;

/**
 * 环形链表
 * 
 * @author zhanghua
 *
 */
public class CircleLinkedList {
	
	class CircleNode {
		int data;
		CircleNode next;
		
		public CircleNode(int data) {
			this.data = data;
			this.next = null;
		}
	}
	
	public CircleNode first;
	public CircleNode last;
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void print() {
		CircleNode current = first;
		while(current != last) {
			System.out.print("[" + current.data + "]");
			current = current.next;
		}
		System.out.print("[" + current.data + "]\n");
	}
	
	/**
	 * 插入节点
	 * @param trp
	 */
	public void insert(CircleNode trp) {
		CircleNode tmp;
		CircleNode newNode;
		if(this.isEmpty()) {
			first = trp;
			last = trp;
			last.next = first;
		} else if(trp.next == null) {
			// 尾节点插入
			last.next = trp;
			last = trp;
			last.next = first;
		} else {
			// 链表中间插入
			newNode = first;
			tmp = first;
			while(newNode.next != trp.next) {
				if(tmp.next == first) {
					// 链表中仅一个节点，跳出循环
					break;
				}
				tmp = newNode;
				newNode = newNode.next;
			}
			tmp.next = trp;
			trp.next = newNode;
		}
	}
	
	/**
	 * 删除节点
	 * @param delNode
	 */
	public void delete(CircleNode delNode) {
		CircleNode newNode;
		CircleNode tmp;
		if(this.isEmpty()) {
			System.out.print("[环形链表已经空了]\n");
			
		} else if(first.data == delNode.data) {
			// 删除节点为表头
			first = first.next;
			if(first == null) {
				System.out.print("[环形链表已经空了]\n");
			}
			
		} else if(last.data == delNode.data) {
			// 删除节点为尾节点
			newNode = first;
			while(newNode.next != last) {
				newNode = newNode.next;
			}
			newNode.next = last.next;
			last = newNode;
			last.next = first;
			
		} else {
			// 删除节点为中间节点
			newNode = first;
			tmp = first;
			while(newNode.data != delNode.data) {
				tmp = newNode;
				newNode = newNode.next;
			}
			tmp.next = delNode.next;
			
		}
	}

}
