package programmers.level2;


import java.util.ArrayList;
import java.util.List;

/**
 * [문제명] 2개 이하로 다른 비트
 * [한줄평] 1차원적으로 값을 하나씩 증가시키면서 확인했더니 시간초과로 실패했고 아예 다른 방식으로 문제를 해결했다. 다른 여러가지 풀이도 공부해보면 좋을 것 같다.
 * v1. 1씩 더해가며 2진수로 변환한 문자열 2개의 각 자리수 비교(실패-10, 11 시간초과)
 * v2. 최초로 발견한 0의 위치에서 "0." -> "10" 으로 바꾸기
 * - 1) 맨 끝 값이 0인 경우, 맨 끝값을 1로 바꾼 값 = 최솟값(...0 -> ...1)
 * - 2) 맨 끝 값이 0이 아닌 경우
 *  - 3-1) 모든 값이 1인 경우, x보다 큰 값은 무조건 자릿수가 하나가 늘어나게 되므로 앞에 0을 하나 붙여준다.
 *  - 3-2) 0이 하나라도 있는 경우, 오른쪽에서 왼쪽으로 0을 최초로 찾은 위치에서 "0." -> "10" 으로 바꾼다.
 *  [예시]
 *  1010 -> 1011(정답)
 *  1111 -> 01111 -> 10111(정답)
 *  1011 -> 1101(정답)
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/77885/solution_groups?language=java">다른 풀이</a>
 */
class Solution77885 {

    public static void main(String[] args) {
        // [3, 11]
        long[] numbers = {2, 7};
        System.out.println(solution(numbers));
    }

    /**
     * @param numbers 정수들이 담긴 배열(1 ≤ 길이 ≤ 100,000)
     * @return numbers의 모든 수들에 대하여 각 수의 f 값을 배열에 차례대로 담아 return
     */
    public static List<Long> solution(long[] numbers) {
        List<Long> answer = new ArrayList<>();
        for(long x : numbers) {
            answer.add(f2(x));
        }
        return answer;
    }

    /**
     * 시간 초과 풀이
     * @param x 0 ≤ x ≤ 10^15
     * @return x보다 크고 x와 비트가 1~2개 다른 수들 중에서 제일 작은 수
     */
    public static long f1(long x) {
        // 1. x -> 2진수 변환
        String s1 = Long.toBinaryString(x);
        System.out.println("s1 = " + s1);
        // 2. x값을 1씩 증가하며 비트값 차이가 1~2개 이내인 수 찾기
        // 모두 1으로 이루어져 있으면 앞에 0 붙이기
        if(!s1.contains("0"))
            s1 = "0" + s1;
        long n = x;
        boolean isFind = false;
        while(!isFind) {
            String s2 = Long.toBinaryString(++n);
            System.out.println("s2 = " + s2);
            int diff = 0;   // 비트가 다른 것의 개수
            for(int i = 0; i < s1.length(); i++) {
                if(s1.charAt(i) != s2.charAt(i)) diff++;
                if(diff >= 3) break;
            }
            if(diff <= 2) isFind = true;
        }
        return n;
    }

    public static long f2(long x) {
        // 1. 10진수(Long) -> 2진수(문자열) 변환
        String s = Long.toString(x, 2);
        int len = s.length();
        // 1. 맨 끝 값이 0인 경우, 1로 바꾼 값 = 최솟값
        if(s.charAt(len - 1) == '0')
            return x + 1;
        // 2. 모든 값이 1인 경우 맨 앞에 0 붙이기
        if(!s.contains("0")) {
            s = "0" + s;
            len++;
        }
        // 3. 오른쪽에서 왼쪽으로 최초로 발견된 0의 위치에서 "0." -> "10" 으로 바꾸기 = 최솟값
        int idx = 0;
        for(int i = len - 1; i >= 0; i--) {
            if(s.charAt(i) == '0') {
                idx = i;
                break;
            }
        }
        s = s.substring(0, idx) + "10" + s.substring(idx + 2, len);
        // 4. 2진수(문자열) -> 10진수(Long) 변환
        return Long.parseLong(s, 2);
    }
}