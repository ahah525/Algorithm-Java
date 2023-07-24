package programmers.level1;


import java.util.*;

/**
 * [문제명] 숫자 짝꿍
 * [풀이시간] 20분 / 17분 / 10분
 * [한줄평] Map 으로 풀었는데, 다른 풀이를 보니 배열로 풀었어도 쉬웠을 것 같다.
 * / Map 으로 풀까하다가 배열로 풀면 더 간단할 것 같아 배열로 풀었는데, 0 반례를 처리하는데 시간이 좀 걸렸다.
 * / 쉽게 풀 수 있는 문제로 더 이상 안풀어봐도 될 문제다.
 * 1_v1. (성공)
 * [풀이] Map에 X,Y의 (숫자, 개수) 저장
 * 2_v1. (성공) -> 빠름
 * [풀이] 배열에 X,Y의 숫자 개수 저장
 * [반례] "0000" -> "0" 반환
 * 3_v1. 문자열(성공)
 * [풀이] HashMap에 X,Y의 (문자, 개수) 저장, StringBuilder 에 제일 큰 문자부터 추가
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/131128">문제</a>
 */
class Solution131128 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
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

    // 2_v1
    public String solution2(String X, String Y) {
        int[] x = new int[10];
        int[] y = new int[10];
        for(char c : X.toCharArray()) {
            x[c - '0']++;
        }
        for(char c : Y.toCharArray()) {
            y[c - '0']++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 9; i >= 0; i--) {
            for(int j = 0; j < Math.min(x[i], y[i]); j++) {
                sb.append(i);
            }
        }
        if(sb.length() == 0) return "-1";
        // 0 여러개 값에 대한 처리
        if(sb.charAt(0) == '0') return "0";
        return sb.toString();
    }

    // 3_v1
    public String solution3(String X, String Y) {
        Map<Character, Integer> mapX = new HashMap<>();
        Map<Character, Integer> mapY = new HashMap<>();
        for(char c : X.toCharArray()) {
            mapX.put(c, mapX.getOrDefault(c, 0) + 1);
        }
        for(char c : Y.toCharArray()) {
            mapY.put(c, mapY.getOrDefault(c, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for(char c = '9'; c >= '0'; c--) {
            if(!mapX.containsKey(c) || !mapY.containsKey(c)) continue;
            int min = Math.min(mapX.get(c), mapY.get(c));
            for(int i = 0; i < min; i++) {
                sb.append(c);
            }
        }
        if(sb.length() == 0) return "-1";
        if(sb.charAt(0) == '0') return "0";
        return sb.toString();
    }
}