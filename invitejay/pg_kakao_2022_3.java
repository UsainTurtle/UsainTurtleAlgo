package com.company.tutle;

import java.util.*;

public class pg_kakao2022_3 {
    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600}; // 기본시간, 기본요금, 단위시간, 단위요금
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        ArrayList<Park> parkedCar = new ArrayList<>();
        Map<String, Park> cars = new HashMap<>();
        for (int tc = 0; tc < records.length; tc++) {
            StringTokenizer st = new StringTokenizer(records[tc], " ");
            StringTokenizer time = new StringTokenizer(st.nextToken(), ":");
            Park p = new Park(Integer.parseInt(time.nextToken()), Integer.parseInt(time.nextToken()), st.nextToken(), st.nextToken());
            if (p.status.equals("IN")) {
                if (!cars.containsKey(p.num)) { // 기존에 없던 차량
                    cars.put(p.num, p);
                    parkedCar.add(p);
                } else { // 다시 입고하는 경우
                    Park in = cars.get(p.num);
                    in.hour = p.hour;
                    in.min = p.min;
                    in.status = "IN";
                }
            } else { // 출고
                Park in = cars.get(p.num);
                in.totalTime += calculateTime(in.hour, in.min, p.hour, p.min);
                in.status = "OUT"; // 출고 처리
            }
        }

        for (Park park : parkedCar) { // 입고되었던 차량
            if (cars.get(park.num).status.equals("IN")) { // 아직 나가지 못한 차량
                park.totalTime += calculateTime(park.hour, park.min, 23, 59);
            }
            if (park.totalTime <= fees[0]) {
                park.fee = fees[1]; // 기본 요금
            } else {
                int dividedFee = (park.totalTime - fees[0]) / fees[2];

                if ((park.totalTime-fees[0]) % fees[2] == 0) { // 맞아 떨어질 경우
                    park.fee = fees[1] + dividedFee * fees[3];
                } else { // 올림
                    park.fee = fees[1] + (dividedFee + 1) * fees[3];
                }
            }
        }
        int[] answer = new int[parkedCar.size()];
        parkedCar.sort(Comparator.comparingInt(o -> Integer.parseInt(o.num)));
        for (int i = 0; i < parkedCar.size(); i++) {
            answer[i] = parkedCar.get(i).fee;
        }
        System.out.print(Arrays.toString(answer));
    }

    static int calculateTime(int inHour, int inMin, int outHour, int outMin) {
        return (outHour * 60 + outMin) - (inHour * 60 + inMin);
    }

    static class Park {
        String num;
        int hour;
        int min;
        String status;
        int fee = 0;
        int totalTime = 0;

        public Park(int hour, int min, String num, String status) {
            this.num = num;
            this.hour = hour;
            this.min = min;
            this.status = status;
        }
    }
}
