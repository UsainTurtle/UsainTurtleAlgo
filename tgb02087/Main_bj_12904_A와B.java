package a0224.study;

import java.io.*;
import java.util.*;

/*

조건
문자열의 뒤에 A를 추가
문자열을 뒤집고 뒤에 B를 추가

입력
S (1<=S<=999)
T (2<=T<1000)
S<T

출력
S를 T로 바꿀수 있으면 1 없으면 0
 */
public class Main_bj_12904_A와B {

	public static void main(String[] args)throws Exception {
		System.setIn(new FileInputStream("res/input_bj_12904.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();
		while(T.length()!=S.length()) {	//T와S 같지않으면 반복
			char a = T.charAt(T.length()-1);	
			if(a=='A') T=T.substring(0,T.length()-1);	//맨뒷글자가 A이면 하나 뺀다
			else {	//B이면
				T=T.substring(0,T.length()-1);	//하나 뺀다
				char[] t = new char[T.length()];
				for(int i=0, j=T.length()-1; i<T.length()||0<=j;i++,j--) t[j]=T.charAt(i);	//뒤집는다.
				T=String.copyValueOf(t);
			}
		}
		if(S.equals(T)) System.out.println(1);
		else System.out.println(0);
		br.close();
	}
}
