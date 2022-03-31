package A0331;

import java.util.*;
import java.io.*;

public class Solution2 {
	

	public static void main(String[] args) throws Exception{
		int n = 1000000;
		int k = 3;
		String change="";
		int answer = 0;
		//진법변환
			
			change = Integer.toString(n, k);
		//	change = "";
			change += '0';
			
			String sector = "";
			for(int i = 0; i<change.length();i++) {
				if(change.charAt(i) != '0')  sector += change.charAt(i);
				else {
					//Integer.parseInt(sector);
					//System.out.println(sector);
					int temp = Integer.parseInt(sector);
					//System.out.println(temp);
					sector = "0"; //null 대신에 이걸 넣어줘야함 
					if(isPrime(temp)) {
						answer ++;
					}
					else continue;
				}
			}
			System.out.println(answer);
				
	}
	static boolean isPrime(long number) {
		
		if(number == 0) return false;
		if(number == 1) return false;
		if(number == 2) return true;
		for(int i = 3; i<=Math.sqrt(number); i = i+2) {
			if(number % i == 0) return false;
		}
		return true;
	}
	

}
