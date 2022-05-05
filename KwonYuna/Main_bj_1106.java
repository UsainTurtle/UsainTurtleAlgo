package a0214;

import java.io.*;
import java.util.*;

/*DP*/
public class Main_bj_1106 {
     static int[] hotel,cost,people;
     static int N,C;
        public static void main(String[] args) throws Exception{
            BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            C=Integer.parseInt(st.nextToken());
            N=Integer.parseInt(st.nextToken());

            hotel = new int[C+1];
            cost = new int[N];
            people = new int[N];
            for(int i=0; i<N ;i++){
                st = new StringTokenizer(br.readLine()," ");
                cost[i]=Integer.parseInt(st.nextToken()); //비용
                people[i]=Integer.parseInt(st.nextToken()); //고객수
            }
            Arrays.fill(hotel,-1);
            System.out.print(getCost(C));
            br.close();
        }

        static int getCost(int num){
            if(num<=0) return 0;
            if(0<=hotel[num]) return hotel[num];

            int result = Integer.MAX_VALUE;
            for(int i=0; i<N ;i++){
                int p=people[i];
                int c=cost[i];
                result = Math.min(result, getCost(num-p)+c);
            }
            return hotel[num]=result;
        }

    }