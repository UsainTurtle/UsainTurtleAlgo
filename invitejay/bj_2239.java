package com.company.tutle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*


103000509
002109400
000704000
300502006
060000050
700803004
000401000
009205800
804000107
 */

// 같은 영역에 있는 것들은 어떻게 봐줄 수 있을까
//
public class bj_2239 {
    static int[][] graph = new int[9][9];
    static boolean check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            String input = br.readLine();
            for (int j = 0; j < 9; j++) {
                int current = input.charAt(j) - '0';
                graph[i][j] = current;
            }
        }

        solution(0, 0);
    }

    static void solution(int x, int y) {
        if (y == 8 && x == 8 && graph[y][x] != 0) { // 끝까지 도달한 경우
            printBest();
            check = true;
            return;
        } else if (y == 8 && x == 8) { // 마지막 칸이 안채워져 있음
            for (int i = 1; i < 10; i++) {
                if (checkSpace(x, y, i) || checkRow(y, i) || checkCol(x, i)) { //스도쿠 조건에 걸리면 다음숫자
                    continue;
                }
                graph[y][x] = i;
            }
            printBest();
            check = true;
            return;
        }
        if (graph[y][x] != 0) { // 해당 칸이 이미 채워져있을 경우
            if (x < 8) {
                solution(x + 1, y);
            } else {
                solution(0, y + 1);
            }
        } else {
            for (int i = 1; i < 10; i++) { // 넣어줄 숫자 리스트
                if (checkSpace(x, y, i) || checkRow(y, i) || checkCol(x, i)) { //스도쿠 조건에 걸리면 다음숫자
                    continue;
                }
                graph[y][x] = i; // 해당 칸에 숫자 입력
                if (x < 8) {
                    solution(x + 1, y);
                } else { // 열이 마지막 칸이면 다음 행으로 이동
                    solution(0, y + 1);
                }
                if (check) {
                    return;
                }
                graph[y][x] = 0; // 해당 숫자는 틀린 경우 였음
            }
        }
    }

    private static void printBest() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(graph[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    static boolean checkSpace(int x, int y, int num) {
        int ny = y / 3 * 3; // 몇번째 행부터
        int nx = x / 3 * 3; // 몇번째 열부터
        for (int i = ny; i < ny + 3; i++) {
            for (int j = nx; j < nx + 3; j++) {
                if (graph[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean checkRow(int y, int num) {
        for (int i = 0; i < 9; i++) {
            if (graph[y][i] == num) {
                return true;
            }
        }
        return false;
    }

    static boolean checkCol(int x, int num) {
        for (int i = 0; i < 9; i++) {
            if (graph[i][x] == num) {
                return true;
            }
        }
        return false;
    }
}
