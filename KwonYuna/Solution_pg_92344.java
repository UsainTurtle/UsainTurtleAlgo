package a0414;

public class Solution_pg_92344 {
    public static void main(String[] args) throws Exception{
//        int[][] board={{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
//        int[][] skill={{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};
        //10

        int[][] board={{1,2,3},{4,5,6},{7,8,9}};
        int[][] board1={{1,2,3},{4,5,6},{7,8,9}};
        int[][] skill={{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}};
//        //6

        System.out.println(solution(board,skill));
        System.out.println(solution_timeout(board1,skill));
    }

   //type, r1, c1, r2, c2, degree
    public static int solution_timeout(int[][] board, int[][] skill) {

        for(int sk=0, sksize=skill.length; sk<sksize ; sk++){
            int r1 = skill[sk][1];
            int c1 = skill[sk][2];
            int r2 = skill[sk][3];
            int c2 = skill[sk][4];
            int degree = skill[sk][5];
            if(skill[sk][0]==1) degree*=-1;

            for(;r1<=r2;r1++){
                for(int c=c1 ;c<=c2;c++) {
                    board[r1][c]+=degree;
                }
            }
        }

        int answer = 0;
        for(int r=0, rsize=board.length;r<rsize;r++){
            for(int c=0, csize=board[0].length;c<csize;c++){
                if(0<board[r][c]) answer++;
            }
        }
        return answer;
    }

    /*누적합*/
    public static int solution(int[][] board, int[][] skill) {
        int[][] map = new int[board.length+1][board[0].length+1];

        for(int sk=0, sksize=skill.length; sk<sksize ; sk++){
            int r1 = skill[sk][1];
            int c1 = skill[sk][2];
            int r2 = skill[sk][3];
            int c2 = skill[sk][4];
            int degree = skill[sk][5];
            if(skill[sk][0]==1) degree*=-1;

            map[r1][c1]+=degree;
            map[r2+1][c2+1]+=degree;
            map[r1][c2+1]+=degree*-1;
            map[r2+1][c1]+=degree*-1;
        }

        int answer = 0;


        for(int r=1, rsize=board.length;r<rsize;r++){
            for (int c = 0, csize = board[0].length; c < csize; c++) {
                map[r][c] += map[r - 1][c];
            }
        }
        for(int r=0, rsize=board.length;r<rsize;r++) {
            for (int c = 1, csize = board[0].length; c < csize; c++) {
                map[r][c] += map[r][c - 1];
            }
        }

        for(int r=0, rsize=board.length;r<rsize;r++) {
            for (int c = 0, csize = board[0].length; c < csize; c++) {
                if (0 < board[r][c]+map[r][c]) answer++;
            }
        }

        return answer;
    }

}