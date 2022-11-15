package programmers.level1;

/**
 * 정수 제곱근 판별
 * v1. Math.sqrt(), Math.pow() 사용
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