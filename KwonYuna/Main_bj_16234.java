package a0228;

import java.io.*;
import java.util.*;

public class Main_bj_16234 {
	final static int[] di = { 0, 1, 0, -1 };
	final static int[] dj = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		LinkedList<Integer> queue = new LinkedList<>();
		LinkedList<Integer> tqueue = new LinkedList<>();
		int[][] arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int day = -1, cnt = 1;
		
		while (cnt<N*N) {
			boolean[][] visited = new boolean[N][N];
			cnt = 0;
			day++;
			for (int ii = 0; ii < N; ii++) {
				for (int jj = 0; jj < N; jj++) {
					if (visited[ii][jj])
						continue;
					cnt++;
					visited[ii][jj] = true;
					int C = 1;
					int P = arr[ii][jj];
					queue.offer(ii);
					queue.offer(jj);

					while (!queue.isEmpty()) {
						int i = queue.poll();
						int j = queue.poll();
						for (int z = 0; z < 4; z++) {
							int ni = i + di[z];
							int nj = j + dj[z];
							if (0 <= ni && ni < N && 0 <= nj && nj < N && !visited[ni][nj]) {
								int num = Math.abs(arr[i][j] - arr[ni][nj]);
								if (L <= num && num <= R) {
									visited[ni][nj] = true;
									C++;
									P += arr[ni][nj];
									queue.offer(ni);
									queue.offer(nj);
								}
							}
						}
						tqueue.offer(i);
						tqueue.offer(j);
					}

					int value = P / C;
					while (!tqueue.isEmpty()) {
						int i = tqueue.poll();
						int j = tqueue.poll();
						arr[i][j] = value;
					}
				}
			}
		}
		System.out.println(day==-1?0:day);
		br.close();
	}
}