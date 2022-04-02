package a0328.study;

import java.io.*;
import java.util.*;

/*
입력
컴퓨터의 수 (100이하)
네트워크상의 직접 연결된 컴퓨터 쌍의 수
연결된 번호쌍

출력
1번컴퓨터가 바이러스에 걸릴때, 감염되는 컴퓨터의 수
 */
public class Main_bj_2606_바이러스 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2606.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		int[] computer=new int[N+1];
		for(int i=1; i<=N; i++) computer[i]=i;
		System.out.println(Arrays.toString(computer));
		for(int i=0; i<C;i++) {
			StringTokenizer st= new StringTokenizer(br.readLine()," ");
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			union(computer,a,b);
			System.out.println(Arrays.toString(computer));
		}
		int cnt=0;
		for(int i=2; i<=N; i++) if(findSet(computer, i)==1) cnt++;
		System.out.println(cnt);
		br.close();
	}
	static int findSet(int[] com, int n) {
		if(n==com[n]) return n;
		return com[n] = findSet(com, com[n]);
	}
	static boolean union(int[] com, int a, int b) {
		int aRoot = findSet(com, a);
		int bRoot = findSet(com, b);
		if(aRoot==bRoot) return false;
		
		else if(aRoot>bRoot) com[aRoot]=bRoot;
		else com[bRoot]=aRoot;
		return true;
	}
}
