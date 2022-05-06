package a0301.study;

import java.io.*;
import java.util.*;

/*

조건
1. 학생들이 추천을 시작하기 전 모든 사진틀은 비어있다.
2. 어떤 학생이 특정 학생을 추천하면, 추천받은 학생의  사진이 반드시 사진틀에 게시되어야 한다.
3. 비어있는 사진틀이 없는경우, 추천받은 횟수가 가장 적은 학생을 삭제,후 게시
(이 학생이 2명 이상인경우 가장 오래된 학생을 삭제)
4. 현재 사진이 게시된 학생이 다른 학생의 추천을 받은 경우에는 추천받은 횟수만 증가
5. 사진이 삭제되는 경우 추천받은 횟수는 0

입력
사진틀 개수 N (1<=N<=20)
전체 학생의 총 추천 횟수 (1000이하)
추천받은 학생 번호 순서 (1~100)

출력
최종 후보의 학생 번호
*/

public class Main_bj_1713_후보추천하기 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1713.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] memo = new int[101];
		List<Integer> pic = new ArrayList<>();
		int C = Integer.parseInt(br.readLine());
		int[] arr = new int[C];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=0; i<C; i++) arr[i]=Integer.parseInt(st.nextToken());
		//System.out.println(Arrays.toString(arr));
		for(int i=0; i<C; i++) {
			int n=arr[i];
			boolean chk=false;
			for(int j=0; j<pic.size(); j++) {
				//사진첩에 있으면 추천만 증가
				if(pic.get(j)==n) {
					memo[n]++;
					chk=true;
					break;
				}
				//사진첩에 없으면 사진첩에 추가, 추천증가
			}
			if(!chk) {
				//사진첩이 꽉찼으면
				if(pic.size()==N) {
					int min=Integer.MAX_VALUE;
					int idx=0;
					for(int k=0; k<pic.size(); k++) {
						//추천수가 가작 적을때 갱신
						if(memo[pic.get(k)]<min) {
							min=memo[pic.get(k)];
							idx=k;
						}
						//추천수가 같을때 갱신
						else if(memo[pic.get(k)]==min){
							if(k<idx) {	//더 오래된사진 갱신
								min=memo[pic.get(k)];
								idx=k;
							}
						}
					}
					memo[pic.get(idx)]=0;
					pic.remove(idx);
				}
				pic.add(n);
				memo[n]++;
			}
		}
		Collections.sort(pic);
		for(int i=0; i<pic.size(); i++) sb.append(pic.get(i)).append(" ");
		System.out.println(sb);
		br.close();
	}

}
