package programmers.level1;


/**
 * [문제명] 소수 찾기
 * [풀이시간] 5분 / 5분
 * [한줄평] 아주 쉬운 문제였고 나누는 수를 어디까지 탐색하는지가 효율성 테스트를 통과할 수 있는 방법이다.
 * / 에라토스테네스의 체로 소수를 구하는 방법에 대해 복습할 수 있었던 문제다.
 * 1_v1. 완전탐색(성공)
 * [풀이] 탐색 범위: 2 ~ Math.sqrt(n)
 * 2_v1. 완전탐색(성공) -> 빠름
 * [풀이] 에라토스테네스의 체
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12921">문제</a>
 * @See <a href="https://firework-ham.tistory.com/8">풀이 참고</a>
 */
class Solution12921 {
    public static void main(String[] args) {
        // 4
        System.out.println(solution(10));

        // 3
        System.out.println(solution(5));
    }

    // 1_v1
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

    // 2_v1
    public int solution2(int n) {
        int answer = 0;
        // true: 소수아님, false: 소수
        boolean[] prime = new boolean[n + 1];
        prime[0] = true;
        prime[1] = true;
        for(int i = 2; i <= n; i++) {
            // 1. 소수가 아니면 패스
            if(prime[i]) continue;
            // 2. 소수의 모든 배수는 소수가 아님
            for(int j = 2 * i; j <= n; j += i) {
                prime[j] = true;
            }
        }
        // 3. 소수 개수 세기
        for(int i = 1; i <= n; i++) {
            if(!prime[i]) answer++;
        }
        return answer;
    }
}