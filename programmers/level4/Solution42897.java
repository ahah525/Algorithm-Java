package programmers.level4;


/**
 * [문제명] 도둑질
 * [풀이시간] 10분 / 11분
 * [한줄평] 예전에 비슷한 문제를 풀어봤던 기억이 있어서 쉽게 풀 수 있었다.
 * / 이전에 풀었던 기억때문에 쉽게 풀 수 있었던 문제다.
 * 1_v1. DP(성공)
 * [풀이] 첫번째 집을 터느냐 안터느냐에 따라 dp 배열을 2개 만든다.
 * [점화식] dp[i] = Math.max(dp[i - 2] + money[i], dp[i - 1])
 * dp[i]: i번째 집까지 훔칠 수 있는 돈의 최댓값(i번째 집은 털수도 안털 수도 있음)
 * 2_v1. DP(성공)
 * [풀이] 1_v1
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42897">문제</a>
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12971">유사 문제</a>
 */
class Solution42897 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1, 2_v1
    public int solution(int[] money) {
        int n = money.length;
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        // 1. 첫번째 집을 털고 마지막 집은 털지않는 경우
        dp1[0] = money[0];
        dp1[1] = money[0];
        // 마지막 집(n-1)은 무조건 털지 않아야 하기 때문에 (n-2)까지 반복
        for(int i = 2; i < n - 1; i++) {
            // i번 집을 터는 경우 vs i번째 집을 털지 않는 경우
            dp1[i] = Math.max(dp1[i - 2] + money[i], dp1[i - 1]);
        }
        // 2. 첫번째 집을 털지않는 경우
        dp2[1] = money[1];
        // 마지막 집(n-1)은 털어도 안털어도 상관없으므로 (n-1)까지 반복
        for(int i = 2; i < n; i++) {
            dp2[i] = Math.max(dp2[i - 2] + money[i], dp2[i - 1]);
        }
        return Math.max(dp1[n - 2], dp2[n - 1]);
    }
}