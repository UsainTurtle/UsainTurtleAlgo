package a0318.study;

import java.io.*;
import java.util.*;

/*
세가지 속성 (x축 : -> , y축 : 아래)
1. 시작 점 
2. 시작 방향 
3. 세대 

0세대 드래곤 커브 : 길이1인 선분
1세대 드래곤 커브 : 0세대 드래곤 커브 끝점을 기준으로 시계방향 90도 회전, 0세대 드래곤 커브의 끝점에 붙인 것.
2세대 드래곤 커브 : 1세대랑 같다.

즉 K세대(K>1)세대 드래곤 커브는 K-1세대 드래곤 커브를 끝 점을 기준으로 90도 시계 방향 회전 시킨 다음, 그것을 끝 점에 붙인것.
크기가 100x100인 격자 위에 드래곤 커브가 N개 있다. 이때 1x1의 정사각형 네 꼭짓점 모두 드래곤 커브의 일부인 정사각형의 개수를 구하라.
0<=x<=100, 0<=y<=100이 유효한 좌표이다.

입력
드래곤 커브의 개수 N (1<=N<=20) 이 주어진다.
N개의 줄에는 드래곤 커브의 정보 x y d g (드래곤 커브 시작점 (x,y)  d는 시작방향, g는 세대)
(0<=x,y<=100, 0<=d<=3, 0<=g<=10)
격자 밖으로 커브는 못나간다. 서로 커브는 겹칠수 있다.
방향
0 : x좌표가 증가 오른쪽 
1 : y좌표가 감소 위쪽 
2 : x좌표가 감소 왼쪽 
3 : y좌표가 증가 아래

출력
정사각형 네 꼭짓점 모두 커브의 일부인 것의 개수

 */

public class Main_bj_15685_드래곤커브 {
	static int[] di= {1,0,-1,0};	// 우 상 좌 하 
	static int[] dj= {0,-1,0,1};
	static int minx=101,miny=101;	//map탐색시 최소범위를 위한 값
	static int maxx=0, maxy=0;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_15685.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[101][101];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			minx=Math.min(minx, y); miny=Math.min(miny, x);
			maxx=Math.max(maxx, y); maxy=Math.max(maxy, x);
			set(map,x,y,d,g);
		}
		int cnt=chk_cnt(map);
		System.out.println(cnt);
		br.close();
	}
	static void set(int[][]  map, int x, int y, int d, int k) {
		map[y][x]=1;
		List<Integer> t = new ArrayList<>();
		t.add(d);
		for(int z=0; z<k; z++) {	//세대만큼반복 
			int s=t.size();
			//현끝점부터 현시작점까지의 방향을, 다시 새로운시작점(현끝점)부터 새로운끝점까지 방향 저장
			for(int i=s-1; 0<=i; i--) {		
				if(t.get(i)==0) t.add(1);		//우->상
				else if(t.get(i)==1) t.add(2);	//상->좌
				else if(t.get(i)==2) t.add(3);	//좌->하
				else if(t.get(i)==3) t.add(0);	//하->우
			}
		}
		//처음 위치부터 저장되었던 방향으로 나아간다. (선을잇는다.)
		while(!t.isEmpty()) {
			d=t.remove(0);
			x+=di[d];
			y+=dj[d];
			if(0<=x && x<=100 && 0<=y && y<=100) {
				map[y][x]=1;
				minx=Math.min(minx, y); miny=Math.min(miny, x);
				maxx=Math.max(maxx, y); maxy=Math.max(maxy, x);
			}
		}	
	}
	static int chk_cnt(int[][] map) {
		int cnt=0;
		// 입력된 범위만큼 1x1정사각형이 4개있는지 확인. 
		for(int i=minx; i<maxx; i++) {
			jump:for(int j=miny; j<maxy; j++) {
				for(int x=0;x<2;x++) {
					for(int y=0;y<2;y++) {
						//0이있을시 정사각형 탐색 다음위치로 
						if(map[i+x][(j+y)]==0) continue jump;
					}
				}
				cnt++;
			}
		}
		return cnt;
	}
}
