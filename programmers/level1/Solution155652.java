package programmers.level1;


import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 둘만의 암호
 * [풀이시간] 50분(35분 + 15분)
 * [한줄평] 변환한 문자를 더 효율적으로 구하기 위해 고민하다가 결국 처음에 생각했던 방법으로 해결했다.
 * 1_v1. (실패-3~8, 10~14, 16~19 실패)
 * [접근법] 현재 문자에서 인덱스만큼 뒤의 알파벳을 구하고 skip 했어야할 문자 수만큼 더 뒤로 이동함
 * 1_v2. HashMap, while문(성공) -> 직관적인 방법
 * [접근법] while 문으로 인덱스만큼 뒤의 알파벳을 구함
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/155652">문제</a>
 */
class Solution155652 {
    public static void main(String[] args) {
        // "happy"
        System.out.println(solution("aukks", "wbqd", 5));

        // "zzzzzz"
        System.out.println(solution("zzzzzz", "abcdefghijklmnopqrstuvwxy", 6));
    }

    // 1_v2
    /**
     * @param s 문자열(5 ≤ s의 길이 ≤ 50)
     * @param skip 문자열(1 ≤ skip의 길이 ≤ 10)
     * @param index 자연수(1 ≤ index ≤ 20)
     * @return 위 규칙대로 s를 변환한 결과
     */
    public static String solution(String s, String skip, int index) {
        // 1. skip 해야할 문자를 map 에 저장
        Map<Character, Integer> map = new HashMap<>();
        for(char c : skip.toCharArray()) {
            map.put(c, 0);
        }
        // 2. 한 문자씩 변환하기
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()) {
            int n = 0;      // 건너뛴 횟수(skip 문자로 건너뛴 것은 카운트하지 않음)
            char tmp = c;   // 변환한 문자
            // 3. n번 만큼 건너뛰기
            while(n < index) {
                // 4. 다음 문자로 건너뛰기
                tmp++;
                if(tmp > 'z') tmp = 'a';
                // 5. 해당 문자가 skip 해야할 문자가 아닐 때만 카운트
                if(!map.containsKey(tmp)) n++;
            }
            // 6. 변환한 문자 추가
            sb.append(tmp);
        }
        return sb.toString();
    }
}