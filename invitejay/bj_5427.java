package com.company.tutle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*
상근이는 빈 공간과 벽으로 이루어진 건물에 갇혀있다. 건물의 일부에는 불이 났고, 상근이는 출구를 향해 뛰고 있다.
매 초마다, 불은 동서남북 방향으로 인접한 빈 공간으로 퍼져나간다. 벽에는 불이 붙지 않는다. 상근이는 동서남북 인접한 칸으로 이동할 수 있으며, 1초가 걸린다.
상근이는 벽을 통과할 수 없고, 불이 옮겨진 칸 또는 이제 불이 붙으려는 칸으로 이동할 수 없다. 상근이가 있는 칸에 불이 옮겨옴과 동시에 다른 칸으로 이동할 수 있다.
빌딩의 지도가 주어졌을 때, 얼마나 빨리 빌딩을 탈출할 수 있는지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 테스트 케이스의 개수가 주어진다. 테스트 케이스는 최대 100개이다.
각 테스트 케이스의 첫째 줄에는 빌딩 지도의 너비와 높이 w와 h가 주어진다. (1 ≤ w,h ≤ 1000)
다음 h개 줄에는 w개의 문자, 빌딩의 지도가 주어진다.

'.': 빈 공간
'#': 벽
'@': 상근이의 시작 위치
'*': 불
각 지도에 @의 개수는 하나이다.

출력
각 테스트 케이스마다 빌딩을 탈출하는데 가장 빠른 시간을 출력한다. 빌딩을 탈출할 수 없는 경우에는 "IMPOSSIBLE"을 출력한다.

5
4 3
####
#*@.
####
7 6
###.###
#*#.#*#
#.....#
#.....#
#..@..#
#######
7 4
###.###
#....*#
#@....#
.######
5 5
.....
.***.
.*@*.
.***.
.....
3 3
###
#@#
###
 */

// 불이 먼저 붙어야 함
// 범위를 벗어나면 탈출
public class bj_5427 {
    static int H, W, T, time;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean check;
    static ArrayDeque<int[]> fire;
    static ArrayDeque<int[]> start;
    static boolean[][] visit;
    static char[][] graph;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/com/company/res/input_bj_5427.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            check = false;
            time = 0;
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            graph = new char[H][W];
            visit = new boolean[H][W];
            fire = new ArrayDeque<>();
            start = new ArrayDeque<>();
            for (int i = 0; i < H; i++) {
                String input = br.readLine();
                for (int j = 0; j < W; j++) {
                    graph[i][j] = input.charAt(j);
                    if (graph[i][j] == '*') { // 불 위치 저장
                        fire.offer(new int[]{j, i}); //x,y좌표 저장
                    } else if (graph[i][j] == '@') {
                        start.offer(new int[]{j, i});
                        visit[i][j] = true;
                    }
                }
            }
            solution();
            if (!check) {
                System.out.println("IMPOSSIBLE");
            }
        }
    }

    static void solution() {
        while (!start.isEmpty()) {
            int currentFireSize = fire.size();
            for (int i = 0; i < currentFireSize; i++) {
                int[] fireXY = fire.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = fireXY[0] + dx[d];
                    int ny = fireXY[1] + dy[d];
                    if (nx < 0 || ny < 0 || nx >= W || ny >= H || graph[ny][nx] == '*' || graph[ny][nx] == '#') { // 범위를 벗어남, 이미 불 붙은 곳, 벽
                        continue;
                    }
                    graph[ny][nx] = '*';
                    fire.offer(new int[]{nx, ny}); // 다음 화재가 일어날 곳
                }
            }
            int currentPosSize = start.size();
            for (int i = 0; i < currentPosSize; i++) {
                int[] xy = start.poll();
                for (int d = 0; d < 4; d++) {
                    int nx = xy[0] + dx[d];
                    int ny = xy[1] + dy[d];
                    if (nx < 0 || ny < 0 || nx >= W || ny >= H) { // 탈출 했을 때
                        System.out.println(++time);
                        check = true;
                        return;
                    }
                    if (graph[ny][nx] == '.' && !visit[ny][nx]) { // 움직일 수 있는 곳이면
                        visit[ny][nx] = true;
                        start.offer(new int[]{nx, ny});
                    }
                }
            }
            time++;
        }
    }
}
