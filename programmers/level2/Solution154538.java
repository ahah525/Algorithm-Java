package programmers.level2;


/**
 * [문제명] 숫자 변환하기
 * [풀이시간] 16분
 * [한줄평] 전형적인 DP 문제여서 쉽게 풀 수 있었다.
 * 1_v1. DP(성공)
 * - d[i]: x 에서 i 를 만들기 위한 최소 연산 횟수
 * [점화식] d[i] = Math.min(d[i], d[i - n], d[i / 2], d[i / 3])
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/154538">문제</a>
 */
class Solution154538 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param x 1 ≤ x ≤ y ≤ 1,000,000
     * @param y 1 ≤ x ≤ y ≤ 1,000,000
     * @param n 1 ≤ n < y
     * @return x를 y로 변환하기 위해 필요한 최소 연산 횟수(x를 y로 만들 수 없다면 -1)
     */
    public int solution(int x, int y, int n) {
        int[] d = new int[y + 1];
        // 1. 최댓값으로 연산 비용 초기화
        int INF = 1000000;
        for(int i = 0; i <= y; i++) {
            d[i] = INF;
        }
        // 2. x -> x 만드는 비용 = 0
        d[x] = 0;
        // 3. x 에서 (x + 1)...(y) 까지 만드는 최소비용 계산
        for(int i = x + 1; i <= y; i++) {
            // 1. n 더하기
            if(i - n >= x)
                d[i] = Math.min(d[i], d[i - n] + 1);
            // 2. 2 곱하기
            if(i % 2 == 0)
                d[i] = Math.min(d[i], d[i / 2] + 1);
            // 3. 3 곱하기
            if(i % 3 == 0)
                d[i] = Math.min(d[i], d[i / 3] + 1);
        }
        return d[y] == INF ? -1 : d[y];
    }
}