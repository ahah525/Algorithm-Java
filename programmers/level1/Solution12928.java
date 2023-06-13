package programmers.level1;

/**
 * [문제명] 약수의 합
 * [풀이시간] / (3분)
 * [한줄평] /
 * 1_v1. (성공)
 * >> 약수 구하는 방법
 * 1. 1 ~ n 반복
 * 2. 1 ~ n/2 반복(추천)
 * 3. 1 ~ 루트 n 반복
 * 2_v1. 수학(실패 - 3~5,8,10,13 실패)
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
}