package a0318.study;

import java.io.*;
import java.util.*;

/*
바이러스는 활성상태와 비활성상태가 있다.
처음에는 모든 바이러스는 비활성 상태이고,
활성 상태인 바이러스는 상하좌우로 인접한 모든 빈칸으로 동시에 복제된다. (1초)
바이러스 M개를 활성상태로 변경

연구소 : NxN
빈칸 : 0
벽 : 1
바이러스 : 2

활성 바이러스가 비활성 바이러스가 있는 칸으로 가면 활성 바이러스가 된다.

연구소에 M개의 바이러스를 활성상태로 만들어,
모든 빈칸에 바이러스를 퍼뜨리는 최소 시간을 구한다.

입력
N (4<=N<=50) M (1<=M<=10)
연구소상태
(M<= 벽 <=10)

출력
최소시간
(퍼뜨리지 못하면 -1출력)


*/
public class Main_bj_17142_연구소3 {
	static int[] di= {-1,1,0,0};		//상 하 좌 우
	static int[] dj= {0,0,-1,1};
	static int min=2500;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17142.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		int v_cnt=0;	//맵의 바이러스 개수
		int[][] map = new int[N][N];
		List<int[]> v_map = new ArrayList<>();
		boolean chk_0=false;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) map[i][j]=-1;	//벽을 -1로
				else if(map[i][j]==2) {		//바이러스 -2로
					map[i][j]=-2;
					v_map.add(new int[] {i,j});
					v_cnt++;
				}
				else chk_0=true;	// 맵에 0이 없는경우
			}
		}
		if(chk_0) {
			//for(int[] a:map) System.out.println(Arrays.toString(a));
			int[] c = new int[v_cnt];
			boolean[] v = new boolean[v_cnt];
			int[] m = new int[M];
			for(int i=0; i<v_cnt; i++) c[i]=i;		
			comb(map,v_map,c,v,m,0,0,v_cnt,M,N);
			if(min==2500) min=0;
			System.out.println(min-1);	
		}
		else {
			System.out.println(0);
		}
		br.close();
	}
	static void comb(int[][] map, List<int[]> v_map,int[] c, boolean[] v, int[] m, int start, int cnt, int n, int M, int N) {
		if(cnt==M) {
			//System.out.println(Arrays.toString(m));
			Queue<int[]> q=new ArrayDeque<>();
			boolean[][] vis= new boolean[N][N];
			for(int i=0; i<m.length; i++) {	//바이러스 세팅
				int x=v_map.get(m[i])[0];
				int y=v_map.get(m[i])[1];
				map[x][y]=1;
				q.offer(new int[] {x,y,1});
				vis[x][y]=true;
			}
			//for(int[] a:map) System.out.println(Arrays.toString(a)); System.out.println();
			bfs(map,vis,q,N);
			for(int i=0; i<m.length; i++) {	//바이러스 초기화
				int x=v_map.get(m[i])[0];
				int y=v_map.get(m[i])[1];
				map[x][y]=-2;
			}
			return;
		}
		
		for(int i=start; i<n; i++) {
			if(v[i]) continue;
			v[i]=true;
			m[cnt]=c[i];
			comb(map,v_map,c,v,m,i,cnt+1,n,M,N);
			v[i]=false;
		}
	}
	static void bfs(int[][] map, boolean[][] v, Queue<int[]> q, int N) {
		int fork_v=0;	//빈칸에 번진 시간
		while(!q.isEmpty()) {
			int[] xy=q.poll();
			int x=xy[0];
			int y=xy[1];
			// xy[2]는 바이러스가 퍼지는 시간
			if(min<=fork_v) return;	// 최소시간보다 많아지면 종료
			for(int d=0; d<4; d++) {
				int nx=x+di[d];
				int ny=y+dj[d];
				//범위체킹
				if(nx<0 || N<=nx || ny<0 || N<=ny || map[nx][ny]==-1 || v[nx][ny]) continue;
				if(map[nx][ny]==0 || map[nx][ny]==-2) {
					if(map[nx][ny]==0) fork_v=xy[2]+1;	//빈칸이면 바이러스가 퍼지는시간 갱신.
					v[nx][ny]=true;
					q.offer(new int[] {nx,ny,xy[2]+1});
				}
			}
		}
		// 맵에 빈칸인데, 방문하지 않았으면 못가는 곳이므로 종료
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!v[i][j]) {		
					if(map[i][j]==0) return;
				}
			}
		}
		//System.out.print(fork_v+"\n");
		//위에서 걸러지지 않고도 바이러스가 퍼진시간이 0이라면 빈칸이 없고, 비활성바이러스만 존재한경우
		//if(fork_v==0) fork_v=1;
		min=Math.min(min, fork_v);
		//for(boolean[] a:v) System.out.println(Arrays.toString(a)); System.out.println();
		//for(int[] a:map) System.out.println(Arrays.toString(a)); System.out.println();
	}
}
