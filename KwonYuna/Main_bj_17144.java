package a0225;

import java.io.*;
import java.util.*;

public class Main_bj_17144 {
	static final int[] di = { 0, -1, 0, 1 };
	static final int[] dj = { 1, 0, -1, 0 };
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int st_i = 0;

		arr = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				arr[i][j] = Short.parseShort(st.nextToken());
			}
			if (st_i == 0 && arr[i][0] == -1)
				st_i = i;
		}

		for (int t = 0; t < T; t++) {
			spread(new boolean[R][C]);
			System.out.println("spread"+t);
			for(int[] a:arr)System.out.println(Arrays.toString(a));
			System.out.println();
			cleaning(st_i);
			System.out.println("cleaning"+t);
			for(int[] a:arr)System.out.println(Arrays.toString(a));
			System.out.println();
		}
		int n_dust = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (0 < arr[i][j])
					n_dust += arr[i][j];
			}
		}
		System.out.println(n_dust);
		br.close();
	}

	static void spread(boolean visited[][]) {
		int[][] tArr = new int[arr.length][arr[0].length];
		LinkedList<Integer> queue = new LinkedList<>();
		queue.offer(0);
		queue.offer(0);
		visited[0][0] = true;

		while (!queue.isEmpty()) {
			int i = queue.poll();
			int j = queue.poll();
			int count = 0;
			int dust = arr[i][j] / 5;

			for (int z = 0; z < 4; z++) {
				int ni = i + di[z];
				int nj = j + dj[z];

				if (0 <= ni && ni < arr.length && 0 <= nj && nj < arr[0].length && -1 != arr[ni][nj]) {
					count++;
					tArr[ni][nj] += dust;

					if (!visited[ni][nj]) {
						visited[ni][nj] = true;
						queue.offer(ni);
						queue.offer(nj);
					}
				}
			}
			tArr[i][j] += arr[i][j] - dust * count;
		}
		arr = tArr;
	}

	static void cleaning(int st_i) {
		// f 우상좌하 0123
		// s 우하좌상 0321
		int f_i = st_i, f_j = 1, s_i = st_i + 1, s_j = 1;
		int f_z = 0, s_z = 0, f_dust = arr[f_i][f_j], s_dust = arr[s_i][s_j];
		
		arr[f_i][0]=arr[s_i][0]=-1;
		arr[f_i][f_j]=arr[s_i][s_j]=0;
		
		while (arr[f_i][f_j] != -1 || arr[s_i][s_j] != -1) {
			if (arr[f_i][f_j] != -1) {
				int f_ni = f_i + di[f_z];
				int f_nj = f_j + dj[f_z];
					if ((f_ni < 0 || arr.length <= f_ni || f_nj < 0 || arr[0].length <= f_nj)&&f_z!=3) {
						f_z++;
						f_ni = f_i + di[f_z];
						f_nj = f_j + dj[f_z];
					}
					f_i = f_ni;
					f_j = f_nj;
					if (arr[f_ni][f_nj] != -1) {
					int tmp = arr[f_i][f_j];
					arr[f_i][f_j] = f_dust;
					f_dust = tmp;
				}
			}
			if (arr[s_i][s_j] != -1) {
				int s_ni = s_i + di[s_z];
				int s_nj = s_j + dj[s_z];
					if ((s_ni < 0 || arr.length <= s_ni || s_nj < 0 || arr[0].length <= s_nj)&&s_z!=1) {
						s_z = s_z == 0 ? 3 : s_z - 1;
						s_ni = s_i + di[s_z];
						s_nj = s_j + dj[s_z];
					}
					s_i = s_ni;
					s_j = s_nj;
					if (arr[s_ni][s_nj] != -1) {
					int tmp = arr[s_i][s_j];
					arr[s_i][s_j] = s_dust;
					s_dust = tmp;
				}
			}
		}
	}
}
