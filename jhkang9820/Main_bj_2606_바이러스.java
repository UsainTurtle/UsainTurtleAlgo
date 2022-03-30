package study;
import jdk.nashorn.internal.parser.TokenType;

import java.io.*;
import java.util.*;

public class Main_bj_2606_바이러스 {

    static int N;
    static int[] parents;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_bj_2606.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int pair = Integer.parseInt(br.readLine());
        int cnt = 0;

        parents = new int[N+1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < pair; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            union(from, to);
        }

        for (int i = 2; i <= N; i++) {
            if (findSet(1) == findSet(i)) cnt++;
        }
        System.out.println(cnt);
        br.close();
    }

    public static int findSet(int a) {
        if(a==parents[a]) return a;
        return parents[a] = findSet(parents[a]); // path compression
    }

    //a,b 두 집합 합치기
    public static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if(aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }
}
