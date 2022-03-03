package com.company.tutle;

/*
수빈이는 A와 B로만 이루어진 영어 단어가 존재한다는 사실에 놀랐다. 대표적인 예로 AB (Abdominal의 약자), BAA (양의 울음 소리), AA (용암의 종류), ABBA (스웨덴 팝 그룹)이 있다.
이런 사실에 놀란 수빈이는 간단한 게임을 만들기로 했다. 두 문자열 S와 T가 주어졌을 때, S를 T로 바꾸는 게임이다. 문자열을 바꿀 때는 다음과 같은 두 가지 연산만 가능하다.
문자열의 뒤에 A를 추가한다.
문자열을 뒤집고 뒤에 B를 추가한다.
주어진 조건을 이용해서 S를 T로 만들 수 있는지 없는지 알아내는 프로그램을 작성하시오.

입력
첫째 줄에 S가 둘째 줄에 T가 주어진다. (1 ≤ S의 길이 ≤ 999, 2 ≤ T의 길이 ≤ 1000, S의 길이 < T의 길이)

출력
S를 T로 바꿀 수 있으면 1을 없으면 0을 출력한다.

예시
B
ABBA

1

AB
ABB

0
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class bj_12904 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        StringBuilder T = new StringBuilder(br.readLine());
        boolean reverse = T.charAt(T.length() - 1) == 'B'; // 마지막이 B로 끝났을 경우에는 앞에서부터 검사를 해줘야하기 때문
        if (reverse) T.deleteCharAt(T.length() - 1);
        while (T.toString().length() != S.length()) {
            char check = (reverse) ? T.charAt(0) : T.charAt(T.length() - 1);
            if (!reverse) T.deleteCharAt(T.length() - 1);
            else T.deleteCharAt(0);
            reverse = (check == 'B') != reverse;
            // 처음 코드
//            if (!reverse) { // 정방향이면
//                char check = T.charAt(T.length() - 1);
//                if (check == 'B') {
//                    reverse = true;
//                }
//                T.deleteCharAt(T.length() - 1); // 끝에 있는것 지워주기
//            } else { // 역방향이면
//                char check = T.charAt(0);
//                if (check == 'B') {
//                    reverse = false;
//                }
//                T.deleteCharAt(0);
//            }
        }
        if (reverse) {
            StringBuilder newT = new StringBuilder();
            for (int i = 0; i < T.length(); i++) newT.append(T.charAt(T.length() - i - 1));
            T = newT;
        }
        System.out.print(S.equals(T.toString()) ? 1 : 0);
    }
}