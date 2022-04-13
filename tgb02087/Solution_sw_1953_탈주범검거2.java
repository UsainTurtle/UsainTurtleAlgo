package a0402.study;

import java.io.*;
import java.util.*;

/*

1 : 상하좌우 
2 : 상하 
3 : 좌우 
4 : 상우 
5 : 하우 
6 : 하좌 
7 : 상좌 

지하터널 지도와 맨홀 뚜껑의 위치, 경과된 시간이 주어진다.
탈주범의 위치할수 있는 장소의 개수를 구하라

입력 
테케 tc
N M 멘홀뚜껑위치 R C 탈출 후 소요시간 L
터널 지도 정보 

출력 
#tc 탈주범이 위치할수 있는 장소개수 

 */
public class Solution_sw_1953_탈주범검거2 {
	static int[] di= {-1,1,0,0};	//상 하 좌 우 
	static int[] dj= {0,0,-1,1};
	static int[][] pipe= {
						 {0,0,0,0},
						 {1,1,1,1},
						 {1,1,0,0},
						 {0,0,1,1},
						 {1,0,0,1},
						 {0,1,0,1},
						 {0,1,1,0},
						 {1,0,1,0},
								 };
	static int N, M, L;
	static int[][] map, v;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_sw_1953.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			v = new int[N][M];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j=0; j<M; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					v[i][j]=987654321;
				}
			}
			sb.append("#").append(tc).append(" ").append(dfs(R,C,1)).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	static int dfs(int x, int y, int dep) {
		int cnt=0;
		if(v[x][y]==987654321) cnt++;
		v[x][y]=dep;
		if(dep==L) return cnt;
		for(int d=0; d<4; d++) {
			if(pipe[map[x][y]][d]==0) continue; 
			int nx=x+di[d];
			int ny=y+dj[d];
			if(nx<0 || N<=nx || ny<0 || M<=ny || v[nx][ny]<dep) continue;
			boolean chk=ismove(nx,ny,d);
			if(chk) cnt+=dfs(nx,ny,dep+1);
		}
		return cnt;
	}
	static boolean ismove(int x, int y, int t) {
		//방향 반대로 세팅 
		t=t%2==0?t+1:t-1;
		if(pipe[map[x][y]][t]==1) return true;
		return false;
	}

}
