package programmers.level1;


import java.util.*;

/**
 * [문제명] 숫자 짝꿍
 * [풀이시간] 20분
 * [한줄평] Map 으로 풀었는데, 다른 풀이를 보니 배열로 풀었어도 쉬웠을 것 같다.
 * 1_v1. Map(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/131128">문제</a>
 */
class Solution131128 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param X
     * @param Y
     * @return X, Y의 짝꿍
     */
    public static String solution(String X, String Y) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for(char c : X.toCharArray()) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        for(char c : Y.toCharArray()) {
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }

        // 공통 키 찾기
        List<Character> list = new ArrayList<>();
        for(char c1 : map1.keySet()) {
            if(!map2.containsKey(c1)) continue;
            int n = Math.min(map1.get(c1), map2.get(c1));
            for(int i = 0; i < n; i++) {
                list.add(c1);
            }
        }

        if(list.size() == 0) {
            return "-1";
        }
        Collections.sort(list, Collections.reverseOrder());
        if(list.get(0) == '0') {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for(char c : list) {
            sb.append(c);
        }
        return sb.toString();
    }
}