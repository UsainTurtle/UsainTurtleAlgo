package a0308.study;

import java.io.*;
import java.util.*;

/*
입력 
첫째줄에 N(4<=N<=20)
S가 주어진다. (1<= Sij <=100)

출력 
스타트팀과 링크팀의 능력치 차이의 최솟값 

 */
public class Main_bj_14889_스타트와링크 {
	static int[] sum;
	static int min=Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14889.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		//for(int[] a:map) System.out.println(Arrays.toString(a));System.out.println();
		
		int[] team = new int[N];
		
		
		boolean[] v = new boolean[N];
		for(int i=0; i<N; i++) team[i]=i;
		
		c(0,0,N,map,team,v);
		System.out.println(min);
		br.close();
	}
	static void c(int start, int cnt, int N, int[][]map, int[] team, boolean[] v) {
		if(v[0]) return;
		if(cnt==N/2) {
			List<Integer> sta = new ArrayList<>();
			List<Integer> link = new ArrayList<>();
			for(int i=0; i<N;i++) {
				if(v[i]) sta.add(i);
				else link.add(i);
			}
			//System.out.println(sta);
			//System.out.println(link);
			
			int[] idx=new int[2];
			sum=new int[2];
			boolean[] vi = new boolean[N/2];
			s_sum(N,0,0,vi,map,idx,sta,link);
			min=Math.min(min, Math.abs(sum[0]-sum[1]));
			return;
		}
		
		for(int i=start; i<N; i++) {
			if(v[i]) continue;
			v[i]=true;
			c(i,cnt+1,N,map,team,v);
			v[i]=false;
		}
	}
	static void s_sum(int N,int start, int cnt, boolean[] v, int[][]map, int[] idx, List<Integer> sta, List<Integer> link) {
		if(cnt==2) {
			sum[0]+=map[sta.get(idx[0])][sta.get(idx[1])]+map[sta.get(idx[1])][sta.get(idx[0])];
			sum[1]+=map[link.get(idx[0])][link.get(idx[1])]+map[link.get(idx[1])][link.get(idx[0])];
			return;
		}
		
		for(int i=start; i<N/2;i++) {
			if(v[i]) continue;
			idx[cnt]=i;
			v[i]=true;
			s_sum(N,i,cnt+1,v,map,idx,sta,link);
			v[i]=false;
		}
	}

}
