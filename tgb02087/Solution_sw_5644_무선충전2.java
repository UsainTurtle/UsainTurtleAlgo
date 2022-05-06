package a0409.study;

import java.io.*;
import java.util.*;

public class Solution_sw_5644_무선충전2 {
	static int[] di= {0,-1,0,1,0};	//x 상 우 하 좌   
	static int[] dj= {0,0,1,0,-1};
	static int[][][] map;
	static BC[] bc;
	static class BC{
		int x,y,c,p;
		public BC(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
	}
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_sw_5644.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int M = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			map = new int[10][10][2];
			//map 초기값 -1
			for(int i=0; i<10; i++) {
				for(int j=0; j<10; j++) {
					map[i][j][0]=-1;
					map[i][j][1]=-1;
				}
			}
			int[] moveA=new int[M+1];
			int[] moveB=new int[M+1];
			//처음자리도 계산해주기위해 0번지에 0을추가
			moveA[0]=0; moveB[0]=0;
			st = new StringTokenizer(br.readLine()," ");
			for(int i=1; i<=M; i++) moveA[i]=Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine()," ");
			for(int i=1; i<=M; i++) moveB[i]=Integer.parseInt(st.nextToken());
			bc = new BC[A];
			for(int i=0; i<A; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int y = Integer.parseInt(st.nextToken())-1;
				int x = Integer.parseInt(st.nextToken())-1;
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bc[i]=new BC(x,y,c,p);
			}
			for(int i=0; i<bc.length; i++) {
				bfs(new boolean[10][10],bc[i].x,bc[i].y,bc[i].c,bc[i].p,0,i);
			}
			//이동
			int[] userA=new int[] {0,0};
			int[] userB=new int[] {9,9};
			int sumA=0,sumB=0;
			for(int i=0; i<=M; i++) {
				userA[0]=userA[0]+di[moveA[i]];
				userA[1]=userA[1]+dj[moveA[i]];
				int curA=map[userA[0]][userA[1]][0];
				userB[0]=userB[0]+di[moveB[i]];
				userB[1]=userB[1]+dj[moveB[i]];
				int curB=map[userB[0]][userB[1]][0];
				//BC가 없을때
				if(curA==-1 && curB==-1) continue;
				//A만 있을때
				else if(-1<curA && curB==-1) sumA+=bc[curA].p;
				//B만 있을때
				else if(curA==-1 && -1<curB) sumB+=bc[curB].p;
				//사용하려는 BC가 같을때
				else if(curA==curB) {
					//2번쩨 BC가 둘다 없을때 : 첫번째 BC 반나누기
					if(map[userA[0]][userA[1]][1]==-1 && map[userB[0]][userB[1]][1]==-1) {
						sumA+=bc[curA].p/2;
						sumB+=bc[curB].p/2;
					}
					//A만 두개일때
					else if(-1<map[userA[0]][userA[1]][1] && map[userB[0]][userB[1]][1]==-1) {
						sumA+=bc[map[userA[0]][userA[1]][1]].p;
						sumB+=bc[curB].p;
					}
					//B만 두개일때
					else if(map[userA[0]][userA[1]][1]==-1 && -1<map[userB[0]][userB[1]][1]) {
						sumA+=bc[curA].p;
						sumB+=bc[map[userB[0]][userB[1]][1]].p;
					}
					//둘다 있을때
					else {
						//두번째BC크기가 A보다 B가크면
						if(bc[map[userA[0]][userA[1]][1]].p<bc[map[userB[0]][userB[1]][1]].p) {
							sumA+=bc[curA].p;
							sumB+=bc[map[userB[0]][userB[1]][1]].p;
						}
						//두번째BC크기가 B보다 A가크면
						else {
							sumA+=bc[map[userA[0]][userA[1]][1]].p;
							sumB+=bc[curB].p;
						}
					}
				}
				//BC두개가 다를때
				else {
					sumA+=bc[curA].p;
					sumB+=bc[curB].p;
				}
			}
			sb.append("#").append(tc).append(" ").append(sumA+sumB).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	static void bfs(boolean[][] v,int x, int y, int c, int p, int ds, int idx) {
		Queue<int[]> q= new ArrayDeque<>();
		v[x][y]=true;
		q.offer(new int[] {x,y,ds});
		while(!q.isEmpty()) {
			int[] xy=q.poll();
			x=xy[0];
			y=xy[1];
			// 처음일때
			if(map[x][y][0]==-1) map[x][y][0]=idx;
			else {
				// 현재충전량이 m0보다 크거나 같을때
				if(bc[map[x][y][0]].p<=p) {
					map[x][y][1]=map[x][y][0];
					map[x][y][0]=idx;
				}
				//m0보다 작을때
				else {
					if(map[x][y][1]==-1) map[x][y][1]=idx;
					else {
						if(bc[map[x][y][1]].p<p) map[x][y][1]=idx;
					}
				}
			}
			if(c==xy[2]) continue;
			for(int d=1; d<5; d++) {
				int nx=xy[0]+di[d];
				int ny=xy[1]+dj[d];
				if(nx<0 || 10<=nx || ny<0 || 10<=ny || v[nx][ny]) continue;
				v[nx][ny]=true;
				q.offer(new int[] {nx,ny,xy[2]+1});
			}
		}
	}
}
