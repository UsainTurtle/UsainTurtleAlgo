package com.company.tutle;

/*적록색약
적록색약은 빨간색과 초록색의 차이를 거의 느끼지 못한다.
따라서, 적록색약인 사람이 보는 그림은 아닌 사람이 보는 그림과는 좀 다를 수 있다.
크기가 N×N인 그리드의 각 칸에 R(빨강), G(초록), B(파랑) 중 하나를 색칠한 그림이 있다. 그림은 몇 개의 구역으로 나뉘어져 있는데, 구역은 같은 색으로 이루어져 있다.
또, 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다.
(색상의 차이를 거의 느끼지 못하는 경우도 같은 색상이라 한다)

첫째 줄에 N이 주어진다. (1 ≤ N ≤ 100)
둘째 줄부터 N개 줄에는 그림이 주어진다.

적록색약이 아닌 사람이 봤을 때의 구역의 개수와 적록색약인 사람이 봤을 때의 구역의 수를 공백으로 구분해 출력한다.

예시 입력
5
RRRBB
GGBBB
BBBRR
BBRRR
RRRRR
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class bj_10026 {
    static char[][] graph;
    static char[][] specialGraph;
    static boolean[][] visit;
    static boolean[][] specialVisit;
    static int N;
    static int[] dx = {1, 0, -1, 0}; //우하좌상
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int common = 0;
        int special = 0;
        graph = new char[N][N];
        specialGraph = new char[N][N];
        visit = new boolean[N][N];
        specialVisit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String st = br.readLine();
            for (int j = 0; j < N; j++) {
                char current = st.charAt(j);
                graph[i][j] = current;
                specialGraph[i][j] = (current == 'G') ? 'R' : current; // R과 B만 있게
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j]) {
                    solution(j, i, graph[i][j]);
                    common++;
                }
                if (!specialVisit[i][j]) {
                    specialSolution(j, i, specialGraph[i][j]);
                    special++;
                }
            }
        }
        System.out.print(common + " " + special);
    }

    static void solution(int x, int y, char color) {
        visit[y][x] = true;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N || visit[ny][nx]) { //범위를 벗어나거나 방문한 곳
                continue;
            }
            if (graph[ny][nx] == color) { // 현재 보고있는 색상과 같다면
                solution(nx, ny, color);
            }
        }
    }

    static void specialSolution(int x, int y, char color) {
        specialVisit[y][x] = true;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N || specialVisit[ny][nx]) { //범위를 벗어나거나 방문한 곳
                continue;
            }
            if (specialGraph[ny][nx] == color) { // 현재 보고있는 색상과 같다면
                specialSolution(nx, ny, color);
            }
        }
    }
}
