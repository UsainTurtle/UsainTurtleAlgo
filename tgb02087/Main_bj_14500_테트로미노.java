package a0312.study;

import java.io.*;
import java.util.*;

/*
조건
1. 정사각형은 서로 겹치면 안된다.
2. 도형은 모두 연결되어 있어야한다.
3. 정사각형의 변끼리 연결되어 있어야 한다. 즉, 꼭짓점과 꼭짓점만 맞닿아 있으면 안된다.

정사각형 4개를 이어붙인 = 테트로미노 5가지

NxM종이위 테트로미노를 놓아서 합이 가장 큰 경우 ( 회전이나, 대칭 가능)

입력 
N M (4<=N,M<=500) 
N개줄 수 (<=1000)

출력 
최대 수 
 */

public class Main_bj_14500_테트로미노 {
	static int[] di = {-1,1,0,0};	//상 하 좌 우 
	static int[] dj = {0,0,-1,1};
	static int max=0;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14500.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		//for(int[] a:map) System.out.println(Arrays.toString(a));
		boolean[][] v = new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				d(map,v,i,j,1,N,M,map[i][j]); 
				b(map,i,j,N,M);
			}
		}

		System.out.println(max);
		
		br.close();
	}
	static void b(int[][] map, int i, int j, int N, int M) {
		List<Integer> arr = new LinkedList<>();
		int sum=map[i][j];
		int count=0;
		for(int d=0; d<4; d++) {
			int ni=i+di[d];
			int nj=j+dj[d];
			if(0<=ni && ni<N && 0<=nj && nj<M) {
				arr.add(map[ni][nj]);
				count++;
			}
		}
		if(count<3)return;
		Collections.sort(arr);
		for(int q=0; q<3; q++) {
			sum+=arr.remove(arr.size()-1);
		}
		max=Math.max(max, sum);
	}
	static void d(int[][]map, boolean[][] v, int i, int j, int cnt, int N, int M, int sum) {
		if(cnt==4) {
			max=Math.max(max, sum);
			//for(boolean[] a:v) System.out.println(Arrays.toString(a));System.out.println();
			//System.out.println(sum);
			return;
		}
		v[i][j]=true;
		for(int d=0; d<4; d++) {
			int ni=i+di[d];
			int nj=j+dj[d];
			if(0<=ni && ni<N && 0<=nj && nj<M && !v[ni][nj]) {
				v[ni][nj]=true;
				d(map,v,ni,nj,cnt+1,N,M,sum+map[ni][nj]);
				v[ni][nj]=false;
			}
		}
		v[i][j]=false;
	}
}
