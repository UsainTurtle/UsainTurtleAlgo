package a0210.study;

import java.io.*;
import java.util.*;

/*
1. 최근에 나온 순서대로, 과제 받으면 바로시작
2. 하던중 새로운 과제가 나오면, 새 과제 진행
3. 새 과제를 끝내면, 이전에 하던 부분부터 이어서 진행

입력
학기 분 N (1<=N<=1000000)
N분째에 주어진 과제
1 A T : 과제의 만점이 A, 과제 해결 T분소요, (1<=A<=100, 1<=T<=1000000)
0 : 해당 시점 과제x

출력
성애가 받을 과제 점수

 */

public class Main_bj_17952_과제는끝나지않아 {

	public static void main(String[] args)throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17952.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Stack<int[]> stack = new Stack<>();
		
		int sum=0;
		for(int t=1; t<=N; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			if(st.nextToken().equals("1")) stack.add(new int[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});	// 첫 수가 1일경우
			
			if(!stack.isEmpty()) {	//스택이 비어있지않으면
				int[] p = stack.pop();	//T를 1감소후 다시 넣는다
				if(--p[1]==0) sum+=p[0];	// T가 0이면 A를 더하고
				else stack.add(p);		// 0이아니면 다시 값 넣기
				
			}
		}
		System.out.println(sum);
		br.close();
	}

}
