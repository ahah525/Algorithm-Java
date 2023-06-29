package programmers.level1;

/**
 * [문제명] 정수 제곱근 판별
 * [풀이시간] / 3분
 * [한줄평] / 너무 기초적인 수학 문제였고 더 풀어볼 필요 없는 문제다.
 * 1_v1. 수학(성공)
 * [풀이] sqrt = (int) Math.sqrt(n), Math.pow(sqrt, 2) == n
 * 2_v1. 수학(성공)
 * [풀이] Math.sqrt(n) == (int) Math.sqrt(n)
 */
class Solution12934 {
    public static void main(String[] args) {
        long n1 = 121;
        // 144
        long answer1 = solution(n1);
        System.out.println(answer1);

        long n2 = 3;
        // -1
        long answer2 = solution(n2);
        System.out.println(answer2);
    }

    // 1_v1
    /**
     * @param n 1이상, 50000000000000 이하인 양의 정수
     * @return
     * n이 양의 정수 x의 제곱이라면 x+1의 제곱
     * n이 양의 정수 x의 제곱이 아니라면 -1
     */
    public static long solution(long n) {
        long sqrt = (long) Math.sqrt(n);
        if(Math.pow(sqrt, 2) == n) {
            return (long) Math.pow(sqrt + 1, 2);
        }
        return -1;
    }
}