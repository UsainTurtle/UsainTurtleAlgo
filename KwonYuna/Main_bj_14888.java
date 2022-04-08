package a0304;

import java.io.*;
import java.util.*;

public class Main_bj_14888 {

	static int numb[], N, MIN, MAX;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numb = new int[N];

		MIN = Integer.MAX_VALUE;
		MAX = Integer.MIN_VALUE;

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			numb[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		calculation(1, numb[0], Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		System.out.println(MAX);
		System.out.println(MIN);
		br.close();
	}

	static void calculation(int cnt, int result, int plus, int minus, int mul, int div) {
		
		if (cnt == N) {
			if(MAX<result) MAX=result;
			if(result<MIN) MIN=result;
			return;
		}

		if (0 < plus) 
			calculation(cnt+1,result+numb[cnt],plus-1, minus, mul, div);
		
		if (0 < minus) 
			calculation(cnt+1, result-numb[cnt],plus, minus-1, mul, div);
		
		if (0 < mul) 
			calculation(cnt+1,result*numb[cnt] ,plus, minus, mul-1, div);
		
		if (0 < div) 
			calculation(cnt+1,result/numb[cnt] ,plus, minus, mul, div-1);
		
	}
}
