package a0228;

import java.io.*;
import java.util.*;
/*
 
N x N 보드(보드의 상하좌우 끝에 벽이 있다)

게임 시작 시 뱀은 맨위 맨좌측 위치 (뱀 길이는 1) 오른쪽방향
뱀은 매 초마다 아래 규칙으로 이동
 1. 머리를 다음 칸에 위치
 2. 이동한 칸에 사과가 있다면, 사과를 먹고 꼬리는 움직이지 않는다. (몸길이+1)
 3. 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비운다. (몸길이 변함없음)
 
=> 뱀이 벽 또는 자기자신의 몸과 부딪히면 게임 종료

** 입력
보드 크기 N (2 ≤ N ≤ 100)
사과 개수 K (0 ≤ K ≤ 100)
사과 위치 (행 열)
뱀의 방향 변환 횟수 L (1 ≤ L ≤ 100)
뱀의 방향 변환 정보 (정수X 문자C) X초가 끝난 뒤에 왼쪽(C='L') 또는 오른쪽(C='D')로 90도 방향 회전 

** 출력
게임 종료 시간

*/
public class Main_bj_3190 {
	static int[] di = { 0, 1, 0, -1 }; //우하좌상
	static int[] dj = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); 
		int K = Integer.parseInt(br.readLine());
		
		int[][] arr_ap = new int[K][]; //사과 위치 저장
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			arr_ap[i] = new int[] { Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1 }; //사과 위치 저장
		}
		
		int L = Integer.parseInt(br.readLine());
		int time = 0, direction = 0;
		int head_i=0,head_j=0;
		boolean[][] pan = new boolean[N][N]; //보드판 (뱀 위치 true로 표시)
		pan[0][0]=true; 
		LinkedList<Integer> Q = new LinkedList<>();
		Q.offer(head_i);
		Q.offer(head_j);
		
		L--;
		st = new StringTokenizer(br.readLine(), " ");
		int ch_time = Integer.parseInt(st.nextToken());
		char al = st.nextToken().charAt(0);

		while (true) {
			boolean small = true; //뱀 몸길이가 변화가 없는지
			time++; 
			head_i += di[direction]; //이동
			head_j += dj[direction];
			
			if (head_i < 0 || N<=head_i || head_j < 0 || N<=head_j||pan[head_i][head_j]) //머리가 벽이나 자신몸에 닿으면 게임종료
				break;
			
			pan[head_i][head_j]=true; //머리 이동
			Q.offer(head_i); //머리 위치 큐에 저장
			Q.offer(head_j); 
			
			for (int i = 0; i < K; i++) { //머리쪽에 사과가 있는지 탐색
				if (head_i == arr_ap[i][0] && head_j == arr_ap[i][1]) { //사과가 있다면
					small = false; //몸 길이가 줄어들면 안됨
					arr_ap[i] = arr_ap[--K]; //먹은 사과는 사라짐
					break;
				}
			}
			
			if (small) { // 몸길이를 줄이기
				pan[Q.poll()][Q.poll()]=false; 
			}

			if (time == ch_time) { //방향 변경
				if (al == 'D') {// 오른쪽 [우하좌상 0123]
					direction = (direction + 1) % 4;
				} else {// 왼쪽 [우상좌하 0321]
					direction = direction == 0 ? 3 : direction - 1;
				}
				if (0 < L--) { //방향 변경 입력이 남아있다면
					st = new StringTokenizer(br.readLine(), " ");
					ch_time = Integer.parseInt(st.nextToken());
					al = st.nextToken().charAt(0);
				}
			}
		}

		System.out.println(time);

		br.close();
	}
}

