package a0331;

import java.util.*;

public class Solution_pg_92334 {
    public static void main(String[] args) {


        // map key: (String) user id / value: (Integer) user id arr idex

//        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
//        String[] report={"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
//        int k=2;

        String[] id_list = {"con", "ryan"};
        String[] report={"ryan con", "ryan con", "ryan con", "ryan con"};
        int k=3;

        Map<String,Integer> map = new HashMap<>();
        LinkedList<Integer>[] arr = new LinkedList[id_list.length];

        for(int i=0; i<id_list.length; i++){
            map.put(id_list[i],i);
            arr[i]=new LinkedList<>();
        }

        for(int i=0; i<report.length; i++){
            StringTokenizer st = new StringTokenizer(report[i]," ");
            String user = st.nextToken();
            String baduser = st.nextToken();
            if(arr[map.get(baduser)].contains(map.get(user))) continue;
            arr[map.get(baduser)].add(map.get(user));
        }

        int[] answer = new int[id_list.length];
        for(int i=0; i<id_list.length; i++) {
            if (k <= arr[i].size()) {
                for (int id = 0, N=arr[i].size(); id < N; id++) {
                    answer[arr[i].get(id)]++;
                }
            }
        }
    }
}
