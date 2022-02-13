package a0213;

import java.io.*;
import java.util.*;

public class Main_bj_3986_좋은단어_study {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_3986.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Character> stk;
		int cnt = 0;
		// 1. 첫 문자는 그냥 넣기.
		// 2. 두 번째 문자 검사 -> 같으면 pop, 다르면 push
		
		for (int n = 0; n < N; n++) {
			String s = br.readLine();
			stk = new Stack<>();
			stk.push(s.charAt(0));
			
			for (int i = 1; i < s.length(); i++) {
				if(!stk.empty()) {
					if (stk.peek() == s.charAt(i)) { // 첫 문자 = 두번째 문자
						stk.pop();
						continue;
					}
				}
				stk.push(s.charAt(i));
			}
			if (stk.empty()) cnt++;
		}
		System.out.println(cnt);
		br.close();
	}
}
