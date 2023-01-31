package programmers.level2;


/**
 * [문제명] N개의 최소공배수
 * [풀이시간] 25분
 * [한줄평] 진짜 간단한 문제였는데, 알고리즘을 요즘 소홀히했던게 느껴졌던 문제였다.. 최대공약수 로직은 복습 좀 하자!!
 * v1. 최소공배수/최대공약수(성공)
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
}