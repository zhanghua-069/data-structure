package com.xyzq.zh.linkedlist;

/**
 * 双向链表
 * 
 * @author zhanghua
 *
 */
public class DoublyLinkedList {
	
	class DoublyNode {
		int data;
		DoublyNode lnext;
		DoublyNode rnext;
		
		public DoublyNode(int data) {
			this.data = data;
			lnext = null;
			rnext = null;
		}
	}
	
	public DoublyNode first;
	public DoublyNode last;
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void print() {
		DoublyNode current = first;
		while(current != null) {
			System.out.print("[" + current.data + "]");
			current = current.rnext;
		}
		System.out.println();
	}
	
	/**
	 * 插入节点
	 * @param trp
	 */
	public void insert(DoublyNode trp) {
		DoublyNode tmp;
		DoublyNode newNode;
		if(this.isEmpty()) {
			first = trp;
			first.rnext = last;
			last = trp;
			last.lnext = first;
		} else {
			if(trp.lnext == null) {
				// 在表头位置插入
				first.lnext = trp;
				trp.rnext = first;
				first = trp;
				
			} else if(trp.rnext == null) {
				// 在表尾的位置插入节点
				last.rnext = trp;
				trp.lnext = last;
				last = trp;
				
			} else {
				// 链表中间插入
				newNode = first;
				tmp = first;
				while(trp.rnext != newNode.rnext) {
					tmp = newNode;
					newNode = newNode.rnext;
				}
				tmp.rnext = trp;
				trp.rnext = newNode;
				newNode.lnext = trp;
				trp.lnext = tmp;
			}
		}
	}
	
	/**
	 * 删除节点
	 * @param delNode
	 */
	public void delete(DoublyNode delNode) {
		DoublyNode newNode;
		DoublyNode tmp;
		if(delNode == null) {
			System.out.print("[错误：del不是表中的节点]\n");
			return;
		}
		if(this.isEmpty()) {
			System.out.print("[表是空的]\n");
			
		} else {
			if(first.data == delNode.data) {
				// 删除节点为表头
				first = first.rnext;
				first.lnext = null;
				
			} else if(last.data == delNode.data) {
				// 删除节点为表尾
				newNode = first;
				while(newNode.rnext != last) {
					newNode = newNode.rnext;
				}
				newNode.rnext = null;
				last = newNode;
				
			} else {
				// 删除链表中间节点
				newNode = first;
				tmp = first;
				while(newNode.data != delNode.data) {
					tmp = newNode;
					newNode = newNode.rnext;
				}
				tmp.rnext = delNode.rnext;
				newNode = newNode.rnext;
				newNode.lnext = delNode.lnext;
			}
		}
	}

}
