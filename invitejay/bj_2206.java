package com.company.tutle;

/*
N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다.
당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다.
최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.
만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.
한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.
맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.

입력
첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다.
다음 N개의 줄에 M개의 숫자로 맵이 주어진다.
(1, 1)과 (N, M)은 항상 0이라고 가정하자.

출력
첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.

예제 입력
6 4
0100
1110
1000
0000
0111
0000

반례
13 13
0100011011000
0001001010000
0000100001000
1100010101011
1111101111000
1011010001001
0100001001001
0100111010001
0101010000100
0001110100000
0000001000100
1010001000111
1001000100000
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 벽은 한번만 부실 수 있기 때문에 cnt를 주거나 벽을 부쉈는지 유무를 체크해줘야 함
// 벽을 부쉈는데, 벽을 또 만나게되면 다시 와줘야 함
public class bj_2206 {
    static int N, M, result;
    static int[] dx = {1, 0, -1, 0}; // 우 하 좌 상
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = Integer.MAX_VALUE;
        int[][] graph = new int[N][M];
        boolean[][][] visit = new boolean[N][M][2];
        for (int i = 0; i < N; i++) { //map 초기화
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                graph[i][j] = input.charAt(j) - '0';
            }
        }
//        solution(0, 0, true, 1, graph, visit);
        solution2(visit, graph);
        System.out.print((result == Integer.MAX_VALUE) ? -1 : result);
        br.close();
    }

    // 부시고 들어갔는지 안부시고 들어간 곳인지 체크해줘야함
    static void solution2(boolean[][][] visit, int[][] graph) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 1, 0}); //x,y,distance,벽 부순거 순서
        visit[0][0][0] = true; // 안부시고 들어감
        visit[0][0][1] = true; // 부시고 들어감
        while (!q.isEmpty()) {
            int[] xyd = q.poll();
            int distance = xyd[2]; // 해당 위치에서의 거리
            int crash = xyd[3]; // 부쉈으면 1

            if (xyd[0] == M - 1 && xyd[1] == N - 1) { //끝에 도달
                result = Math.min(result, distance);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nx = xyd[0] + dx[d];
                int ny = xyd[1] + dy[d];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) { //범위를 벗어남
                    continue;
                }

                if (graph[ny][nx] == 1 && crash == 0 && !visit[ny][nx][1]) { // 벽, 부술 수 있음, 해당 벽을 부순 적이 없음
                    visit[ny][nx][1] = true;
                    q.offer(new int[]{nx, ny, distance + 1, 1});
                } else if (graph[ny][nx] == 0 && !visit[ny][nx][crash]) { // 벽아님, 방문한 적 없음, 여기서 crash 값은 0
                    visit[ny][nx][crash] = true;
                    q.offer(new int[]{nx, ny, distance + 1, crash});
                }
            }
        }
    }

    //dfs 시간초과
    static void solution(int x, int y, boolean crash, int distance, int[][] graph, boolean[][] visit) {
        if (graph[y][x] == 1 || distance >= result) { // 벽이거나 최단거리보다 같거나 클 경우
            return;
        }
        if (x == M - 1 && y == N - 1) { // 끝나는 조건
            result = Math.min(result, distance);
            return;
        }
        visit[y][x] = true;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || ny < 0 || nx >= M || ny >= N || visit[ny][nx]) { // 범위를 벗어나거나 방문한 곳이면 패스
                continue;
            }
            if (graph[ny][nx] == 1) { // 벽을 만남
                if (!crash) { // 부실 수 없음
                    continue;
                }
                graph[ny][nx] = 0;
                solution(nx, ny, false, distance + 1, graph, visit);
                graph[ny][nx] = 1; //탐색을 마치고 온 경우
            } else {
                solution(nx, ny, crash, distance + 1, graph, visit);
            }
            visit[y][x] = false;
        }
    }
}
