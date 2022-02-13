package com.company.tutle;

/*
선영이는 주말에 할 일이 없어서 새로운 언어 AC를 만들었다. AC는 정수 배열에 연산을 하기 위해 만든 언어이다. 이 언어에는 두 가지 함수 R(뒤집기)과 D(버리기)가 있다.
함수 R은 배열에 있는 수의 순서를 뒤집는 함수이고, D는 첫 번째 수를 버리는 함수이다. 배열이 비어있는데 D를 사용한 경우에는 에러가 발생한다.
함수는 조합해서 한 번에 사용할 수 있다. 예를 들어, "AB"는 A를 수행한 다음에 바로 이어서 B를 수행하는 함수이다. 예를 들어, "RDD"는 배열을 뒤집은 다음 처음 두 수를 버리는 함수이다.
배열의 초기값과 수행할 함수가 주어졌을 때, 최종 결과를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 테스트 케이스의 개수 T가 주어진다. T는 최대 100이다.
각 테스트 케이스의 첫째 줄에는 수행할 함수 p가 주어진다. p의 길이는 1보다 크거나 같고, 100,000보다 작거나 같다.
다음 줄에는 배열에 들어있는 수의 개수 n이 주어진다. (0 ≤ n ≤ 100,000)
다음 줄에는 [x1,...,xn]과 같은 형태로 배열에 들어있는 정수가 주어진다. (1 ≤ xi ≤ 100)
전체 테스트 케이스에 주어지는 p의 길이의 합과 n의 합은 70만을 넘지 않는다.

출력
각 테스트 케이스에 대해서, 입력으로 주어진 정수 배열에 함수를 수행한 결과를 출력한다. 만약, 에러가 발생한 경우에는 error를 출력한다.

입력 예시
4
RDD
4
[1,2,3,4]
DD
1
[42]
RRD
6
[1,1,2,3,5,8]
D
0
[]

출력 예시
[2,1]
error
[1,2,3,5,8]
error

// D의 갯수가 배열의 길이를 초과하면 error
// R이 들어올 때마다 Flag값을 바꿔주면 될듯
//
//
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class bj_5430 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            boolean checkPre = true;
            boolean reverse = false;
            String command = br.readLine();
            int len = Integer.parseInt(br.readLine());
            StringBuilder numbers = new StringBuilder(br.readLine());
            StringBuilder result = new StringBuilder("[");
            numbers.deleteCharAt(0);
            numbers.deleteCharAt(numbers.length() - 1);
            StringTokenizer st = new StringTokenizer(numbers.toString(), ",");
            ArrayDeque<Integer> arr = new ArrayDeque<>();

            for (int i = 0; i < len; i++) { // deque으로 초기화
                arr.offer(Integer.parseInt(st.nextToken()));
            }
            int cntD = countD(command);
            if (cntD > arr.size()) { // D가 arr의 갯수보다 크거나 같을 때
                System.out.println("error");
                continue;
            }

            for (int c = 0; c < command.length(); c++) {
                if (command.charAt(c) == 'R') {
                    checkPre = !checkPre;
                    continue;
                }
                if (checkPre) { //앞에를 빼줄 때
                    arr.pollFirst();
                } else { //뒤를 빼줘야 할 때
                    arr.pollLast();
                }
            }
            if ((command.length() - cntD) % 2 == 1) { // R의 갯수가 홀수일 때 뒤집기
                reverse = true;
            }
            int dequeSize = arr.size();
            for (int i = 0; i < dequeSize; i++) {
                if (reverse) { // 뒤집힌 상황
                    result.append(arr.pollLast()).append(",");
                } else {
                    result.append(arr.pollFirst()).append(",");
                }
            }
            if (dequeSize == 0) {
                result.append("]");
            } else {
                result.deleteCharAt(result.length() - 1).append("]");
            }
            System.out.println(result);
        }
    }

    static int countD(String check) {
        int cnt = 0;
        for (int i = 0; i < check.length(); i++) {
            if (check.charAt(i) == 'D') {
                cnt++;
            }
        }
        return cnt;
    }
}
