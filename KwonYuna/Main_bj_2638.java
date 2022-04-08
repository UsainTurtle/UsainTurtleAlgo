package a0217;

import java.io.*;
import java.util.*;

public class Main_bj_2638 {
	final static int[] di = { -1, 0, 1, 0 };
	final static int[] dj = { 0, 1, 0, -1 };
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		boolean[][] v;
		int st_i = N, en_i = 0, st_j = M, en_j = 0; //탐색하는 시간을 줄이기 위해 탐색유효범위 지정
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					st_i = i < st_i ? i : st_i;
					en_i = en_i < i ? i : en_i;
					st_j = j < st_j ? j : st_j;
					en_j = en_j < j ? j : en_j;
				}
			}
		}

		int ans = 0;
		boolean repeat=true;
		while (repeat) {
			ans++; //1시간 지남
			v = new boolean[N][M];
			dfs(arr,v,0, 0); //dfs로 외부 공기와 닿는 치즈확인
			repeat=false;//남은 치즈가 없으면 반복문 빠져 나옴
			for (int i = st_i; i <= en_i; i++) {
				for (int j = st_j; j <= en_j; j++) {
					if (2 < arr[i][j]) //2초과면 외부에 치즈가 2면이상 노출되어 녹음
						arr[i][j] = 0;
					else if (1 <= arr[i][j]) { //1이상이면 외부에 1면만 노출되었거나 노출되지 않음
						arr[i][j] = 1;	//다시 1로 설정
						repeat=true; //치즈가 남아있으니까 while문 반복 
					}
				}
			}	
		}
		System.out.println(ans); //시간 출력
		br.close();
	}

	static void dfs(int[][] arr,boolean[][] v,int i, int j) {
		v[i][j]=true;
		for (int z = 0; z < 4; z++) {
			int ni=i + di[z],nj=j + dj[z];
			if (0 <= ni&& ni < N && 0 <= nj && nj < M) {
				if (0<arr[ni][nj]) { //arr[ni][nj]값이 1이상이면 치즈		
					arr[ni][nj]++; //외부 공기와 한 면이 접촉했다는 의미로 값 1 증가
				}else { //외부 공기일 경우
					if(!v[ni][nj])dfs(arr,v,ni, nj); //방문하지않았다면 dfs 호출
				}
			}
		}
	}
}
