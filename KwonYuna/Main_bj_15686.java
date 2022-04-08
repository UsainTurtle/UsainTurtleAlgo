import java.io.*;
import java.util.*;

public class Main_bj_15686 {
	static int MIN,M;
	static boolean[] v;
	static int[][] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MIN = Integer.MAX_VALUE;
		List<int[]> house = new ArrayList<int[]>();
		List<int[]> chicken = new ArrayList<int[]>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp == 1) house.add(new int[] { i, j });
				else if (tmp == 2) chicken.add(new int[] { i, j });
			}
		}
		v = new boolean[chicken.size()];
		arr = new int[chicken.size()][house.size()];
		for (int i = 0, cs=chicken.size(), hs=house.size(); i < cs; i++) {
			for (int j = 0; j < hs; j++) {
				arr[i][j]=Math.abs(chicken.get(i)[0] - house.get(j)[0])
						+ Math.abs(chicken.get(i)[1] - house.get(j)[1]);
			}
		}

		comb(0, 0, chicken.size(), house.size());

		System.out.print(MIN);
		br.close();
	}

	static void comb(int cnt, int st, int cs, int hs) {

		if (cnt == M) {
			int sum = 0;
			int[] arr_min = new int[hs];
			for (int i = 0; i < cs; i++) {
				if (!v[i])
					continue;
				for (int j = 0; j < hs; j++) {
					if (arr_min[j] == 0 || arr[i][j] < arr_min[j]) {
						sum = sum - arr_min[j] + arr[i][j];
						arr_min[j] = arr[i][j];
					}
				}
			}
			if (sum < MIN) MIN = sum;
			return;
		}

		for (int i = st; i < cs; i++) {
			if (!v[i]) {
				v[i] = true;
				comb(cnt + 1, i,cs,hs);
				v[i] = false;
			}
		}
	}
}