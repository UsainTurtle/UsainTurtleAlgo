package a0304;

import java.io.*;
import java.util.*;

public class Main_bj_14889 {

	static int arr[][], N, MIN; //능력치 배열
	static boolean team[];      //N/2명씩 팀 나누기

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		MIN = Integer.MAX_VALUE;
		arr = new int[N][N];
		team = new boolean[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int j = 0;
			//(j<i) S_ij와 S_ji 능력치를 합한 후 arr[i][j]에 저장 
			for (; j < i; j++) 
				arr[i][j] = Integer.parseInt(st.nextToken()) + arr[j][i];
			//(j>i)  S_ji값을 모르기 때문에 우선 arr[i][j]에 저장 
			for (; j < N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		perm(0, 0);
		System.out.print(MIN);
		br.close();
	}

	static void perm(int cnt, int st) {
		if (cnt == N/2) { //팀 구성 완료
			int score_s = 0, score_l = 0; //능력치 합

			//앞번호부터 같은 팀에서 자신보다 뒷번호인 사람과의 능력치 합을 구해서 각 팀에 더함
			for (int i = 0, M = N - 1; i < M; i++) {
				for (int j = i + 1; j < N; j++) {
					//i번 사람과 j번 사람이 다른 팀일 경우
					if (team[i] != team[j]) 
						continue;

					//i번 사람이 true이면 start팀, false이면 link팀이다
					if (team[i])
						score_s += arr[j][i];
					else
						score_l += arr[j][i];
				}
			}
			//능력치 차이가 최소인 경우를 구함
			if (Math.abs(score_s - score_l) < MIN)
				MIN = Math.abs(score_s - score_l);
			return;
		}
		
		//4명이 있을 경우 
		//start(2,3) link(1,4)와 start(1,4) link(2,3)로 
		//팀이 나뉜 결과는 같기 때문에 중복을 피해서 팀을 나눔
		//
		//N명의 사람이 있을 때 N/2명씩 팀이 되어야 한다.
		//아래는 번호 순으로 팀원을 정렬할 때
		//각 인덱스 별로 올 수 있는 최대 번호이다.
		//(N/2,...,N-2,N-1)
		//그래서 M을 아래와 같이 설정함
		
		for (int i = st, M = N/2 + cnt; i < M; i++) {
			team[i] = true; //start팀에 i번 사람이 들어감
			perm(cnt + 1, i + 1);
			team[i] = false; //link팀에 i번 사람이 들어감
		}
	}

}
