package a0308;

import java.io.*;
import java.util.*;

public class Main_bj_14500 {
	final static int[] di= {0,1,-1,0};
	final static int[] dj= {1,0,0,-1};
	static int arr[][],MAX,N,M;
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		v = new boolean[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				v[i][j]=true;
				dfs(i,j,0,arr[i][j]);
				v[i][j]=false;
				check(i,j,arr[i][j]);
			}
		}
		System.out.println(MAX);
		br.close();
	}
	
	static void dfs(int i, int j, int cnt,int sum) {
		if(cnt==3) {
			if(MAX<sum)MAX=sum;
			return;
		}
		
		for(int z=0;z<3;z++) {
			int ni=i+di[z];
			int nj=j+dj[z];
			if(0<=ni&&ni<N&&0<=nj&&nj<M&&!v[ni][nj]) {
				v[ni][nj]=true;
				dfs(ni,nj,cnt+1,sum+arr[ni][nj]);
				v[ni][nj]=false;
			}
		}
	}
	static void check(int i, int j ,int sum) {
		int count=4,min=Integer.MAX_VALUE;
		for(int z=0;z<4;z++) {
			int ni=i+di[z];
			int nj=j+dj[z];
			if(0<=ni&&ni<N&&0<=nj&&nj<M) {
				sum+=arr[ni][nj];
				if(arr[ni][nj]<min) min=arr[ni][nj];
			}else {
				count--;
			}
		}
		
		if(count==4) {
			sum-=min;
			count--;
		}
		if(count==3&&MAX<sum) {
			MAX=sum;
		}

	}
}
