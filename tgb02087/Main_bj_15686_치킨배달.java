package a0223.camp;

import java.io.*;
import java.util.*;

/*

치킨거리 : 집과 가장 가까운 치킨집
도시의 치킨거리 : 모든 집의 치킨 거리의 합

두칸(r1,c1)와(r2,c2)사이 거리는 |r1-r2| + |c1-c2|


치킨집 최대 M개

입력
N M (2<=N<=50) (1<=M<=13)
N줄의 도시정보 (0: 빈칸 1:집 2:치킨집)
1<=집의 개수<=2N
M<=치킨집 개수<=13

출력
도시의 치킨거리 최솟값
 */
public class Main_bj_15686_치킨배달 {
	static List<cur> ck, hm;
	static int result=Integer.MAX_VALUE;
	static class cur{
		int r,c;

		public cur(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args)throws Exception {
		System.setIn(new FileInputStream("res/input_bj_15686.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ck = new ArrayList<>();
		hm = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j=0; j<N; j++) {
				int a=Integer.parseInt(st.nextToken());
				if(a==1) hm.add(new cur(i,j));
				else if(a==2) ck.add(new cur(i,j));
			}
		}
		int[] ckc= new int[ck.size()];
		int[] ans = new int[M];
		for(int i=0; i<ck.size();i++) ckc[i]=i;
		c(0,0,ckc,ans);
		System.out.println(result);	
		
	}
	static void c(int cnt, int start, int[] ckc, int[] ans) {
		if(cnt==ans.length) {
			//System.out.println(Arrays.toString(ans));
			int a =set(ans);
			if(a<result) result = a;
			return;
		}
		
		for(int i=start; i<ckc.length;i++) {
			ans[cnt]=ckc[i];
			c(cnt+1,i+1,ckc,ans);
		}
	}
	static int set(int[] ans) {

		int[] hmck = new int[hm.size()];
		for(int i=0; i<hm.size(); i++) {
			int min=Integer.MAX_VALUE;
			for(int j=0; j<ans.length; j++) {
				int a=Math.abs(ck.get(ans[j]).r-hm.get(i).r) + Math.abs(ck.get(ans[j]).c-hm.get(i).c);
				if(a<min) min=a;
			}
			hmck[i]=min;
		}
		int sum=0;
		for(int i=0; i<hmck.length;i++) sum+=hmck[i];
		return sum;

	}

}
