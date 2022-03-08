package a0304;

import java.io.*;
import java.util.*;

//아래 처음 작성한 코드와 시간,메모리 모두 2배 차이
/* 첫번째 작성한 코드 296780KB 868ms */
/* 두번째 작성한 코드 126156KB 364ms */
public class Main_bj_14502 {

	static final int[] di = { -1, 0, 1, 0 };
	static final int[] dj = { 0, 1, 0, -1 };
	
	//두번째 작성한 코드
	static int arr[][], count, MAX, N, M;
	static ArrayList<int[]> virus;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		virus = new ArrayList<>(); //시작부터 바이러스인 칸 좌표 저장

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if (arr[i][j] == 2) virus.add(new int[] { i, j });
				else if (arr[i][j] == 0) count++; //빈 칸 개수
			}
		}
		count -= 3; //무조건 벽을 3개는 세워야하기 때문에 먼저 3을 빼줌
		make(0,0); //벽 세우고 안전구역 체크하기
		
		System.out.print(MAX);
		br.close();
	}

	static void make(int cnt, int idx) {
		if (cnt == 3) { //벽을 다 세웠으면 바이러스 번식시키고 bfs로 안전구역 개수 체크
			//c는 안전구역 수
			int c = count;
			Queue<Integer> queue = new LinkedList<Integer>();
			boolean[][] visited = new boolean[N][M];

			for (int[] v : virus) {
				visited[v[0]][v[1]] = true;
				queue.offer(v[0]);
				queue.offer(v[1]);
			}

			while (!queue.isEmpty()) {
				int i = queue.poll(), j = queue.poll();
				for (int z = 0; z < 4; z++) {
					int ni = i + di[z], nj = j + dj[z];
					if (0 <= ni && ni < N && 0 <= nj && nj < M && !visited[ni][nj] && arr[ni][nj] == 0) {
						c--;
						visited[ni][nj] = true;
						queue.offer(ni);
						queue.offer(nj);
					}
				}
			}
			if (MAX < c) MAX = c;
			return;
		}

		//재귀로 벽 세우기
		//N,M이 최대 8이라서 배열 전체 확인
		for (; idx < N * M; idx++) {
			int i = idx / M, j = idx % M;
			if (arr[i][j] == 0) {
				arr[i][j] = 1;
				make(cnt + 1, idx + 1);
				arr[i][j] = 0;
			}
		}

	}
	
	/* 첫번째 작성한 코드
	 
	static int arr[][],count, MAX;
	static ArrayList<int[]> virus, virus_ing;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		
		virus = new ArrayList<>(); //시작부터 바이러스인 칸 좌표 저장
		virus_ing = new ArrayList<>(); //시작이후 바이러스가 번식될 칸 좌표 저장
		
		Queue<int[]> queue = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					virus.add(new int[] { i, j });
					visited[i][j] = true;
					queue.offer(new int[] { i, j });
				} else if (arr[i][j] == 0) {
					count++; //빈 칸 개수
				}
			}
		}
		count -= 3; //무조건 벽을 3개는 세워야하기 때문에 먼저 3을 빼줌
		
		//바이러스가 번식될 칸에 벽을 세워야 바이러스 번식을 막을 수 있다고 생각함
		//bfs로 시작 이후 바이러스가 번식될 빈칸을 찾아 배열에 저장
		while (!queue.isEmpty()) {
			int[] ij = queue.poll();
			for (int z = 0; z < 4; z++) {
				int ni = ij[0] + di[z], nj = ij[1] + dj[z];
				if (0 <= ni && ni < N && 0 <= nj && nj < M && !visited[ni][nj] && arr[ni][nj] == 0) {
					visited[ni][nj] = true;
					virus_ing.add(new int[] { ni, nj });
					queue.offer(new int[] { ni, nj });
				}
			}
		}

		make(0); //벽 세우고 안전구역 체크하기
		System.out.print(MAX);
		br.close();
	}

	static void make(int cnt) {
		if (cnt == 3) { //벽을 다 세웠으면 바이러스 번식시키고 bfs로 안전구역 개수 체크
			bfs(); 
			return;
		}
		
		//재귀로 벽 세우기
		for (int vn = 0, VN = virus_ing.size(); vn < VN; vn++) {
			int i=virus_ing.get(vn)[0], j=virus_ing.get(vn)[1];
			if (arr[i][j] == 0) {
				arr[i][j] = 1;
				make(cnt + 1);
				arr[i][j] = 0;
			}
		}

	}

	static void bfs() {
		//c는 안전구역 수
		int c = count,N=arr.length,M=arr[0].length;
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[][] visited = new boolean[N][M];
		
		for (int[] v : virus) {
			visited[v[0]][v[1]] = true;
			queue.offer(v[0]);
			queue.offer(v[1]);
		}
		
		while (!queue.isEmpty()) {
			int i = queue.poll(), j = queue.poll();
			for (int z = 0; z < 4; z++) {
				int ni = i + di[z], nj = j + dj[z];
				if (0 <= ni && ni < N && 0 <= nj && nj < M && !visited[ni][nj] && arr[ni][nj] == 0) {
					c--;
					visited[ni][nj] = true;
					queue.offer(ni);
					queue.offer(nj);
				}
			}
		}

		if (MAX < c)
			MAX = c;
	}
	*/
}
