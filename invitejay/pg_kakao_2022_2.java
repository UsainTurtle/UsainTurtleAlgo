package com.company.tutle;

//Integer.toString(n, k);
public class pg_kakao2022_2 {
    public static void main(String[] args) {
        int n = 437674;
        int k = 3;
        int result = 0;
        StringBuilder num = new StringBuilder(Integer.toString(n, k));
//        while (n >= k) {
//            int divideRes = n / k;
//            int rest = n % k;
//            num.append(rest);
//            n = divideRes;
//        }
//        num.append(n);
//        num.reverse();
        StringBuilder check = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) - '0' != 0) { // 계속 확인해줌
                check.append(num.charAt(i));
                continue;
            }
            if (num.charAt(i) - '0' == 0 && check.length() != 0) {
                long checkNum = Long.parseLong(check.toString());
                if (isPrime(checkNum)) {
                    result++;
                }
                check = new StringBuilder();
            }
        }
        if (isPrime(Long.parseLong(check.toString()))) {
            result++;
        }
        System.out.print(result);
    }

    //소수 판별
    static boolean isPrime(long num) {
        if (num == 1) {
            return false;
        } else if (num == 2 || num == 3) {
            return true;
        }
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
