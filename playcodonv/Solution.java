package A0331;

import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) {
		String[] id_list= {"con", "ryan"};
		String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
		int[][] check = new int[id_list.length][id_list.length];
		int k = 3;
		int[] stop = new int[id_list.length];
		int[] answer = new int[id_list.length];
		
		for(int i = 0;i<report.length;i++) {
			StringTokenizer st = new StringTokenizer(report[i]," ");
			String one = st.nextToken();
			String two = st.nextToken();
			//System.out.println(one+ " "+two);	
			int police =  0; //신고한사람 idx
			int thief = 0; //신고 당한사람 idx
			for (int j = 0; j < id_list.length; j++) {
				if(id_list[j].equals(one)) police = j;
				if(id_list[j].equals(two)) thief = j;
			}
			check[police][thief] = 1;
		}
		
		for(int i = 0;i< id_list.length;i++) {
			for(int j = 0;j< id_list.length;j++) {
				if(check[j][i] == 1) stop[i] ++;
			}
		}
		/*for(int i =0;i<stop.length;i++) {
			System.out.print(stop[i]+" ");
		}
		
		System.out.println("");*/
		
		
		for(int i = 0;i<id_list.length;i++) {
			for(int j = 0;j<id_list.length;j++) {
				if(stop[j] >= k && check[i][j] == 1) {
					answer[i]++;
				}
			}
		}
		/*for(int i =0;i<stop.length;i++) {
			System.out.print(answer[i]+" ");
		}*/
		
	}

}
