package com.xyzq.zh.stack;

/**
 * 中序转后序java算法
 * 
 * @author zhanghua
 *
 */
public class InfixCovert {
	
	private static final int MAX = 50;
	private char[] infixArray = new char[MAX];
	
	/**
	 * ICP(输入优先级)表，优先权值为INDEX/2
	 */
	public static final char[] ICP = new char[]{'q', ')', '+', '-', '*', '/', '^', ' ', '('};
	/**
	 * ISP(栈内优先级)表，优先权值为INDEX/2
	 */
	public static final char[] ISP = new char[]{'q', '(', '+', '-', '*', '/', '^', ' '};
	
	/**
	 * 中序转后序方法
	 */
	public String infix2Postfix(String input) {
		String result = "";
		int rear = 0, top = 0;
		char[] stackArray = new char[MAX];
		
		for(int i = 0; i < input.length(); i++) {
			infixArray[++rear] = input.charAt(i);
		}
		
		infixArray[rear-1] = 'q';// 在队列尾端加入q作为结束符号
		stackArray[top] = 'q';// 在栈中尾端加入q作为结束符号
		
		for(int i = 0; i < rear; i++) {
			switch(infixArray[i]) {
			case ')':
				// 输入为)，则输出堆栈内运算符，直到堆栈内位(
				while(stackArray[top] != '(') {
					result += stackArray[top--];
				}
				top--;
				break;
			case 'q':
				// 输出为q，则将栈内还未输出的运算符输出
				while(stackArray[top] != 'q') {
					result += stackArray[top--];
				}
				break;
			case '(':
			case '^':
			case '*':
			case '/':
			case '+':
			case '-':
				// 输出为运算符，若ISP>=ICP，则将栈内运算符弹出，否则将运算符入栈
				while(compare(stackArray[top], infixArray[i]) == 1) {
					result += stackArray[top--];
				}
				stackArray[++top] = infixArray[i];
				break;
			default:
				result += infixArray[i];
				break;
			}
		}
		
		return result;
	}
	
	/**
	 * 运算符优先级比较，若ISP>=ICP，则返回1，否则为0
	 * 
	 * @param ispArg
	 * @param icpArg
	 * @return
	 */
	private int compare(char ispArg, char icpArg) {
		int indexIcp =0, indexIsp = 0;
		while(ISP[indexIsp] != ispArg) {
			indexIsp++;
		}
		while(ICP[indexIcp] != icpArg) {
			indexIcp++;
		}
		return indexIsp/2 >= indexIcp/2 ? 1 : 0;
	}
	
}
