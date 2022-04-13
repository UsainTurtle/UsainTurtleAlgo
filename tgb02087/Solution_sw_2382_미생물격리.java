package a0402.study;

import java.io.*;
import java.util.*;

/*
NxN 정사각형
K개의 미생물 군집

1. 최초 각 위치, 미생물수, 이동방향이 주어진다. 약품에는 배치가 안되고, 상하좌우 4방향 이동가능
2. 각 군집은 1시간마다 이동방향에 있는 다음 셀로 이동
3. 미생물 군집이 이동후 약품에 닿으면 절반이 죽고, 방향이 반대로 바뀐다.
(홀수인 경우, 나누기2를 하고 소수점을 버린값. 즉, 미생물 1마리인경우 0으로 바뀌어 사라지게된다.)
4. 이동 후 두개이상의 군집이 한셀에 모이는 경우 군집이 합게된다.
(합쳐지는 미생물수가 많은 군집의 방향이 합쳐진 군집의 방향)

M시간후 남아있는 미생물들의 총합을 구하기.

이동방향 
상:1 하:2 좌:3 우:4

입력
테케 T
셀의개수 N 격리시간 M 미생물 군집의 개수 K
K줄의 미생물정보
세로위치 가로위치 미생물 수 이동방향

출력
#tc 미생물 수
 */
