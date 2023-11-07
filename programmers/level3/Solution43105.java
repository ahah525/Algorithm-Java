package programmers.level3;

/**
 * [문제명] 정수 삼각형
 * [풀이시간] / 11분 / 10분
 * [한줄평]
 * / DP 문제인 것을 알고 풀어서 정말 쉽게 풀 수 있었던 문제였다. 1번 풀이가 수행 시간은 조금 더 빨랐다.
 * / 아주 기초적인 DP 문제라 더 안 풀어봐도 될 것 같다.
 * 1_v1. DP(성공)
 * [점화식] dp[i][j]: (i,j)에 오기까지 거쳐간 숫자의 최댓값
 * 1) j == 0, dp[i][j] = dp[i - 1][j] + triangle[i][j]
 * 2) j == i, dp[i][j] = dp[i - 1][j - 1] + triangle[i][j]
 * 3) 0 < j < i, dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j]
 * 2_v1. DP(성공)
 * 3_v1. DP(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43105">문제</a>
 */
class Solution43105 {
    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        int answer = solution(triangle);
        System.out.println(answer);
    }

    // 1_v1
    public static int solution(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;
        int[][] dp = new int[n][n];
        dp[0][0] = triangle[0][0];
        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                if(j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                } else if(j == i) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
                }
            }
        }
        for(int i = 0; i < n; i++) {
            answer = Math.max(answer, dp[n - 1][i]);
        }
        return answer;
    }

    // 2_v1
    public int solution2(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;
        int[][] d = new int[n][n];
        d[0][0] = triangle[0][0];
        for(int i = 0; i < n - 1; i++) {
            for(int j = 0; j <= i; j++) {
                // 1. (i, j) 에서 왼쪽 대각선 방향 = (i + 1, j)
                d[i + 1][j] = Math.max(d[i + 1][j], d[i][j] + triangle[i + 1][j]);
                // 2. (i, j) 에서 오른쪽 대각선 방향 = (i + 1, j + 1)
                d[i + 1][j + 1] = Math.max(d[i + 1][j + 1], d[i][j] + triangle[i + 1][j + 1]);
            }
        }
        for(int num : d[n - 1]) {
            answer = Math.max(answer, num);
        }
        return answer;
    }

    // 3_v1
}