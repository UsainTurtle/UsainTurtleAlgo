import java.util.*;
import java.io.*;

public class Solution_bj_1012 {
	static int[][] arr;
	static boolean[][] visited;
	static int[] ni = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] nj = { 0, 0, -1, 1 };
	static int row,col;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_1012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		for(int tc = 0; tc<T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			col = Integer.parseInt(st.nextToken());
			row = Integer.parseInt(st.nextToken());
			int bug = Integer.parseInt(st.nextToken());//벌레 수
			
			arr = new int[row][col];
			visited = new boolean[row][col];

			for(int i = 0;i<bug;i++) {
				st = new StringTokenizer(br.readLine()," ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				arr[y][x] = 1; //배추가 심어져있는곳 체크
			}
			
//			System.out.println("#tc"+tc);
//			for (int i = 0; i < row; i++) { //영역개수 확인 
//				for (int j = 0; j < col; j++) {
//					System.out.print(arr[i][j]+" ");
//				}
//				System.out.println();
//		}
			
			int cnt = 0;
			for (int i = 0; i < row; i++) { //영역개수 확인 
				for (int j = 0; j < col; j++) {
					if (!visited[i][j] && arr[i][j] == 1) {
						dfs(i, j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
			
		}
		br.close();
		
	}
	public static void dfs(int x, int y) {
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + ni[i];
			int ny = y + nj[i];
			if (0 <= nx && nx < row && 0 <= ny && ny < col && !visited[nx][ny] && arr[nx][ny] == 1) {
				dfs(nx, ny);
			}
		}
	}

}
