package programmers.level2;


import java.util.LinkedList;
import java.util.List;

/**
 * [문제명] [1차] 캐시
 * [풀이시간] 1시간(40분 + 20분)
 * [한줄평] 처음에는 PriorityQueue 로 자동 정렬하면서 가장 오래 전에 사용된 도시를 삭제하는 방식으로 구현해야겠다고 생각했는데 들어온 순서대로 빼면 되는 일이었다.. 꼭 다시 풀어봐야할 문제!
 * v1. PriorityQueue(실패 - 7, 11, 15, 17, 18, 19)
 * v2. PriorityQueue(실패 - 11, 15, 18, 19)
 * - 캐시 크기가 0일 때 예외처리 추가
 * 3. LinkedList(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/17680">문제</a>
 * @See <a href="https://school.programmers.co.kr/questions/4969">반례</a>
 * @See <a href="https://ilmiodiario.tistory.com/106">문제 풀이 힌트</a>
 */
class Solution17680 {
    public static void main(String[] args) {
        // 50
        String[] cities1 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(solution(3, cities1));

        // 52
        String[] cities4 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        System.out.println(solution(5, cities4));

        // 16
        String[] cities5 = {"Jeju", "Pangyo", "NewYork", "newyork"};
        System.out.println(solution(2, cities5));

        // 4
        String[] cities6 = {"a", "b", "c", "a"};
        System.out.println(solution(0, cities6));

        // 16
        String[] cities7 = {"a", "b", "c", "a"};
        System.out.println(solution(5, cities7));
    }

    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        // 1. 캐시 크기가 0일 때 예외처리
        if(cacheSize == 0) {
            return 5 * cities.length;
        }
        // 2. 도시명 소문자 변환
        for(int i = 0; i < cities.length; i++) {
            cities[i] = cities[i].toLowerCase();
        }
        // 3. 실행시간 계산
        List<String> cache = new LinkedList<>();
        for(String city : cities) {
            if(cache.contains(city)) {
                // 1) 캐시 히트
                answer += 1;
                cache.remove(city);
                cache.add(city);
            } else {
                // 2) 캐시 미스
                if(cache.size() == cacheSize) {
                    // 가장 오래전에 사용된 도시 삭제
                    cache.remove(0);
                }
                answer += 5;
                cache.add(city);
            }
        }
        return answer;
    }
}