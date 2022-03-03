package a0301.study;

import java.io.*;
import java.util.*;

/*
NxN
r행c열에 있는 나라에는 A[r][c]명 살고있다.

조건
1. 국경선을 공유하는 두 나라의 인구 차이가 L명이상, R명 이하라면, 국경선을 연다.
2. 국경선이 모두 열리면, 인구 이동 시작
3. 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
4. 연합을 이루는 각 칸의 인구수는 (연합인구수)/(연합을 이루는 칸의 개수)가 된다.(소수점 버림)
5. 연합을 해체하고, 모든 국경선을 닫는다.

입력
N L R (1<=N<=50, 1<=L,R<=100)
N개의 줄에 각나라의 인구수, r행 c열에 주어지는 정수는 A[r][c]의 값 (0<=A<=100)
인구이동 일수가 2000이하 값만 주어진다.

출력
인구이동이 며칠 발생하는지

*/

public class Main_bj_16234_인구이동 {
	static int[] di= {-1,1,0,0};	//상 하 좌 우
	static int[] dj= {0,0,-1,1};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_16234.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st =new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		//v = new boolean[N][N];
		for(int i=0; i<N; i++) {
			st =new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		///for(int[] a:map) System.out.println(Arrays.toString(a));System.out.println();
		
		int day=0;
		fi:while(true) {
			boolean[][] v = new boolean[N][N];
			for(int x=0; x<N; x++) {
				for(int y=0; y<N; y++) {
					if(!v[x][y]) {
						b(x,y,map,v,N,L,R);
						//for(boolean[] a:v) System.out.println(Arrays.toString(a));System.out.println();
						//for(int[] a:map) System.out.println(Arrays.toString(a)); System.out.println();
					}
				}
			}
			//for(int[] a:map) System.out.println(Arrays.toString(a)); System.out.println();
			for(int x=0; x<N; x++) {
				for(int y=0; y<N; y++) {
					if(v[x][y]) {
						day++;
						continue fi;
					}
				}
			}
			break;
		}
		

		//for(int[] a:map) System.out.println(Arrays.toString(a));
		System.out.println(day);
		br.close();
	}
	static void b(int i, int j, int[][] map, boolean[][] v, int N, int L, int R) {
		Queue<int[]> q = new ArrayDeque<>();
		Queue<int[]> q2 = new ArrayDeque<>();
		boolean flag=false;
		//boolean[][] chk = new boolean[N][N];
		int sum=0;
		int cnt=0;
		q.offer(new int[] {i,j});
		q2.offer(new int[] {i,j});
		v[i][j]=true;
		//chk[i][j]=true;
		
		while(!q.isEmpty()) {
			int[] xy=q.poll();
			i=xy[0];
			j=xy[1];
			sum+=map[i][j];
			cnt++;
			for(int d=0; d<4; d++) {
				int ni=i+di[d];
				int nj=j+dj[d];
				if(0<=ni && ni<N && 0<=nj && nj<N && !v[ni][nj] && L<=Math.abs(map[i][j]-map[ni][nj]) && Math.abs(map[i][j]-map[ni][nj])<=R) {
					v[ni][nj]=true;
					//chk[ni][nj]=true;
					q.offer(new int[] {ni,nj});
					q2.offer(new int[] {ni,nj});
					flag=true;
				}
			}
		}
		if(flag) {
			int n = sum/cnt;
//			for(int x=0; x<N; x++) {
//				for(int y=0; y<N; y++) {
//					if(chk[x][y]) map[x][y]=n;
//				}
//			}
			while(!q2.isEmpty()) {
				int[] ij=q2.poll();
				map[ij[0]][ij[1]]=n;
			}
		}
		else v[i][j]=false;
		//for(int[] a:map) System.out.println(Arrays.toString(a)); System.out.println();
	}

}
