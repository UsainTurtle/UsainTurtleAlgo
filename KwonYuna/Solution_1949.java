package a0408;

import java.io.*;
import java.util.*;

public class Solution_1949 {
    static boolean[][] visited;
    static int[] di={-1,0,1,0}, dj={0,1,0,-1};
    static int N,K,result,map[][];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        int T=Integer.parseInt(br.readLine().trim());
        for(int tc=1; tc<=T; tc++){
            StringTokenizer st =new StringTokenizer(br.readLine()," ");
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map=new int[N][N];
            visited=new boolean[N][N];
            result=0;

            ArrayList<int[]> maxarr = new ArrayList<>();
            int max=0;
            for(int i=0; i<N;i++){
                st =new StringTokenizer(br.readLine()," ");
                for(int j=0; j<N;j++){
                    map[i][j]=Integer.parseInt(st.nextToken());
                    if(max<=map[i][j]){
                        if(max<map[i][j]){
                            maxarr.clear();
                            max=map[i][j];
                        }
                        maxarr.add(new int[]{i,j});
                    }
                }
            }

            for(int z=0,len=maxarr.size(); z<len;z++){
                int i=maxarr.get(z)[0];
                int j=maxarr.get(z)[1];

                visited[i][j]=true;
                dfs(i,j,1,false);
                visited[i][j]=false;
            }
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }

    static void dfs(int i, int j, int len, boolean check){
        result=Math.max(result,len++);
        for(int z=0; z<4; z++){
            int ni=i+di[z];
            int nj=j+dj[z];
            if(ni<0||N<=ni||nj<0||N<=nj||visited[ni][nj]) continue;

            visited[ni][nj]=true;
            if(map[ni][nj] < map[i][j]){
                dfs(ni, nj, len, check);
            }else if(!check && map[ni][nj]-K < map[i][j]){
                int tmp=map[ni][nj];
                map[ni][nj]=map[i][j]-1;
                dfs(ni, nj, len, true);
                map[ni][nj]=tmp;
            }
            visited[ni][nj]=false;
        }
    }
}
