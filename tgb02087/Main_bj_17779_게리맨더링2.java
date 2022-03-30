package a0323.study;

import java.io.*;
import java.util.*;

/*
NxN 구역을 5개의 선거구로 나눈다.
선거구는 적어도 한개의 구역을 포함해야한다.
한 선거구의 구역들은 모두 연결되어 있어야한다.

선거구 기준
1. 기준점(x,y)와 경계의 길이 d1,d2 (d1,d2>=1) (1<=x<x+d1+d2<=N) (1<=y-d1<y<y+d2<=N)
2. 경계선
	2.1 (x,y),(x+1,y-1),...,(x+d1,y-d1)
	2.2 (x, y), (x+1, y+1), ..., (x+d2, y+d2)
	2.3 (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
	2.4 (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
3. 경계선과 경계선의 안구역은 5번 선거구
4. 3을 제외한 모든 구역의 기준
	1번 선거구 : 1<=r<x+d1, 1<=c<=y
	2번 선거구 : 1<=r<=x+d2, y<c<=N
	3번 선거구 : x+d1<=r<=N, 1<=c<y-d1+d2
	4번 선거구 :  x+d2<r<=N, y-d1+d2<=c<= N

선거구의 인구는 선거구의 모든 인구의 합

입력
N
NxN의 인구

출력
인구가 가장 많은 구역- 가장 적은 구역의 최솟값. ( 둘의 차이가 적어야함)

제한
5<=N<=20
1<=인구<=100

 */

public class Main_bj_17779_게리맨더링2 {
	static int[] di= {1,1,-1,-1};	//d1,d2구하기
	static int[] dj= {1,-1,-1,1};
	static int ans=Integer.MAX_VALUE;
	static int sum=0;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17779.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for(int i=0; i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				sum+=map[i][j];
			}
		}
		int n=N-2;
		int[] x= new int[n];
		int[] y= new int[n];
		// x,y좌표
		for(int i=0; i<n; i++) x[i]=i;
		for(int i=0; i<n; i++) y[i]=i+1;
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				List<int[]> list=new ArrayList<>();
				list = p(list,N,0,new int[2],x[i],y[j]);
				map_set(map,list,x[i],y[j],N);
			}
		}
		System.out.println(ans);
		br.close();
	}
	static void map_set(int[][] map, List<int[]> list, int x, int y,int N) {
		/*
		 * xy[0] : 우
		 * xy[1] : 하
		 * xy[2] : 좌
		 */
		int[][] xy=new int[3][2];
		for(int i=0; i<list.size();i++) {
			int all=sum;
			int num1=0,num2=0,num3=0,num4=0;
			boolean[][] v = new boolean[N][N];
			int d1=list.get(i)[0];
			int d2=list.get(i)[1];
			int nx=x;
			int ny=y;
			for(int s=0; s<d2; s++) {	//우하2
				nx=nx+di[0];	//d1
				ny=ny+dj[0];
				v[nx][ny]=true;
			}
			xy[0][0]=nx;
			xy[0][1]=ny;
			for(int s=0; s<d1; s++) {	//우하2
				nx=nx+di[1];	//d2
				ny=ny+dj[1];
				v[nx][ny]=true;
			}
			xy[1][0]=nx;
			xy[1][1]=ny;
			for(int s=0; s<d2; s++) {	//우하3
				nx=nx+di[2];	//d2
				ny=ny+dj[2];
				v[nx][ny]=true;
			}
			xy[2][0]=nx;
			xy[2][1]=ny;
			for(int s=0; s<d1; s++) {	//우상4
				nx=nx+di[3];	//d1
				ny=ny+dj[3];
				v[nx][ny]=true;
			}
			int max=0;
			int min=Integer.MAX_VALUE;
			//1번구역
			for(int j=0; j<xy[2][0];j++) {
				for(int k=0; k<=y; k++) {
					if(v[j][k])break;
					num1+=map[j][k];
				}
			}
			max=Math.max(num1, max);
			min=Math.min(num1, min);
			//2번구역
			for(int j=0; j<=xy[0][0];j++) {
				for(int k=N-1; y<k; k--) {
					if(v[j][k])break;
					num2+=map[j][k];
				}
			}
			max=Math.max(num2, max);
			min=Math.min(num2, min);
			//3번구역
			for(int j=xy[2][0]; j<N;j++) {
				for(int k=0; k<xy[1][1]; k++) {
					if(v[j][k])break;
					num3+=map[j][k];
				}
			}
			max=Math.max(num3, max);
			min=Math.min(num3, min);
			//4번구역
			for(int j=xy[0][0]+1; j<N;j++) {
				for(int k=N-1; xy[1][1]<=k; k--) {
					if(v[j][k])break;
					num4+=map[j][k];
				}
			}
			max=Math.max(num4, max);
			min=Math.min(num4, min);
			all=all-num1-num2-num3-num4;
			if(0<all) {
				max=Math.max(all, max);
				min=Math.min(all, min);
				if(max-min<ans) ans=max-min;
			}
		}
	}
	static List<int[]> p(List<int[]> list, int N, int cnt, int[] d, int x, int y) {
		if(cnt==2) {
			//System.out.println(Arrays.toString(d));
			//if((x<x+d[0]+d[1] && x+d[0]+d[1]<=N) || (y-d[0]<y && y<y+d[1])) {
			if(x+d[0]+d[1]<N){
				if(0<=y-d[0] && y+d[1]<N) {
					list.add(new int[] {d[0],d[1]});
				}
			}
			return list;
		}
		for(int i=1;i<=N-2;i++) {
			d[cnt]=i;
			p(list,N,cnt+1,d,x,y);
		}
		return list;
	}
}
