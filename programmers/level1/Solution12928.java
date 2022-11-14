package programmers.level1;

/**
 * 약수를 구할 때는
 * v1. 1 ~ n 반복
 * v2. 1 ~ n/2 반복
 * v3. 1 ~ 루트 n 반복
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

        for(int i = 1; i <= n; i++) {
            if(n % i == 0) {
                answer += i;
            }
        }

        return answer;
    }
}