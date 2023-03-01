package programmers.level3;

/**
 * [문제명] 정수 삼각형
 * [풀이시간] / 11분
 * [한줄평] / DP 문제인 것을 알고 풀어서 정말 쉽게 풀 수 있었던 문제였다. 1번 풀이가 수행 시간은 조금 더 빨랐다.
 * 1_v1. DP(성공)
 * 2_v1. DP(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43105">문제</a>
 */
class Solution43105 {
    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        int answer = solution(triangle);
        System.out.println(answer);
    }

    public static int solution(int[][] arr) {
        int answer = 0;
        int n = arr.length;
        int max = 0;
        int[][] d = new int[n][n];
        d[0][0] = arr[0][0];
        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                if(j == 0) {
                    max = d[i - 1][j];
                } else if(j == i) {
                    max = d[i - 1][j - 1];
                } else {
                    max = Math.max(d[i - 1][j - 1], d[i - 1][j]);
                }
                d[i][j] = max + arr[i][j];
            }
        }
        for(int i = 0; i < n; i++) {
            answer = Math.max(answer, d[n - 1][i]);
        }
        return answer;
    }

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
}