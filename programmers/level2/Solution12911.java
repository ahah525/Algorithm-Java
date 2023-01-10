package programmers.level2;


/**
 * [문제명] 다음 큰 숫자
 * [풀이시간] 40분(30분 + 10분)
 * [한줄평] 처음에는 규칙을 찾아서 효율적으로 문제를 풀고자 했지만 결국에는 값을 하나씩 증가시키는 방법을 선택했다. 2진수에서 1의 개수를 셀 때 쓰는 메서드가 있다는 사실을 새롭게 알게 되었다.
 * v1. Integer.toString(n, 2) 사용(실패 - 효율성 테스트 모두 시간초과)
 * - Integer.toString() 으로 2진수로 변환한 문자열의 길이 - replaceAll("1", "")로 변환한 문자열의 길이로 1의 개수를 센다.
 * v2. Integer.bitCount() 사용(성공)
 * - Integer.bitCount() 로 1의 개수를 바로 센다
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12911">문제</a>
 * @See <a href="https://fbtmdwhd33.tistory.com/240">풀이 힌트</a>
 */
class Solution12911 {
    public static void main(String[] args) {
        // 83
        System.out.println(solution(78));

        // 23
        System.out.println(solution(15));
    }

    /**
     * @param n 1,000,000 이하의 자연수
     * @return n의 다음 큰 숫자
     * 조건 1. n의 다음 큰 숫자는 n보다 큰 자연수 입니다.
     * 조건 2. n의 다음 큰 숫자와 n은 2진수로 변환했을 때 1의 갯수가 같습니다.
     * 조건 3. n의 다음 큰 숫자는 조건 1, 2를 만족하는 수 중 가장 작은 수 입니다.
     */
    public static int solution(int n) {
        int cnt = Integer.bitCount(n);  // n 을 2진수로 변환했을 때 1의 개수
        int num = n + 1;
        // (n + 1)부터 2진수로 변환했을 때 1의 개수가 같은 값 찾기
        while(true) {
            if(cnt == Integer.bitCount(num))
                break;
            num++;
        }
        return num;
    }
}