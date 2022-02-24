package com.company.tutle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
크기가 N×N인 도시가 있다. 도시는 1×1크기의 칸으로 나누어져 있다. 도시의 각 칸은 빈 칸/치킨집/집 중 하나이다.
도시의 칸은 (r, c)와 같은 형태로 나타내고, r행 c열 또는 위에서부터 r번째 칸, 왼쪽에서부터 c번째 칸을 의미한다. r과 c는 1부터 시작한다.
이 도시에 사는 사람들은 치킨을 매우 좋아한다. 따라서, 사람들은 "치킨 거리"라는 말을 주로 사용한다.
치킨 거리는 집과 가장 가까운 치킨집 사이의 거리이다. 즉, 치킨 거리는 집을 기준으로 정해지며, 각각의 집은 치킨 거리를 가지고 있다. 도시의 치킨 거리는 모든 집의 치킨 거리의 합이다.
임의의 두 칸 (r1, c1)과 (r2, c2) 사이의 거리는 |r1-r2| + |c1-c2|로 구한다.
예를 들어, 아래와 같은 지도를 갖는 도시를 살펴보자.
0은 빈 칸, 1은 집, 2는 치킨집이다.
(2, 1)에 있는 집과 (1, 2)에 있는 치킨집과의 거리는 |2-1| + |1-2| = 2, (5, 5)에 있는 치킨집과의 거리는 |2-5| + |1-5| = 7이다. 따라서, (2, 1)에 있는 집의 치킨 거리는 2이다.
(5, 4)에 있는 집과 (1, 2)에 있는 치킨집과의 거리는 |5-1| + |4-2| = 6, (5, 5)에 있는 치킨집과의 거리는 |5-5| + |4-5| = 1이다. 따라서, (5, 4)에 있는 집의 치킨 거리는 1이다.
이 도시에 있는 치킨집은 모두 같은 프랜차이즈이다. 프렌차이즈 본사에서는 수익을 증가시키기 위해 일부 치킨집을 폐업시키려고 한다. 오랜 연구 끝에 이 도시에서 가장 수익을 많이 낼 수 있는  치킨집의 개수는 최대 M개라는 사실을 알아내었다.
도시에 있는 치킨집 중에서 최대 M개를 고르고, 나머지 치킨집은 모두 폐업시켜야 한다. 어떻게 고르면, 도시의 치킨 거리가 가장 작게 될지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N(2 ≤ N ≤ 50)과 M(1 ≤ M ≤ 13)이 주어진다.=
둘째 줄부터 N개의 줄에는 도시의 정보가 주어진다.
도시의 정보는 0, 1, 2로 이루어져 있고, 0은 빈 칸, 1은 집, 2는 치킨집을 의미한다. 집의 개수는 2N개를 넘지 않으며, 적어도 1개는 존재한다. 치킨집의 개수는 M보다 크거나 같고, 13보다 작거나 같다.

출력
첫째 줄에 폐업시키지 않을 치킨집을 최대 M개를 골랐을 때, 도시의 치킨 거리의 최솟값을 출력한다.

5 3
0 0 1 0 0
0 0 2 0 1
0 1 2 0 0
0 0 1 0 0
0 0 0 0 2

 */
// 치킨집을 조합으로 구한다.
// 각 치킨집에서 집까지 걸리는 거리를 구한다.
public class bj_15686 {
    static int N, M;
    static int[][] graph;
    static ArrayList<Point> house = new ArrayList<>();
    static ArrayList<Point> chicken = new ArrayList<>();
    static ArrayList<Point> select = new ArrayList<>();

    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        result = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int current = Integer.parseInt(st.nextToken());
                if (current == 1) { //집 일경우
                    house.add(new Point(j, i));
                } else if (current == 2) {//치킨 일 경우
                    chicken.add(new Point(j, i));
                }
            }
        }
        solution(0, 0);
        System.out.print(result);
    }

    static void solution(int cnt, int start) {
        if (cnt == M) {
            int sum = 0;
            for (int i = 0; i < house.size(); i++) {
                int distance = Integer.MAX_VALUE;
                for (int j = 0; j < select.size(); j++) {
                    int currentDis = Math.abs(house.get(i).x - select.get(j).x) + Math.abs(house.get(i).y - select.get(j).y);
                    distance = Math.min(distance, currentDis);
                }
                sum += distance;
                if(sum >= result) {
                    return;
                }
            }
            result = Math.min(sum,result);
            return;
        }
        for (int i = start; i < chicken.size(); i++) {
            select.add(chicken.get(i));
            solution(cnt + 1, i + 1);
            select.remove(select.size() - 1);
        }
    }

    public static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /* 시간초과가 났음 조합을 잘못 짜줬음
    static void solution(int cnt, int x, int y) {
        if (cnt == M) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visit[i][j]) { // 검사할 치킨집이 아니면
                        continue;
                    }
                    boolean[][] currentVisit = new boolean[N][N];
                    ArrayDeque<int[]> q = new ArrayDeque<>();
                    q.offer(new int[]{j, i}); //x,y 순서, 치킨집 위치
                    currentVisit[i][j] = true;
                    while (!q.isEmpty()) {
                        int[] xy = q.poll();
                        for (int d = 0; d < 4; d++) {
                            int nx = xy[0] + dx[d];
                            int ny = xy[1] + dy[d];
                            if (nx < 0 || ny < 0 || nx >= N || ny >= N || currentVisit[ny][nx]) { //범위 벗어나거나 방문한 곳이라면
                                continue;
                            }
                            currentVisit[ny][nx] = true;
                            if (graph[ny][nx] == 1) {
                                distance[ny][nx] = Math.min(distance[ny][nx], Math.abs(i - ny) + Math.abs(j - nx)); //치킨집으로부터 해당 집의 거리를 계산하여 distance에 넣어주기
                            }
                            q.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
            int sum = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (distance[i][j] != Integer.MAX_VALUE) {
                        sum += distance[i][j];
                        distance[i][j] = Integer.MAX_VALUE; //계산이 끝난 뒤에 다시 초기화
                    }
                }
            }
            result = Math.min(sum, result);
            return;
        }

        for (int i = y; i < N; i++) { //2차원 배열에서 조합을 짜줘야함, 이 코드는 조합을 효율적으로 짜주지 못했음
            for (int j = 0; j < N; j++) {
                if(i == y && j <= x) {
                    continue;
                }
                if (graph[i][j] == 2) { //치킨집
                    visit[i][j] = true;
                    solution(cnt + 1, j, i);
                    visit[i][j] = false;
                }
            }
        }
    }
    */
}
