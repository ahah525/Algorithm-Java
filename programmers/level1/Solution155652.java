package programmers.level1;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * [문제명] 둘만의 암호
 * [풀이시간] 50분(35분 + 15분) / 10분
 * [한줄평] 변환한 문자를 더 효율적으로 구하기 위해 고민하다가 결국 처음에 생각했던 방법으로 해결했다.
 * 1_v1. (실패-3~8, 10~14, 16~19 실패)
 * [풀이] 현재 문자에서 인덱스만큼 뒤의 알파벳을 구하고 skip 했어야할 문자 수만큼 더 뒤로 이동함
 * 1_v2. (성공)
 * [풀이] skip 문자를 HashMap 으로 관리하여 O(1)로 탐색
 * 2_v1. (성공)
 * [풀이] skip 문자를 HashSet 으로 관리하여 O(1)로 탐색
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

    // 2_v1
    Set<Character> set;
    public String solution2(String s, String skip, int index) {
        // 1. skip 에 포함된 알파벳을 집합으로 만들기
        set = new HashSet<>();
        for(char c : skip.toCharArray()) {
            set.add(c);
        }
        // 2. 각 문자를 변환하여 문자열로 리턴
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()) {
            sb.append(change(c, index));
        }
        return sb.toString();
    }

    // 문자를 규칙에 맞게 index 만큼 뒤의 문자로 변환
    public char change(char c, int index) {
        // index 만큼 반복
        for(int i = 0; i < index; i++) {
            while(true) {
                c++;
                // 1. z 를 넘어가면 a 로 변환
                if(c > 'z') c = 'a';
                // 2. skip 에 있는 문자가 아니면 종료
                if(!set.contains(c)) break;
            }
        }
        return c;
    }
}