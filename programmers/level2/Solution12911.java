package programmers.level2;


/**
 * [문제명] 다음 큰 숫자
 * [풀이시간] 40분(30분 + 10분) / 8분, 5분 / 15분(10분, 5분)
 * [한줄평] / 처음에는 규칙을 찾아서 효율적으로 문제를 풀고자 했지만 결국에는 값을 하나씩 증가시키는 방법을 선택했다. 2진수에서 1의 개수를 셀 때 쓰는 메서드가 있다는 사실을 새롭게 알게 되었다.
 * / Integer.bitCount() 함수의 존재를 잊고 2진수로 변환하고 replace()를 이용해 직접 1의 개수를 셌다가 시간초과가 됐다.
 * 1_v1. (실패 - 효율성 테스트 모두 시간초과)
 * [풀이] 1의 개수 = Integer.toString() 으로 2진수로 변환한 문자열의 길이 - replace("1", "")로 변환한 문자열의 길이
 * 1_v2. (성공) -> 추천
 * [풀이] 1의 개수 = Integer.bitCount()
 * 2_v1. (성공)
 * [풀이] 1_v2 동일
 * 2_v2. 비트마스크(성공)
 * 3_v1. (실패 - 효율성 5 시간초과)
 * [풀이] 1_v1 동일
 * 3_v2. (성공)
 * [풀이] 1_v2 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12911">문제</a>
 * @See <a href="https://fbtmdwhd33.tistory.com/240">풀이 힌트</a>
 * @See <a href="https://advenoh.tistory.com/18">비트마스크 풀이</a>
 */
class Solution12911 {
    public static void main(String[] args) {
        // 83
        System.out.println(solution(78));

        // 23
        System.out.println(solution(15));
    }

    // 1_v2, 2_v1
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

    // 2_v2
    public int solution2(int n) {
        int num = n + 1;
        int cnt = bitCount(n);
        while(true) {
            if(bitCount(num) == cnt) break;
            num++;
        }
        return num;
    }

    // n 에서 1의 개수 조회
    public int bitCount(int n) {
        int cnt = 0;
        while(n != 0) {
            cnt += (n & 1);
            n >>= 1;
        }
        return cnt;
    }

    // 3_v1
    public int solution3(int n) {
        int num = n + 1;
        int origin = getOne(n);
        while(true) {
            if(origin == getOne(num)) return num;
            num++;
        }
    }

    public int getOne(int num) {
        String s = Integer.toString(num, 2);
        return s.length() - s.replace("1", "").length();
    }
}