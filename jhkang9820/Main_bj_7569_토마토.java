package study;
import java.io.*;
import java.util.*;
public class Main_bj_7569_토마토 {

    // 현위치, 위, 아래 ->
    static int[] dh = {0, 0, 0, 0, -1, 1};
    static int[] di = {0, 0, -1, 1, 0, 0};
    static int[] dj = {-1, 1, 0, 0, 0, 0};
    static int[][][] map;
    static boolean[][][] visited;
    static int M, N, H;

    public static class Node {
        int h, i, j;
        Node(int h, int i, int j){
            this.h = h;
            this.i = i;
            this.j = j;
        }
    }
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("res/input_bj_7569.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][N][M];
        visited = new boolean[H][N][M];
        Queue<Node> queue = new LinkedList<>();

        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < M; j++) {
                    map[h][i][j] = Integer.parseInt(st.nextToken());
                    if (map[h][i][j] == 1) queue.offer(new Node(h, i, j));
                }
            }
        }
        System.out.println(bfs(queue));
        br.close();
    }

    public static int bfs (Queue<Node> queue) {
        while (!queue.isEmpty()) {
            Node tom = queue.poll();
            for (int d = 0; d < 6; d++) {
                int nh = tom.h + dh[d];
                int ni = tom.i + di[d];
                int nj = tom.j + dj[d];
                if (!check(nh, ni, nj) || map[nh][ni][nj] != 0) continue;
                queue.add(new Node(nh, ni, nj));
                map[nh][ni][nj] = map[tom.h][tom.i][tom.j] + 1;
            }
        }
        int day = Integer.MIN_VALUE;
        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[h][i][j] == 0) {
                        return -1;
                    }
                    day = Math.max(day, map[h][i][j]);
                }
            }
        }
        if (day == 1) return 0;
        else return day - 1;
    }
    public static boolean check(int h, int i, int j){
        if (0 <= h && h < H && 0 <= i && i < N && 0 <= j && j < M) return true;
        return false;
    }
}
