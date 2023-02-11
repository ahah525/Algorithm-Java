package programmers.level1;


import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 가장 가까운 같은 글자
 * [풀이시간] 10분
 * [한줄평] 쉽게 풀 수 있는 문제였다.
 * 1_v1. (성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/142086">문제</a>
 */
class Solution142086 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

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
}