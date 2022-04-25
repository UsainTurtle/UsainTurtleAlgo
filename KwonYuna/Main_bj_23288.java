package a0420;

import java.io.*;
import java.util.*;

public class Main_bj_23288 {
    static int[] di = {-1, 0, 1, 0}, dj = {0, 1, 0, -1};
    static int[][] map, scoreBoard;
    static boolean[][] visited;
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        scoreBoard = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                }
            }
        }
        //현재 주사위 위치 (i,j), 방향, 최종점수
        int i = 0, j = 0, dir = 3, score = 0;
        //주사위 북, 서, 아래 면의 값
        int ptN = 2, ptW = 4, ptD = 6;

        for (int k = 0; k < K; k++) {
            //1. 주사위가 이동 방향으로 한 칸 굴러간다.
            int ni = i + di[dir];
            int nj = j + dj[dir];
            if (ni < 0 || N <= ni || nj < 0 || M <= nj) {
                // 만약, 이동 방향에 칸이 없다면, 이동 방향을 반대로 한 다음 한 칸 굴러간다.
                dir = (dir + 2) % 4;
                ni = i + di[dir];
                nj = j + dj[dir];
            }
            i = ni;
            j = nj;

            //전개도 변경 => 포인터 값 변경
            int tmp;
            switch (dir) {
                case 0: //북
                    tmp = ptN;
                    ptN = 7 - ptD;
                    ptD = tmp;
                    break;
                case 1: //동
                    tmp = ptD;
                    ptD = 7 - ptW;
                    ptW = tmp;
                    break;
                case 2: //남
                    tmp = ptN;
                    ptN = ptD;
                    ptD = 7 - tmp;
                    break;
                default: //서
                    tmp = ptD;
                    ptD = ptW;
                    ptW = 7 - tmp;
                    break;

            }

            //주사위가 도착한 칸 (x, y)에 대한 점수를 획득
            score += scoreBoard[i][j];

            // 주사위의 아랫면에 있는 정수 A와 주사위가 있는 칸 (x, y)에 있는 정수 B를 비교해 이동 방향을 결정
            if (ptD < map[i][j]) { // A < B인 경우 이동 방향을 90도 반시계 방향으로 회전
                dir = dir == 0 ? 3 : dir - 1;
            } else if (ptD > map[i][j]) { // A > B인 경우 이동 방향을 90도 시계 방향으로 회전
                dir = (dir + 1) % 4;
            }
            // A = B인 경우 이동 방향에 변화는 없다.
        }
        System.out.println(score);
        br.close();
    }

    static void bfs(int i, int j) {
        LinkedList<int[]> queue = new LinkedList<>();
        LinkedList<int[]> squeue = new LinkedList<>();

        visited[i][j] = true;
        queue.offer(new int[]{i, j});
        int cnt = 0, num = map[i][j];

        while (!queue.isEmpty()) {
            int[] ij = queue.poll();
            cnt++;
            for (int z = 0; z < 4; z++) {
                int ni = ij[0] + di[z];
                int nj = ij[1] + dj[z];
                if (0 <= ni && ni < N && 0 <= nj && nj < M && !visited[ni][nj] && map[ni][nj] == num) {
                    visited[ni][nj] = true;
                    queue.offer(new int[]{ni, nj});
                }
            }
            squeue.offer(ij);
        }
        cnt *= num; // 점수 = 해당 칸에 적힌 숫자 * 연속이동 가능 횟수
        while (!squeue.isEmpty()) {
            int[] ij = squeue.poll();
            scoreBoard[ij[0]][ij[1]] = cnt;
        }
    }

}
