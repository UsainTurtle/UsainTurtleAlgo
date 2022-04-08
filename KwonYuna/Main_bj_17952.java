package a0210;

import java.util.*;
import java.io.*;

public class Main_bj_17952 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17952.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		LinkedList<int[]> stack = new LinkedList<>();
		int answer = 0;
		int[] tmp;
		for (int m = 0; m < N; m++) { //학기 시간 N분동안 반복
			st = new StringTokenizer(br.readLine(), " ");
			/*
			 * 1. 새 과제가 있으면, 값을 받아와 배열객체 생성
			 * 2. 새 과제가 없고, 하던 과제가 있으면 최근 과제를 가져옴
			 * 3. 새 과제도 없고, 하건 과제도 없으면 for문 넘어감
			 * 
			 * 과제 해결 시간 1 감소 후 과제가 끝났으면 과제 점수를 answer에 더함
			 * 과제 안 끝났으면 stack에 과제 넣기
			 * */
			if (st.nextToken().equals("1")) tmp = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			else if(!stack.isEmpty()) tmp = stack.pop();
			else continue;
			if(--tmp[1]==0) answer+=tmp[0];
			else stack.push(tmp);
		}
		System.out.println(answer);
		br.close();
	}
}