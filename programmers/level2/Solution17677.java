package programmers.level2;

import java.util.*;

/**
 * [문제명] [1차] 뉴스 클러스터링
 * [풀이시간] / 32분 / 47분
 * [한줄평] 문제 설명대로 풀기만 하면 되는 구현문제였다. 실제로 집합을 구하는 것이 아니라 map 에 원소 개수를 저장해두고 수학적으로 풀었다.
 * / 2번째 풀어서 쉽게 풀수 있었다.
 * / 두 글자씩 끊어서 자를 떄 영문자가 아닌 것은 제외시킨다는 조건을 잘못 이해해서 푸는데 오래걸렸던 문제다.
 * 1_v1. HashMap(성공)
 * [풀이] substring() 으로 길이가 2인 문자열 추출
 * 2_v1. HashMap(성공) -> 추천
 * [풀이] charAt() 2개를 이어 붙여 문자열 추출
 * 3_v1. HashMap(성공)
 * [풀이] 1_v1 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/17677">문제</a>
 */
class Solution17677 {
    public static void main(String[] args) {
        String str1 = "aa1+aa2";
        String str2 = "AAAA12";

        int answer = solution(str1, str2);
        // 43690
        System.out.println(answer);
    }

    /**
     * @param str1 각 문자열의 길이는 2 이상, 1,000 이하
     * @param str2 각 문자열의 길이는 2 이상, 1,000 이하
     * @return 입력으로 들어온 두 문자열의 자카드 유사도(유사도 값은 0에서 1 사이의 실수이므로, 이를 다루기 쉽도록 65536을 곱한 후에 소수점 아래를 버리고 정수부만)
     */
    public static int solution(String str1, String str2) {
        double res = 0;
        int n1 = 0; // 교집합 크기
        int n2 = 0; // 합집합 크기

        // 1. 다중 집합 만들기
        Map<String, Integer> map1 = makeSet(str1);
        Map<String, Integer> map2 = makeSet(str2);

        // 둘다 공 집합이면 65536 리턴
        if(map1.isEmpty() && map2.isEmpty()) {
            return 65536;
        }

        // 2. 유사도 계산
        // 2-1. map1 먼저 계산
        for(String s : map1.keySet()) {
            if(map2.containsKey(s)) {
                // 1) 해당 문자열이 map2에 있으면
                n1 += Math.min(map1.get(s), map2.get(s));   // 교집합에 최솟값
                n2 += Math.max(map1.get(s), map2.get(s));   // 합집합에 최댓값
            } else {
                // 2) 해당 문자열이 map2에 없으면 합집합에 넣기
                n2 += map1.get(s);
            }
        }
        // 2-2. map2 계산
        for(String s : map2.keySet()) {
            if(!map1.containsKey(s)) {
                // 2) 해당 문자열이 map1에 없으면 합집합에 넣기
                n2 += map2.get(s);
            }
        }
        res = (double) n1 / n2;
        return (int) (res * 65536);
    }

    public static Map<String, Integer> makeSet(String str) {
        // 소문자 변환
        str = str.toLowerCase();
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < str.length() - 1; i++) {
            String s = str.substring(i, i + 2);
            // 알파벳 문자로 이루어진 것만 집합에 넣기
            if(s.matches("^[a-zA-Z]*$")) {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }
        return map;
    }

    public int solution2(String str1, String str2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        // 1. 소문자 변환
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        // 2. str1, str2 다중 집합 만들기
        for(int i = 0; i < str1.length() - 1; i++) {
            char c1 = str1.charAt(i);
            char c2 = str1.charAt(i + 1);
            if('a' <= c1 && c1 <= 'z' && 'a' <= c2 && c2 <= 'z') {
                String s = String.valueOf(c1) + String.valueOf(c2);
                map1.put(s, map1.getOrDefault(s, 0) + 1);
            }
        }
        for(int i = 0; i < str2.length() - 1; i++) {
            char c1 = str2.charAt(i);
            char c2 = str2.charAt(i + 1);
            if('a' <= c1 && c1 <= 'z' && 'a' <= c2 && c2 <= 'z') {
                String s = String.valueOf(c1) + String.valueOf(c2);
                map2.put(s, map2.getOrDefault(s, 0) + 1);
            }
        }
        // 3. 둘 다 공집합일 경우 바로 리턴
        if(map1.size() == 0 && map2.size() == 0) {
            return 65536;
        }
        // 4. 자카드 유사도 계산
        int a = 0;  // 교집합 크기
        int b = 0;  // 합집합 크기
        for(String key : map1.keySet()) {
            if(map2.containsKey(key)) {
                // O, O
                a += Math.min(map1.get(key), map2.get(key));
                b += Math.max(map1.get(key), map2.get(key));
            } else {
                // O, X
                b += map1.get(key);
            }
        }
        for(String key : map2.keySet()) {
            if(!map1.containsKey(key)) {
                // X, O
                b += map2.get(key);
            }
        }
        return (int) ((double) a / b * 65536);
    }

    // 3_v1
    public int solution3(String str1, String str2) {
        int NUM = 65536;
        //
        Map<String, Integer> map1 = split(str1);
        Map<String, Integer> map2 = split(str2);
        //
        if(map1.size() >= map2.size()) return (int) (calc(map1, map2) * NUM);
        return (int) (calc(map2, map1) * NUM);

    }

    // s 를 두글자씩 자른 원소 map 구하기
    public Map<String, Integer> split(String s) {
        s = s.toLowerCase();
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length() - 1; i++) {
            String key = s.substring(i, i + 2);
            if(!isValid(key)) continue;
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        return map;
    }

    // s가 영문자로만 이루어져 있는지
    public boolean isValid(String s) {
        for(char c : s.toCharArray()) {
            if('a' > c || c > 'z') return false;
        }
        return true;
    }

    // map1의 크기 >= map2의 크기
    public double calc(Map<String, Integer> map1, Map<String, Integer> map2) {
        int min = 0; // 교집합
        int max = 0; // 합집합
        for(String key : map1.keySet()) {
            int cnt1 = map1.get(key);
            int cnt2 = map2.getOrDefault(key, 0);
            min += Math.min(cnt1, cnt2);
            max += Math.max(cnt1, cnt2);
        }
        for(String key : map2.keySet()) {
            if(map1.containsKey(key)) continue;
            max += map2.get(key);
        }
        if(max == 0) return 1;
        return (double) min / max;
    }
}