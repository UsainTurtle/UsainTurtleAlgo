package a0414;

import java.util.Arrays;

public class Solution_pg_92342 {
    public static void main(String[] args) throws Exception {
//        int n=5;
//        int[] info={2,1,1,1,0,0,0,0,0,0,0};
//0,2,2,0,1,0,0,0,0,0,0

//        int n=1;
//        int[] info={1,0,0,0,0,0,0,0,0,0,0};
////-1

        int n = 9;
        int[] info = {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1};
////1,1,2,0,1,2,2,0,0,0,0

//        int n=10;
//        int[] info={0,0,0,0,0,0,0,0,3,4,3};
////1,1,1,1,1,1,1,1,0,0,2

        System.out.println(Arrays.toString(solution(n, info)));
    }

    static int max, rmins, rminc;
    static int[] maxArr;

    public static int[] solution(int n, int[] info) {

        max = 0;
        rmins = 10;
        rminc = n;
        maxArr = new int[]{-1};

        int ascore = 0;
        for (int i = 0; i < 11; i++) {
            if (0 < info[i]) ascore += (10 - i);
        }
        comb(0, n, info.clone(), new int[11], ascore, 0);


        int[] answer = maxArr;
        return answer;
    }

    static void comb(int idx, int n, int[] info, int[] score, int a, int r) {

        if (n == 0 || idx == 10) {
            if (r <= a) return;
            if (idx == 10) score[idx] = n;

            if (max < (r - a)) {
                for (int i = 10; 0 <= i; i--) {
                    if (0 < score[i]) {
                        rmins = 10 - i;
                        rminc = score[i];
                        break;
                    }
                }
                max = r - a;
                maxArr = score.clone();

            } else if (max == (r - a)) {
                int tmps = 10, tmpc = 10;
                for (int i = 10; 0 <= i; i--) {
                    if (0 < score[i]) {
                        tmps = 10 - i;
                        tmpc = score[i];
                        break;
                    }
                }
                if (tmps < rmins || (tmps == rmins && rminc < tmpc)) {
                    rmins = tmps;
                    rminc = tmpc;
                    max = r - a;
                    maxArr = score.clone();
                }
            }
            return;
        }

        for (int i = 0; i <= n; i++) {
            score[idx] = i;
            int ascore = a;
            int rscore = r;
            if (info[idx] < score[idx]) {
                if (0 < info[idx]) ascore -= (10 - idx);
                rscore += (10 - idx);
            }

            comb(idx + 1, n - i, info.clone(), score.clone(), ascore, rscore);
        }
    }
}
