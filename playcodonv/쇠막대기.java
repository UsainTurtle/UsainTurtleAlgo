import java.util.*;
import java.io.*;
class 쇠막대기 {	
	public static int solution(String str){
		int cnt=0;
		Stack<Character> stack=new Stack<>();
		for(int i=0; i<str.length(); i++){
			if(str.charAt(i)=='(') stack.push('('); //열린 괄호가 나오면 스택에 push
			else{
				stack.pop(); 
				if(str.charAt(i-1)=='(') cnt+=stack.size();
				else cnt++;
			}
		}
		return cnt;
	}
	public static void main(String[] args) throws Exception{
	
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		System.out.println(solution(str));
	}
}