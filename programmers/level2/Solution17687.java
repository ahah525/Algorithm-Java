package programmers.level2;


/**
 * [문제명] [3차] n진수 게임
 * [풀이시간] 30분
 * [한줄평] 처음 구현했을 때 마지막 입력예제처럼 m과 p의 값이 같을 경우에 대한 처리를 따로 해주지 않아 무한루프를 돌았다. 문제 구현자체는 쉬웠는데 사소한 오류를 고치느라 시간이 좀 더 걸렸다.
 * v1. (성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/17687">문제</a>
 */
class Solution17687 {
    public static void main(String[] args) {
        // "0111"
        System.out.println(solution(2, 4, 2, 1));

        // "02468ACE11111111"
        System.out.println(solution(16, 16, 2, 1));

        // "13579BDF01234567"
        System.out.println(solution(16, 16, 2, 2));
    }

    /**
     * @param n 진법
     * @param t 미리 구할 숫자의 갯수
     * @param m 게임에 참가하는 인원
     * @param p 튜브의 순서
     * @return 튜브가 말해야 하는 숫자 t개를 공백 없이 차례대로 나타낸 문자열. (단, 10~15는 각각 대문자 A~F로 출력한다.)
     */
    public static String solution(int n, int t, int m, int p) {
        int num = 0;    // 10진수
        int cnt = 0;    // 사람들이 말한 숫자들의 총개수
        StringBuilder sb = new StringBuilder(); // 튜브가 말해야하는 숫자 t개를 이어붙인 문자열
        p %= m;         // p = m일 경우를 고려해 나머지연산한 값으로 변경해야함!

        l1:
        while(true) {
            // 1. num 을 n 진법 문자열로 만들기
            String s = Integer.toString(num, n);
            // 2. 해당 문자열의 1자리씩 진행
            for(char c : s.toCharArray()) {
                cnt++;
                // t개를 다 구했으면 종료
                if(sb.length() == t) {
                    break l1;
                }
                // p번째 수이면 기록
                if(cnt % m == p) {
                    sb.append(c);
                }
            }
            num++;
        }
        // 3. 대문자로 변환하여 리턴
        return sb.toString().toUpperCase();
    }
}