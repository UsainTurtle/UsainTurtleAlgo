package a0220.study;

import java.io.*;
import java.util.*;

/*
적록색약 : 빨간색과 초록색 구분x
NxN 에 R,G,B로만 구성
상 하 좌 우 인접해 있는경우 같은 구역

입력
N (1<=N<=100)
그림

출력
적록색약, 일반인 구역의 수출력
 */

public class Main_bj_10026_적록색약3 {
	static int[] di= {-1,1,0,0};	//상 하 좌 우
	static int[] dj= {0,0,-1,1};
	static boolean[][] v;
	static int N, cnt=0;
	static Queue<int[]> q;
	public static void main(String[] args)throws Exception {
		System.setIn(new FileInputStream("res/input_bj_10026.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		char[][] map = new char[N][N];
		v = new boolean[N][N];
		q = new LinkedList<>();
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j]= s.charAt(j);
			}
		}
		
		for(int k=0; k<2; k++) {
			cnt=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					d(i,j,map);
					if(map[i][j]=='G') map[i][j]='R';
				}
			}
			sb.append(cnt).append(" ");
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					v[i][j]=false;
				}
			}
		}
		System.out.println(sb);
		br.close();
	}
	static void d(int i, int j, char[][] map) {
		//test[i][j]=++test1;
		if(v[i][j]) {
			cnt--;
			return;
		}
		v[i][j]=true;
		//for(boolean[] a:v) System.out.println(Arrays.toString(a)); System.out.println();
		
		for(int d=0;d<4; d++) {		//같은거 찾기
			int ni=i+di[d];
			int nj=j+dj[d];
			//범위 체킹 & v=false & 전에 있던 값이 같은 값이면
			if(0<=ni && ni<N && 0<=nj && nj<N && !v[ni][nj] && map[i][j]==map[ni][nj]) {
				d(ni,nj,map);
			}
		}
		cnt++;
	}
}