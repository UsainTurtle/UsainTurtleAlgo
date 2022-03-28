package a0310.study;

import java.io.*;
import java.util.*;

/*

NxN격자
초기에 격자엔 검은색:-1, 무지개:0, 일반:M가지색(M이하의자연수) 블록이 하나씩 있다.

조건
블록그룹에는 일반블록이 적어도 하나, 일반블록 색은 모두 같아야한다.(같은수)
검은색 블록(-1)은 포함되면 안되고, 무지개블록(0)은 얼마나 들어있든 상관없다.
2<=블록의 개수
인접한 칸으로 이동해서 그룹의 모든 칸을 이동가능해야한다.
블록 그룹의 기준 블록은 무지개블록이 아닌 블록중에서 1.행의 번호가 가장 작은 블록, 2. 열의 번호가 가장 작은 블록

오토플레이
1. 크기가 가장 큰 블록그룹, 그룹이 여러개면 무지개 블록의 수가 가장 많은 블록 그룹, 이것도 여러개면 기준 블록의 행이 가장 큰것, 
다음은 열이 가장 큰것
2. 1의 블록그룹의 모든 블록을 제거, 블록그룹에 포함된 블럭의 수^2점 휙득
3. 격자에 중력이 작용
4. 격자가 90도 반시계 방향으로 회전
5. 다시 격자에 중력이 작용

격자에 중력작용
검은색 블록을 제외한 모든 블록이 행의 번호가 가장 큰 칸으로 이동. 
이동은 다른 블록이나 격자의 경계를 만나기 전까지 계속된다.


입력
격자N 색상개수 M
격자의 정보
(1<=N<=20) (1<=M<=5)

출력
휙득한 점수의 합

정리
1. 크기가 가장큰 블록그룹 찾기
2. 블록그룹 블록 제거 및 점수 휙득
3. 중력 -> 90도 반시계회전 -> 중력

 */
public class Main_bj_21609_상어중학교 {
	static int[] di= {-1,1,0,0};	//상 하 좌 우
	static int[] dj= {0,0,-1,1};
	static int rb1,rb2;
	static int[] rbx1,rbx2;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_21609.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		//for(int[] a:map)System.out.println(Arrays.toString(a));
		int sum=0;
		
		while(true) {
			boolean[][] g = new boolean[N][N];
			boolean[][] ans = new boolean[N][N];
			rbx2=new int[2];
			rb2=0;
			boolean[][] v = new boolean[N][N];
			int max = 0;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!v[i][j]&&map[i][j]!=-5&&map[i][j]!=-1) {
						g = new boolean[N][N];
						rb1=0;
						rbx1=new int[2];
						int n=b(N,i,j,map,v,g);
						//System.out.print(n+" ");
						//System.out.printf("rb1 : %d rb2: %d\n",rb1,rb2);
						if(max<n) {
							max=n;
							ans=g;
							rb2=rb1;
							rbx2=rbx1;
						}
						else if(max==n) {
							//System.out.println(n);
							if(rb2<rb1) {	//무지개블럭수가 많으면
								max=n;
								ans=g;
								rb2=rb1;
								rbx2=rbx1;
							}
							else if(rb2==rb1) {
								if(rbx2[0]<rbx1[0]) {
									max=n;
									ans=g;
									rb2=rb1;
									rbx2=rbx1;
								}
								else if(rbx2[0]==rbx1[0]) {
									if(rbx2[1]<rbx1[1]) {
										max=n;
										ans=g;
										rb2=rb1;
										rbx2=rbx1;
									}
								}
							}
						}
						//for(boolean[] a:v)System.out.println(Arrays.toString(a));System.out.println();
						for(int x=0; x<N; x++) {
							for(int y=0; y<N; y++) {
								if(g[x][y]) {
									if(map[x][y]==0) {
										v[x][y]=false;
									}
								}
							}
						}
					}

					
				}
				//System.out.println();
			}
			if(max<2)break;
			//System.out.println(max);
			sum+=max*max;
			
			//for(boolean[] a:ans)System.out.println(Arrays.toString(a));System.out.println();
			//for(int[] a:map)System.out.println(Arrays.toString(a));System.out.println();
			remove(N,map,ans);
			//for(int[] a:map)System.out.println(Arrays.toString(a));System.out.println();
			map=turn(N,map);
			//for(int[] a:map)System.out.println(Arrays.toString(a));System.out.println();
			set(N,map);
			//System.out.println("제거");
			//for(int[] a:map)System.out.println(Arrays.toString(a));System.out.println();
			
		}
		System.out.println(sum);
		
		br.close();
	}
	static int b(int N, int i, int j, int[][] map, boolean[][] v, boolean[][] g) {
		Queue<int[]> q = new ArrayDeque<>();
		//rb1=0;
		boolean rb_flag=false;
		int num=map[i][j];
		int cnt=0;
		v[i][j]=true;
		g[i][j]=true;
		if(map[i][j]==0)rb1++;
		q.offer(new int[] {i,j});
		while(!q.isEmpty()) {
			int[] xy=q.poll();
			i=xy[0];
			j=xy[1];
			if(!rb_flag) {
				if(0<map[i][j]) {
					rbx1[0]=i;
					rbx1[1]=j;
					rb_flag=true;
				}
			}
			cnt++;
			
			for(int d=0; d<4; d++) {
				int ni=i+di[d];
				int nj=j+dj[d];
				if(0<=ni && ni<N && 0<=nj && nj<N && !v[ni][nj] && map[ni][nj]!=-5 && (map[ni][nj]==num || map[ni][nj]==0)) {
					if(map[ni][nj]==-1) continue;
					//System.out.println(map[ni][nj]);
					if(map[ni][nj]==0) rb1++;
					v[ni][nj]=true;
					g[ni][nj]=true;
					q.offer(new int[] {ni,nj});
				}
			}
		}
		//System.out.println("rb1:"+rb1);
		//System.out.println("cnt:"+cnt);
		if(rb1==cnt) cnt=0;
		return cnt;
	}
	static void remove(int N, int[][] map, boolean[][] ans) {	//삭제
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(ans[i][j]) {
					map[i][j]=-5;
				}
			}
		}
		//for(int[] a:map)System.out.println(Arrays.toString(a));System.out.println();
		set(N,map);

	}
	static void set(int N, int[][] map) {	//중력
		for(int j=0;j<N;j++) {
			for(int i=N-2;0<=i;i--) {
				if(0<=map[i][j]) {	//0보다 크거나같을때
					int ni=i+1;
					while(map[ni][j]==-5) {
						map[ni][j]=map[ni-1][j];
						map[ni-1][j]=-5;
						ni++;
						if(ni==N) break;
					}
				}
			}
		}
	}
	static int[][] turn(int N, int[][] map) {	//회전
		int[][] tmp = new int[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0; j<N;j++) {
				tmp[N-1-j][i]=map[i][j];
			}
		}
		return tmp;
	}
}
