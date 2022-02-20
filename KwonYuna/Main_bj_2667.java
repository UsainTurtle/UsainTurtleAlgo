import java.io.*;
import java.util.*;

public class Main_bj_2667 {
	final static int[] di= {-1,0,1,0};
	final static int[] dj= {0,1,0,-1};
	static char[][] arr;
	static boolean[][] v;
	static int N,C;
//	static ArrayDeque<Integer> queue;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new char[N][];
		v = new boolean[N][N];
//		queue = new ArrayDeque<>();
		LinkedList<Integer> arr_num=new LinkedList<>();
		for(int i=0;i<N;i++) arr[i]=br.readLine().toCharArray();
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(arr[i][j]=='1'&&!v[i][j]) {
					C=0;
					v[i][j]=true;
					dfs(i,j);
//					queue.offer(i);
//					queue.offer(j);
//					bfs();
					arr_num.push(C);
				}
			}
		}
		arr_num.sort(null);
		System.out.println(arr_num.size());
		while(!arr_num.isEmpty()) {
			System.out.println(arr_num.pop());
		}
		br.close();
	}
	
	static void dfs(int i, int j) {
		C++;
		for(int z=0;z<4;z++) {
			int ni=i+di[z];
			int nj=j+dj[z];
			if(0<=ni&&ni<N&&0<=nj&&nj<N&&arr[ni][nj]=='1'&&!v[ni][nj]) {
				v[ni][nj]=true;
				dfs(ni,nj);
			}
		}
	}
	
//	static void bfs() {
//		while (!queue.isEmpty()) {
//			int i = queue.poll(), j = queue.poll();
//			C++;
//			for (int z = 0; z < 4; z++) {
//				int ni = i + di[z];
//				int nj = j + dj[z];
//				if (0 <= ni && ni < N && 0 <= nj && nj < N && arr[ni][nj] == '1' && !v[ni][nj]) {
//					v[ni][nj] = true;
//					queue.offer(ni);
//					queue.offer(nj);
//				}
//			}
//		}
//	}
}
