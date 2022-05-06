package a0409.study;

import java.io.*;
import java.util.*;

public class Solution_sw_5653_줄기세포배양 {
	static int[] di= {-1,1,0,0};	//상 하 좌 우
	static int[] dj= {0,0,-1,1};
	static int n,m;
	static boolean[][] v;
	static List<int[]> non, list;
	static Cell[][] map;
	static class Cell{
		int life;	//0:생명력, 1:비활성, 2:활성, 3:죽은
		int nac;
		int ac;
		boolean die;
		public Cell(int life, int nac, int ac, boolean die) {
			this.life=life;
			this.nac = nac;
			this.ac = ac;
			this.die = die;
		}
	}
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_sw_5653.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T= Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			n=2*K+N+1;
			m=2*K+M+1;
			map = new Cell[n][m];
			non = new ArrayList<>();
			list = new ArrayList<>();
			v=new boolean[n][m];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine()," ");
				for(int j=0; j<M; j++) {
					int n=Integer.parseInt(st.nextToken());
					if(n!=0) {
						non.add(new int[] {i+K,j+K});
						map[i+K][j+K]=new Cell(n,n,0,false);
					}
				}
			}
			int time=0;
			while(true) {
				time++;
				for(int i=non.size()-1; 0<=i; i--) {
					int[] xy=non.get(i);
					//비활성시간이 남아있으면
					if(0<map[xy[0]][xy[1]].nac) {
						//-1감소
						map[xy[0]][xy[1]].nac--;
						//감소후 0이면 활성화
						if(map[xy[0]][xy[1]].nac==0) map[xy[0]][xy[1]].ac=map[xy[0]][xy[1]].life;
					}
					//비활성시간이 0일때
					else {
						//첫1시간때
						if(map[xy[0]][xy[1]].ac==map[xy[0]][xy[1]].life) {
							bfs(xy[0],xy[1]);
							//감소후 0이면 죽는다.
							map[xy[0]][xy[1]].ac--;
							if(map[xy[0]][xy[1]].ac==0) {
								map[xy[0]][xy[1]].die=true;
								non.remove(i);
							}
						}
						else {
							//감소후 0이면 죽는다.
							map[xy[0]][xy[1]].ac--;
							if(map[xy[0]][xy[1]].ac==0) {
								map[xy[0]][xy[1]].die=true;
								non.remove(i);
							}
						}
					}
				}
				for(int i=0; i<list.size(); i++) {
					int[] xy=list.get(i);
					v[xy[0]][xy[1]]=true;
					non.add(new int[] {xy[0],xy[1]});
				}
				list.clear();
				if(time==K) break;
			}
			//for(Cell[] a:map) System.out.println(Arrays.toString(a));
			sb.append("#").append(tc).append(" ").append(non.size()).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	static void bfs(int x, int y) {
		v[x][y]=true;
		for(int d=0; d<4; d++) {
			int nx=x+di[d];
			int ny=y+dj[d];
			if(nx<0 || n<=nx || ny<0 || m<=ny || v[nx][ny]) continue;
			if(map[nx][ny]==null) {
				list.add(new int[] {nx,ny});
				map[nx][ny]=new Cell(map[x][y].life,map[x][y].life,0,false);
			}
			else {
				//생명력이 클경우 갱신
				if(map[nx][ny].life<map[x][y].life) {
					map[nx][ny].nac=map[x][y].life;
					map[nx][ny].life=map[x][y].life;
				}
			}

		}
	}
}
