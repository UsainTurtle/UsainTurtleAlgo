package a0408.camp;

import java.io.*;
import java.util.*;

/*

입력
N M (3<=N,M<=8)
NxM
(0:빈칸, 1:벽, 2:바이러스)
(2<=바이러스<=10)
(3<=빈칸)

출력
안전영역의 최대 크기

 */

public class Main_bj_14502_연구소 {
	static int[] di= {-1,1,0,0};	//상 하 좌 우
	static int[] dj= {0,0,-1,1};
	static int[][] map, nmap;
	static int N,M, min=Integer.MAX_VALUE;
	static List<int[]> list, virus;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14502.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map = new int[N][M];
		list=new ArrayList<>();
		virus=new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==0) list.add(new int[] {i,j});
				if(map[i][j]==2) virus.add(new int[] {i,j});
			}
		}
		//for(int[] a:map) System.out.println(Arrays.toString(a)); System.out.println();
		c(new int[3],0,0);
		System.out.println(list.size()-min-3);
		br.close();
	}
	static void copy() {
		nmap=new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				nmap[i][j]=map[i][j];
			}
		}
	}
	// 빈칸 조합
	static void c(int[] ac, int st, int cnt) {
		if(cnt==3) {
			copy();
			for(int i=0; i<3; i++) {
				int[] xy=list.get(ac[i]);
				nmap[xy[0]][xy[1]]=1;
			}
			min=Math.min(bfs(), min);
			return;
		}
		for(int i=st; i<list.size(); i++) {
			ac[cnt]=i;
			c(ac,i+1,cnt+1);
		}
	}
	static int bfs() {
		int cnt=0;
		Queue<int[]> q=new ArrayDeque<>();
		for(int i=0; i<virus.size(); i++) {
			int[] xy=virus.get(i);
			q.offer(new int[] {xy[0],xy[1]});
		}
		while(!q.isEmpty()) {
			int[] xy=q.poll();
			for(int d=0; d<4; d++) {
				int nx=xy[0]+di[d];
				int ny=xy[1]+dj[d];
				if(nx<0 || N<=nx || ny<0 || M<=ny || nmap[nx][ny]!=0) continue;
				nmap[nx][ny]=2;
				cnt++;
				q.offer(new int[] {nx,ny});
			}
		}
		return cnt;
	}
}
