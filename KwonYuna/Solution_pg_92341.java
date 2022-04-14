package a0331;

import java.util.*;

public class Solution_pg_92341 {
    public static void main(String[] args) {

         //fees 기본 시간(분) / 기본 요금(원) / 단위 시간(분) / 단위 요금(원)
        int[] fees = {180, 5000, 10, 600};
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        ////[14600, 34400, 5000]

        //        int[] fees={ 120, 0, 60, 591};
        //        String[] records={"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN" };
        //////[0, 591]

        //        int[] fees={ 1, 461, 1, 10};
        //        String[] records={"00:00 1234 IN" };
        /////[14841]
        //

        Map<String, Integer> carin = new HashMap<>();
        Map<String, Integer> carout = new HashMap<>();

        for(int i=0; i<records.length; i++){
            StringTokenizer st = new StringTokenizer(records[i],": ");
            int time= Integer.parseInt(st.nextToken())*60+Integer.parseInt(st.nextToken());
            String carnum = st.nextToken();
            String status = st.nextToken();

            if(status.equals("IN")){
                carin.put(carnum,time);
            }else{
                int intime = carin.remove(carnum);
                time-=intime;
                if(carout.containsKey(carnum)){
                    carout.replace(carnum,carout.get(carnum)+time);
                }else {
                    carout.put(carnum, time);
                }
            }
        }

        for(String carnum: carin.keySet()){
            int intime = carin.get(carnum);
            int time = (23*60+59)-intime;

            if(carout.containsKey(carnum)){
                carout.replace(carnum,carout.get(carnum)+time);
            }else {
                carout.put(carnum, time);
            }
        }

        PriorityQueue<Car> pq = new PriorityQueue<>();
        for(String carnum: carout.keySet()){
            int fee=fees[1];
            int time=carout.get(carnum);


            if(fees[0]<time){
                time-=fees[0];
                fee+=(time/fees[2])*fees[3];
                if(0<time%fees[2])fee+=fees[3];
            }
            pq.offer(new Car(carnum,fee));
        }

        int[] answer = new int[pq.size()];
        for(int i=0; i<answer.length; i++){
            answer[i]= pq.poll().cost;
        }

        System.out.println(Arrays.toString(answer));

    }

    static class Car implements Comparable<Car>{
        String carnum;
        int cost;

        public Car(String carnum, int cost) {
            this.carnum = carnum;
            this.cost = cost;
        }

        @Override
        public int compareTo(Car o) {
            return carnum.compareTo(o.carnum);
        }

    }
}
