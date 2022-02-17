package a0210;

import java.io.*;

public class Main_bj_5430 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_5430.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			String s = br.readLine();
			int len = Integer.parseInt(br.readLine());
			String list_s = br.readLine();
			String[] listArr_s = list_s.substring(1, list_s.length() - 1).split(",");
			boolean isRe = false, error = false;
			int st = 0, en = len;

			exit: for (int i = 0; i < s.length(); i++) {
				switch (s.charAt(i)) {
				case 'R':
					isRe = !isRe;
					break;
				case 'D':
					if (en - st < 1) {
						error = true;
						break exit;
					} else {
						if (isRe)
							en--;
						else
							st++;
					}
					break;
				}
			}
			if (error) {
				sb.append("error\n");
			} else {
				sb.append("[");

				if (isRe)
					for (int i = en-1;st<=i; i--)
						sb.append(listArr_s[i]).append(",");
				else
					for (int i = st; i < en; i++)
						sb.append(listArr_s[i]).append(",");

				if (0 < en - st)
					sb.setLength(sb.length() - 1);
				sb.append("]\n");
			}
		}

		System.out.print(sb.toString());
		br.close();
	}
}