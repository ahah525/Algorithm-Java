package programmers.level2;


/**
 * [문제명] 숫자 카드 나누기
 * [풀이시간] 20분
 * [한줄평] 최대 공약수 구하는 것을 응용해서 풀 수 있는 문제였다.
 * 1_v1. 수학(성공)
 * [접근법] 최대공약수 -
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/135807">문제</a>
 */
class Solution135807 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    public int solution(int[] arrayA, int[] arrayB) {
        int a = gcd(arrayA);
        int b = gcd(arrayB);
        if(a == b) return 0;
        if(a > b) {
            for(int n : arrayB) {
                if(n % a == 0) return 0;
            }
        } else {
            for(int n : arrayA) {
                if(n % b == 0) return 0;
            }
        }
        return Math.max(a, b);
    }
    public int gcd(int[] arr) {
        int a = arr[0];
        for(int i = 1; i < arr.length; i++) {
            a = gcd(a, arr[i]);
        }
        return a;
    }

    // 최대 공약수
    public int gcd(int a, int b) {
        for(int i = Math.min(a, b); i >= 2; i--) {
            if(a % i == 0 && b % i == 0) return i;
        }
        return 1;
    }
}