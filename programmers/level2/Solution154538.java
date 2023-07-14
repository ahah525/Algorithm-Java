package programmers.level2;


import java.util.Arrays;

/**
 * [문제명] 숫자 변환하기
 * [풀이시간] 16분 / 11분 / 19분(14분+5분)
 * [한줄평] 전형적인 DP 문제여서 쉽게 풀 수 있었다.
 * /
 * / 쉬운 문제였는데 점화식을 잘못 세워서 틀렸다. 다음에 다시 한 번 풀어봐야겠다.
 * 1_v1. DP(성공)
 * - d[i]: x 에서 i 를 만들기 위한 최소 연산 횟수
 * [점화식] d[i] = Math.min(d[i], d[i - n], d[i / 2], d[i / 3])
 * 2_v1. DP(성공) -> 빠름
 * 2_v2. DP(성공)
 * 3_v1. DP(실패 - 1 실패)
 * 3_v2. DP(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/154538">문제</a>
 */
class Solution154538 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1, 2_v1, 3_v1
    /**
     * @param x 1 ≤ x ≤ y ≤ 1,000,000
     * @param y 1 ≤ x ≤ y ≤ 1,000,000
     * @param n 1 ≤ n < y
     * @return x를 y로 변환하기 위해 필요한 최소 연산 횟수(x를 y로 만들 수 없다면 -1)
     */
    public int solution(int x, int y, int n) {
        int INF = 1000000;
        int[] d = new int[y + 1];
        // 1. 최댓값으로 연산 비용 초기화
        Arrays.fill(d, INF);
        // 2. x -> x 만드는 비용 = 0
        d[x] = 0;
        // 3. x 에서 (x + 1)...(y) 까지 만드는 최소비용 계산
        for(int i = x + 1; i <= y; i++) {
            // 1. n 더하기
            if(i - n >= x) d[i] = Math.min(d[i], d[i - n] + 1);
            // 2. 2 곱하기
            if(i % 2 == 0) d[i] = Math.min(d[i], d[i / 2] + 1);
            // 3. 3 곱하기
            if(i % 3 == 0) d[i] = Math.min(d[i], d[i / 3] + 1);
        }
        return d[y] == INF ? -1 : d[y];
    }

    // 2_v2
    public int solution2(int x, int y, int n) {
        int INF = 1000000;
        int[] d = new int[y + 1];
        Arrays.fill(d, INF);
        d[x] = 0;
        for(int i = x; i <= y; i++) {
            if(i + n <= y)
                d[i + n] = Math.min(d[i + n], d[i] + 1);
            if(i * 2 <= y)
                d[i * 2] = Math.min(d[i * 2], d[i] + 1);
            if(i * 3 <= y)
                d[i * 3] = Math.min(d[i * 3], d[i] + 1);
        }
        return d[y] == INF ? -1 : d[y];
    }

    //
    public int solution3(int x, int y, int n) {
        int[] dp = new int[y + 1];
        Arrays.fill(dp, 1000000);
        //
        dp[x] = 0;
        for(int i = x + 1; i <= y; i++) {
            if(i - n >= x) dp[i] = dp[i - n] + 1;
            if(i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            if(i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
        }
        if(dp[y] == 1000000) return -1;
        return dp[y];
    }
}