public class Solution_sw_2382_미생물격리 {
	static int[] di= {-1,1,0,0};	//상 하 좌 우
	static int[] dj= {0,0,-1,1};
	static List<int[]> list;
	static boolean[][] v;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_sw_2382.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T=Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[][][] map = new int[N][N][3];
			for(int k=0; k<K; k++) {
				st = new StringTokenizer(br.readLine()," ");
				int r= Integer.parseInt(st.nextToken());
				int c= Integer.parseInt(st.nextToken());
				map[r][c][0]=Integer.parseInt(st.nextToken());	// 미생물수
				map[r][c][1]=Integer.parseInt(st.nextToken())-1;	// 이동방향
				map[r][c][2]=0;	//이동했는지 유무 0:이동전 1:이동후
			}
//			System.out.println("=====맵 출력====");
//			for(int[][] a:map) {
//				for(int j=0; j<a.length; j++) {
//					System.out.printf("\t");
//					System.out.print(Arrays.toString(a[j]));
//				}
//				System.out.println();
//			}
			for(int i=0; i<M; i++) {
				boolean[][] chk= new boolean[N][N];
				v=new boolean[N][N];
				list=new ArrayList<>();
				for(int r=0; r<N; r++) {
					for(int c=0; c<N; c++) {
						//미생물이 있으면
						if(0<map[r][c][0] && !v[r][c]) {
							iso(map,chk,r,c,N);
						}
					}
				}
				
				for(int r=0; r<N; r++) {
					for(int c=0; c<N; c++) {
						if(chk[r][c]) {
							set(map,r,c);
						}
						map[r][c][2]=0;
					}
				}
//				System.out.println("=====맵 출력====");
//				for(int[][] a:map) {
//					for(int j=0; j<a.length; j++) {
//						System.out.printf("\t");
//						System.out.print(Arrays.toString(a[j]));
//					}
//					System.out.println();
//				}
			}
			int cnt=0;
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					if(0<map[r][c][0]) cnt+=map[r][c][0];
				}
			}
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
	static void iso(int[][][] map, boolean[][] chk, int r, int c, int N) {
		v[r][c]=true;
		int s=map[r][c][0];
		int d=map[r][c][1];
		r=r+di[d];
		c=c+dj[d];
		v[r][c]=true;
		//약품 공간이면
		if(0==r || N-1==r || 0==c || N-1==c) {
			//이동하려는 곳에 있을때
			if(0<map[r][c][0]) {
				int[] temp=new int[] {r-di[d],c-dj[d],s,d};
				map[r-di[d]][c-dj[d]][0]=0;
				map[r-di[d]][c-dj[d]][1]=0;
				map[r-di[d]][c-dj[d]][2]=0;
				//해당 미생물 이동
				iso(map,chk,r,c,N);
				//다시 전 미생물 호출
				s=temp[2];
				d=temp[3];
				r=temp[0]+di[d];
				c=temp[1]+dj[d];

				//System.out.println(r+" "+c+" "+s+" "+d);
				//이동하려는곳이 비어있으면
				if(map[r][c][0]==0) {
					map[r][c][0]=s/2;
					if(s/2==0) {
						map[r][c][1]=0;
						map[r][c][2]=0;
					}
					else {
						if(d==0) map[r][c][1]=1;
						else if(d==1) map[r][c][1]=0;
						else if(d==2) map[r][c][1]=3;
						else  map[r][c][1]=2;
						map[r][c][2]=1;
					}
				}
			}
			// 없을때
			else {
				//미생물수, 방향 이동
				map[r][c][0]=s/2;
				if(s/2==0) {
					map[r][c][1]=0;
					map[r][c][2]=0;
					
				}
				else {
					if(d==0) map[r][c][1]=1;
					else if(d==1) map[r][c][1]=0;
					else if(d==2) map[r][c][1]=3;
					else  map[r][c][1]=2;
					// 원래 있던곳 삭제
					map[r][c][2]=1;
				}
				map[r-di[d]][c-dj[d]][0]=0;
				map[r-di[d]][c-dj[d]][1]=0;
				map[r-di[d]][c-dj[d]][2]=0;
			}

		}
		//일반 공간일때
		else {
			//이동 하려는곳이 비어있으면
			if(map[r][c][0]==0) {
				//중복체크가 되어있으면
				if(chk[r][c]) {
					list.add(new int[] {r,c,s,d});
					// 원래 있던곳 삭제
					map[r-di[d]][c-dj[d]][0]=0;
					map[r-di[d]][c-dj[d]][1]=0;
				}
				else {
					//미생물수, 방향 이동
					map[r][c][0]=s;
					map[r][c][1]=d;
					// 원래 있던곳 삭제
					map[r-di[d]][c-dj[d]][0]=0;
					map[r-di[d]][c-dj[d]][1]=0;
					map[r][c][2]=1;
				}
			}
			//이동 하려는곳에 미생물이 있을때
			else {
				// 이동한 미생물 일때
				if(map[r][c][2]==1) {
					//중복체크가 되어있으면
					if(chk[r][c]) {
						list.add(new int[] {r,c,s,d});
						// 원래 있던곳 삭제
						map[r-di[d]][c-dj[d]][0]=0;
						map[r-di[d]][c-dj[d]][1]=0;
					}
					else {
						chk[r][c]=true;
						list.add(new int[] {r,c,s,d});
						r=r-di[d];
						c=c-dj[d];
						map[r][c][0]=0;
						map[r][c][1]=0;
						r=r+di[d];
						c=c+dj[d];
						s=map[r][c][0];
						d=map[r][c][1];
						list.add(new int[] {r,c,s,d});
						map[r][c][0]=0;
						map[r][c][1]=0;
					}
				}
				// 이동전 미생물 일때
				else {
					int[] temp=new int[] {r-di[d],c-dj[d],s,d};
					map[r-di[d]][c-dj[d]][0]=0;
					map[r-di[d]][c-dj[d]][1]=0;
					map[r-di[d]][c-dj[d]][2]=0;
					//해당 미생물 이동
					iso(map,chk,r,c,N);
					//다시 전 미생물 호출
					s=temp[2];
					d=temp[3];
					r=temp[0]+di[d];
					c=temp[1]+dj[d];

					//System.out.println(r+" "+c+" "+s+" "+d);
					//이동하려는곳이 비어있으면
					if(map[r][c][0]==0) {
						map[r][c][0]=s;
						map[r][c][1]=d;
						map[r][c][2]=1;
					}
					//미생물이 있으면
					else {
						chk[r][c]=true;
						list.add(new int[] {r,c,s,d});
						r=r-di[d];
						c=c-dj[d];
						map[r][c][0]=0;
						map[r][c][1]=0;
						r=r+di[d];
						c=c+dj[d];
						s=map[r][c][0];
						d=map[r][c][1];
						list.add(new int[] {r,c,s,d});
						map[r][c][0]=0;
						map[r][c][1]=0;
					}
					
				}

			}
			
		}
	}
	static void set(int[][][] map, int x, int y) {
		List<int[]> l = new ArrayList<>();
		//for(int[] a:list)System.out.println(Arrays.toString(a)); System.out.println();
		for(int i=list.size()-1; 0<=i; i--) {
			int r=list.get(i)[0];
			int c=list.get(i)[1];
			if(r==x && c==y) {
				l.add(list.get(i));
				list.remove(i);
			}
		}
		int max=0;
		int d=0;
		for(int i=0; i<l.size(); i++) {
			map[x][y][0]+=l.get(i)[2];
			if(max<l.get(i)[2]) {
				max=l.get(i)[2];
				d=l.get(i)[3];
			}
		}
		map[x][y][1]=d;
		map[x][y][2]=0;
	}
}
