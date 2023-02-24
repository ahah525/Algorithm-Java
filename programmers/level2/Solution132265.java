package programmers.level2;


import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 롤케이크 자르기
 * [풀이시간] 13분
 * [한줄평] 배열이나 Map 을 이용해 쉽게 풀 수 있는 문제였다.
 * 1_v1. Map 2개(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/132265">문제</a>
 */
class Solution132265 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param topping 롤케이크에 올려진 토핑들의 번호를 저장한 정수 배열
     * @return 롤케이크를 공평하게 자르는 방법의 수
     */
    public int solution(int[] topping) {
        int answer = 0;
        // (토핑종류, 토핑개수)
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for(int t : topping) {
            map2.put(t, map2.getOrDefault(t, 0) + 1);
        }
        // 경우의 수 세기
        for(int i = 0; i < topping.length; i++) {
            // map2 에서 꺼내서 map1 에 넣기
            int t = topping[i];
            map2.put(t, map2.get(t) - 1);
            if(map2.get(t) == 0)
                map2.remove(t);
            map1.put(t, map1.getOrDefault(t, 0) + 1);
            // map1 의 종류수와 map2의 종류수가 같을때 카운트
            if(map1.size() == map2.size()) {
                answer++;
            }
        }
        return answer;
    }
}