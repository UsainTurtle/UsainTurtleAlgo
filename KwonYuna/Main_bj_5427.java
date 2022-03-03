package a0228;

import java.io.*;
import java.util.*;

public class Main_bj_5427 {
	final static int[] di = { 0, 1, 0, -1 };
	final static int[] dj = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			char[][] arr = new char[N][M];
			int[][] fire = new int[N][M];

			boolean[][] visited = new boolean[N][M];
			LinkedList<int[]> queue = new LinkedList<>();

			int me_i=0,me_j=0;
			
			for (int i = 0; i < N; i++) {
				String tmp = br.readLine();
				Arrays.fill(fire[i], Integer.MAX_VALUE);
				for (int j = 0; j < M; j++) {
					arr[i][j] = tmp.charAt(j);
					if (arr[i][j] == '@') {
						me_i=i;
						me_j=j;
						arr[i][j] = '.';
					} else if (arr[i][j] == '*') {
						visited[i][j] = true;
						fire[i][j] = 0;
						queue.offer(new int[] {i,j});
					} else if (arr[i][j] == '#') {
						visited[i][j] = true;
						fire[i][j] = -1;
					}
				}
			}

			// 시간마다 불 번지는 영역 체크
			while (!queue.isEmpty()) {
				int[] ij=queue.poll();

				for (int z = 0; z < 4; z++) {
					int ni = ij[0] + di[z];
					int nj = ij[1] + dj[z];
					if (0 <= ni && ni < N && 0 <= nj && nj < M && !visited[ni][nj]
							&& fire[ni][nj] == Integer.MAX_VALUE) {
						visited[ni][nj] = true;
						fire[ni][nj] = fire[ij[0]][ij[1]] + 1;
						queue.offer(new int[] {ni,nj});
					}
				}
			}

			boolean result = false;
			int time = 0;
			queue.offer(new int[] { me_i, me_j, 0 });
			visited = new boolean[N][M];
			exit: while (!queue.isEmpty()) {
				int[] ijt = queue.poll();
				int i = ijt[0];
				int j = ijt[1];

				for (int z = 0; z < 4; z++) {
					int ni = i + di[z];
					int nj = j + dj[z];
					if (0 <= ni && ni < N && 0 <= nj && nj < M) {
						if (!visited[ni][nj] && arr[ni][nj] == '.' && ijt[2] + 1 < fire[ni][nj]) {
							visited[ni][nj] = true;
							queue.offer(new int[] { ni, nj, ijt[2] + 1 });
						}
					} else {
						time = ijt[2] + 1;
						result = true;
						break exit;
					}
				}
			}
			sb.append(result ? time : "IMPOSSIBLE").append("\n");
		}

		System.out.print(sb.toString());
		br.close();
	}
}