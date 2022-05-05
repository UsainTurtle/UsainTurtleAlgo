package a0330;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1953 {

    static int[] di={-1,0,1,0};
    static int[] dj={0,1,0,-1};
    static char[][] map;
    static boolean[][] visited;
    static int N,M,L,cnt;
    static ArrayDeque<int[]> queue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            N=Integer.parseInt(st.nextToken());
            M=Integer.parseInt(st.nextToken());
            int R=Integer.parseInt(st.nextToken()), C=Integer.parseInt(st.nextToken());
            L=Integer.parseInt(st.nextToken());
            queue = new ArrayDeque<>();

            map = new char[N][M];
            visited = new boolean[N][M];
            cnt=0;

            for(int i=0; i<N;i++){
                st = new StringTokenizer(br.readLine()," ");
                for(int j=0; j<M ; j++){
                    map[i][j]=st.nextToken().charAt(0);
                }
            }

            queue.offer(new int[]{R,C,1});
            visited[R][C]=true;
            cnt++;

            while(!queue.isEmpty()){
                int[] ijd = queue.poll();
                if(!play(ijd[0], ijd[1], ijd[2])) break;
            }
            sb.append("#").append(tc).append(" ").append(cnt).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }

    static boolean play(int i,int j, int d){
        int[] z;
        switch (map[i][j]){
            case '1':z = new int[]{0,1,2,3};break;
            case '2':z = new int[]{0,2};break;
            case '3':z = new int[]{1,3};break;
            case '4':z = new int[]{0,1};break;
            case '5':z = new int[]{1,2};break;
            case '6':z = new int[]{2,3};break;
            default:z = new int[]{0,3};break;
        }

        for(int zi: z){
            int ni= i+di[zi];
            int nj= j+dj[zi];
            if(0<=ni&&ni<N&&0<=nj&&nj<M&&!visited[ni][nj]&&check(zi,map[ni][nj])){
                if(L<d+1) return false;
                queue.offer(new int[]{ni,nj,d+1});
                visited[ni][nj]=true;
                cnt++;
            }
        }
        return true;
    }

    static boolean check(int d, char m){
        d=1<d?d-2:d+2;
        switch (m){
            case '0':return false;
            case '1':return true;
            case '2':if(d==0||d==2)return true;break;
            case '3':if(d==1||d==3)return true;break;
            case '4':if(d==0||d==1)return true;break;
            case '5':if(d==1||d==2)return true;break;
            case '6':if(d==3||d==2)return true;break;
            default:if(d==0||d==3)return true;break;
        }
        return false;
    }
}
