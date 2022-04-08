package a0316;

import java.util.*;
import java.io.*;

//리팩토링 전 코드
//코드를 줄이기 전이라 설명과 이해하는 부분에 있어 이 코드가 더 적절할 것 같아서 같이 푸시합니당
//번거롭겠지만 두 코드 모두 확인해주세요!
public class Main_bj_13460_comment {
    static int MIN;
    static int[] hole; //구멍 위치 저장
    static char[][] board; //구슬을 표시하지 않은 보드
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        MIN = 11; //10번 이하로 움직이는 경우만 확인하면 되기 때문에 MIN은 11로 초기화
        int[] red=new int[2], blue=new int[2]; //각 구슬의 위치 저장
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

        //사방
        dfs(red, blue, 0, false); //보드를 상하로 움직인다.
        dfs(red, blue, 0, true);  //보드를 좌우로 움직인다.

        if(MIN==11) System.out.println(-1); //11인경우 10번이내로 빨간구슬을 빼낼 수 없으므로 -1 출력
        else System.out.println(MIN);


        br.close();
    }

    /**
     * dfs를 통해 보드를 움직여 빨간구슬이 구멍으로 빠져나오는 지 확인
     *
     * @param red 빨간 구슬 위치
     * @param blue 파란 구슬 위치
     * @param cnt 이전까지 보드를 움직인 횟수
     * @param lr 보드를 움직일 방향 true: 좌우로 움직임, false: 상하로 움직임
     *           보드를 좌우로 움직이면 그 다음 턴에는 좌우로 움직여도 소용이 없을 것이라 생각해서 상하로만 움직이도록 구현
     */
    static void dfs(int[] red,int[] blue, int cnt, boolean lr) {

        //최솟값을 구하는 문제이므로 MIN 이상의 값은 필요없음
        if(MIN<=cnt) return;

        int[] tmp_red, tmp_blue; //보드를 움직일때 구슬이 이동한 위치
        if(lr){ //보드를 좌우로 움직여야하는 경우
            boolean red_left = red[1] < blue[1]; //true: 빨간 구슬이 파란 구슬보다 상대적으로 왼쪽에 위치

            //보드를 좌로 움직이기
            tmp_red=move(red[0],red[1],'l');
            tmp_blue=move(blue[0],blue[1],'l');

            //!(tmp_blue[0]==blue[0]&&tmp_blue[1]==blue[1] && tmp_red[0]==red[0]&&tmp_red[1]==red[1])
            //보드를 움직여도 빨간 구슬과 파란 구슬 2개 모두 움직임이 없을 경우는 넘어감
            //(tmp_blue[0]!=hole[0]||tmp_blue[1]!=hole[1])
            //파란 구슬이 빠져나오는 경우는 빨간 구슬이 빠져나오더라도 실패한 경우이므로 넘어감
            if(!(tmp_blue[0]==blue[0]&&tmp_blue[1]==blue[1] && tmp_red[0]==red[0]&&tmp_red[1]==red[1])
                    &&(tmp_blue[0]!=hole[0]||tmp_blue[1]!=hole[1])){

                if(tmp_red[0]==hole[0]&&tmp_red[1]==hole[1]){ //빨간 구슬이 빠져나온 경우, 성공
                    if(++cnt<MIN)MIN=cnt;
                    return;
                }

                if(tmp_red[0]==tmp_blue[0]&&tmp_red[1]==tmp_blue[1]){
                    //두 구슬이 움직인 결과 같은 칸에 위치한다면, 이전에 행이 같은 상태로 움직였다는 의미이다.
                    if (red_left) { //이전에 레드가 파랑보다 왼쪽에 위치했을 경우
                        tmp_blue[1]++; //파란 구슬의 열값을 1 증가시킨다
                    } else { //이전에 블루가 왼쪽에 위치했을 경우
                        tmp_red[1]++;
                    }
                }
                dfs(tmp_red, tmp_blue, cnt+1, false); //재귀
            }

            //보드를 우로 움직이기
            tmp_red=move(red[0],red[1],'r');
            tmp_blue=move(blue[0],blue[1],'r');

            //보드를 움직여도 빨간 구슬과 파란 구슬 2개 모두 움직임이 없을 경우는 넘어감
            //보드를 좌로 움직였을 경우는 우로도 움직여봐야하기 때문에 그냥 넘어갔지만 이 경우에는 이후에 움직이지 않아도 되므로 함수를 나온다
            if(tmp_blue[0]==blue[0]&&tmp_blue[1]==blue[1] && tmp_red[0]==red[0]&&tmp_red[1]==red[1]) return;

            if(tmp_blue[0]!=hole[0]||tmp_blue[1]!=hole[1]){
                if(tmp_red[0]==hole[0]&&tmp_red[1]==hole[1]){
                    if(++cnt<MIN)MIN=cnt;
                    return;
                }

                if(tmp_red[0]==tmp_blue[0]&&tmp_red[1]==tmp_blue[1]){
                    if (red_left) { //이전에 레드가 왼쪽에 위치했을 경우
                        tmp_red[1]--; //빨간 구슬의 열값을 1 감소시킨다
                    } else {
                        tmp_blue[1]--;
                    }


                }
                dfs(tmp_red, tmp_blue, cnt+1, false);
            }
        }else {//보드를 상하로 움직여야하는 경우
            boolean red_up = red[0] < blue[0]; //true: 빨간 구슬이 파란 구슬보다 상대적으로 위쪽에 위치

            //보드를 상으로 움직이기
            tmp_red = move(red[0], red[1], 'u');
            tmp_blue = move(blue[0], blue[1], 'u');

            if (!(tmp_blue[0]==blue[0]&&tmp_blue[1]==blue[1] && tmp_red[0]==red[0]&&tmp_red[1]==red[1])&&(tmp_blue[0] != hole[0] || tmp_blue[1] != hole[1])) {

                if (tmp_red[0] == hole[0] && tmp_red[1] == hole[1]) {
                    if (++cnt < MIN) MIN = cnt;
                    return;
                }

                if (tmp_red[0] == tmp_blue[0] && tmp_red[1] == tmp_blue[1]) {
                    //두 구슬이 움직인 결과 같은 칸에 위치한다면, 이전에 열이 같은 상태로 움직였다는 의미이다.
                    if (red_up) { //이전에 레드가 파랑보다 위쪽에 위치했을 경우
                        tmp_blue[0]++; //파란 구슬의 행값을 1 증가시킨다
                    } else { //이전에 블루가 위쪽에 위치했을 경우
                        tmp_red[0]++;
                    }
                }
                dfs(tmp_red, tmp_blue, cnt + 1, true); //재귀
            }


            //보드를 하로 움직이기
            tmp_red = move(red[0], red[1], 'd');
            tmp_blue = move(blue[0], blue[1], 'd');

            if(tmp_blue[0]==blue[0]&&tmp_blue[1]==blue[1] && tmp_red[0]==red[0]&&tmp_red[1]==red[1]) return;

            if (tmp_blue[0] != hole[0] || tmp_blue[1] != hole[1]) {
                if (tmp_red[0] == hole[0] && tmp_red[1] == hole[1]) {
                    if (++cnt < MIN) MIN = cnt;
                    return;
                }

                if (tmp_red[0] == tmp_blue[0] && tmp_red[1] == tmp_blue[1]) {
                    if (red_up) { //이전에 레드가 위쪽에 위치했을 경우
                        tmp_red[0]--; //빨간 구슬의 행값을 1 감소시킨다
                    } else {
                        tmp_blue[0]--;
                    }
                }
                dfs(tmp_red, tmp_blue, cnt + 1, true);
            }
        }
    }

    /**
     * 보드가 움직인 방향으로 구슬을 움직였을 때 구슬 위치 반환
     *
     * @param der 보드가 움직인 방향
     */
    static int[] move(int i, int j, char der){
        int tmp_i=i, tmp_j=j;
        switch (der){
            case 'u': //위
                while (true){
                    tmp_i-=1; //행값을 감소시킴
                    if(board[tmp_i][tmp_j]=='.'){
                        continue;
                    }
                    if(board[tmp_i][tmp_j]=='O'){ //구멍에 도착하면 구멍 위치에서 구슬 위치 반환
                        return new int[]{tmp_i, tmp_j};
                    }
                    //이후는 벽인 경우이므로 그 전값을 구슬 위치로 하여 반환
                    return new int[]{tmp_i+1, tmp_j};

                }
            case 'd': //아래
                while (true){
                    tmp_i+=1;
                    if(board[tmp_i][tmp_j]=='.'){
                        continue;
                    }
                    if(board[tmp_i][tmp_j]=='O'){
                        return new int[]{tmp_i, tmp_j};
                    }

                    return new int[]{tmp_i-1, tmp_j};
                }
            case 'l': //왼쪽
                while (true){
                    tmp_j-=1;
                    if(board[tmp_i][tmp_j]=='.'){
                        continue;
                    }
                    if(board[tmp_i][tmp_j]=='O'){
                        return new int[]{tmp_i, tmp_j};
                    }

                    return new int[]{tmp_i, tmp_j+1};
                }
            default://오른쪽
                while (true){
                    tmp_j+=1;
                    if(board[tmp_i][tmp_j]=='.'){
                        continue;
                    }
                    if(board[tmp_i][tmp_j]=='O'){
                        return new int[]{tmp_i, tmp_j};
                    }
                    return new int[]{tmp_i, tmp_j-1};
                }
        }
    }

}
