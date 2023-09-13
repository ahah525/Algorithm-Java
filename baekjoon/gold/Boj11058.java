package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [문제명] 크리보드
 * [풀이시간] 40분(22분+18분)
 * [한줄평] 처음에는 BFS로 풀면 시간초과가 안날거라고 생각하고 풀었는데, 메모리 초과가 나서 결국 풀이를 보고 이해했던 문제고 생각보다 너무 어려웠다.
 * 1_v1. BFS(실패-메모리 초과)
 * [풀이] 각 depth 마다 화면/드래그/버퍼에 있는 A개수를 기록해서 최댓값을 구했다.
 * 1_v2. DP(성공)
 * [점화식] dp[i] = Math.max(dp[i], dp[i - j] * (j - 1)) (7 <= i, j < i)
 * - dp[i]: 버튼을 i번 눌러서 화면에 출력된 A개수의 최댓값
 * @See <a href="https://www.acmicpc.net/problem/11058">문제</a>
 * @See <a href="https://velog.io/@solser12/%EB%B0%B1%EC%A4%80-11058-%ED%81%AC%EB%A6%AC%EB%B3%B4%EB%93%9C-JAVA">풀이</a>
 */
class Boj11058 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 횟수
        long[] dp = new long[n + 1];
        // 1. 1 ~ 6 까지는 A를 계속 화면에 출력하는 것이 최댓값
        for(int i = 1; i <= Math.min(n, 6); i++) {
            dp[i] = i;
        }
        // 2.
        for(int i = 7; i <= n; i++) {
            // (i-j)번째 값을 전체선택하고 붙여넣기를 (j-2)번 하면, i번째 개수 = (i-j)번째 개수의 (j-1)배
            for(int j = 3; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] * (j - 1));
            }
        }
        System.out.println(dp[n]);
    }
}