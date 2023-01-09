package programmers.level2;


/**
 * [문제명] 피보나치 수
 * [풀이시간] 10분
 * [한줄평] 처음에 아무생각없이 재귀로 풀었다가 시간초과가 났는데, dp를 이용해 이전에 계산한 값을 활용하는 것이 포인트다.
 * v1. dp(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12945">문제</a>
 */
class Solution12945 {
    public static void main(String[] args) {
        // 2
        System.out.println(solution(3));

        // 5
        System.out.println(solution(5));
    }

    /**
     * @param n 2 이상 100,000 이하인 자연수
     * @return 2 이상의 n이 입력되었을 때, n번째 피보나치 수를 1234567으로 나눈 나머지
     */
    public static int solution(int n) {
        int[] f = new int[n + 1];
        f[1] = 1;
        for(int i = 2; i <= n; i++) {
            f[i] = (f[i - 1] + f[i - 2]) % 1234567;
        }
        return f[n];
    }
}