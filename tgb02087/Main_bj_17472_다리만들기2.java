package a0303.study;

import java.io.*;
import java.util.*;

/*
조건
1.다리 양끝은 섬과 인접한 바다위
2.방향이 중간에 바뀌지 않는다.
3.다리길이는 최소2이상
4.다리의 중간변이 다른섬과 인접하더라도 그섬과 연결된 것은 아니다.
5.다리는 교차할수 있다. (이때 교차한 각각의 길이를 계산)

제한
1<=N,M<=10
3<=NxM<=100
2<=섬의개수<=6

입력
NxM
N개의 지도 정보 (0:바다, 1:땅)

출력
모든 섬을 연결하는 다리의 길이 최솟값 (연결이 안되면 -1출력)

 */

public class Main_bj_17472_다리만들기2 {
	static int[] di = {-1,1,0,0};		//상 하 좌 우
	static int[] dj = {0,0,-1,1};
	static int max=0, sum=0;	//섬개수

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17472.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		boolean[][] v = new boolean[N][M];
		int[] xy=new int[2];
		boolean chk =true;
		for(int i=0; i<N; i++) {
			 st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(chk && map[i][j]==1) {
					xy[0]=i;
					xy[1]=j;
					chk=false;
				}
			}
		}
		land(xy[0],xy[1],1,N,M,map,v);
		for(int[] a : map )System.out.println(Arrays.toString(a));
		System.out.println(max);
		v = new boolean[N][M];
		Queue<int[]> q2 = new ArrayDeque<>();
		for(int n=1; n<=max; n++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j]==n) {
						q2.offer(new int[] {i,j});
						v[i][j]=true;
					}
					bridge(q2, map, v, N, M);
				}
			}
		}
		System.out.println(sum);

	}
	static void land(int x, int y, int c, int N, int M, int[][] map, boolean[][] v) {
		Queue<int[]> q = new ArrayDeque<>();
		v[x][y]=true;
		q.offer(new int[] {x,y,c});		
		//System.out.println(c);
		//for(int[] a : map )System.out.println(Arrays.toString(a));System.out.println();
		while(!q.isEmpty()) {
			int[] xy = q.poll();
			int i=xy[0];
			int j=xy[1];
			int k=xy[2];
			//System.out.println(i);
			//System.out.println(j);
			//v[i][j]=true;
			map[i][j]=k;
			max=Math.max(max, k);
			
			for(int d=0;d<4;d++) {
				int ni=i+di[d];
				int nj=j+dj[d];
				if(0<=ni && ni<N && 0<=nj && nj<M && !v[ni][nj] && map[ni][nj]==1) {
					v[ni][nj]=true;
					q.offer(new int[] {ni,nj,k});
				}
			}
		}
		//for(boolean[] a : v )System.out.println(Arrays.toString(a));System.out.println();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!v[i][j] && map[i][j]==1) {
					land(i,j,c+1,N,M,map,v);
				}
			}
		}
	}
	static void bridge(Queue<int[]> q, int[][] map, boolean[][] v, int N, int M) {
		int cnt=0;
		int min=Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			int[] xy=q.poll();
			int ni=xy[0];
			int nj=xy[1];
			for(int d=0;d<4;d++) {
				while(true) {
					ni=ni+di[d];
					nj=nj+dj[d];
					if(ni<0 || N<=ni || nj<0 || M<=nj || v[ni][nj]) break;
					if(0<map[ni][nj]) {
						if(2<=cnt) {
							System.out.println("cnt"+cnt);
							min=Math.min(min, cnt);
							break;
						}
						break;
					}
					cnt++;
				}
			}
		}
		//System.out.println(min);
		sum+=cnt;

	}

}
