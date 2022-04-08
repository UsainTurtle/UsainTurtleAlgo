package a0224;

import java.io.*;

public class Main_bj_12904 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringBuilder t = new StringBuilder(br.readLine());
		boolean re = false;
		while(s.length()<t.length()) {
			char al;
			if(re) {
				al=t.charAt(0);
				t=t.deleteCharAt(0);
			}else {
				al=t.charAt(t.length()-1);
				t=t.deleteCharAt(t.length()-1);
			}
			if(al=='B') re=!re;
		}
		String result="";
		if(re) {
			for(int i=t.length()-1;0<=i;i--) {
				result+=t.charAt(i);
			}
		}else {
			result = t.toString();
		}
		
		if(!result.equals(s))System.out.println(0);
		else System.out.println(1);
		br.close();
	}
}
