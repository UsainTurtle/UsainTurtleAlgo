package a0330;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_2382 {

    static int[] di = {-1, 1, 0, 0}; //상 하 좌 우
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
            PriorityQueue<Node> pq = new PriorityQueue<>();

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine(), " ");
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int der = Integer.parseInt(st.nextToken());
                pq.offer(new Node(i, j, cnt, der - 1));
            }


            int[][][] board = new int[N][N][2]; // 0: sum(cnt) 1: d

            for (int m = 0; m < M; m++) {

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        board[i][j][0] = 0;
                        board[i][j][1] = 0;
                    }
                }

                while (!pq.isEmpty()) {
                    Node node = pq.poll();
                    node.i += di[node.der];
                    node.j += dj[node.der];

                    if (node.i == 0 || node.i == N - 1 || node.j == 0 || node.j == N - 1) {
                        //살아남은 미생물 수 = 원래 미생물 수를 2로 나눈 후 소수점 이하를 버림 한 값
                        node.cnt /= 2;
                        node.der = node.der % 2 == 0 ? node.der + 1 : node.der - 1;
                    }

                    board[node.i][node.j][0] += node.cnt;
                    board[node.i][node.j][1] = node.der;
                }

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (0 < board[i][j][0]) pq.offer(new Node(i, j, board[i][j][0], board[i][j][1]));
                    }
                }
            }

            int answer = 0;
            while (!pq.isEmpty()) {
                answer += pq.poll().cnt;
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }

    static class Node implements Comparable<Node> {
        int i;
        int j;
        int cnt; //군집 수
        int der; //방향

        public Node(int i, int j, int cnt, int der) {
            this.i = i;
            this.j = j;
            this.cnt = cnt;
            this.der = der;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }
}
