package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [문제명] 계단 오르기
 * [풀이시간] 17분
 * [한줄평] 전형적인 DP 문제라 다음에 한 번 더 풀어봐도 좋을 것 같다.
 * 1_v1. DP(성공)
 * [점화식] dp[i] = arr[i] + Math.max(dp[i - 3] + arr[i - 1], dp[i - 2]) (3 <= i < n)
 * - dp[i]: i번째 계단을 밟았을 때 총 점수의 최댓값
 * @See <a href="https://www.acmicpc.net/problem/2579">문제</a>
 */
class Boj2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 계단 수
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int[] dp = new int[n];
        dp[0] = arr[0];
        if(n >= 2) dp[1] = arr[0] + arr[1];
        if(n >= 3) dp[2] = arr[2] + Math.max(arr[0], arr[1]);
        for(int i = 3; i < n; i++) {
            // 1. OXOO: (i-3)번째 계단 -> (i-1)번째 계단 -> i번째 계단
            // 2. OXO: (i-2)번째 게단 -> i번째 계단
            dp[i] = arr[i] + Math.max(dp[i - 3] + arr[i - 1], dp[i - 2]);
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[n - 1]);
    }
}