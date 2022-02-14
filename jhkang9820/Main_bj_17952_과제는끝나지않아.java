package a0210;

import java.io.*;
import java.util.*;

public class Main_bj_17952_과제는끝나지않아 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17952.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		int num = 0;
		Stack<int[]> task = new Stack<>();
		
		int total = 0;
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			if (st.nextToken().charAt(0) == '1') { // 1
				int score = Integer.parseInt(st.nextToken());
				int min = Integer.parseInt(st.nextToken());
				
				if (min == 1) total += score;
				else task.push(new int[] {score, min-1});
			} 
			
			else { // '0'인 경우
				if (!task.empty()) {
					int[] a = task.pop();
					if (a[1] == 1) total += a[0];
					else { a[1]--; task.push(a); }
				}
			}
		}
		System.out.println(total);
		br.close();
	}
}
