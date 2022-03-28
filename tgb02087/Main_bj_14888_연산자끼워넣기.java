package a0308.study;

import java.io.*;
import java.util.*;

/*

입력
수의 개수 N (2<=N<=11)
수 An(1<=An<=100)
+, -, *, / 개수

출력
최댓값
최솟값
(-10억<=합<=10억)
 */

public class Main_bj_14888_연산자끼워넣기 {
	static int min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14888.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		char[] oper = new char[N-1]; //+,-,*,/
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=0; i<N; i++) num[i]=Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine()," ");
		int idx=0;
		char op='\0';
		for(int i=0; i<4; i++) {
			int n=Integer.parseInt(st.nextToken());
			if(i==0) op='+';
			if(i==1) op='-';
			if(i==2) op='*';
			if(i==3) op='/';
			for(int j=0; j<n; j++) oper[idx++]=op;
		}
		//System.out.println(Arrays.toString(oper));
		boolean[] v = new boolean[N-1];
		char[] ans = new char[N-1];
		set(num,oper,ans,v,N,0);
		System.out.println(max);
		System.out.println(min);
		br.close();
	}
	static void set(int[] num, char[] oper, char[] ans, boolean[] v, int N, int cnt) {
		if(cnt==N-1) {
			//System.out.println(Arrays.toString(ans));
			int sum=0;
			for(int i=0; i<N; i++) {
				if(i==0) sum=num[i];
				else sum=operator(ans[i-1],sum,num[i]);
			}
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}
		for(int i=0; i<N-1; i++) {
			if(v[i])continue;
			v[i]=true;
			ans[cnt]=oper[i];
			set(num,oper,ans,v,N,cnt+1);
			v[i]=false;
		}
	}
	static int operator(char oper, int n1, int n2) {
		if(oper=='+') return n1+n2;
		else if(oper=='-') return n1-n2;
		else if(oper=='*') return n1*n2;
		else {
			if(n1<0 && 0<n2) {
				n1*=-1;
				n1/=n2;
				n1*=-1;
				return n1;
			}
			return n1/n2;
		}
	}
}
