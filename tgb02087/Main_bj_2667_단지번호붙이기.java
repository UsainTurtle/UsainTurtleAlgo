package a0330.camp;

import java.io.*;
import java.util.*;

public class Main_bj_2667_단지번호붙이기 {
	static int[] di= {-1,1,0,0};	//상하좌우
	static int[] dj= {0,0,-1,1};
	static int N;
	static boolean[][] map;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2667.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		
		for(int i=0; i<N;i++) {
			String s=br.readLine();
			for(int j=0; j<N;j++) {
				if(s.charAt(j)=='1') map[i][j]=true;
				else map[i][j]=false;
			}
		}
		PriorityQueue<Integer> pq=new PriorityQueue<>();
		for(int i=0; i<N;i++) {
			for(int j=0; j<N;j++) {
				if(map[i][j]) {
					pq.add(bfs(i,j));
				}
			}
		}
		sb.append(pq.size()).append("\n");
		while(!pq.isEmpty()) sb.append(pq.poll()).append("\n");
		System.out.println(sb);
		br.close();
	}
	static int bfs(int x, int y) {
		ArrayDeque<int[]> q =new ArrayDeque<>();
		int cnt=1;
		map[x][y]=false;
		q.offer(new int[] {x,y});
		while(!q.isEmpty()) {
			int[] xy=q.poll();
			for(int d=0; d<4; d++) {
				int nx=xy[0]+di[d];
				int ny=xy[1]+dj[d];
				if(nx<0 || N<=nx || ny<0 || N<=ny || !map[nx][ny]) continue;
				cnt++;
				map[nx][ny]=false;
				q.offer(new int[] {nx,ny});
			}
		}
		return cnt;
	}
}
