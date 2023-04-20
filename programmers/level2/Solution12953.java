package programmers.level2;


import java.util.Arrays;

/**
 * [문제명] N개의 최소공배수
 * [풀이시간] 25분 / 16분
 * [한줄평] 진짜 간단한 문제였는데, 알고리즘을 요즘 소홀히했던게 느껴졌던 문제였다.. 최대공약수 로직은 복습 좀 하자!!
 * / 완전 1차원적으로 풀었는데 통과가 되어서 놀랐다.
 * 1_v1. 수학(성공)
 * [접근법] 최소공배수, 최대공약수
 * 2_v1. 수학(성공)
 * [접근법] 단순 반복문
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12953">문제</a>
 */
class Solution12953 {
    public static void main(String[] args) {
        // 168
        int[] arr1 = {2,6,8,14};
        System.out.println(solution(arr1));

        // 6
        int[] arr2 = {1,2,3};
        System.out.println(solution(arr2));
    }

    /**
     * @param arr n개의 숫자를 담은 배열
     * @return 이 수들의 최소공배수
     */
    public static int solution(int[] arr) {
        int target = arr[0];
        for(int i = 1; i < arr.length; i++) {
            target = lcm(target, arr[i]);
        }
        return target;
    }

    // 두 수의 최소공배수
    public static int lcm(int a, int b) {
        return a * b / gcd(Math.max(a, b), Math.min(a, b));
    }

    // 두 수의 최대 공약수(a > b)
    public static int gcd(int a, int b) {
        int r;
        while(b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
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
}