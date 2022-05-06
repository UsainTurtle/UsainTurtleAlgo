package a0331.study;

import java.io.*;
import java.util.*;

/*
- 각유저는 한번에 한명의 유저 신고
- 신고횟수 제한은 없다. 서로 다른 유저를 계속 신고할수 있다.
- 한유저를 여러번 신고할수 있지만, 횟수는 1회로 처리
- k번 이상 신고된 유저는 이용 정지, 해당 유저를 신고한 모든 유저에게 정지 사실을 메일로 발송
- 유저가 신고한 모든 내용을 취합하여 마지막에 한꺼번에 게시판 이용 정지를 시키면서 정지 메일을 발송



 */
public class pm_lv1_신고결과받기 {
	// 신고 받은 횟수
	static class User{
		String name;
		int cnt;
		
		public User(String name, int cnt) {
			this.name = name;
			this.cnt = cnt;
		}
		@Override
		public String toString() {
			return name+" "+cnt;
		}
	}
	// 유저ID, 신고한ID, 정지된ID
	static class UserID{
		String id;
		List<String> rpid = new ArrayList<>();
		List<String> stopid = new ArrayList<>();
		
		public UserID(String id, String rpid, String stopid) {
			this.id = id;
			this.rpid.add(rpid);
			this.stopid.add(stopid);
		}
		public UserID(String id) {
			this.id=id;
		}
		@Override
		public String toString() {
			return id+" "+rpid+" "+stopid;
		}
		
	}

	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/input_bj_1463.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] id_list= new String[]{"con", "ryan"};
		String[] report = new String[] {"ryan con", "ryan con", "ryan con", "ryan con"};
		int k=3;
		User[] user = new User[id_list.length];
		UserID[] userid = new UserID[id_list.length];
		
		for(int i=0; i<id_list.length; i++) {
			user[i]=new User(id_list[i],0);
			userid[i]=new UserID(id_list[i]);
		}
		
		for(int i=0; i<report.length; i++) {
			StringTokenizer st = new StringTokenizer(report[i]," ");
			String a = st.nextToken();
			String b = st.nextToken();
			for(int j=0; j<userid.length; j++) {
				if(userid[j].id.equals(a)) {
					// 중복된 신고ID가 있을시 1번만 신고
					boolean chk=false;
					for(int q=0; q<userid[j].rpid.size(); q++) {
						if(userid[j].rpid.get(q).equals(b)) {
							chk=true;
						}
					}
					if(!chk) {
						userid[j].rpid.add(b);
						for(int d=0; d<user.length; d++) {
							if(user[d].name.equals(b)) {
								user[d].cnt++;
								break;
							}
						}
					}
					break;
				}
			}
		}
		System.out.println(Arrays.toString(user));
		System.out.println(Arrays.toString(userid));
		List<String> rpname = new ArrayList<>();
		//신고 당한 사람추가

		for(int i=0; i<user.length; i++) {
			if(k<=user[i].cnt) {
				rpname.add(user[i].name);
				for(int j=0; j<userid.length; j++) {
					for(int d=0; d<userid[j].rpid.size(); d++) {
						if(userid[j].rpid.get(d).equals(user[i].name)) {
							userid[j].stopid.add(user[i].name);
						}
					}
					
				}
			}
		}
		int[] answer = new int[user.length];
		for(int i=0; i<userid.length; i++) {
			answer[i]=userid[i].stopid.size();
		}
		System.out.println(Arrays.toString(userid));
		System.out.println(Arrays.toString(answer));
		
	}

}
