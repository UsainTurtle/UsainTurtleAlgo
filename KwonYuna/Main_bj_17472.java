package a0303;

import java.io.*;
import java.util.*;

public class Main_bj_17472 {
	final static int[] di = { -1, 0, 1, 0 };
	final static int[] dj = { 0, 1, 0, -1 };

	static int[][] arr;
	static boolean[][] v;
	static int[] p;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		v = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt_area = 0; //섬 개수
		for (int i = 0; i < N; i++) {//섬 체크
			for (int j = 0; j < M; j++) {
				if (0 < arr[i][j] && !v[i][j])
					dfs(i, j, ++cnt_area); 
			}
		}

		ArrayList<int[]> vertex = new ArrayList<>(); //간선 리스트

		for (int i = 0; i < N; i++) { //세로로 다리를 놓을 경우 
			int col_cnt = 0, col_num = 0;
			for (int j = 0; j < M; j++) {
				if (0 < arr[i][j]) {
					if (col_num == 0 || col_num == arr[i][j]) {
						col_num = arr[i][j];
						col_cnt = 0;
					} else {
						if (1 < col_cnt)
							vertex.add(new int[] { col_num, arr[i][j], col_cnt });
						col_num = arr[i][j];
						col_cnt = 0;
					}
				} else {
					col_cnt++;
				}
			}
		}

		for (int j = 0; j < M; j++) { //가로로 다리를 놓을 경우 
			int row_cnt = 0, row_num = 0;
			for (int i = 0; i < N; i++) {
				if (0 < arr[i][j]) {
					if (row_num == 0 || row_num == arr[i][j]) {
						row_num = arr[i][j];
						row_cnt = 0;
					} else {
						if (1 < row_cnt)
							vertex.add(new int[] { row_num, arr[i][j], row_cnt });
						row_num = arr[i][j];
						row_cnt = 0;
					}
				} else {
					row_cnt++;
				}
			}
		}

		//간선 리스트 최소 다리길이로 정렬
		Collections.sort(vertex, (o1, o2) -> Integer.compare(o1[2], o2[2]));
		
		p = new int[cnt_area + 1];
		for (int i = 1; i <= cnt_area; i++)
			p[i] = i;
		int result = 0;
		for (int[] item : vertex) {
			if (union(item[0], item[1])) {
				result += item[2];
				if (1 == --cnt_area)
					break;
			}
		}
		if (cnt_area == 1)
			System.out.println(result);
		else
			System.out.println(-1);
	}

	static int find(int a) {
		if (p[a] == a)
			return a;
		return p[a] = find(p[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;
		p[aRoot] = bRoot;
		return true;
	}

	static void dfs(int i, int j, int cnt) {
		v[i][j] = true;
		arr[i][j] = cnt;
		
		for (int z = 0,N = arr.length, M = arr[0].length; z < 4; z++) {
			int ni = i + di[z];
			int nj = j + dj[z];
			if (0 <= ni && ni < N && 0 <= nj && nj < M && !v[ni][nj] && 0 < arr[ni][nj]) {
				dfs(ni,nj,cnt);
			}
		}

	}

}
