package a0228;

import java.io.*;
import java.util.*;

public class Main_bj_2239 {
	static boolean[][] i_arr, j_arr, re_arr;
	static char[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		arr = new char[9][9]; //스도쿠 보드판
		i_arr = new boolean[9][10]; //스도쿠 행 숫자 체크					| 0 | 1 | 2 |
		j_arr = new boolean[9][10]; //스도쿠 열 숫자 체크					| 3 | 4 | 5 |
		re_arr = new boolean[9][10]; //스도쿠 사각형 숫자 체크  --------->	| 6 | 7 | 8 | 
		int excount = 82, count = 81; //한개 숫자만 들어갈 수 있는 경우를 체크하기 위함 [이전 남은 빈칸수, 현재 남은 빈칸수]
		
		for (int i = 0; i < 9; i++) {
			String s = br.readLine();
			for (int j = 0; j < 9; j++) {
				int tmp = s.charAt(j) - '0';
				arr[i][j] = s.charAt(j); //스도쿠 배열에 저장
				if (0 < tmp) { //빈칸이 아닐 경우
					count--; 
					i_arr[i][tmp] = true; //해당 행의 숫자는 이미 있다는 의미로 true로 만든다.
					j_arr[j][tmp] = true;
					re_arr[(i / 3) * 3 + j / 3][tmp] = true; 	
				}
			}
		}

		while (count < excount) { //이전 빈칸과 차이가 없다면 반복문 나옴
			excount = count;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					int zz = 0, c = 0; //zz=빈칸에 들어 갈 수 있는 숫자 중 제일 큰 수 저장 , c=빈칸에 들어 갈 수 있는 숫자 개수 
					if ('0' < arr[i][j]) //채워진 칸은 관심없음
						continue;
					for (int z = 1; z <= 9; z++) {
						if (!i_arr[i][z] && !j_arr[j][z] && !re_arr[(i / 3) * 3 + j / 3][z]) { //가로,세로,사각형에 없는 숫자인 경우
							if (1 < c++) // 빈칸에 들어갈 수 있는 숫자가 1개 이상이면 어떤게 알맞은지 모르므로 일단 넣지 않고 나온다.
								break;
							zz = z; 
						}
					}
					if (1 == c) { //빈칸에 들어갈 수 있는 숫자가 1개일 경우
						count--;
						arr[i][j] = (char) (zz + '0');
						i_arr[i][zz] = true;
						j_arr[j][zz] = true;
						re_arr[(i / 3) * 3 + j / 3][zz] = true;
					}
				}
			}
			
		}
		
		//빈칸이 1개 이상이라면 스도쿠의 정답이 여러가지일 수 있다
		if (0<count) sudoku(count); 
		
		for (char[] a : arr) {
			for (char al : a) {
				sb.append(al);
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}

	static boolean sudoku(int count) {
		
		if (count == 0) return true; //빈칸이 없으면 true 반환
 
		int i = 0,j = 0;

		exit: for (i = 0; i < 9; i++) { //가장 처음 발견한 빈칸의 행열값 저장
			for (j = 0; j < 9; j++) {
				if('0' == arr[i][j]) {
					break exit;
				}
			}
		}

		for (int z = 1; z <= 9; z++) { //1~9 수 중 넣을 수 있는 숫자를  작은 수부터 넣는다.
			if (i_arr[i][z] || j_arr[j][z] || re_arr[(i / 3) * 3 + j / 3][z]) continue;

			arr[i][j] = (char) (z + '0');
			i_arr[i][z] = true;
			j_arr[j][z] = true;
			re_arr[(i / 3) * 3 + j / 3][z] = true;
			
			if (sudoku(count - 1)) return true;

			arr[i][j] = '0'; 
			i_arr[i][z] = false;
			j_arr[j][z] = false;
			re_arr[(i / 3) * 3 + j / 3][z] = false;

		}
		return false;
	}

}