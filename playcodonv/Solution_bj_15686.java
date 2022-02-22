

import java.util.*;
import java.io.*;

class Point{
	public int x, y;
	Point(int x, int y){ //좌표를 편리하게 관리하기 위한 클래스
		this.x=x;
		this.y=y;
	}
}

public class Solution_bj_15686{
	static int n, m;
	static int res=Integer.MAX_VALUE;
	static int[] visited;
	static ArrayList<Point> home, chicken;
	
	public static void DFS(int L, int s){
		if(L==m){
						
			int sum=0;
			for(Point h : home){ //모든 집들을 돌면서
				int dis=Integer.MAX_VALUE;
				for(int i : visited){ //조합으로 선택한 치킨들과의 거리를 계산해서 누적
					dis=Math.min(dis, Math.abs(h.x-chicken.get(i).x)+Math.abs(h.y-chicken.get(i).y));
				}
				sum+=dis;
			}
			res=Math.min(res, sum);
		}
		else{//좋아요~
			for(int i=s; i<chicken.size(); i++){ //조합
				visited[L]=i;
				DFS(L+1, i+1);
			}
		}
	}

	public static void main(String[] args) throws Exception{

				//0빈칸 1은 집 2 치킨집
				//(r1, c1)과 (r2, c2) 사이의 거리는 |r1-r2| + |c1-c2|
				//치킨집 중에 최대 m개를 남겨두고 나머진 폐업  
				//도시의 치킨 거리 : 모든 치킨 거리의 총합 
				//도시의 치킨거리가 가장작을경우가 정답 !
				
				
				/*최대 M개 중에서 몇개를 남겼을때 치킨 배달거리가 최소가 될것인지
				 * 
				 * 치킨집 1,2,3,4,5 중(1,2,3,4) (1,2,3)을 선택한 경우를 가정 -> 그묶음과 각 집들과의 거리 턴마다 누적!
				 * ->그 중에 최소값
				 */
		System.setIn(new FileInputStream("res/input_bj_15686.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine()," ");
		
		
		n=Integer.parseInt(st.nextToken()); //입력
		m=Integer.parseInt(st.nextToken());
		
		chicken=new ArrayList<>(); 
		home=new ArrayList<>();
		visited=new int[m];
		
		for(int i=0; i<n; i++){
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<n; j++){
				int tmp=	Integer.parseInt(st.nextToken());
				
				if(tmp==1) home.add(new Point(i, j)); // 집인 경우 집 리스트에 추가
				else if(tmp==2) chicken.add(new Point(i, j)); //치킨집인 경우 치킨집 리스트에 추가
			}
		}


		DFS(0, 0);
		System.out.println(res);
	}
}



