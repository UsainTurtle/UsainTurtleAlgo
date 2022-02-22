
import java.util.*;
import java.io.*;


public class Solution_bj_2667 {
	static char[][] arr;
	static boolean[][] visited;
	static int[] ni = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] nj = { 0, 0, -1, 1 };
	static int n;
	static int area; //각 구역의크기
	public static void main(String[] args) throws Exception{
		
		System.setIn(new FileInputStream("res/input_bj_2667.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new char[n][n];
		visited = new boolean[n][n];
		ArrayList<Integer> res = new ArrayList<>();

		for (int i = 0; i < n; i++) { // 입력
			String temp = br.readLine();
			arr[i] = temp.toCharArray();
		}
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && arr[i][j] == '1') {
					cnt ++;
					area = 1;
					dfs(i, j);
					res.add(area);
				}
			}
		}
		Collections.sort(res);
		System.out.println(cnt);
		for(int i = 0;i<res.size();i++) {
			System.out.println(res.get(i));
		}

	}
	public static void dfs(int x, int y) {
		visited[x][y] = true;
		char temp = arr[x][y]; // R
		for (int i = 0; i < 4; i++) {
			int nx = x + ni[i];
			int ny = y + nj[i];
			if (0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny] && arr[nx][ny] == '1') {
				area++;
				dfs(nx, ny);
			}
		}
	}

}
