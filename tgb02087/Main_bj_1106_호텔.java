package a0214.study;

import java.io.*;
import java.util.*;

/*

ex. 9원을 써서 홍보시 3명의 고객이 는다.
돈의 정수배 만큼 투자가능 => 9원 3명 , 18원 6명, 27원 9명
안되는 경우 => 3원 1명, 12원 4명
"적어도 C명"을 늘이기 위해 투자해야 하는 돈의 최솟값

입력
고객의수C 도시의개수N (C<=1000, N<=20) 자연수
N개의줄
홍보비용 M 비용대비 고객의수 G (이 값은 <=100) 자연수

출력
적어도 C명을 늘이기 위해 투자해야 하는 돈의 최솟값
 */


public class Main_bj_1106_호텔 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_1106.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int C=Integer.parseInt(st.nextToken());
		int N=Integer.parseInt(st.nextToken());
		int[] cost = new int[C+100]; 		//호텔의 고객을 적어도 C명늘리기 위한 최소비용
		
		for(int i=1; i<cost.length;i++) cost[i]=1000*100;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int M=Integer.parseInt(st.nextToken());
			int G=Integer.parseInt(st.nextToken());
			for(int j=G; j<cost.length;j++) {
				cost[j]=Math.min(cost[j], cost[j-G]+M);	//최소 비용이므로 ex) 미리 계산된 비용 + 현재비용을 더한값중 최소값 계산
			}
			System.out.println(Arrays.toString(cost ));
		}
		int ans = 1000*100;
		for(int i=C; i<cost.length; i++) {
			ans = Math.min(ans, cost[i]);		//최소한의 C명부터 최대값까지중 최소비용을 출력
		}
		System.out.println(ans);
		

	}

}
