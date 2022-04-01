package a0331;

import java.util.StringTokenizer;

public class Solution_pg_92335 {
    public static void main(String[] args) {

        int n=437674;
        int k=3;
        ////result=3

//        int n=110011;
//        int k=10;
//        ////result=2

        int answer=0;
        StringTokenizer st = new StringTokenizer(changeNum(n,k),"0");
        while(st.hasMoreTokens()){
            long num = Long.parseLong(st.nextToken());
            if(1<num && (num==2 || num==3 || (num%2!=0 && check(num)))) answer++;
        }
        System.out.println(answer);
    }

    static String changeNum(int n, int k){
        StringBuilder sb = new StringBuilder();
        while(0<n){
            sb.insert(0,n%k);
            n/=k;
        }
        return sb.toString();
    }

    static boolean check(long n) {
        long sqrtn = (long)Math.sqrt(n);
        for(int i=3; i<=sqrtn; i+=2){
            if(n%i==0) return false;
        }
        return true;
    }

}
