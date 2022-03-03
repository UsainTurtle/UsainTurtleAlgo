import java.util.*;
import java.io.*;

public class Solution_bj_10026 {

	static char[][] arr;
	static boolean[][] visited;
	static int[] ni = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] nj = { 0, 0, -1, 1 };
	static int n;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_10026.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		arr = new char[n][n];
		visited = new boolean[n][n];

		for (int i = 0; i < n; i++) { // 입력
			String temp = br.readLine();
			arr[i] = temp.toCharArray();
		}
		// 정상인
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					dfs(i, j);
					cnt++;
				}
			}
		}
		visited = new boolean[n][n];
		//색약 처리
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] == 'G') {
					arr[i][j] = 'R';
				}
			}
		}
		int res = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					dfs(i, j);
					res++;
				}
			}
		}
		System.out.println(cnt+" "+res);
		br.close();

	}

	public static void dfs(int x, int y) {
		visited[x][y] = true;
		char temp = arr[x][y]; // R
		for (int i = 0; i < 4; i++) {
			int nx = x + ni[i];
			int ny = y + nj[i];
			if (0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny] && arr[nx][ny] == temp) {
				dfs(nx, ny);
			}
		}
	}
}
