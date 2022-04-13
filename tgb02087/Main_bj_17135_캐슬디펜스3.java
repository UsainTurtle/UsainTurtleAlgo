package a0407.study;

import java.io.*;
import java.util.*;

/*
1.궁수는 동시에 공격한다.	: 조합
2.궁수는 D이하의 적중 가장 가까운 적을 먼저 죽인다.	: BFS
3.거리가 같을때는 가장 왼쪽에 있는 적을 먼저 죽인다.	: 좌상우
4.같은적이 여러번 공격 당할수 있다.	: 중복처리

*거리 계산 : (r1,c1)과 (r2,c2)의 거리는 |r1-r2|+|c1-c2|이다.

입력
N M 궁수 거리D
NxM상태
(0:빈칸, 1:적)

출력
제거할수 있는 적의 최대 수

조건
3<=N,M<=15
1<=D<=10

 */
public class Main_bj_17135_캐슬디펜스3 {
	static int[] di = {0,-1,0};	//좌 상 우
	static int[] dj = {-1,0,1};
	static int[][] map;
	static int N, M, D, max=0, a_cnt=0;
	static boolean end;
	static List<int[]> list;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17135.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		D= Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) a_cnt++;
			}
		}
		if(a_cnt==0) System.out.println(0);
		else {
			list = new ArrayList<>();
			c(new int[3],0,0);
			System.out.println(max);
		}
		br.close();
	}
	//궁수 위치뽑기
	static void c(int[] ac, int st, int cnt) {
		if(end) return;
		if(cnt==3) {
			max=Math.max(defence(ac,new boolean[N][M]), max);
			if(max==a_cnt) end=true;
			return;
		}
		for(int i=st; i<M; i++) {
			ac[cnt]=i;
			c(ac,i+1,cnt+1);
		}
	}
	//디펜스 시작
	static int defence(int[] ac, boolean[][] kill) {
		int cur=N, cnt=0;	//cur : 궁수의 위치(행)
		while(true) {
			for(int i=0; i<3; i++) {
				//cur-1:궁수 바로윗칸이 가장 가까운 적
				bfs(new boolean[N][M],kill,cur-1,ac[i],1);
			}
			//화살에 맞은 적 세팅
			for(int i=0; i<list.size(); i++) {
				int[] xy=list.get(i);
				if(!kill[xy[0]][xy[1]]) {	//true이면 넘어감
					kill[xy[0]][xy[1]]=true;
					cnt++;
				}
			}
			list.clear();
			if(cur==1) return cnt;
			else cur--;
		}
	}
	//적 탐색
	static void bfs(boolean[][] v,boolean[][] kill,int x, int y, int dt) {
		Queue<int[]> q=new ArrayDeque<>();
		v[x][y]=true;
		q.offer(new int[] {x,y,dt});
		while(!q.isEmpty()) {
			int[] xy=q.poll();
			//거리가 벗어나면
			if(D<xy[2]) return;
			//맵에서 적이 있을 때 && kill을 하지 않았다면
			if(map[xy[0]][xy[1]]==1 && !kill[xy[0]][xy[1]]) {
				list.add(new int[] {xy[0],xy[1]});
				return;
			}
			for(int d=0; d<3; d++) {
				int nx=xy[0]+di[d];
				int ny=xy[1]+dj[d];
				if(nx<0 || ny<0 || M<=ny || v[nx][ny]) continue;
				v[nx][ny]=true;
				q.offer(new int[] {nx,ny,xy[2]+1});
			}
		}
	}
}
