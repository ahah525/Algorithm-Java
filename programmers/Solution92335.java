package programmers;

/**
 * n, k: 양의 정수
 * 1 ≤ n ≤ 1,000,000
 * 3 ≤ k ≤ 10
 * answer: n을 k진수로 바꿨을 때, 변환된 수 안에서 찾을 수 있는 위 조건에 맞는 소수의 개수
 * ----------------------------------------------------------------------
 * - 0P0처럼 소수 양쪽에 0이 있는 경우
 * - P0처럼 소수 오른쪽에만 0이 있고 왼쪽에는 아무것도 없는 경우
 * - 0P처럼 소수 왼쪽에만 0이 있고 오른쪽에는 아무것도 없는 경우
 * - P처럼 소수 양쪽에 아무것도 없는 경우
 *  - 단, P는 각 자릿수에 0을 포함하지 않는 소수입니다.
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

    public static int solution(int n, int k) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();

        // 1. n 10진수 -> k진수 변환
        while(n != 0) {
            sb.append(n % k);
            n /= k;
        }
        String s = sb.reverse().toString();
        // 2. 0을 기준으로 분리
        String[] nums = s.split("0");
        // 3. 소수 판별 개수 세기
        for(String num : nums) {
            if(!num.isEmpty() && isPrime(Integer.parseInt(num))) {
                answer++;
            }
        }
        return answer;
    }

    // 소수인지 판별
    public static boolean isPrime(int n) {
        if(n == 1) return false;

        for(int i = 2; i < n; i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }
}