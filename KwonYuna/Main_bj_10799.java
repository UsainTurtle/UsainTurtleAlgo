package a0210;

import java.io.*;

public class Main_bj_10799 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_10799.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s=br.readLine();
		int st_size = 0;
		int result=0;
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				if (s.charAt(i+1) == ')') {
					result+=st_size;
					i++;
				}else {
					st_size++;
					result++;
				}
			} else {
				st_size--;
			}
		}
		System.out.print(result);
		br.close();
	}

}
