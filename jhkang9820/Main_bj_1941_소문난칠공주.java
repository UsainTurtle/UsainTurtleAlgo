package study;
import java.io.*;
import java.util.*;

public class Main_bj_1941_소문난칠공주 {

    static char[][] map;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int[] selected;
    static int result;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_1941.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[5][5]; // 학생 자리 배치
        selected = new int[7]; // 선택된 학생
        result = 0; // 결성할 수 있는 수

        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().toCharArray();
        }

        comb(0, 0);
        System.out.println(result);
    }
    static void comb(int cnt, int start){
        if (cnt == 7){
            int som = 0;
            boolean[] selVisited = new boolean[25];

            for (int i = 0; i < 7; i++) {
                selVisited[selected[i]] = true; // 선택된 칠공주 boolean 표시
                int x = selected[i]/5;
                int y = selected[i]%5;
                if (map[x][y] == 'S') som++;
            }
            System.out.println(Arrays.toString(selected));
            if (som >= 4) {
                if (bfsCheck(selVisited)) {
                    result++;
                }
                return;
            }
            return;
        }
        for (int i = start; i < 25; i++) {
            selected[cnt] = i;
            comb(cnt+1, i+1);
        }
    }
    static boolean bfsCheck(boolean[] selVisited){
        int cnt = 1; // 첫 q cnt에 포함.
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[25];

        queue.offer(selected[0]);
        visited[selected[0]] = true; // 방문처리
        while (!queue.isEmpty()){
            int cur = queue.poll();
            int i = cur/5;
            int j = cur%5;
            for (int d = 0; d < 4; d++) {
                int ni = i + di[d];
                int nj = j + dj[d];
                if (!check(ni, nj) || visited[ni*5+nj]) continue;
                if (!selVisited[ni*5+nj]) continue;
                cnt++;
                visited[ni*5+nj] = true;
                queue.offer(ni*5+nj);
            }
        }
        if (cnt == 7) {
            return true;
        }
        else return false;
    }
    static boolean check(int i, int j){
        if (0 <= i && i < 5 && 0 <= j && j < 5) return true;
        return false;
    }
}
