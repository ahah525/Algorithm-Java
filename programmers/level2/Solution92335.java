package programmers.level2;

/**
 * [문제명] k진수에서 소수 개수 구하기
 * [풀이시간] / 17분
 * [한줄평] / 접근을 빨랐으나 반례 힌트를 보고 해결했다. 항상 범위를 잘보자!!
 * 1_v1. (성공)
 * 2_v1. Integer.parseInt()(실패 - 런타임 에러)
 * 2_v2. Long.parseLong()(성공)
 * [반례] 1,000,000 3진수 변환 => 10^12 자리, 10^12 자리 문자열 -> int 로 변환하면 int 범위(10^9)벗어나므로 런타임 에러
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/92335">문제</a>
 * @See <a href="https://school.programmers.co.kr/learn/courses/14743/lessons/118834">반례</a>
 */
class Solution92335 {
    public static void main(String[] args) {
        int n1 = 437674;
        int k1 = 3;

        int answer1 = solution(n1, k1);
        System.out.println(answer1);

        int n2 = 110011;
        int k2 = 10;

        int answer2 = solution(n2, k2);
        System.out.println(answer2);
    }

    // 2_v2
    /**
     * @param n 1 ≤ n ≤ 1,000,000
     * @param k 3 ≤ k ≤ 10
     * @return n을 k진수로 바꿨을 때, 변환된 수 안에서 찾을 수 있는 위 조건에 맞는 소수의 개수
     */
    public static int solution(int n, int k) {
        int answer = 0;
        // 1. n 10진수 -> k 진수 변환, "0"을 기준으로 분리
        String[] split = Integer.toString(n, k).split("0");
        for(String s : split) {
            // 1. 첫번째 예외처리("" 은 정수로 변환 불가)
            if(s.equals("")) continue;
            // 2. 두번째 예외처리(s 의 최대 길이일 때 int 로는 변환 불가)
            if(isPrime(Long.parseLong(s)))
                answer++;
        }
        return answer;
    }

    // 소수인지 판별
    public static boolean isPrime(long n) {
        if(n == 1) return false;
        // 2 ~ 루트 n 까지 확인 -> 시간 초과 해결
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }
}