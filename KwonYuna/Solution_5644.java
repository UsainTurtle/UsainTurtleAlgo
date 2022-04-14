package a0408;

import java.util.*;
import java.io.*;

public class Solution_5644 {
    static int[][] map;
    static int[] dy={0,-1,0,1,0}, dx={0,0,1,0,-1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        int T=Integer.parseInt(br.readLine().trim());
        for(int tc=1; tc<=T; tc++){
            StringTokenizer st =new StringTokenizer(br.readLine()," ");
            int M = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            char[] arrA = new char[M];
            char[] arrB = new char[M];
            map=new int[10][10];

            st =new StringTokenizer(br.readLine()," ");
            for(int i=0; i<M; i++) arrA[i]=st.nextToken().charAt(0);
            st =new StringTokenizer(br.readLine()," ");
            for(int i=0; i<M; i++) arrB[i]=st.nextToken().charAt(0);

            AP[] aps = new AP[A];
            for(int i=0; i<A; i++){
                st =new StringTokenizer(br.readLine()," ");
                aps[i]=new AP(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            }
            Arrays.sort(aps);
            for(int i=0; i<A; i++){
                checkAP (1<<i, aps[i]);
            }
            int yA=0, xA=0, yB=9, xB=9, score=0;
            int time=0;
            while(true){
                int selA=-1,selB=-1;
                if(0<map[yA][xA]){
                    for(selA=0;selA<A;selA++){
                        if(0<(map[yA][xA]&(1<<selA))) break;
                    }
                    score+=aps[selA].p;
                }

                if(0<map[yB][xB]){
                    for(selB=0;selB<A;selB++){
                        if(0<(map[yB][xB]&(1<<selB))) break;
                    }
                    if(selA==selB){
                        selB++;
                        for(;selB<A;selB++){
                            if(0<(map[yA][xA]&(1<<selB))||0<(map[yB][xB]&(1<<selB))) break;
                        }
                    }
                    if(selB<A){
                        score+=aps[selB].p;
                    }
                }

                if(time==M) break;

                int idxA=arrA[time]-'0';
                int idxB=arrB[time++]-'0';
                yA+=dy[idxA];
                xA+= dx[idxA];
                yB+=dy[idxB];
                xB+= dx[idxB];
            }
            sb.append("#").append(tc).append(" ").append(score).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }

    static void checkAP (int num, AP ap){
        ArrayDeque<AP> queue = new ArrayDeque<>();

        map[ap.y][ap.x]|=num;
        queue.offer(ap);

        while(!queue.isEmpty()){
            AP cur= queue.poll();
            if(cur.c==0)continue;
            for(int z=1;z<5;z++){
                int ny=cur.y+dy[z];
                int nx=cur.x+ dx[z];

                if(0<=ny&&ny<10&&0<=nx&&nx<10&&((num&map[ny][nx]))==0){
                    map[ny][nx]|=num;
                    queue.offer(new AP(nx,ny,cur.c-1,0));
                }
            }
        }
    }

    static class AP implements Comparable<AP>{
        int x,y,c,p;
        public AP(int x, int y, int c, int p) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }

        @Override
        public int compareTo(AP o) {
            return Integer.compare(o.p,this.p);
        }
    }
}
