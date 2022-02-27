package a0228;

import java.io.*;
import java.util.*;

public class Main_bj_3190 {
	static int[] di = { 0, 1, 0, -1 };
	static int[] dj = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int A = Integer.parseInt(br.readLine());
		int[][] arr_ap = new int[A][];
		for (int i = 0; i < A; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			arr_ap[i] = new int[] { Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1 };
		}
		
		int C = Integer.parseInt(br.readLine());
		int time = 0, direction = 0;
		int[] s_st = new int[] { 0, 0 }, s_ed = new int[] { 0, 0 };
		boolean[][] pan = new boolean[N][N];
		pan[0][0]=true;
		LinkedList<Integer> Q = new LinkedList<>();
		
		C--;
		st = new StringTokenizer(br.readLine(), " ");
		int change = Integer.parseInt(st.nextToken());
		char al = st.nextToken().charAt(0);

		while (true) {
			boolean small = true;
			time++;
			s_st[0] += di[direction];
			s_st[1] += dj[direction];
			if (s_st[0] < 0 || N<=s_st[0] || s_st[1] < 0 || N<=s_st[1]||pan[s_st[0]][s_st[1]])
				break; // 게임끝
			pan[s_st[0]][s_st[1]]=true;
			Q.offer(s_st[0]);
			Q.offer(s_st[1]);
			for (int i = 0; i < A; i++) {
				if (s_st[0] == arr_ap[i][0] && s_st[1] == arr_ap[i][1]) {
					small = false;
					arr_ap[i] = arr_ap[--A];
					break;
				}
			}
			if (small) {
				pan[s_ed[0]][s_ed[1]]=false;
				s_ed[0] = Q.poll();
				s_ed[1] = Q.poll();
			}

			if (time == change) {
				if (al == 'D') {// 오른쪽
					direction = (direction + 1) % 4;
				} else {
					direction = direction == 0 ? 3 : direction - 1;
				}
				if (0 < C--) {
					st = new StringTokenizer(br.readLine(), " ");
					change = Integer.parseInt(st.nextToken());
					al = st.nextToken().charAt(0);
				}
			}
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					if(pan[i][j]) System.out.print(7);
//					else System.out.print(0);
//				}
//				System.out.println();
//			}
//			System.out.println("====================");
		}

		System.out.println(time);

		br.close();
	}
}

