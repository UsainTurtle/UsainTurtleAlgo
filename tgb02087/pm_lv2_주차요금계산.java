package a0331.study;

import java.io.*;
import java.util.*;

public class pm_lv2_주차요금계산 {
	static class Car_in {
		String num;
		int intime;

		public Car_in(String num, int intime) {
			this.num = num;
			this.intime = intime;
		}
		@Override
		public String toString() {
			return num+" "+intime;
		}
	}
	static class Car_out{
		String num;
		int outtime;
		
		public Car_out(String num, int outtime) {
			this.num = num;
			this.outtime = outtime;
		}
		@Override
		public String toString() {
			return num+" "+outtime;
		}
	}
	static class Price implements Comparable<Price>{
		int num;
		int price;
		public Price(int num, int price) {
			this.num = num;
			this.price = price;
		}
		@Override
		public String toString() {
			return num+" "+price;
		}
		@Override
		public int compareTo(Price o) {
			return this.num-o.num;
		}
	}

	public static void main(String[] args) throws Exception{
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 주차요금
		int[] fees = new int[] {180, 5000, 10, 600};
		// 차량 입출 기록
		String[] records = new String[] {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
		
		List<Car_in> list_in = new ArrayList<>();
		List<Car_out> list_out = new ArrayList<>();
		List<Price> price = new ArrayList<>();
		
		for(int i=0; i<records.length; i++) {
			StringTokenizer st = new StringTokenizer(records[i]," ,:");
			int t1=Integer.parseInt(st.nextToken());
			int t2=Integer.parseInt(st.nextToken());
			int time=(t1*60)+t2;
			String num=st.nextToken();
			String sta=st.nextToken();
			//차량 IN이면
			if(sta.equals("IN")) {
				list_in.add(new Car_in(num,time));
			}
			else {
				//list_out.add(new Car_out(num,time));
				for(int j=0; j<list_in.size(); j++) {
					// 같은넘버의 차가 나가면
					if(list_in.get(j).num.equals(num)) {
						time=time-list_in.get(j).intime;
						list_out.add(new Car_out(num,time));
						list_in.remove(j);
						break;
					}
				}
			}
		}
		System.out.println(list_in);
		System.out.println(list_out);
		// 모든 출입 후에 in에 남아있는 차는 23:59로 계산
		for(int j=list_in.size()-1; 0<=j; j--) {
			// 같은넘버의 차가 나가면
			int time= ((23*60)+59)-list_in.get(j).intime;
			list_out.add(new Car_out(list_in.get(j).num,time));
			list_in.remove(j);
		}
		System.out.println(list_in);
		System.out.println(list_out);
		
		for(int i=0; i<list_out.size(); i++) {
			String num=list_out.get(i).num;
			int time=list_out.get(i).outtime;
			
			boolean chk=false;
			
			for(int j=list_out.size()-1; i<j; j--) {
				if(list_out.get(j).num.equals(num)) {
					chk=true;
					
					int time2=list_out.get(j).outtime;
					time=time+time2;
					list_out.remove(j);
				}
			}
			if(chk) {
				list_in.add(new Car_in(num,time));
			}
			if(!chk) {
				list_in.add(new Car_in(num,time));
			}
		}
		System.out.println();
		System.out.println(list_in);
		System.out.println(list_out);
		
		int bs_time=fees[0];
		int bs_price=fees[1];
		int d_time=fees[2];
		int d_price=fees[3];
		
		
		for(int i=0; i<list_in.size(); i++) {
			int total=0;
			int time=0;
			//기본 시간보다 초과되면
			if(bs_time<list_in.get(i).intime) {
				time=list_in.get(i).intime-bs_time;
				if(time%d_time!=0) {
					time/=d_time;
					time++;
				}
				else {
					time/=d_time;
				}
				total+=time*d_price;
			}
			total+=bs_price;
			int num=Integer.parseInt(list_in.get(i).num);
		
			price.add(new Price(num,total));
		}
		Collections.sort(price);
		//System.out.println(price);
		int[] answer = new int[price.size()];
		for(int i=0; i<price.size(); i++) {
			answer[i]=price.get(i).price;
		}
		System.out.println(Arrays.toString(answer));
	}
}
