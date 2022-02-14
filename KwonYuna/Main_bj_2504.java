package a0210;

import java.util.*;
import java.io.*;

public class Main_bj_2504 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2504.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<Integer> stack = new LinkedList<>();
		String s = br.readLine();

		int i = 0;
		for (i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(' || s.charAt(i) == '[') {
				int num = s.charAt(i) == '(' ? 2 : 3;
				if (i + 1 == s.length()) break;
				if (s.charAt(i + 1) == (s.charAt(i) == '(' ? ')' : ']')) {
					if (!stack.isEmpty() && 0 < stack.peek()) stack.push(stack.pop() + num);
					else stack.push(num);
					i++;
				} else {
					stack.push(-num);
				}
			} else {
				int num = s.charAt(i) == ')' ? 2 : 3;
				if (stack.isEmpty()) break;
				int value = stack.pop();
				if (!stack.isEmpty() && 0 < value && stack.pop() == -num) {
					value *= num;
					if (!stack.isEmpty() && 0 < stack.peek())
						value += stack.pop();
					stack.push(value);
				} else {
					break;
				}
			}
		}
		
		int result = 0;
		if (!stack.isEmpty()) result = stack.pop();
		if (i < s.length() || !stack.isEmpty()) System.out.print(0);
		else System.out.print(result);
		
		br.close();
	}
}