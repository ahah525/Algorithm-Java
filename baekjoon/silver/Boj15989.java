package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [문제명] 1, 2, 3 더하기 4
 * [풀이시간] 10분
 * [한줄평] 이전에 프로그래머스에서 거의 비슷한 문제를 풀어봤던 적이 있어서 쉽게 풀 수 있었고 더 안풀어봐도 될 것 같다.
 * 1_v1. DP(성공)
 * [점화식]
 * 1) dp[i][j] = dp[i - 1][j]
 * 2) dp[i][j] = dp[i - 1][j] + dp[i][j - i] (단, j - i >= 0)
 * @See <a href="https://www.acmicpc.net/problem/15989">문제</a>
 */
class Boj15989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int[][] dp = new int[4][10001];
        for(int i = 1; i <= 3; i++) {
            dp[i][0] = 1;
        }
        for(int i = 1; i <= 3; i++) {
            for(int j = 1; j <= 10000; j++) {
                dp[i][j] = dp[i - 1][j];
                if(j - i >= 0) dp[i][j] += dp[i][j - i];
            }
        }
//        System.out.println(Arrays.toString(dp));
        for(int i= 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[3][n]);
        }
    }
}