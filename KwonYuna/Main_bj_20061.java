package a0308;

import java.util.*;
import java.io.*;

public class Main_bj_20061 {
	static boolean[][] green, blue;
	static int score;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		green = new boolean[6][4];
		blue = new boolean[6][4];

		//  1. []  2. [][]  3. []
		//                     []
		
		for(int n=0; n<N ; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			char t = st.nextToken().charAt(0);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			switch(t) {
			case '1':
				fill_block1('G',y);
				fill_block1('B',x);
				break;
			case '2':
				fill_block2('G',y);
				fill_block3('B',x);
				break;
			default:
				fill_block3('G',y);
				fill_block2('B',x);
				break;
			}
		}
		
		System.out.println(score);
		
		int cnt=0;
		for(int i=2; i<6; i++) {
			for(int j=0;j<4;j++) {
				if(blue[i][j]) cnt++;
				if(green[i][j]) cnt++;
			}
		}
		
		System.out.println(cnt);
		br.close();
	}


	//  1. []  
	static void fill_block1(char code, int y) {
		boolean[][] arr = code == 'B' ? blue : green;

		int x = 2; //2~5행 블럭 체크
		for (; x < 6; x++) {
			if (arr[x][y])
				break;
		}
		arr[x - 1][y] = true;
		
		if (2 < x) { //진한 칸에 놓여짐
			check(code, x-1);
		} else { //연한 칸에 놓여짐_행 한칸 밀림
			push_arr(code,1);
		}
	}

	//2. [][] 
	static void fill_block2(char code, int y) {
		boolean[][] arr = code == 'B' ? blue : green;

		int x = 2;
		for (; x < 6; x++) {
			if (arr[x][y] || arr[x][y + 1])
				break;
		}
		arr[x - 1][y + 1] = arr[x - 1][y] = true;

		if (2 < x) {
			check(code, x-1);
		} else {
			push_arr(code,1);
		}
	}

	//3. []
    //   []
	static void fill_block3(char code, int y) {
		boolean[][] arr = code == 'B' ? blue : green;

		int x = 2;
		for (; x < 6; x++) {
			if (arr[x][y])
				break;
		}
		arr[x - 2][y] = arr[x - 1][y] = true;
		
		for(int i=0; i<2; i++) { //2번 반복
			if (1 < --x) { //진한 칸
				//점수를 얻는 경우 행이 사라져 x를 더함
				if(check(code, x)) ++x;
			}else { //연한 칸
				push_arr(code,x);
			}
		}

	}

	static boolean check(char code, int x) {
		boolean[][] arr = code == 'B' ? blue : green;

		int i = 0;
		for (; i < 4; i++) {
			if (!arr[x][i])
				break;
		}
		
		if (i == 4) { //x행이 다 참
			score++;
			for(;0<x; x--) 
				arr[x]=arr[x-1].clone();
			arr[0]=new boolean[4];
			return true;
		}
		return false;
	}
	
	static void push_arr(char code, int x) {
		boolean[][] arr = code == 'B' ? blue : green;
		
		for(int i=5; 0<i; i--) 
			arr[i]=arr[i-1].clone();
		
		arr[0]=new boolean[4];
	}
}
