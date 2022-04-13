package a0409.study;

import java.io.*;
import java.util.*;

public class Solution_sw_1949_등산로조성 {
	static int[] di= {-1,1,0,0};	// 상 하 좌 우   
	static int[] dj= {0,0,-1,1};
	static int[][] map;
	static int N,K, max;
	static boolean[][] v;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_sw_1949.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T= Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			N= Integer.parseInt(st.nextToken());
			K= Integer.parseInt(st.nextToken());
			map = new int[N][N];
			max=0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					max=Math.max(max, map[i][j]);
				}
			}
			//for(int[] a:map) System.out.println(Arrays.toString(a));
			List<int[]> list = new ArrayList<>();
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]==max) list.add(new int[] {i,j});
				}
			}
			max=0;
			v = new boolean[N][N];
			for(int i=0; i<list.size(); i++) {
				int[] xy=list.get(i);
				v[xy[0]][xy[1]]=true;
				dfs(xy[0],xy[1],1,false);
				v[xy[0]][xy[1]]=false;
			}
			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	static void dfs(int x, int y, int cnt, boolean chk) {
		for(int d=0; d<4; d++) {
			int nx=x+di[d];
			int ny=y+dj[d];
			if(nx<0 || N<=nx || ny<0 || N<=ny || v[nx][ny]) continue;
			//자신 보다 낮은곳 이동 
			if(map[nx][ny]<map[x][y]) {
				v[nx][ny]=true;
				dfs(nx,ny,cnt+1,chk);
				v[nx][ny]=false;
			}
			//자신 보다 높거나 , 같을때 공사 할수있으면 해보기 
			else if(!chk) {
				for(int i=1; i<=K; i++) {
					if(map[nx][ny]-i<0) break;
					if(map[nx][ny]-i<map[x][y]) {
						map[nx][ny]-=i;
						v[nx][ny]=true;
						dfs(nx,ny,cnt+1,true);
						v[nx][ny]=false;
						map[nx][ny]+=i;
						break;
					}
				}
			}
		}
		max=Math.max(cnt,max);
	}
}
