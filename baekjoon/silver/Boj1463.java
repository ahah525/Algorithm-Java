package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * [문제명] 1로 만들기
 * [풀이시간] 15분(8분+7분)
 * [한줄평] 완전 기초적인 문제인데 오랜만에 풀어서 헤맸다.
 * 1_v1. DP(실패)
 * 1_v2. DP(성공)
 * @See <a href="https://www.acmicpc.net/problem/1463">문제</a>
 * @See <a href="https://odysseyj.tistory.com/19">풀이 참고</a>
 */
class Boj1463 {
    // 1_v2
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            if(i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            if(i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(dp[n] - 1);
    }
}