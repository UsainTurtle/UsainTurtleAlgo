package a0221;

import java.io.*;
import java.util.*;

public class Main_bj_2206 {
	final static int[] di = { -1, 0, 1, 0 };
	final static int[] dj = { 0, 1, 0, -1 };

	static int[][][] cost;
	static char[][] arr;
	static boolean[][] v;
	static ArrayDeque<Integer> queue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		v = new boolean[N][M];
		cost = new int[N][M][2];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = s.charAt(j);
			}
		}
		queue = new ArrayDeque<>();
		cost[0][0][0] = 1;
		v[0][0] = true;
		queue.offer(0);
		queue.offer(0);

		bfs();

		int r_0 = cost[N - 1][M - 1][0], r_1 = cost[N - 1][M - 1][1];
		int result = r_0 < r_1 ? r_0 : r_1;
		if (r_0 == 0 && 0 < r_1)
			result = r_1;
		else if (0 < r_0 && r_1 == 0)
			result = r_0;
		else if (result == 0)
			result = -1;
		System.out.println(result);
		br.close();
	}

	static void bfs() {
		while (!queue.isEmpty()) {
			ArrayDeque<Integer> tQueue = new ArrayDeque<>();
			boolean re = false;
			int i = queue.poll(), j = queue.poll();
			for (int z = 0, N = arr.length, M = arr[0].length; z < 4; z++) {

				int ni = i + di[z];
				int nj = j + dj[z];
				if (0 <= ni && ni < N && 0 <= nj && nj < M) {
					if (!v[ni][nj]) {
						v[ni][nj] = true;
						queue.offer(ni);
						queue.offer(nj);
					} else {
						if (arr[i][j] == '1') {
							if (0 < cost[ni][nj][0] && (cost[i][j][1] == 0 || cost[ni][nj][0] + 1 < cost[i][j][1])) {
								cost[i][j][1] = cost[ni][nj][0] + 1;
								re = true;
							}else if(cost[ni][nj][0] == 0 && arr[ni][nj] == '0') {
								tQueue.offer(ni);
								tQueue.offer(nj);
							}
						} else {
							if (0 < cost[ni][nj][0] && (cost[i][j][0] == 0 || cost[ni][nj][0] + 1 < cost[i][j][0])) {
								cost[i][j][0] = cost[ni][nj][0] + 1;
								re = true;
							}
							if (0 < cost[ni][nj][1] && (cost[i][j][1] == 0 || cost[ni][nj][1] + 1 < cost[i][j][1])) {
								cost[i][j][1] = cost[ni][nj][1] + 1;
								re = true;
							}
							if ((cost[ni][nj][0] == 0 && arr[ni][nj] == '0')
									|| (cost[ni][nj][1] == 0 && arr[ni][nj] == '1')) {
								tQueue.offer(ni);
								tQueue.offer(nj);
							}
						}
					}
				}
			}

			if (re) {
				while (!tQueue.isEmpty()) {
					queue.offer(tQueue.poll());
					queue.offer(tQueue.poll());
				}
			}
		}
	}
}
