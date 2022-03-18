package a0317.study;

import java.io.*;
import java.util.*;
public class Main_bj_16235_나무재테크 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_16235.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int[] di= {-1,-1,0,1,1,1,0,-1};	//상 우상 우 우하 하 좌하 좌 좌상
		int[] dj= {0,1,1,1,0,-1,-1,-1};
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		int K=Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		int[][] S2D2 = new int[N][N];
		List<Integer>[][] tree= new ArrayList[N][N];
		for(int i=0; i<N; i++) for(int j=0; j<N; j++) tree[i][j]=new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
				map[i][j]=5;
				S2D2[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<M;i++) {
			st = new StringTokenizer(br.readLine()," ");
			int x=Integer.parseInt(st.nextToken())-1;
			int y=Integer.parseInt(st.nextToken())-1;
			int k=Integer.parseInt(st.nextToken());
			tree[x][y].add(k);
			//System.out.println(tree[x][y].size());
		}
		int cnt=0;
		//K년 반복
		for(int q=0; q<K; q++) {
			//봄
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// 나무가 있다면
					if(tree[i][j]!=null) {
						for(int d=0; d<tree[i][j].size(); d++) {	//나이가 제일 어린나무부터	
							map[i][j]-=tree[i][j].get(d);
							//뺀값이 양수라면 양분충분
							if(0<=map[i][j]) {
								tree[i][j].set(d, tree[i][j].get(d)+1);
							}
							//음수라면 죽은나무
							else {
								map[i][j]+=tree[i][j].get(d);
								tree[i][j].set(d, tree[i][j].get(d)*-1);
							}
							
						}
						//여름
						for(int d=tree[i][j].size()-1; 0<=d; d--) {
							// 죽은 나무들의 나이/2만큼 양분 추가
							if(tree[i][j].get(d)<0) {
								map[i][j]+=(tree[i][j].get(d)*-1)/2;
								tree[i][j].remove(d);
							}
						}
					}
				}
			}
			//가을
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(tree[i][j]!=null) {
						for(int d=0; d<tree[i][j].size(); d++) {
							//나무들의 나이가 5의 배수이면 번식
							if(tree[i][j].get(d)%5==0) {
								for(int a=0;a<8;a++) {
									int ni=i+di[a];
									int nj=j+dj[a];
									if(0<=ni && ni<N && 0<=nj && nj<N) {
										tree[ni][nj].add(0,1);	//나이1인 나무추가
									}
								}
							}
						}
					}
				}
			}
			//겨울
			//로봇이 양분추가
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j]+=S2D2[i][j];
				}
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(tree[i][j]!=null) {
					cnt+=tree[i][j].size();
				}
			}
		}
		System.out.println(cnt);
		
		br.close();
	}
}