package a0217.study;

import java.io.*;
import java.util.*;

/*
NxM 격자
치즈는 4변중 2변이 공기와 접촉시 1시간만에 사라짐.
치즈 내부 공간은 공기와 접촉x
한 시간후 외부 공기와 이어질시 1시간후 사라짐

입력
N M (5 <= N, M <= 100)
치즈1 공백0

출력
치즈가 모두 없어지는 시간

 */

public class Main_bj_2638_치즈 {
	static int[] di= {-1,0,1,0};		//상 우 하 좌
	static int[] dj= {0,1,0,-1};
	static int N,M;
	static int[][] map;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2638.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int time=0;
		while(true) {
			boolean[][] v = new boolean[N][M];
			boolean chk=true;
			//가장자리 치즈 확인
			bfs(v,0,0);
			//가장자리 녹이기
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(!v[i][j]) {
						chk=false;
						//2분면이상 만났을경우
						if(2<map[i][j]) map[i][j]=0;
						else if(0<map[i][j]) map[i][j]=1;
					}
				}
			}
			if(chk) break;
			time++;
		}
		System.out.println(time);
		br.close();
	}
	static void bfs(boolean[][] v, int x, int y) {
		ArrayDeque<int[]> q =new ArrayDeque<>();
		v[x][y]=true;
		q.offer(new int[] {x,y});
		while(!q.isEmpty()) {
			int[] xy=q.poll();
			for(int d=0; d<4; d++) {
				int nx=xy[0]+di[d];
				int ny=xy[1]+dj[d];
				if(nx<0 || N<=nx || ny<0 || M<=ny || v[nx][ny]) continue;
				if(map[nx][ny]==0) {
					v[nx][ny]=true;
					q.offer(new int[] {nx,ny});
				}
				else if(0<map[nx][ny]) map[nx][ny]++;
			}
		}
	}

}
