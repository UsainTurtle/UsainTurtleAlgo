package study.a0502;
import java.io.*;
import java.util.*;
public class Main_bj_4889_안정적인문자열 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 0;
        while (true) {
            String s = br.readLine();
            if (s.contains("-")) break;
            Stack<Character> stk = new Stack<>();
            int cnt = 0;

            for (int i = 0; i < s.length(); i++) {
                if (stk.isEmpty() && s.charAt(i) == '}') { // stack이 비어있거나, '{'인 경우
                    cnt++;
                    char ch = '{';
                    stk.push(ch);
                } else if (!stk.isEmpty() && s.charAt(i) == '}') {
                    stk.pop();
                } else stk.push(s.charAt(i));
            }
            //System.out.println("남은 stack 크기: " + stk.size());
            //System.out.println("cnt 수: " + cnt);
            cnt = cnt + stk.size()/2;
            System.out.println(++num + ". " + cnt);
        }
    }
}
/*
}{
{}{}{}
{{{}
---
 */