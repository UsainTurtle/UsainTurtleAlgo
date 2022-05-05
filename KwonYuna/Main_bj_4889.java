package a0502;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_bj_4889 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s;
        int tc = 1;
        while ((s = br.readLine()).charAt(0) != '-') {
            int cnt = 0, size = 0, len = s.length();

            for (int i = 0; i < len; i++) {
                if (s.charAt(i) == '{') {
                    size++;
                } else if (size == 0) {
                    cnt++;
                    size++;
                } else {
                    size--;
                }
            }
            cnt += size / 2;
            sb.append(tc++).append(". ").append(cnt).append("\n");
        }
        System.out.print(sb.toString());
    }

}
