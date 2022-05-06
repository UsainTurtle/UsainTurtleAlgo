package a0502;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main_bj_16719 {

    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();
        visited=new boolean[s.length()];
        check(s,0,s.length()-1);
        System.out.print(sb.toString());
    }

    static void check(String s, int idx, int end){
        char min=s.charAt(idx);
        int len= s.length();
        int tidx=idx;
        //가장 작은거 체크
        for(int i=tidx+1; i<=end; i++){
            if(!visited[i]&&s.charAt(i)<min){
                min=s.charAt(i);
                tidx=i;
            }
        }
        if(!visited[tidx]){
            //문자열 저장
            visited[tidx]=true;
            for(int i=0; i<len; i++){
                if(visited[i])sb.append(s.charAt(i));
            }
            if(++cnt==len) return ;
            sb.append("\n");

        }


        //뒤에 함수 호출
        if(cnt<len&&tidx+1<len) check(s, tidx+1, end);
        //앞에 함수 호출
        if(cnt<len&&idx!=tidx) check(s, idx, tidx-1);
    }
}
