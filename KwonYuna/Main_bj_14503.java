package a0127;

import java.util.*;
import java.io.*;

public class Main_bj_14503 {

	public static int[] di = { 0, -1, 0, 1 }; //좌상우하
	public static int[] dj = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		char[][] arr = new char[N][M];
		
		st = new StringTokenizer(br.readLine()," ");
		int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = st.nextToken().charAt(0);
			}
		}

		int clean = 0;

		exit: while (true) {
			//1. 현재 위치 청소
			arr[r][c] = '2';
			clean++;
			exit_1: while (true) {
				int dr = r + di[d], dc = c + dj[d];
				
				if (arr[dr][dc] == '0') {
					//2-a. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 
					//그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
					r = dr;
					c = dc;
					d = (d == 0) ? 3 : d - 1;
					break exit_1; //1번으로 돌아가기
				} else {
					if (arr[r + di[0]][c + dj[0]] != '0' && arr[r + di[1]][c + dj[1]] != '0'
							&& arr[r + di[2]][c + dj[2]] != '0' && arr[r + di[3]][c + dj[3]] != '0') {
						//2-c&d. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는,
						
						//2-c. 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
						int dd = (d == 0) ? 3 : d - 1;
						if (arr[r + di[dd]][c + dj[dd]] == '2') {
							r = r + di[dd];
							c = c + dj[dd];
						} else {
							//2-d. 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
							break exit;
						}
					} else {
						//2-b. 왼쪽 방향에 청소할 공간이 없다면,
						//그 방향으로 회전하고 2번으로 돌아간다.
						d = (d == 0) ? 3 : d - 1;
					}
				}
			}
		}
		System.out.print(clean);
		br.close();
	}
}