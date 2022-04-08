package a0301.study;

import java.io.*;
import java.util.*;

/*

불은 매초마다 동서남북 으로 퍼진다. 벽에는 불이 붙지 않는다.
상근이는 동서남북 이동가능, 1초걸림.
벽x 불이 옮겨진 칸, 불이 이제 붙으려는 칸, 이동x
상근이 칸에 불이 옮겨옴과 동시에 다른칸으로 이동 가능

입력 
테케 (최대100)
빌딩 지도 w h (1<=w,h<=1000)

다음 조건
. : 빈 공간
# : 벽
@ : 상근이의 시작 위치
* : 불
각 지도의 @는 하나

출력
빌딩을 탈출하는 가장 빠른 시간 출력
(탈출할 수 없는 경우 IMPOSSIBLE 출력

*/

public class Main_bj_5427_불 {
	static int W, H;
	static int[] di= {-1,1,0,0};	//상 하 좌 우
	static int[] dj= {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_5427.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			int[][] fire = new int[H][W];	// 불맵
			boolean[][] v = new boolean[H][W];
			int[] xy=new int[2];		//상근이 위치
			for(int i=0; i<H;i++) {
				String s=br.readLine();
				for(int j=0; j<W;j++) {
					char a=s.charAt(j);
					if(a=='#') fire[i][j]=-1;	//벽이면 -1
					else if(a=='*') fire[i][j]=1;	//불이면 1
					else if(a=='@') {			//상근이일때는 위치만 저장
						xy[0]=i;
						xy[1]=j;
					}
				}
			}
			//for(char[] a:map) System.out.println(Arrays.toString(a));System.out.println();
			//for(int[] a:fire) System.out.println(Arrays.toString(a));System.out.println();
			fire_set(fire, v);
			for(int[] a:fire) System.out.println(Arrays.toString(a));
			int ans=0;
			v = new boolean[H][W];
			ans=b(xy[0],xy[1],fire,v);
			if(ans==0) sb.append("IMPOSSIBLE").append("\n");	//탈출하지못하면 0
			else sb.append(ans).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	static int b(int i, int j, int[][] fire, boolean[][] v) {
		int ans=0;
		Queue<int[]> q = new ArrayDeque<>();
		v[i][j]=true;
		q.offer(new int[] {i,j,1});
		
		while(!q.isEmpty()) {
			int xy[] = q.poll();
			i = xy[0];
			j = xy[1];
			int k = xy[2];	//깊이
			if(i==0 || i==H-1 || j==0 || j==W-1) {
				ans=k;
				break;
			}
			//System.out.println(k);
			for(int d=0; d<4; d++) {
				int ni=i+di[d];
				int nj=j+dj[d];
				if(0<=ni && ni<H && 0<=nj && nj<W && !v[ni][nj] && (fire[ni][nj]==0 || k+1<fire[ni][nj])) {
					v[ni][nj]=true;
					q.offer(new int[] {ni,nj,k+1});
				}
			}
		}
		//System.out.println(ans);
		return ans;
	}
	static void fire_set(int[][] fire, boolean[][] v) {	//불
		Queue<int[]> q = new ArrayDeque<>();
		for(int x=0; x<H;x++) {
			for(int y=0; y<W;y++) {
				if(fire[x][y]==1) {
					v[x][y]=true;
					q.offer(new int[] {x,y,1});
				}
			}
		}
		while(!q.isEmpty()) {	//q가 비어있지않으면
			int xy[] = q.poll();
			int i = xy[0];
			int j = xy[1];
			int k = xy[2];	//깊이
			fire[i][j]=k;
			for(int d=0; d<4; d++) {
				int ni=i+di[d];
				int nj=j+dj[d];
				if(0<=ni && ni<H && 0<=nj && nj<W && !v[ni][nj] && fire[ni][nj]==0) {
					v[ni][nj]=true;
					q.offer(new int[] {ni,nj,k+1});
				}
			}
		}		
	}
}
