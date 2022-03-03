package a0228;

import java.io.*;
import java.util.*;

public class Main_bj_1713 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int S = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int[][] arr = new int[N][3];
		boolean[] s_arr = new boolean[101];
		int idx=0;
		for (int i = 0; i < S; i++) {
			int tmp=Integer.parseInt(st.nextToken());
				if(idx<N||s_arr[tmp]) {
					for (int j = 0; j < N; j++) {
						if(idx==j) {
							s_arr[tmp]=true;
							arr[idx++]=new int[] {tmp,i,1};
							break;
						}
						if(arr[j][0]==tmp) {
							arr[j][2]++;
							break;
						}
					}
				}else {
					Arrays.sort(arr,(o1,o2)->o1[2]==o2[2]?Integer.compare(o1[1], o2[1]):Integer.compare(o1[2], o2[2]));
					s_arr[arr[0][0]]=false;
					s_arr[tmp]=true;
					arr[0]=new int[] {tmp,i,1};
				}
		}
		Arrays.sort(arr,(o1,o2)->Integer.compare(o1[0], o2[0]));
		for(int[] a:arr) {
			if(a[0]==0)continue;
			sb.append(a[0]).append(" ");
		}
		System.out.print(sb.toString());
		br.close();
	}
}