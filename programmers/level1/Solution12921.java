package programmers.level1;


/**
 * [문제명] 소수 찾기
 * [풀이시간] 5분
 * [한줄평] 아주 쉬운 문제였고 나누는 수를 어디까지 탐색하는지가 효율성 테스트를 통과할 수 있는 방법이다.
 * v1. (성공)
 * - 소수를 찾을 때는 (2 ~ 루트 n) 범위까지 탐색해야 효율적이다.
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12921">문제</a>
 */
class Solution12921 {
    public static void main(String[] args) {
        // 4
        System.out.println(solution(10));

        // 3
        System.out.println(solution(5));
    }

    /**
     * @param n 2이상 1000000이하의 자연수
     * @return 1부터 입력받은 숫자 n 사이에 있는 소수의 개수를 반환
     */
    public static int solution(int n) {
        int answer = 0;
        // 1은 소수가 아니므로 2 ~ n 탐색
        for(int i = 2; i <= n; i++) {
            if(isPrime(i))
                answer++;
        }
        return answer;
    }

    // n 이 소수인지 판별하는 메서드
    public static boolean isPrime(int n) {
        // 시간 단축을 위해 n이 아닌 루트 n까지 나누어보면 된다
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0)
                return false;
        }
        return true;
    }
}