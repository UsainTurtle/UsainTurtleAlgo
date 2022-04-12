package a0330;

import java.util.*;
import java.io.*;

public class Solution_2383 {
    static LinkedList<Student> list;
    static int downA, downB, result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            list = new LinkedList<>();
            int ia=-1, ja=-1, ib=-1, jb=-1;
            result=Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j]==1) {
                        list.add(new Student(i,j));
                    }else if(1<map[i][j]) {
                        if(ia==-1){
                            ia=i;
                            ja=j;
                            downA=map[i][j];
                        }else{
                            ib=i;
                            jb=j;
                            downB=map[i][j];
                        }
                    }
                }
            }

            for(int i=0, len=list.size();i<len;i++){
                Student student = list.get(i);
                student.distA= Math.abs(ia-student.i)+Math.abs(ja- student.j);
                student.distB= Math.abs(ib-student.i)+Math.abs(jb- student.j);
            }
            subset(0,new boolean[list.size()]);

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }

    static void subset(int idx, boolean[] teamA){
        if(idx==teamA.length){
            int time=0;
            int[] arr;
            PriorityQueue<Integer> pq= new PriorityQueue<>();
            for(int i=0, len=teamA.length; i<len; i++){
                if(teamA[i]) pq.offer(list.get(i).distA);
            }
            if(!pq.isEmpty()) {
                arr = new int[pq.size()];
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = pq.poll() + 1;
                }
                if (result < arr[arr.length - 1] + downA) return;
                time = play(arr, downA);

                if (result <= time) return;
            }
            for(int i=0, len=teamA.length; i<len; i++){
                if(!teamA[i]) pq.offer(list.get(i).distB);
            }
            if(!pq.isEmpty()) {
                arr = new int[pq.size()];
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = pq.poll() + 1;
                }
                if (result <= arr[arr.length - 1] + downB) return;
                time = Math.max(time, play(arr, downB));

            }
            result = Math.min(time,result);
            return;
        }

        teamA[idx]=true;
        subset(idx+1, teamA);
        teamA[idx]=false;
        subset(idx+1, teamA);
    }

    static int play(int[] arr, int down){

        int time=arr[0];
        int pt=0,ing=0,ed=0;

        while(pt<arr.length){
            time++;
            ed=0;
            ing=0;
            for(int i=0; i<3 && (pt+i)<arr.length; i++){
                if(arr[pt+i]<=time) ing++;
                else break;
            }

            for(int i=0; i<ing && (pt+i)<arr.length; i++){
                if(time-arr[pt+i]==down){
                    ed++;
                }
            }
            for(int i=0;i<ed && (pt+ing+i)<arr.length; i++){
                if(time<=arr[pt+ing+i]) break;
                arr[pt+ing+i]=time;
            }
            pt+=ed;
        }
        return time;
    }

    static class Student{
        int i;
        int j;
        int distA;
        int distB;
        public Student (int i, int j){
            this.i=i;
            this.j=j;
        }
    }
}
