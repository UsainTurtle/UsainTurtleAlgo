package a0312.study;

import java.io.*;
import java.util.*;

/*
로봇을 올리거나 이동시, 내구도1감

조건
1. 벨트가 한칸 회전 (로봇과 함께)
2. 가장 먼저 올라간 로봇부터 회전하는 방향으로 이동, 이동할수 없으면 가만히있는다. 
(이동하려는 칸에 로봇이 없으며, 내구도 1이상)
3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
4. 내구도가 0인 칸의 개수가 k개 이상이면 과정 종료, 아니면 1번으로 반복

종료시 몇번째 단계인지 구하기 

입력
N K
A1...A2n
(2<=N<=100) (1<=K<=2N) (1<=Ai<=1000)

출력
몇번째 단계인지 

 */
public class Main_bj_20055_컨베이어벨트위의로봇 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_20055.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] up = new int[N];
		int[] down = new int[N];
		boolean[] robot = new boolean[N];
		
		st = new StringTokenizer(br.readLine()," ");
		for(int i=0; i<N; i++) up[i]=Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) down[i]=Integer.parseInt(st.nextToken());
		int ans=0;
		
		while(true) {
			
			//1단계 
			set_1(robot,up,down,N);
			//2단계 
			set_2(robot,up,N);
			//3단계 
			if(0<up[0]) {
				robot[0]=true;
				up[0]--;
			}
			//4단계 
			int cnt=0;
			for(int i=0; i<N;i++) {
				if(up[i]==0) cnt++;
				if(down[i]==0) cnt++;
			}
			ans++;
			if(K<=cnt) break;
		}
		
		System.out.println(ans);
		
		br.close();
	}
	static void set_1(boolean[] robot, int[] up, int[] down, int n) {
		int tmp=0;
		boolean tmp2=false;
		tmp=down[n-1];
		tmp2=robot[n-1];
		for(int i=n-2; 0<=i; i--) down[i+1]=down[i];
		for(int i=n-1; 0<=i; i--) {
			if(i==n-1) down[0]=up[i];
			else {
				up[i+1]=up[i];
				robot[i+1]=robot[i];
			}
			if(i==n-2) {	//로봇이 끝에 도달하면 
				if(robot[i+1]) robot[i+1]=false;
			}
		}
		up[0]=tmp;
		robot[0]=tmp2;
	}
	static void set_2(boolean[] robot, int[] up, int n) {
		for(int i=n-2; 0<=i; i--) {
			if(robot[i]) {	//로봇이 있으면 
				if(!robot[i+1] && 0<up[i+1]) {	//다음 위치 로봇이 없을떄 && 내구도1이상  
					robot[i]=false;
					if(i+1!=n-1) robot[i+1]=true;
					up[i+1]--;
				}
			}
		}
	}
}
