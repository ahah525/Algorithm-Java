package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 기타리스트
 * [풀이시간] 35분(16분+19분)
 * [한줄평] 어려운 문제는 아니었는데, DP로 어떻게 풀어야할지 고민하다가 시간일 오래걸렸다. 다시 한 번 풀어봐야겠다.
 * 1_v1. DFS(실패-시간 초과)
 * [복잡도] O(2^N)
 * 1_v2. DP(성공)
 * [복잡도] O(N * M)
 * [점화식] dp[i][j]: i번째 곡을 j볼륨으로 연주할 수 있는지 여부
 * @See <a href="https://www.acmicpc.net/problem/1495">문제</a>
 */
class Boj1495 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 곡의 개수
        int s = Integer.parseInt(st.nextToken());   // 시작 볼륨
        int m = Integer.parseInt(st.nextToken());   // 볼륨 최댓값
        int[] v = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            v[i] = Integer.parseInt(st.nextToken());
        }
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][s] = true;
        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= m; j++) {
                if(j - v[i - 1] >= 0 && dp[i - 1][j - v[i - 1]]) dp[i][j] = true;
                if(j + v[i - 1] <= m && dp[i - 1][j + v[i - 1]]) dp[i][j] = true;
            }
        }
        //
        int max = -1;
        for(int j = 0; j <= m; j++) {
            if(dp[n][j]) max = j;
        }
        System.out.println(max);
    }
}