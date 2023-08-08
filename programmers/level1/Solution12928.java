package programmers.level1;

/**
 * [문제명] 약수의 합
 * [풀이시간] / 13분(3분 + 10분)
 * [한줄평]
 * / 반례를 찾지 못해서 결국 답을 봤는데, 생각지못하게 제곱근 판별 수식을 틀려서 애를 먹었다.
 * / 더이상 풀어볼 필요 없는 쉬운 문제다.
 * 1_v1. 수학(성공)
 * 2_v1. 수학(실패 - 3~5,8,10,13 실패)
 * [풀이] 제곱근 판별 방법: (sqrt * sqrt == n) 사용
 * 2_v2. 수학(성공)
 * [해결] 제곱근 판별 방법: (Math.floor(sqrt) == sqrt) 사용
 * 3_v1. 수학(성공)
 * [풀이] 제곱근 판별 방법: (Math.sqrt(n) % 1 == 0) 사용
 */
class Solution12928 {
    public static void main(String[] args) {
        int n1 = 12;
        // 28
        int answer1 = solution(n1);
        System.out.println(answer1);

        int n2 = 5;
        // 6
        int answer2 = solution(n2);
        System.out.println(answer2);
    }

    // 1_v1
    /**
     * @param n 0 이상 3000이하인 정수
     * @return 정수 n의 약수를 모두 더한 값
     */
    public static int solution(int n) {
        int answer = 0;

        for(int i = 1; i * i <= n; i++) {
            if(n % i == 0) {
                answer += i;
                answer += n / i;
            }
        }
        // 제곱근인지 비교
        Double sqrt = Math.sqrt(n);
        if(sqrt == sqrt.intValue()) {
            answer -= sqrt;
        }
        return answer;
    }

    // 2_v2
    public int solution2(int n) {
        int answer = 0;
        double sqrt = Math.sqrt(n);
        for(int i = 1; i <= sqrt; i++) {
            if(n % i != 0) continue;
            answer += i;
            answer += n / i;
        }
        // 실수값이 정수인지 판별
        if(Math.floor(sqrt) == sqrt) answer -= sqrt;
        return answer;
    }

    // 3_v1
    public int solution3(int n) {
        int answer = 0;
        double sqrt = Math.sqrt(n);
        for(int i = 1; i <= sqrt; i++) {
            if(n % i == 0) answer += i + (n / i);
        }
        if(sqrt % 1 == 0) answer -= sqrt;
        return answer;
    }
}