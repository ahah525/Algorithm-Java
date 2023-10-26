package programmers.level4;


import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] [3차] 자동완성
 * [풀이시간] (52분)
 * [한줄평]
 * 1_v1. (실패-14,19,21,22 메모리 초과)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/17685">문제</a>
 */
class Solution17685 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    public int solution(String[] words) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();
        for(String w : words) {
            StringBuilder sb = new StringBuilder();
            for(char c : w.toCharArray()) {
                sb.append(c);
                map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            }
        }
        //
        for(String w : words) {
            boolean find = false;
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < w.length(); i++) {
                sb.append(w.charAt(i));
                if(map.get(sb.toString()) == 1) {
                    answer += (i + 1);
                    find = true;
                    break;
                }
            }
            if(!find) answer += w.length();
        }
        // System.out.println(map);
        return answer;
    }
}