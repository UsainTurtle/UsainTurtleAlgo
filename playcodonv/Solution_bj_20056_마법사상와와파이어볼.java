import java.util.*;
import java.io.*;


class Fireball{
	int x, y, weight,speed,direction;
	public Fireball(int x, int y, int weight, int speed, int direction) {
		super();
		this.x = x;
		this.y = y;
		this.weight = weight;
		this.speed = speed;
		this.direction = direction;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fireball [x=").append(x).append(", y=").append(y).append(", weight=").append(weight)
				.append(", velocity=").append(speed).append(", direction=").append(direction).append("]");
		return builder.toString();
	}
	
}


public class Solution_bj_20056_마법사상와와파이어볼 {
	static int[] ni = {-1,-1,0,1,1,1,0,-1}; //상 우상 우 우하 하 좌하 좌 좌상
	static int[] nj = {0,1,1,1,0,-1,-1,-1};
	static int n,m,k; //맵 크기, 파이어볼 개수, 마법사의 명령 횟수
	static ArrayList<Fireball>[][] map;
	static Queue<Fireball> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_20056.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		
		map = new ArrayList[n][n];
		for(int i = 0;i<n;i++) {
			for(int j = 0;j<n;j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 0;i<m;i++) { 		//(x,y,질량,속도,방향)
			st = new StringTokenizer(br.readLine()," ");
			q.add(new Fireball(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,
											  Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		
		for(int i = 0; i < k ; i++) { //명령 횟수
			//파이어볼 이동
			int len = q.size();
			for(int j = 0;j<len;j++) {
				Fireball cur = q.poll();
				int x = cur.x; int y = cur.y; int weight = cur.weight; int speed =  cur.speed; int direction = cur.direction;
				int nx = x + ni[direction]*(speed%n);
				int ny = y + nj[direction]*(speed%n);
				nx = (nx+n)%n;
				ny = (ny+n)%n;
				map[nx][ny].add(new Fireball(nx,ny,weight,speed,direction));
			}
			for(int j =0;j<n;j++) {
				for(int k = 0;k<n;k++) {
					if(map[j][k].size() == 0) continue;
					if(map[j][k].size() == 1) {
						q.add(map[j][k].get(0));
						continue;
					}
					int weight = 0; int speed = 0; int even = 0; int odd = 0;
					for(int m = 0; m < map[j][k].size(); m++) {
						weight += map[j][k].get(m).weight; //무게 전부 합치기
						speed += map[j][k].get(m).speed; //속도 전부 더하기
						if((map[j][k].get(m).direction) % 2 == 0) {
							even++;
						}else {
							odd++;
						}
					}
					weight/=5;
					speed/=map[j][k].size();
					if(weight == 0) continue;
					if(odd == 0 || even == 0) {
						q.add(new Fireball(j,k,weight,speed,0)); q.add(new Fireball(j,k,weight,speed,2)); q.add(new Fireball(j,k,weight,speed,4)); q.add(new Fireball(j,k,weight,speed,6));
					}else {
						q.add(new Fireball(j,k,weight,speed,1));q.add(new Fireball(j,k,weight,speed,3));q.add(new Fireball(j,k,weight,speed,5));q.add(new Fireball(j,k,weight,speed,7));
					}
					
				}
				
			}
			for(int o = 0;o<n;o++) {
				for(int p = 0;p<n;p++) {
					map[o][p] = new ArrayList<>();
				}
			}
		}
		int res = 0;
		int len = q.size();
		for(int i = 0;i< len;i++) {
			res+=q.poll().weight;
		}
		System.out.println(res);
		
		br.close();
	}

}
