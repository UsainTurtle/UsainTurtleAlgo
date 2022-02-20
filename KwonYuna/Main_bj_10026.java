package a0221;
import java.io.*;

public class Main_bj_10026 {
	final static int[] di= {-1,0,1,0};
	final static int[] dj= {0,1,0,-1};
	
	static char[][] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new char[N][];
		boolean[][] r_v = new boolean[N][N];
		boolean[][] rg_v = new boolean[N][N];
		int RG = 0, R = 0;
		for(int i=0;i<N;i++) arr[i]=br.readLine().toCharArray();
		
		for(int i=0;i<N;i++) {

			for(int j=0;j<N;j++) {
				if(!r_v[i][j]) {
					R++;
					r_v[i][j]=true;
					dfs(i,j,arr[i][j],r_v,false);
				}
				if(!rg_v[i][j]) {
					RG++;
					rg_v[i][j]=true;
					dfs(i,j,arr[i][j],rg_v,true);
				}
			}
		}
		/*
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!r_v[i][j]) {
					R++;
					r_v[i][j] = true;
					if (arr[i][j] == 'B') {
						RG++;
						rg_v[i][j] = true;
						dfs(i, j, arr[i][j], r_v, rg_v, false);
					} else {
						dfs(i, j, arr[i][j], r_v, null, false);
						if (!rg_v[i][j]) {
							RG++;
							rg_v[i][j] = true;
							dfs(i, j, arr[i][j], rg_v, null, true);
						}
					}
				}
			}
		}
		*/
		System.out.println(R+" "+RG);
		br.close();
	}
	
	static void dfs(int i, int j,char al,boolean[][] v,boolean rg_check) {
		for(int z=0, N=arr.length;z<4;z++) {
			int ni=i+di[z];
			int nj=j+dj[z];
			if(0<=ni&&ni<N&&0<=nj&&nj<N) {
				if((arr[ni][nj]==al||(rg_check&&((al=='G'&&arr[ni][nj]=='R')||(al=='R'&&arr[ni][nj]=='G'))))
						&&!v[ni][nj]) {
						v[ni][nj]=true;
						dfs(ni,nj,al,v,rg_check);
					}
			}			
		}
	}
	/*
	static void dfs(int i, int j, char al, boolean[][] v, boolean[][] b_v, boolean rg_check) {
		for (int z = 0,N=arr.length; z < 4; z++) {
			int ni = i + di[z];
			int nj = j + dj[z];
			if (0 <= ni && ni < N && 0 <= nj && nj < N) {
				if ((arr[ni][nj] == al
						|| (rg_check && ((al == 'G' && arr[ni][nj] == 'R') || (al == 'R' && arr[ni][nj] == 'G'))))
						&& !v[ni][nj]) {
					v[ni][nj] = true;
					if (b_v != null) b_v[ni][nj] = true;
					dfs(ni, nj, al, v, b_v, rg_check);
				}
			}
		}
	}
	*/
}
