package programmers.level2;


import java.util.Arrays;

/**
 * [문제명] 2개 이하로 다른 비트
 * [풀이시간] / 40분(20분 + 20분)
 * [한줄평] 1차원적으로 값을 하나씩 증가시키면서 확인했더니 시간초과로 실패했고 아예 다른 방식으로 문제를 해결했다. 다른 여러가지 풀이도 공부해보면 좋을 것 같다. / 역시나 완전탐색으로 접근해서 실패했던 문제다.
 * 1_v1. 완전탐색(실패-10, 11 시간초과)
 * [접근법] 1씩 더해가며 2진수로 변환한 문자열 2개의 각 자리수 비교
 * 1_v2. 최초로 발견한 0의 위치에서 "0." -> "10" 으로 바꾸기
 * - 1. 짝수(맨 끝 값이 0인 경우), 맨 끝값을 1로 바꾼 값 = 최솟값(...0 -> ...1)
 * - 2. 홀수(맨 끝 값이 1인 경우)
 *  - 2.1. 모든 값이 1인 경우, x보다 큰 값은 무조건 자릿수가 하나가 늘어나게 되므로 앞에 0을 하나 붙여준다.
 *  - 2.2. 0이 하나라도 있는 경우, 오른쪽에서 왼쪽으로 0을 최초로 찾은 위치에서 "01" -> "10" 으로 바꾼다.
 *  [예시]
 *  1. 1010 -> 1011(정답)
 *  2.1. 1111 -> 01111 -> 10111(정답)
 *  2.2. 1011 -> 1101(정답)
 *  2_v1. 완전탐색(실패 - 10, 11 시간초과)
 *  2_v2. (성공)
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/77885">문제</a>
 * <a href="https://ilmiodiario.tistory.com/108">풀이 힌트</a>
 */
class Solution77885 {

    public static void main(String[] args) {
        // [3, 11]
        System.out.println(Arrays.toString(solution(new long[]{2, 7})));
    }

    /**
     * @param numbers 정수들이 담긴 배열(1 ≤ 길이 ≤ 100,000)
     * @return numbers의 모든 수들에 대하여 각 수의 f 값을 배열에 차례대로 담아 return
     */
    public static long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        int idx = 0;
        for(long x : numbers)
            answer[idx++] = f(x);
        return answer;
    }

    // 1_v2, 2_v2
    public static long f(long x) {
        // 1. 짝수인 경우, 맨 마지막 비트를 0 -> 1로 바꾼 값(즉, x + 1)이 정답
        if(x % 2 == 0) return x + 1;
        // 2. 홀수인 경우
        // 3. 10진수(Long) -> 2진수(문자열) 변환
        String s = Long.toString(x, 2);
        // 4. 모두 1인 경우, 앞에 0 붙이기
        if(x == Math.pow(2, s.length()) - 1)
            s = "0" + s;
        // 5. 문자열 끝에서부터 최초로 발견되는 "01" -> "10" 바꾸기
        int idx = s.lastIndexOf("01");
        s = s.substring(0, idx) + "10" + s.substring(idx + 2);
        // 6. 2진수(문자열) -> 10진수(Long) 변환하여 리턴
        return Long.parseLong(s, 2);
    }

    // 1_v1, 2_v1
    public static long f2(long x) {
        // 1. x -> 2진수 변환
        String a = Long.toString(x, 2);
        // 2. 모두 1으로 이루어져 있으면 앞에 0 붙이기
        if(!a.contains("0"))
            a = "0" + a;
        // 2. x값을 1씩 증가하며 비트값 차이가 1~2개 이내인 가장 작은 수 찾기
        while(true) {
            String b = Long.toString(++x, 2);
            int cnt = 0;
            for(int j = 0; j < a.length(); j++) {
                if(a.charAt(j) != b.charAt(j)) cnt++;
                if(cnt == 3) break;
            }
            if(cnt != 3) return x;
        }
    }
}