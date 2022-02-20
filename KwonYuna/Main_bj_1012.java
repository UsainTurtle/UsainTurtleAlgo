import java.io.*;
import java.util.*;

public class Main_bj_1012 {
	final static int[] di = { -1, 0, 1, 0 };
	final static int[] dj = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken()),N = Integer.parseInt(st.nextToken()),K = Integer.parseInt(st.nextToken());

			char[][] arr = new char[M][N];
			boolean[][] v = new boolean[M][N];
			LinkedList<Integer> queue = new LinkedList<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
			}
			int count=0;
			
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == 1 && !v[i][j]) {
						v[i][j] = true;
						count++;
						queue.offer(i);
						queue.offer(j);
						while (!queue.isEmpty()) {
							int ti=queue.poll(), tj=queue.poll();
							for (int z = 0; z < 4; z++) {
								int ni = ti + di[z];
								int nj = tj + dj[z];
								if (0 <= ni && ni < M && 0 <= nj && nj < N && !v[ni][nj] && arr[ni][nj] == 1) {
									v[ni][nj] = true;
									queue.offer(ni);
									queue.offer(nj);
								}
							}
						}
					}
				}
			}
			sb.append(count).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}
}
