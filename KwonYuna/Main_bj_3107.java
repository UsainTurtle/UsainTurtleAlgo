package a0502;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_bj_3107 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] arr = br.readLine().split(":");
        int cnt = arr.length;
        boolean check = false;
        for (int i = 0; i < cnt; i++) {
            if (arr[i].equals("")) {
                if (check) {
                    sb.append("0000:");
                } else {
                    int zeros = 9 - cnt;
                    for (int z = 0; z < zeros; z++) {
                        sb.append("0000:");
                    }
                    check = true;
                }
            } else {
                int zero = 4 - arr[i].length();
                for (int z = 0; z < zero; z++) {
                    sb.append('0');
                }
                sb.append(arr[i]).append(":");
            }
        }
        if (!check) {
            int zeros = 8 - cnt;
            for (int z = 0; z < zeros; z++) {
                sb.append("0000:");
            }
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb.toString());
    }
}
