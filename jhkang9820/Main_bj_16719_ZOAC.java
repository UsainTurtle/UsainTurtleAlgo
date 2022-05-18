package study.a0502;
import com.sun.xml.internal.bind.v2.runtime.output.DOMOutput;
import jdk.internal.util.xml.impl.Input;

import java.util.*;
import java.io.*;
public class Main_bj_16719_ZOAC {
    static int N;
    static char alpha[];
    static boolean visited[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        N = s.length();
        alpha = s.toCharArray();
        visited = new boolean[N];
        search(0, N);
        br.close();
    }
    static public void search (int s, int e) {
        int min = Integer.MAX_VALUE, idx = -1;
        for (int i = s; i < e; i++) {
            if (!visited[i] && min > alpha[i]) {
                min = alpha[i];
                idx = i;
            }
        }
        if (idx != -1) { // 찾음
            StringBuilder sb = new StringBuilder();
            visited[idx] = true;
            for (int i = 0; i < N; i++) {
                if (visited[i]) sb.append(alpha[i]);
            }
            System.out.println(sb.toString());
            search(idx, N);
            search(s, idx);
        }
    }
}
