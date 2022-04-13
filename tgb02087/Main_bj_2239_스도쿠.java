package a0406.camp;

import java.io.*;
import java.util.*;
/*

1. 행 1~9
2. 열 1~9
3. 3x3 사각형 1~9
각 1~9는 중복없이 채우기

입력
9x9 스도쿠
(0을 채우기)

출력
스도쿠완성출력
답이 여러개시 사전식으로 앞서는것 출력
 */
public class Main_bj_2239_스도쿠 {
	static int[][] map;
	static List<int[]> list;
	static boolean end=false;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2239.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		list=new ArrayList<>();
		map = new int[9][9];
		for(int i=0; i<9; i++) {
			String s=br.readLine();
			for(int j=0; j<9; j++) {
				map[i][j]=s.charAt(j)-'0';
				if(map[i][j]==0) list.add(new int[]{i,j});
			}
		}
		set_num(0);
		System.out.println(sb);
		br.close();
	}
	static void set_num(int index) {
		if(end) return;
		if(index==list.size()) {
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			end=true;
			return;
		}
		int[] xy=list.get(index);
		for(int i=1; i<=9; i++) {
			if(!chk_row(xy[0],xy[1],i)) continue;
			if(!chk_col(xy[0],xy[1],i)) continue;
			if(!chk_sq(xy[0],xy[1],i)) continue;
			map[xy[0]][xy[1]]=i;
			set_num(index+1);
			map[xy[0]][xy[1]]=0;
		}
	}
	//행 체크
	static boolean chk_row(int x, int y,int n) {
		for(int i=0; i<9; i++) {
			if(map[x][i]==n) return false;
		}
		return true;
	}
	//열 체크
	static boolean chk_col(int x, int y, int n) {
		for(int i=0; i<9; i++) {
			if(map[i][y]==n) return false;
		}
		return true;
	}
	//3x3 체크
	static boolean chk_sq(int x, int y, int n) {
		if(0<=x && x<3) x=0;
		else if(3<=x && x<6) x=3;
		else x=6;
		if(0<=y && y<3) y=0;
		else if(3<=y && y<6) y=3;
		else y=6;
		for(int i=x; i<x+3; i++) {
			for(int j=y; j<y+3; j++) {
				if(map[i][j]==n) return false;
			}
		}
		return true;
	}

}
