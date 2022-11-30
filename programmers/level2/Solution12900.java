package programmers.level2;


/**
 * [문제명] 2 x n 타일링
 * [한줄평] 유명한 DP 문제인 타일링 문제였기 때문에 쉽게 풀 수 있었다.
 * v1. DP(성공)
 * [점화식]
 * d[i] = 2 * 1 타일로 2 * n 직사각형을 채우는 경우의 수
 * d[i] = d[i - 1] + d[i - 2]
 */
class Solution12900 {
    public static void main(String[] args) {
        // 5
        int n = 4;
        System.out.println(solution(n));
    }

    /**
     * @param n 가로의 길이(60,000이하의 자연수)
     * @return 경우의 수를 1,000,000,007으로 나눈 나머지를 return
     */
    public static int solution(int n) {
        int[] d = new int[n + 1];
        d[1] = 1;
        d[2] = 2;
        for(int i = 3; i <= n; i++) {
            d[i] = (d[i - 1] + d[i - 2]) % 1000000007;
        }
        return d[n];
    }
}