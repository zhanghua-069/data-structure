package com.xyzq.zh.stack;

/**
 * 使用数组模拟堆栈
 * 
 * @author zhanghua
 *
 */
public class StackByArray {
	
	private int[] stack;// 栈内存放数据的数组
	private int top;// 指向栈顶的指针
	
	public StackByArray(int size) {
		stack = new int[size];
		top = -1;
	}
	
	/**
	 * 将数据压入栈顶
	 * 
	 * @param data
	 * @return
	 */
	public boolean push(int data) {
		if(top >= stack.length) {
			System.out.println("堆栈已满，无法再加入");
			return false;
		} else {
			// 指针指向栈顶，压入数据
			stack[++top] = data;
			return true;
		}
	}
	
	/**
	 * 判断堆栈是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		if(top == -1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 从栈顶取出数据
	 * 
	 * @return
	 */
	public int pop() {
		if(isEmpty()) {
			return -1;
		} 
		// 取出栈顶数据，指针往下移
		return stack[top--];
	}

	public int getTop() {
		return top;
	}
	
}
