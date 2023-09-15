package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 스티커
 * [풀이시간] 35분
 * [한줄평] 풀 수 있을 것 같았는데, 결국 점화식을 못세워서 풀이를 봤다. 나머지 1가지 경우의 수를 떠올리지 못해서 틀렸어서 다시 한 번 꼭 풀어봐야겠다.
 * 1_v1. DP(성공)
 * [점화식] dp[i][j]: (i,j)를 선택하할 때 점수의 최댓값
 * dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + arr[0][i];
 * dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + arr[1][i];
 * 1. (0, i)를 선택할 때, 나올 수 있는 2가지 경우
 * -----
 *   X O
 *   O X
 * -----
 * X X O
 * O X X
 * -----
 * 2. (1, i)를 선택할 때, 나올 수 있는 2가지 경우
 * -----
 *   O X
 *   X O
 * -----
 * O X X
 * X X O
 * -----
 * @See <a href="https://www.acmicpc.net/problem/9465">문제</a>
 * @See <a href="https://velog.io/@yanghl98/%EB%B0%B1%EC%A4%80-9465-%EC%8A%A4%ED%8B%B0%EC%BB%A4-JAVA">풀이</a>
 */
class Boj9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int k = 0; k < t; k++) {
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[2][n];
            for(int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            //
            int[][] dp = new int[2][n];
            dp[0][0] = arr[0][0];
            dp[1][0] = arr[1][0];
            dp[0][1] = arr[1][0] + arr[0][1];
            dp[1][1] = arr[0][0] + arr[1][1];
            for(int i = 2; i < n; i++) {
                dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + arr[0][i];
                dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + arr[1][i];
            }
            System.out.println(Math.max(dp[0][n - 1], dp[1][n - 1]));
        }
    }
}