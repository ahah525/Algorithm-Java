package programmers.level1;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 가장 가까운 같은 글자
 * [풀이시간] 10분 / 5분, 3분
 * [한줄평] 쉽게 풀 수 있는 문제였다. / 해시로 푸는 아주 기초 문제로 더 풀어볼 필요는 없다.
 * 1_v1. HashMap(성공)
 * 2_v1. HashMap(성공)
 * [풀이] 알파벳의 최신 위치를 HashMap 에 (알파벳, 인덱스)로 저장
 * 2_v2. (성공) -> 빠름
 * [풀이] 알파벳의 최신 위치를 배열에 저장
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/142086">문제</a>
 */
class Solution142086 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1, 2_v1
    /**
     * @param s 문자열
     * @return s의 각 위치마다 자신보다 앞에 나왔으면서, 자신과 가장 가까운 곳에 있는 같은 글자가 어디 있는지
     */
    public int[] solution(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int[] answer = new int[s.length()];
        int i = 0;
        for(char c : s.toCharArray()) {
            if(map.containsKey(c)) {
                answer[i] = i - map.get(c);
            } else{
                answer[i] = -1;
            }
            map.put(c, i);
            i++;
        }
        return answer;
    }

    // 2_v2
    public int[] solution2(String s) {
        // 1. 알파벳의 최신 위치를 -1로 초기화
        int[] arr = new int[26];
        Arrays.fill(arr, -1);
        int[] answer = new int[s.length()];
        int i = 0;
        for(char c : s.toCharArray()) {
            if(arr[c - 'a'] == -1) {
                answer[i] = -1;
            } else {
                answer[i] = i - arr[c - 'a'];
            }
            arr[c - 'a'] = i++;
        }
        return answer;
    }
}