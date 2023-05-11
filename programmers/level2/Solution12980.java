package programmers.level2;


import java.util.Arrays;

/**
 * [문제명] 점프와 순간 이동
 * [풀이시간] 12분 / (6분)
 * [한줄평] 처음에는 DP로 푸는 문제인가 했는데, N 이 최대 10억이라 O(N) 으로 풀 수 있는 문제가 아니라는 것을 알아차렸다. 코드는 굉장히 단순했던 문제였다.
 * 1_v1. (성공)
 * - 건전지 사용량의 최솟값 = 2로 나눌 수 있다면 무조건 2로 나누기
 * 2_v1. (실패 - 효율성 테스트 1~10 시간초과)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12980">문제</a>
 */
class Solution12980 {
    public static void main(String[] args) {
        // 2
        System.out.println(solution(5));

        // 2
        System.out.println(solution(6));

        // 5
        System.out.println(solution(5000));
    }

    /**
     * @param n 아이언 슈트 구매자가 이동하려는 거리
     * @return 사용해야 하는 건전지 사용량의 최솟값
     */
    public static int solution(int n) {
        int ans = 0;
        while(n != 0) {
            if(n % 2 == 0) {
                // 1. 짝수이면 2로 나누기(순간이동)
                n /= 2;
            } else {
                // 2. 홀수이면 1 빼기(점프)
                n -= 1;
                ans++;
            }
        }
        return ans;
    }

    // 2_v1
    public int solution2(int n) {
        int[] d = new int[n + 1];
        int INF = Integer.MAX_VALUE;
        Arrays.fill(d, INF);
        d[0] = 0;
        for(int i = 1; i <= n; i++) {
            d[i] = Math.min(d[i], d[i - 1] + 1);
            if(i % 2 == 0)
                d[i] = Math.min(d[i], d[i / 2]);
        }
        return d[n];
    }
}