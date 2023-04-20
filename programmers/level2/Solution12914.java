package programmers.level2;


/**
 * [문제명] 멀리 뛰기
 * [풀이시간] 15분 / 4분
 * [한줄평] 단순 피보나치(DP) 문제인걸 빨리 알아차렸는데, 런타임 에러를 잡느라 조금 시간이 지체됬다.
 * / 완전 단순한 DP 문제였다.
 * 1_v1. DP(성공)
 * 2_v1. DP(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12914">문제</a>
 */
class Solution12914 {
    public static void main(String[] args) {
        // 5
        System.out.println(solution(4));

        // 3
        System.out.println(solution(3));
    }

    // 1_v1, 2_v1
    /**
     * @param n 멀리뛰기에 사용될 칸의 수(1 이상, 2000 이하인 정수)
     * @return 효진이가 끝에 도달하는 방법이 몇 가지인지 알아내, 여기에 1234567를 나눈 나머지를 리턴
     */
    public static long solution(int n) {
        int[] d = new int[n + 1];
        d[1] = 1;
        if(n > 1)
            d[2] = 2;
        for(int i = 3; i <= n; i++) {
            d[i] = (d[i - 1] + d[i - 2]) % 1234567;
        }
        return d[n];
    }
}