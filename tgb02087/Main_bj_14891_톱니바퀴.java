package a0413.study;

import java.io.*;
import java.util.*;

public class Main_bj_14891_톱니바퀴 {
	static int[][] mag;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_14891.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		mag = new int[4][8];
		for(int i=0; i<4; i++) {
			String s=br.readLine();
			for(int j=0; j<8; j++) {
				mag[i][j]=s.charAt(j)-'0';
			}
		}
		int K=Integer.parseInt(br.readLine());
		int[][] turn = new int[K][2];
		for(int i=0; i<K; i++) {
			StringTokenizer st=new StringTokenizer(br.readLine()," ");
			turn[i][0]=Integer.parseInt(st.nextToken())-1;
			turn[i][1]=Integer.parseInt(st.nextToken());
		}
		//자석 회전
		for(int i=0; i<K; i++) set(turn[i][0],turn[i][1]);
		int sum=0;
		for(int i=0; i<4; i++) {
			//빨간위치가 S일때
			if(mag[i][0]==1) {
				if(i==0) sum+=1;
				else if(i==1) sum+=2;
				else if(i==2) sum+=4;
				else sum+=8;
			}
		}
		System.out.println(sum);
		br.close();
	}
	static void set(int n, int d) {
		if(n==0) {
			//0번과 1번이 다른지
			if(mag[0][2]!=mag[1][6]) {
				//1번과 2번이 다른지
				if(mag[1][2]!=mag[2][6]) {
					//2번과 3번이 다른지
					if(mag[2][2]!=mag[3][6]) {
						spin(3,d*-1);
					}
					spin(2,d);
				}
				spin(1,d*-1);
			}
			spin(0,d);
		}
		else if(n==1) {
			if(mag[0][2]!=mag[1][6]) spin(0,d*-1);
			if(mag[1][2]!=mag[2][6]) {
				if(mag[2][2]!=mag[3][6]) {
					spin(3,d);
				}
				spin(2,d*-1);
			}
			spin(1,d);
		}
		else if(n==2) {
			if(mag[2][2]!=mag[3][6]) spin(3,d*-1);
			if(mag[2][6]!=mag[1][2]) {
				if(mag[1][6]!=mag[0][2]) {
					spin(0,d);
				}
				spin(1,d*-1);
			}
			spin(2,d);
		}
		else {
			//3번과 2번이 다른지
			if(mag[3][6]!=mag[2][2]) {
				//2번과 1번이 다른지
				if(mag[2][6]!=mag[1][2]) {
					//1번과 0번이 다른지
					if(mag[1][6]!=mag[0][2]) {
						spin(0,d*-1);
					}
					spin(1,d);
				}
				spin(2,d*-1);
			}
			spin(3,d);
		}
	}
	static void spin(int n, int d) {
		int tmp=0;
		if(d==1) {
			tmp=mag[n][7];
			for(int i=6; 0<=i; i--) {
				mag[n][i+1]=mag[n][i];
			}
			mag[n][0]=tmp;
		}
		else {
			tmp=mag[n][0];
			for(int i=1; i<8; i++) {
				mag[n][i-1]=mag[n][i];
			}
			mag[n][7]=tmp;
		}
	}
}
