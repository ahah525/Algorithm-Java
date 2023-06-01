package programmers.level3;


/**
 * [문제명] 연속 펄스 부분 수열의 합
 * [풀이시간] 25분(13분 + 12분)
 * [한줄평] 투포인터로 접근했다가 시간초과를 해결하지 못해서 결국 풀이를 보고 dp로 해결했다.
 * 1_v1. 투포인터(실패 - 16~20 시간초과)
 * 1_v2. DP(성공)
 * [점화식] dpA[i] = Math.max(dpA[i - 1] + a[i], a[i])
 * dpA[i]: a 배열에서 i번째 원소를 무조건 선택할 때 부분수열의 최댓값
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/"161988">문제</a>
 * @See <a href="https://chamggae.tistory.com/190">풀이 참고</a>
 */
class Solution161988 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2
    /**
     * @param sequence 정수 수열
     * @return 연속 펄스 부분 수열의 합 중 가장 큰 것
     */
    public long solution(int[] sequence) {
        long answer = 0;
        int[] a = new int[sequence.length]; // (1, -1) 곱한 배열
        int[] b = new int[sequence.length]; // (1, -1) 곱한 배열
        // 1. 2가지 배열 초기화
        for(int i = 0; i < sequence.length; i++) {
            if(i % 2 == 0) {
                a[i] = sequence[i];
                b[i] = -sequence[i];
            } else {
                a[i] = -sequence[i];
                b[i] = sequence[i];
            }
        }
        long[] dpA = new long[sequence.length];
        long[] dpB = new long[sequence.length];
        // 2. 초기화
        dpA[0] = a[0];
        dpB[0] = b[0];
        answer = Math.max(dpA[0], dpB[0]);
        for(int i = 1; i < sequence.length; i++) {
            // 3.1. (i - 1)번째 원소가 속한 부분 수열을 선택하고 i 번째 원소도 선택한 경우
            // 3.2. i번째 원소만 선택한 경우
            dpA[i] = Math.max(dpA[i - 1] + a[i], a[i]);
            dpB[i] = Math.max(dpB[i - 1] + b[i], b[i]);
            answer = Math.max(answer, Math.max(dpA[i], dpB[i]));
        }
        return answer;
    }
}