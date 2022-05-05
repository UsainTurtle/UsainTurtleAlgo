/*
N개의 컴퓨터가 서로 연결되어 있는 랜선의 길이가 주어질 때,
다솜이가 기부할 수 있는 랜선의 길이의 최댓값을 출력하는 프로그램을 작성하시오.

기부할 수 있는 최댓값 = (전체 랜선 길이) - (컴퓨터 연결 랜선 최솟값)
*/

package study.a0502;
import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;

import java.util.*;
import java.io.*;
public class Main_bj_1414_불우이웃돕기 {

    static class Edge implements Comparable<Edge>{
        int from, to, weight;
        public Edge (int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return this.weight-o.weight;
        }
    }

    static void makeSet () {
        parents = new int[N];
        for (int i = 0; i < N; i++) parents[i] = i;
    }

    static int findSet(int a) {
        if (a == parents[a]) return a;
        return parents[a] = findSet(parents[a]);
    }

    static boolean union (int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }
    static int N, parents[];
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_1414.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                if (s.charAt(j) == '0') map[i][j] = 0; // 랜선 연결 안 됨
                else if ('a' <= s.charAt(j) && s.charAt(j) <= 'z') map[i][j] = s.charAt(j) - 96;
                else map[i][j] = s.charAt(j) - 38;
                sum += map[i][j]; // 보유 중인 랜선의 총 길이
            }
        }
        makeSet();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int min = Math.min(map[i][j], map[j][i]);
                if (min==0) { // min이 0 -> 둘 다 연결 안되거나, 둘 중 하나가 연결 안 된 경우
                    if (map[i][j] == 0 && map[j][i] != 0) min = map[j][i];
                    else if (map[i][j] != 0 && map[j][i] == 0) min = map[i][j];
                    else continue;
                }
                pq.offer(new Edge(i, j, min)); // 최소 연결값만 넣음.
            }
        }

        int result = 0, cnt = 0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            //System.out.println("길이: " + cur.weight);
            if (union(cur.from, cur.to)) {
                result += cur.weight;
                if (++cnt == N-1) break;
            }
        }
        //System.out.println(Arrays.deepToString(map));
        //System.out.println("총합: " + sum);
        if (cnt != N-1) System.out.println(-1); // 모든 컴퓨터가 연결이 안되는 경우
        else System.out.println(sum - result); // 기부할 수 있는 랜선 길이의 최대값
        br.close();
    }
}
