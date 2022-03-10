package com.company.tutle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
N×N크기의 땅이 있고, 땅은 1×1개의 칸으로 나누어져 있다. 각각의 땅에는 나라가 하나씩 존재하며, r행 c열에 있는 나라에는 A[r][c]명이 살고 있다.
인접한 나라 사이에는 국경선이 존재한다. 모든 나라는 1×1 크기이기 때문에, 모든 국경선은 정사각형 형태이다.
오늘부터 인구 이동이 시작되는 날이다.
인구 이동은 하루 동안 다음과 같이 진행되고, 더 이상 아래 방법에 의해 인구 이동이 없을 때까지 지속된다.
국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
연합을 해체하고, 모든 국경선을 닫는다.
각 나라의 인구수가 주어졌을 때, 인구 이동이 며칠 동안 발생하는지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N, L, R이 주어진다. (1 ≤ N ≤ 50, 1 ≤ L ≤ R ≤ 100)
둘째 줄부터 N개의 줄에 각 나라의 인구수가 주어진다. r행 c열에 주어지는 정수는 A[r][c]의 값이다. (0 ≤ A[r][c] ≤ 100)
인구 이동이 발생하는 일수가 2,000번 보다 작거나 같은 입력만 주어진다.

출력
인구 이동이 며칠 동안 발생하는지 첫째 줄에 출력한다.

 */

// 큐에 교환 가능한 나라들을 점검해주고 visit 처리를 해준다.
// 계산을 위해 해당 칸에 있는 값을 저장해줄 수 있는 리스트를 만들어준다.
// 모두 더해주고 visit 처리해준 곳들마다 그 값을 넣어준다.
// visit 배열은 매번 새롭게 만들어준다.
// 이 과정을 반복
public class bj_16234 {
    static int N, L, R, result;
    static int[][] graph;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visit;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/com/company/res/input_bj_16234.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        result = 0;
        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        while (checkMore()) {
            visit = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visit[i][j]) {
                        continue;
                    }
                    solution(j, i);
                }
            }
            result++;
        }
        System.out.println(result);
        br.close();
    }

    static void solution(int x, int y) {
        ArrayList<Integer> people = new ArrayList<>();
        ArrayDeque<int[]> nations = new ArrayDeque<>();
        ArrayDeque<int[]> afterNations = new ArrayDeque<>();
        nations.offer(new int[]{x, y}); // x,y 순서로 저장
        afterNations.offer(new int[]{x, y});
        visit[y][x] = true;
        people.add(graph[y][x]);

        while (!nations.isEmpty()) {
            int[] xy = nations.poll();
            int currentX = xy[0];
            int currentY = xy[1];
            for (int d = 0; d < 4; d++) {
                int nx = xy[0] + dx[d];
                int ny = xy[1] + dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visit[ny][nx] || Math.abs(graph[ny][nx] - graph[currentY][currentX]) < L || Math.abs(graph[ny][nx] - graph[currentY][currentX]) > R) {
                    continue;
                }
                visit[ny][nx] = true; //방문 처리
                people.add(graph[ny][nx]); // 인구 더해주기
                nations.offer(new int[]{nx, ny});
                afterNations.offer(new int[]{nx, ny});
            }
        }
        if (people.size() == 1) { // 국경을 열지 못한 경우
            return;
        }
        int avg = 0;
        for (Integer num : people) {
            avg += num;
        }
        avg /= people.size();
        while (!afterNations.isEmpty()) {
            int[] xy = afterNations.poll();
            graph[xy[1]][xy[0]] = avg;
        }
    }

    static boolean checkMore() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int d = 0; d < 4; d++) {
                    int nx = j + dx[d];
                    int ny = i + dy[d];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                        continue;
                    }
                    if (Math.abs(graph[ny][nx] - graph[i][j]) >= L && Math.abs(graph[ny][nx] - graph[i][j]) <= R) { // 국경이 열릴 조건이면
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
