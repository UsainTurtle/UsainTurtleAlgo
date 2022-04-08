package com.company.tutle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class pg_kakao2022_1 {
    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        ArrayList<ArrayList<String>> reportList = new ArrayList<>(id_list.length);
        for (int i = 0; i < id_list.length; i++) {
            reportList.add(new ArrayList<>());
        }

        int[] reportCnt = new int[id_list.length];
        int k = 2;
        int[] answer = new int[id_list.length];
        for (String s : report) {
            StringTokenizer st = new StringTokenizer(s, " ");
            String from = st.nextToken();
            String to = st.nextToken();
            for (int i = 0; i < id_list.length; i++) { // 신고 리스트 저장
                if (from.equals(id_list[i])) { // 신고한 사람 찾기
                    for (int j = 0; j < id_list.length; j++) {
                        if (to.equals(id_list[j]) && !reportList.get(i).contains(to)) {
                            reportCnt[j]++;
                            reportList.get(i).add(to);
                            break;
                        }
                    }
                    break;
                }
            }
        }
        for (int i = 0; i < reportList.size(); i++) {
            for (int j = 0; j < id_list.length; j++) {
                if (reportList.get(i).contains(id_list[j]) && reportCnt[j] >= k) {
                    answer[i]++;
                }
            }
        }
        System.out.print(Arrays.toString(answer));
    }
}
