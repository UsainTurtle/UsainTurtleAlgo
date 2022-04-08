package a0310;

import java.util.*;
import java.io.*;

public class Main_bj_21609 {

	static int[] di = { -1, 0, 1, 0 }, dj = { 0, 1, 0, -1 }; 
	static int N, arr[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); //M 값은 사용하지 않음, 배열에서 블록 제거 시 M+1 대신 6 저장 (조건:1 ≤ M ≤ 5)
		arr = new int[N][N];
		int score = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			ArrayList<int[]> group = new ArrayList<>(); 
			//그룹 정보 저장 (기준블럭 행값,기준블럭 열값, 그룹크기, 무지개블럭 개수, 일반블럭 색)
			LinkedList<Integer> queue = new LinkedList<>();
			boolean[][] visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (0 < arr[i][j] && arr[i][j] < 6 && !visited[i][j]) { 
						boolean[][] rainbow_visited = new boolean[N][N]; 
						//무지개블럭인 경우 여러 그룹에 중복으로 포함되어도 되므로 방문 배열을 매번 생성
						queue.offer(i);
						queue.offer(j);
						visited[i][j] = true;

						int cnt = 0, r_cnt=0, color = arr[i][j];
						//그룹크기, 그룹 내 무지개블럭 개수, 일반블럭 색
						while (!queue.isEmpty()) {
							int ti = queue.poll();
							int tj = queue.poll();
							cnt++; 
							if(arr[ti][tj]==0)r_cnt++;

							for (int z = 0; z < 4; z++) {
								int ni = ti + di[z];
								int nj = tj + dj[z];
								if (0 <= ni && ni < N && 0 <= nj && nj < N 
										&& ((arr[ni][nj] == color&&!visited[ni][nj])|| (arr[ni][nj] == 0&&!rainbow_visited[ni][nj]))) {
									//일반블럭인 경우 visited에서 방문 확인, 무지개블럭인 경우 rainbow_visited에서 방문 확인
									queue.offer(ni);
									queue.offer(nj);
									if(arr[ni][nj] == color) visited[ni][nj] = true;
									else rainbow_visited[ni][nj] = true;
								}
							}
						}

						if (1 < cnt) group.add(new int[] { i, j, cnt, r_cnt, color });
						

					}
				}
			}

			if (group.size() == 0) //그룹이 만들어지지 않으면 무한루프 빠져나옴
				break;

			//정렬
			Collections.sort(group,
					(o1, o2) -> o1[2] == o2[2]
							? (o1[3] == o2[3]?
									(o1[0] == o2[0] ? Integer.compare(o2[1], o1[1]) : Integer.compare(o2[0], o1[0])):Integer.compare(o2[3], o1[3]))
							: Integer.compare(o2[2], o1[2]));

			//점수 계산
			score += group.get(0)[2] * group.get(0)[2];
			
			//블럭 제거
			visited = new boolean[N][N];
			queue.offer(group.get(0)[0]);
			queue.offer(group.get(0)[1]);
			visited[group.get(0)[0]][group.get(0)[1]] = true;
			int color = group.get(0)[4];
			
			while (!queue.isEmpty()) {
				int ti = queue.poll();
				int tj = queue.poll();
				arr[ti][tj] = 6;
				for (int z = 0; z < 4; z++) {
					int ni = ti + di[z];
					int nj = tj + dj[z];
					if (0 <= ni && ni < N && 0 <= nj && nj < N && !visited[ni][nj]
							&& (arr[ni][nj] == color || arr[ni][nj] == 0)) {
						queue.offer(ni);
						queue.offer(nj);
						visited[ni][nj] = true;
					}
				}
			}

			gravity(); 
			spin();
			gravity();
		}
		System.out.print(score);
	}

	static void gravity() {

		for (int j = 0; j < N; j++) {
			int em_i = 0, em_j = 0;
			for (int i = N - 1; 0 <= i; i--) {
				if (arr[i][j] == 6) {
					if (em_i == 0 && em_j == 0) {
						em_i = i;
						em_j = j;
					}
				} else if (arr[i][j] == -1) {
					em_i = 0;
					em_j = 0;
				} else if (em_i != 0 || em_j != 0) {
					arr[em_i][em_j] = arr[i][j];
					arr[i][j] = 6;
					if (arr[em_i - 1][em_j] == 6) {
						em_i--;
					}
				}
			}
		}

	}

	static void spin() {
		int[][] temp = new int[N][N];

		for (int i = 0; i < N; i++) {
		      for (int j = 0; j < N; j++) {
		    	  temp[N - 1 - j][i] = arr[i][j];
		      }
		    }
		arr = temp;
	}
}
