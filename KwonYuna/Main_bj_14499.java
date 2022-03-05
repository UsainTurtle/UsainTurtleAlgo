package a0304;

import java.io.*;
import java.util.*;

public class Main_bj_14499 {
/* 
 * 시작할 때 주사위 인덱스 번호(위:0, 동:2, 북:1)
	  1
	3 0 2
	  4
	  5
 * 마주보는 면 인덱스 번호 합=5
 * 
 * 처음 주사위 모든 면은 0
 * [check] 주사위를 굴렸을 때, 
 * 1. 이동한 칸의 수가 0이면, 주사위 바닥면 숫자가 칸에 복사
 * 2. 이동한 칸의 수가 1이상, 칸 숫자가 주사위 바닥에 복사, 칸은 0이 됨
 * 
 * 출력: 주사위가 이동했을 때마다 주사위 윗면 숫자 출력
 * (주사위가 지도를 벗어날 경우 명령을 무시(해당 명령에 대해 출력도 안함) 
 */
	static char dice[], board[][]; // 주사위, 지도
	static int up,east,north;      // 주사위 위,동,북쪽 방향에 있는 인덱스 번호
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		board = new char[N][M]; 
		dice = new char[6];    
		up = 0;
		east = 2;
		north = 1;
		
		Arrays.fill(dice,'0'); //시작할 때 주사위 모든 면은 0
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				board[i][j] = st.nextToken().charAt(0);
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int c = 0; c < K; c++) {
			int tmp;
			switch (st.nextToken().charAt(0)) { //명령 방향
			case '1': // 동
				if (y + 1 < M) { //지도 밖을 벗어나지 않는다면,
					tmp = east;  //주사위를 동쪽으로 굴림
					east = up;   //윗면이 동쪽으로
					up = 5 - tmp;//서쪽면이 윗면으로

					check(x, ++y);
				}
				break;
			case '2': // 서
				if (0 <= y - 1) {
					tmp = up;
					up = east;
					east = 5 - tmp;

					check(x, --y);
				}
				break;
			case '3': // 북
				if (0 <= x - 1) {
					tmp = north;
					north = up;
					up = 5 - tmp;

					check(--x, y);
				}
				break;
			default:// 남
				if (x + 1 < N) {
					tmp = up;
					up = north;
					north = 5 - tmp;
					
					check(++x, y);
				}
				break;
			}
		}
		System.out.print(sb.toString());
		br.close();
	}
	
	static void check(int x, int y) { //굴린 후 복사 실행 함수
		if ('0' == board[x][y]) {
			board[x][y]=dice[5-up];
		} else {
			dice[5-up]=board[x][y];
			board[x][y]='0';
		}
		sb.append(dice[up]).append("\n"); //윗면 출력
	}

}
