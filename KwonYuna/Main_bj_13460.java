package a0316;

import java.io.*;
import java.util.*;

public class Main_bj_13460 {
    static int MIN;
    static int[] hole;
    static char[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        MIN = 11;
        int[] red=new int[2], blue=new int[2];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == '#' || board[i][j] == '.') continue;
                if (board[i][j] == 'R') {
                    board[i][j] = '.';
                    red=new int[]{i,j};
                } else if (board[i][j] == 'B') {
                    board[i][j] = '.';
                    blue=new int[]{i,j};
                } else {
                    hole=new int[]{i,j};
                }
            }
        }

        dfs(red, blue, 0, false);
        dfs(red, blue, 0, true);

        if(MIN==11) System.out.println(-1);
        else System.out.println(MIN);


        br.close();
    }

    static void dfs(int[] red,int[] blue, int cnt, boolean lr) {

        int[] tmp_red, tmp_blue;
        //보드를 좌우로 움직일 경우는 구슬들의 행이 같은지, 상하로 움직일 경우는 구슬들의 열이 같은지 확인
        boolean red_position = lr? red[1] < blue[1]: red[0] < blue[0];
        //상좌하우로 사방탐색 배열을 설정하여 상,하(0,2) 좌,우(1,3)로 방향표시를 함
        //상하 값 +1이면 좌우 값이라 이를 매번 체크하지않기 위해 num 변수 생성
        int num=lr?1:0;

        tmp_red=move(red[0],red[1],0+num); //상:0, 좌:1
        tmp_blue=move(blue[0],blue[1],0+num);

        if(!(tmp_blue[0]==blue[0]&&tmp_blue[1]==blue[1] && tmp_red[0]==red[0]&&tmp_red[1]==red[1])&&(tmp_blue[0]!=hole[0]||tmp_blue[1]!=hole[1])){
            if(tmp_red[0]==hole[0]&&tmp_red[1]==hole[1]){
                if(++cnt<MIN)MIN=cnt;
                return;
            }

            if(tmp_red[0]==tmp_blue[0]&&tmp_red[1]==tmp_blue[1]){ //상하: 두 구슬 열 값이 같다 , 좌우: 두 구슬 행 값이 같다
                if (red_position) { //상하: 이전 위치에서 빨간 구슬이 더 위쪽, 좌우: 이전 위치에서 빨간 구슬이 더 왼쪽
                    tmp_blue[num]++; //상하:0(행) , 좌우:1(열)
                } else {
                    tmp_red[num]++;
                }
            }
            if(cnt+1<MIN) dfs(tmp_red, tmp_blue, cnt+1, !lr);
        }

        tmp_red=move(red[0],red[1],2+num); //하:2, 우:3
        tmp_blue=move(blue[0],blue[1],2+num);

        if(tmp_blue[0]==blue[0]&&tmp_blue[1]==blue[1] && tmp_red[0]==red[0]&&tmp_red[1]==red[1]) return;
        if(tmp_blue[0]!=hole[0]||tmp_blue[1]!=hole[1]){
            if(tmp_red[0]==hole[0]&&tmp_red[1]==hole[1]){
                if(++cnt<MIN)MIN=cnt;
                return;
            }

            if(tmp_red[0]==tmp_blue[0]&&tmp_red[1]==tmp_blue[1]){
                if (red_position) {
                    tmp_red[num]--;
                } else {
                    tmp_blue[num]--;
                }
            }
            if(cnt+1<MIN)dfs(tmp_red, tmp_blue, cnt+1, !lr);
        }
    }

    static int[] move(int i, int j, int der){
        int[] di={-1,0,1,0},dj={0,-1,0,1};
        while (true) {
            i += di[der];
            j += dj[der];
            if (board[i][j] == '.') continue;
            if (board[i][j] == 'O') return new int[]{i, j};
            return new int[]{i - di[der], j - dj[der]}; //벽일 경우 직전위치로 구슬위치 반환
        }
    }

}
