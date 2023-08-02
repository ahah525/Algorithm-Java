package programmers.level3;


/**
 * [문제명] 거스름돈
 * [풀이시간] 38분 / 30분 / 10분
 * [한줄평] 처음에 1차원 DP 배열로 풀다가 중복을 거르지 못해서 2차원 DP 배열을 사용한다는 힌트를 보고 점화식을 다시 세워서 해결했다.
 * / 2차원 DP 배열을 사용한다는 힌트를 보고 풀었던 문제로 꼭 다시 풀어봐야할 문제다.
 * / 거스름돈 문제는 대표적인 DP 문제로 쉽게 풀 수 있었다.
 * 1_v1. DP(실패)
 * [점화식] dp[i] += dp[i - m]
 * - dp[i]: i를 만드는 방법의 수
 * 1_v2. DP(성공)
 * [점화식] dp[i][j] = dp[i - 1][j] + dp[i][j - m] (단, j - m >= 0)
 * - dp[i][j]: i번째 동전까지 사용해서 j를 만드는 방법의 수
 * 1_v3. DP(성공) -> 빠름
 * [점화식] dp[i] += dp[i - m]
 * - dp[i]: ?개의 거스름돈을 사용해서 i를 만드는 경우의 수
 * [풀이] dp[i][j] 값을 계산할 때 dp[i-1][j] 와 dp[i][j-m]값을 활용하기 때문에 2차원 배열 대신 1차원 배열로 선언하고 덮어써도 문제없다.
 * 2_v1. DP(성공)
 * [풀이] 1_v3 동일
 * 3_v1. DP(성공)
 * [풀이] 1_v3 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12907">문제</a>
 * @See <a href="https://deokisys.github.io/%EC%BD%94%EB%94%A9%ED%85%8C%EC%8A%A4%ED%8A%B8/2020/12/27/%EA%B1%B0%EC%8A%A4%EB%A6%84%EB%8F%88-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4.html">힌트</a>
 */
class Solution12907 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2
    /**
     * @param n 거슬러 줘야 하는 금액
     * @param money Finn이 현재 보유하고 있는 돈의 종류
     * @return Finn이 n 원을 거슬러 줄 방법의 수
     */
    public int solution(int n, int[] money) {
        int m = money.length;
        // 1. dp 초기화
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++) {
            dp[i][0] = 1;
        }
        // 2.
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                // 2.1. (i-1)번째 동전까지 사용해서 j를 만드는 방법의 수
                dp[i][j] = dp[i - 1][j];
                if(j - money[i - 1] < 0) continue;
                // 2.2. i번째 동전까지 사용해서 (j - m)을 만드는 방법의 수
                dp[i][j] = (dp[i][j] + dp[i][j - money[i - 1]]) % 1000000007;
            }
        }
        return dp[m][n];
    }

    // 1_v3, 2_v1, 1_v3
    public int solution2(int n, int[] money) {
        int MOD = 1000000007;
        // 1. dp 초기화
        int[] dp = new int[n + 1];
        dp[0] = 1;
        // 2.
        for(int coin : money) {
            for(int i = coin; i <= n; i++) {
                // 2.1. (i-1)번째 동전까지 사용해서 j를 만드는 방법의 수
                // 2.2. i번째 동전까지 사용해서 (j - coin)을 만드는 방법의 수
                dp[i] = (dp[i] + dp[i - coin]) % MOD;
            }
        }
        return dp[n];
    }
}