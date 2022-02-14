package a0210;

import java.io.*;
import java.util.*;

public class Main_bj_3986 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_3986.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()),count=0;
		for (int tc = 0; tc < T; tc++) {
			String s = br.readLine();
			LinkedList<Character> stack = new LinkedList<>();
			for(int i=0;i<s.length();i++) {
				if(stack.isEmpty()||s.charAt(i)!=stack.peek()) stack.push(s.charAt(i));
				else stack.pop();
			}
			if(stack.isEmpty())count++;
		}
		System.out.print(count);
		br.close();
	}
}