package programmers.level2;

import java.util.*;

/**
 * [문제명] [1차] 뉴스 클러스터링
 * [한줄평] 문제 설명대로 풀기만 하면 되는 구현문제였다. 실제로 집합을 구하는 것이 아니라 map 에 원소 개수를 저장해두고 수학적으로 풀었다.
 * v1. map 2개, 수학으로 풀이(성공)
 * str1, str2: 2이상 1,000 이하의 길이인 문자열
 * answer: 두 문자열 사이의 유사도
 * ---------------------------------------
 * 두 집합 A, B 사이의 자카드 유사도 J(A, B)
 * = 두 집합의 교집합 크기 / 두 집합의 합집합 크기
 * 집합 A와 집합 B가 모두 공집합일 경우, 나눗셈이 정의되지 않으니 따로 J(A, B) = 1
 * ---------------------------------------
 * 문자열 사이의 자카드 유사도 계산
 * - 입력으로 들어온 문자열은 두 글자씩 끊어서 다중집합의 원소로 만든다. (영문자로 된 글자 쌍만 유효하고, 기타 공백이나 숫자, 특수 문자가 들어있는 경우는 그 글자 쌍을 버린다.)
 * - 다중집합 원소 사이를 비교할 때, 대문자와 소문자의 차이는 무시한다.
 */
class Solution17677 {
    public static void main(String[] args) {
        String str1 = "aa1+aa2";
        String str2 = "AAAA12";

        int answer = solution(str1, str2);
        // 43690
        System.out.println(answer);
    }

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
}