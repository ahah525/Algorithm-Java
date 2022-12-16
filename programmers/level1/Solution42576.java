package programmers.level1;


import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 완주하지 못한 선수
 * [풀이시간] 10분
 * [한줄평] HashMap 을 사용해서 푸는 아주 간단한 문제였는데, 프로그래머스에서 메서드 자동완성을 쓰지 않으니 map 메서드명이 약간 헷갈렸던 문제였다.
 * v1. HashMap 1개(성공)
 * - HashMap: 완주자 명단(이름, 수)
 * @See <a href="https://school.programmers.co.kr/learn/courses/15008/lessons/42576">문제</a>
 */
class Solution42576 {
    public static void main(String[] args) {
        // "leo"
        String[] participant1 = {"leo", "kiki", "eden"};
        String[] completion1 = {"eden", "kiki"};
        System.out.println(solution(participant1, completion1));

        // "vinko"
        String[] participant2 = {"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] completion2 = {"josipa", "filipa", "marina", "nikola"};
        System.out.println(solution(participant1, completion2));

        // "mislav"
        String[] participant3 = {"mislav", "stanko", "mislav", "ana"};
        String[] completion3 = {"stanko", "ana", "mislav"};
        System.out.println(solution(participant3, completion3));
    }

    public static String solution(String[] participant, String[] completion) {
        // 1. 완주자 명단(이름, 수) 구하기
        Map<String, Integer> map = new HashMap<>();
        for(String name : completion) {
            map.put(name, map.getOrDefault(name, 0) + 1);
        }
        // 2. 참가자 이름이 완주자 명단에 있는지 검사
        for(String name : participant) {
            int n = map.getOrDefault(name, 0);
            if(n == 0)
                return name;
            map.put(name, n - 1);
        }
        return "";
    }
}