package com.company.tutle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
N×M의 모눈종이 위에 아주 얇은 치즈가 <그림 1>과 같이 표시되어 있다. 단, N 은 세로 격자의 수이고, M 은 가로 격자의 수이다.
이 치즈는 냉동 보관을 해야만 하는데 실내온도에 내어놓으면 공기와 접촉하여 천천히 녹는다.
그런데 이러한 모눈종이 모양의 치즈에서 각 치즈 격자(작 은 정사각형 모양)의 4변 중에서 적어도 2변 이상이 실내온도의 공기와 접촉한 것은 정확히 한시간만에 녹아 없어져 버린다.
따라서 아래 <그림 1> 모양과 같은 치즈(회색으로 표시된 부분)라면 C로 표시된 모든 치즈 격자는 한 시간 후에 사라진다.

<그림 2>와 같이 치즈 내부에 있는 공간은 치즈 외부 공기와 접촉하지 않는 것으로 가정한다. 그러므 로 이 공간에 접촉한 치즈 격자는 녹지 않고 C로 표시된 치즈 격자만 사라진다.
그러나 한 시간 후, 이 공간으로 외부공기가 유입되면 <그림 3>에서와 같이 C로 표시된 치즈 격자들이 사라지게 된다.

모눈종이의 맨 가장자리에는 치즈가 놓이지 않는 것으로 가정한다. 입력으로 주어진 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간을 구하는 프로그램을 작성하시오.

입력
첫째 줄에는 모눈종이의 크기를 나타내는 두 개의 정수 N, M (5 ≤ N, M ≤ 100)이 주어진다.
그 다음 N개의 줄에는 모눈종이 위의 격자에 치즈가 있는 부분은 1로 표시되고, 치즈가 없는 부분은 0으로 표시된다.
또한, 각 0과 1은 하나의 공백으로 분리되어 있다.

8 9
0 0 0 0 0 0 0 0 0
0 0 0 1 1 0 0 0 0
0 0 0 1 1 0 1 1 0
0 0 1 1 1 1 1 1 0
0 0 1 1 1 1 1 0 0
0 0 1 1 0 1 1 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0

8 9
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 1 1 0 0 0 1 1 0
0 1 0 1 1 1 0 1 0
0 1 0 0 1 0 0 1 0
0 1 0 1 1 1 0 1 0
0 1 1 0 0 0 1 1 0
0 0 0 0 0 0 0 0 0


 */

// 외부와 내부를 어떤 식으로 갈라줄지
// 내부가 치즈로 감싸져 있는 graph를 또 만들어주고 기존 graph와 비교해보는건 어떨지
// 1 = 치즈
// 2 = 사라질 치즈
// 0 = 확인 전의 공간, 내부 공간
// 7 = 외부공간

// 1. 치즈가 모두 녹았는지 확인
// 2. 모두 녹았으면 return
// 2-1. 녹지 않았으면 3번으로
// 3. 모든 공간 확인해주면서 내부와 외부 공간 표시
// 4. 치즈 확인
// 5. 사라질 치즈 0으로 변경, 변경이 끝나면 결과값 + 1
// 6. 외부 공간 값을 모두 0으로 변경하여 외부/내부 확인을 다시 해줌
// 1번으로 이동

public class bj_2638 {
    static int N, M;
    static int[][] graph;
    static int result;
    static int[] dx = {1, 0, -1, 0}; //우하좌상
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        result = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        updateInner(0, 0);
        solution();

        System.out.print(result);
    }

    static void solution() {
        if (isAllMelted()) { // 치즈가 다 녹은 경우
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] != 1) continue; // 치즈가 아니라면 다음 케이스
                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = j + dx[d];
                    int ny = i + dy[d];
                    if (graph[ny][nx] == 7) { // 상하좌우를 확인하고 외부공간인 경우가 2보다 큰 경우
                        cnt++;
                    }
                }
                if (cnt >= 2) {
                    graph[i][j] = 2; //사라질 치즈
                }
            }
        }
        rmCh(); // 사라질 치즈 제거
        updateInner(0, 0);
        solution();
    }

    static boolean isAllMelted() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    static void rmCh() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 2) {
                    graph[i][j] = 0;
                } else if (graph[i][j] == 7) { // 다시 공간을 체크해주기 위해서 외부공간 이었던 곳을 0으로 돌려주기
                    graph[i][j] = 0;
                }
            }
        }
        result++;
    }

    static void updateInner(int x, int y) {
        graph[y][x] = 7;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || ny < 0 || nx >= M || ny >= N || graph[ny][nx] == 7) {
                continue; // 범위를 벗어나면 다음 케이스
            }
            if (graph[ny][nx] != 1) {
                updateInner(nx, ny);
            }
        }
    }
}
