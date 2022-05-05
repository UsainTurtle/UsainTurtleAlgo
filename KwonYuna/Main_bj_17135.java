package a0407;

import java.io.*;
import java.util.*;

public class Main_bj_17135 {
    static int[] archers;
    static int N, M, D, result, cnt;
    static int[] di = {0, -1, 0}, dj = {-1, 0, 1};
    static boolean[][] map, mem;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        mem = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                if (st.nextToken().equals("1")) {
                    map[i][j] = true;
                    cnt++; //적의 수
                }
            }
        }

        archers = new int[3];
        comb(0, 0);
        System.out.println(result);
        br.close();
    }

    static void comb(int idx, int st) {
        if (idx == 3) {
            for (int i = 0; i < N; i++) mem[i] = map[i].clone();
            result = Math.max(result, kill(cnt));
            return;
        }
        for (int i = st, len = M - (2 - idx); i < len; i++) {
            archers[idx] = i;
            comb(idx + 1, i + 1);
        }
    }

    static int kill(int count) {
        int suc = 0; //궁수가 죽인 적의 수

        while (0 < count) {

            //궁수마다 죽일 적을 선택
            int[][] enemys= new int[3][];
            for(int z=0;z<3;z++){
                enemys[z]=bfs(archers[z]);
            }

            //적을 죽임
            for(int z=0;z<3;z++){
                if (enemys[z] != null && mem[enemys[z][0]][enemys[z][1]]) {
                    mem[enemys[z][0]][enemys[z][1]] = false;
                    suc++;
                    count--;
                }
            }

            for (int j = 0; j < M; j++) { //성으로 들어가는 적의 수 카운트
                if (mem[N - 1][j]) count--;
            }
            for (int i = N - 1; 0 < i; i--) mem[i] = mem[i - 1].clone(); //적이동
            mem[0] = new boolean[M];
        }
        return suc;
    }

    static int[] bfs(int j) {
        if (mem[N - 1][j]) return new int[]{N - 1, j};

        boolean[][] visited = new boolean[N][M];
        ArrayDeque<Node> queue = new ArrayDeque<>();

        visited[N - 1][j] = true;
        queue.offer(new Node(N - 1, j, 1));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (D <= node.d) return null;

            for (int z = 0; z < 3; z++) {
                int ni = node.i + di[z];
                int nj = node.j + dj[z];
                if (0 <= ni && 0 <= nj && nj < M && !visited[ni][nj]) {
                    if (mem[ni][nj]) return new int[]{ni, nj};
                    visited[ni][nj] = true;
                    queue.offer(new Node(ni, nj, node.d + 1));
                }
            }
        }
        return null;
    }

    static class Node {
        int i;
        int j;
        int d;
        public Node(int i, int j, int d) {
            this.i = i;
            this.j = j;
            this.d = d;
        }
    }
}

