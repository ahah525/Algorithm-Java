package programmers.level2;


import java.util.*;

/**
 * [문제명] [1차] 캐시
 * [풀이시간] (40분)
 * [한줄평]
 * v1. PriorityQueue(실패 - 7, 11, 15, 17, 18, 19)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/17680">문제</a>
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
    }

    static class City {
        String name;
        int order;

        public City(String name, int order) {
            this.name = name;
            this.order = order;
        }
    }

    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<City> q = new PriorityQueue<>((o1, o2) -> o1.order - o2.order);    // 캐시에 있는 도시이름
        // 1. 도시명 소문자 변환
        for(int i = 0; i < cities.length; i++) {
            cities[i] = cities[i].toLowerCase();
        }
        // 2.
        for(int i = 0; i < cities.length; i++) {
            String city = cities[i];
            boolean isHit = false;
            for(City c : q) {
                if(city.equals(c.name)) {
                    isHit = true;
                    answer += 1;
                    c.order = i;
                }
            }
            if(!isHit) {
                if(q.size() == cacheSize) {
                    q.poll();
                }
                answer += 5;
                q.add(new City(city, i));
            }
        }
        return answer;
    }
}