package programmers.level2;


import java.util.Arrays;

/**
 * [문제명] N개의 최소공배수
 * [풀이시간] 25분 / 16분, 20분 / 6분
 * [한줄평] 진짜 간단한 문제였는데, 알고리즘을 요즘 소홀히했던게 느껴졌던 문제였다.. 최대공약수 로직은 복습 좀 하자!!
 * / 완전 1차원적으로 풀었는데 통과가 되어서 놀랐다. 최대공약수 로직 복습하자
 * / git
 * 1_v1. 수학(성공)
 * 2_v1. 수학(성공)
 * [풀이] 단순 반복문
 * 2_v2. 수학(성공)
 * [풀이] A, B, C의 최소공배수 = (A, B의 최소공배수)와 C의 최소공배수
 * 3_v1. 수학(성공)
 * [풀이] A, B 의 최대공약수 => A, B의 최솟값~1까지 나누는 것을 반복할 때 (A%i == 0 && B%i == 0)를 만족하는 i
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12953">문제</a>
 */
class Solution12953 {
    public static void main(String[] args) {
        // 168
        System.out.println(solution(new int[] {2,6,8,14}));

        // 6
        System.out.println(solution(new int[] {1,2,3}));
    }

    // 1_v1, 2_v2
    /**
     * @param arr n개의 숫자를 담은 배열
     * @return 이 수들의 최소공배수
     */
    public static int solution(int[] arr) {
        int answer = arr[0];
        for(int i = 1; i < arr.length; i++) {
            answer = lcm(answer, arr[i]);
        }
        return answer;
    }

    // 두 수의 최소공배수
    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    // 두 수의 최대 공약수(A < B)
    public static int gcd(int a, int b) {
        int A = Math.min(a, b);
        int B = Math.max(a, b);
        int r;
        while(A != 0) {
            r = B % A;
            B = A;
            A = r;
        }
        return B;
    }

    // 2_v1
    public int solution2(int[] arr) {
        Arrays.sort(arr);
        // 최소 공배수는 최댓값 이상의 값이므로 최댓값으로 초기화
        int answer = arr[arr.length - 1];
        while(true) {
            boolean flag = true;
            for(int n : arr) {
                if(answer % n != 0) {
                    flag = false;
                    break;
                }
            }
            // 최소 공배수로 모두 나누어지면 종료
            if(flag) break;
            answer++;
        }
        return answer;
    }

    // 3_v1
    public int solution3(int[] arr) {
        int answer = arr[0];
        for(int i = 1; i < arr.length; i++) {
            answer = lcm2(answer, arr[i]);
        }
        return answer;
    }

    public int gcd2(int a, int b) {
        int min = Math.min(a, b);
        for(int i = min; i > 1; i--) {
            if(a % i == 0 && b % i == 0) return i;
        }
        return 1;
    }

    public int lcm2(int a, int b) {
        return a * b / gcd2(a, b);
    }
}