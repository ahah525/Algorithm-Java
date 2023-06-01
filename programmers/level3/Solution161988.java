package programmers.level3;


/**
 * [문제명] 연속 펄스 부분 수열의 합
 * [풀이시간] (13분)
 * [한줄평]
 * 1_v1. (실패 - 16~20 시간초과)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/"161988">문제</a>
 */
class Solution161988 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    /**
     * @param sequence 정수 수열
     * @return 연속 펄스 부분 수열의 합 중 가장 큰 것
     */
    public long solution(int[] sequence) {
        long answer = 0;
        // (1, -1)
        int[] a = new int[sequence.length];
        // (-1, 1)
        int[] b = new int[sequence.length];
        for(int i = 0; i < sequence.length; i++) {
            if(i % 2 == 0) {
                a[i] = sequence[i];
                b[i] = -sequence[i];
            } else {
                a[i] = -sequence[i];
                b[i] = sequence[i];
            }
        }
        // System.out.println(Arrays.toString(a));
        // System.out.println(Arrays.toString(b));
        for(int i = 0; i < sequence.length; i++) {
            long sumA = 0;
            long sumB = 0;
            for(int j = i; j < sequence.length; j++) {
                sumA += (long) a[j];
                sumB += (long) b[j];
                answer = Math.max(answer, Math.max(sumA, sumB));
            }
        }
        return answer;
    }
}