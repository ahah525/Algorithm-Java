package programmers.level3;


/**
 * [문제명] 보행자 천국
 * [풀이시간] 2시간 15분
 * [한줄평] DP로 풀어야겠다고 생각했는데, 점화식을 세우지 못해서 결국 풀이를 보고 이해했던 문제다.
 * 1_v1. DP(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/181187">문제</a>
 */
class Solution181187 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        // 0: 아래쪽, 1: 오른쪽
        int[][][] dp = new int[2][m + 1][n + 1];
        dp[0][1][0] = 1;
        dp[1][1][0] = 1;
        for(int i = 1, x = 0; i <= m; i++, x++) {
            for(int j = 1, y = 0; j <= n; j++, y++) {
                if(cityMap[x][y] == 0) {
                    // 현재 방향과 상관없이 왼쪽, 위쪽에서 오는 모든 경우의 수를 더함
                    dp[0][i][j] = (dp[0][i - 1][j] + dp[1][i][j - 1]) % MOD;
                    dp[1][i][j] = (dp[0][i - 1][j] + dp[1][i][j - 1]) % MOD;
                } else if(cityMap[x][y] == 2) {
                    // 현재 방향과 같은 방향에서 오는 경우의 수만 더함
                    dp[0][i][j] = dp[0][i - 1][j] % MOD;
                    dp[1][i][j] = dp[1][i][j - 1] % MOD;
                }
            }
        }
        return dp[0][m][n];
    }
}