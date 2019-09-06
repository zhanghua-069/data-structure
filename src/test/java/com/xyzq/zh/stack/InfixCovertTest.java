package com.xyzq.zh.stack;

import java.io.IOException;

import org.junit.Test;

public class InfixCovertTest {

	@Test
	public void testInfix2Postfix() {
		System.out.println("=====================================");
		System.out.println("本程序会将其转成后序表达式");
		System.out.println("请输入中序表达式");
		System.out.println("例如：(9+3)*8+7*6-12/4");
		System.out.println("可以使用的运算符包括:^,*,+,-,/,(,)等");
		System.out.println("=====================================");
		System.out.print("请开始输入中序表达式：");

		String input = "";
		char arg = ' ';
		do {
			try {
				arg = (char) System.in.read();
				input += arg;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (arg != '\n');

		String result = new InfixCovert().infix2Postfix(input);
		System.out.println("后序表示法：" + result);
		System.out.println("=====================================");
	}

}
