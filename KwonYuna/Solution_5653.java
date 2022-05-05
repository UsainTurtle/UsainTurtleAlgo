package a0408;

import java.io.*;
import java.util.*;

public class Solution_5653 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb =new StringBuilder();
        int T=Integer.parseInt(br.readLine().trim());
        for(int tc=1; tc<=T; tc++){
            StringTokenizer st =new StringTokenizer(br.readLine()," ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            Node[][] map=new Node[N+2*K][M+2*K];
            ArrayDeque<Node> queue = new ArrayDeque<>();
            for(int i=0; i<N;i++){
                st =new StringTokenizer(br.readLine()," ");
                for(int j=0; j<M;j++){
                    int c=Integer.parseInt(st.nextToken());
                    if(0<c){
                        map[K+i][K+j]=new Node(K+i,K+j,c,0,0);
                        queue.offer( map[K+i][K+j]);
                    }
                }
            }

            int[] di={-1,0,1,0}, dj={0,1,0,-1};
            int time=0;
            while(!queue.isEmpty() && time++<K){
                ArrayDeque<Node> tmpQ = new ArrayDeque<>();
                ArrayList<int[]> arr = new ArrayList<>();
                while(!queue.isEmpty()){
                    Node node = queue.poll();
                    if(node.s==1&&((time-1)-node.t)%node.c==0){
                        for(int z=0; z<4; z++){
                            int ni=node.i+di[z];
                            int nj=node.j+dj[z];
                            Node n = map[ni][nj];
                            if(n==null){
                                map[ni][nj]=new Node(ni,nj,node.c,time,0);
                                arr.add(new int[]{ni, nj});
                            }else if(n.t==time && n.c<node.c){
                                map[ni][nj].c=node.c;
                            }
                        }
                    }

                    if((time-node.t)%node.c==0){
                        if(1==++node.s){
                            tmpQ.offer(node);
                        }
                    }else{
                        tmpQ.offer(node);
                    }
                }
                queue=tmpQ;
                for(int[] a:arr){
                    queue.offer(map[a[0]][a[1]]);
                }
            }
            sb.append("#").append(tc).append(" ").append(queue.size()).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }

    static class Node{
        int i,j,t,c,s;
        public Node(int i, int j, int c, int t, int s) {
            this.i = i;
            this.j = j;
            this.c = c; //생명력 수치
            this.t = t; //만들어진 시간
            this.s = s; //현재 상태
        }
    }
}
