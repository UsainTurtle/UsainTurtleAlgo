import java.util.*;
import java.io.*;

public class Solution_bj_20055_컨베이어벨트위의로봇 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_20055.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken()); //종료 조건
		LinkedList<Integer> belt = new LinkedList<>(); 
		int[] robot = new int[n];
		int res = 0;
		st = new StringTokenizer(br.readLine()," ");
		for(int i = 0;i<n*2;i++) {
			belt.add(Integer.parseInt(st.nextToken()));
		}
		while(true) {
			++res;
			//1.벨트 회전
			int temp = belt.remove(belt.size()-1);
			belt.add(0, temp);
			//로봇들도 같이 회전
			for(int i = n-1 ; i > 0 ; i--) robot[i] = robot[i-1];
			robot[0] = 0;
			robot[n-1] = 0; //문제의 조건에 충실하도록 하자
			//2.가장 먼저 벨트에 올라간 로봇부터  //역순탐색
			for(int i = n-2 ; i > 0 ; i--) {
				if(robot[i] == 1 && belt.get(i+1) != 0 && robot[i+1] == 0) { //현재칸 로봇, 다음칸 내구도 0아님, 다음칸 빈칸
					robot[i] = 0; robot[i+1] = 1; //로봇 이동
					belt.set(i+1, belt.get(i+1)-1); //내구도 1감소
				}
			}
			robot[n-1] = 0;
			if(belt.get(0) != 0) {
				robot[0] = 1; //내구도가 0이 아니라면 로봇을 올리고
				belt.set(0, belt.get(0)-1); //내구도 1감소
			}
			
			//종료 조건
			int zero = 0;
			for(int x : belt) if(x == 0) zero++;
			if(zero >= k) break;
		}
		
		System.out.println(res);
		br.close();
	}
	

}
