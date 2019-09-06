package com.xyzq.zh.linkedlist;

/**
 * 自定义单向链表linkedList
 * 
 * @author zhanghua
 *
 */
public class StudLinkedList {
	
	/**
	 * 头节点
	 */
	Node first;
	/**
	 * 尾节点
	 */
	Node last;
	
	/**
	 * 初始方法1：链表是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return first == null;
	}
	
	/**
	 * 初始方法2：打印链表各节点
	 */
	public void print() {
		Node current = first;
		while(current != null) {
			System.out.println("[" + current.data + " " + current.name + " " + current.score + "]");
			current = current.next;
		}
		System.out.println();
	}
	
	/**
	 * 初始方法3：新增节点到链表尾端
	 * 
	 * @param data
	 * @param name
	 * @param score
	 */
	public void insert(int data, String name, int score) {
		Node newNode = new Node(data, name, score);
		if(this.isEmpty()) {
			first = newNode;
			last = newNode;
		} else {// 链表非空，从尾端加入节点
			last.next = newNode;
			last = newNode;
		}
	}
	
	public void delete(Node delNode) {
		Node newNode;
		Node tmp;
		if(first.data == delNode.data) {
			// 如果删除的节点是头节点：将头节点指针后移一位即可
			first = first.next;
		} else if(last.data == delNode.data) {
			// 如果要删除的节点是尾节点：遍历节点至尾节点的前一个节点，将该节点设置为last节点，同时指向null
			System.out.println("I am here\n");
			newNode = first;
			while(newNode.next != last) {
				newNode = newNode.next;
			}
			newNode.next = last.next;
			last = newNode;
		} else {
			// 如果要删除的节点是中间节点：遍历节点至中间节点的前一个节点，将其指针指向要删除节点的下一个节点
			newNode = first;
			tmp = first;// 目标节点定位
			while(newNode.data != delNode.data) {
				tmp = newNode;
				newNode = newNode.next;
			}
			tmp.next = delNode.next;
		}
	}

}
