package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 점프
 * [풀이시간] 50분(30분+20분)
 * [한줄평] 쉬운 줄 알았는데 생각보다 어려웠던 문제였다. 꼭 복습이 필요하다.
 * 1_v1. 완전탐색, DFS(실패-시간초과)
 * [풀이] (0, 0) -> (n-1, n-1) 갈 수 있는 모든 경로 탐색
 * 1_v2. DP(성공)
 * @See <a href="https://www.acmicpc.net/problem/1890">문제</a>
 * @See <a href="https://hu-coding.tistory.com/36">풀이</a>
 */
class Boj1890 {
    // 1_v2
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 크기
        int[][] map = new int[n + 1][n + 1];
        long[][] dp = new long[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //
        dp[0][0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                int jump = map[i][j];
                if(jump == 0) break;
                if(j + jump <= n) dp[i][j + jump] += dp[i][j];
                if(i + jump <= n) dp[i + jump][j] += dp[i][j];
            }
        }
        System.out.println(dp[n][n]);
    }
}