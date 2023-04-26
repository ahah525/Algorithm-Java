package programmers.level2;


import java.util.*;

/**
 * [문제명] 귤 고르기
 * [풀이시간] 15분 / 7분
 * [한줄평] 나름 쉽게 풀 수 있는 문제였다. / Map 의 value 기준으로 내림차순 정렬한다는 아이디어만 떠올리면 쉽게 풀 수 있는 문제였다.
 * 1_v1. HashMap(성공) -> 빠름
 * [접근법] 최솟값 = 같은 크기의 개수가 많은 것부터 꺼내기
 * 2_v1. HashMap(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/138476">문제</a>
 */
class Solution138476 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    /**
     * @param k 경화가 한 상자에 담으려는 귤의 개수
     * @param tangerine 귤의 크기를 담은 배열
     * @return 경화가 귤 k개를 고를 때 크기가 서로 다른 종류의 수의 최솟값
     */
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        // 1. map 초기화(크기, 개수)
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : tangerine) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        // 2. 개수 내림차순 정렬
        List<Integer> values = new ArrayList<>(map.values());
        Collections.sort(values, Collections.reverseOrder());
        // 3. 차례로 k개 꺼내기
        for(int n : values) {
            if(k <= 0) break;
            k -= n;
            answer++;
        }
        return answer;
    }

    // 2_v1
    public int solution2(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : tangerine) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        // map value 내림차순 정렬
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys, (o1, o2) -> {
            return map.get(o2) - map.get(o1);
        });
        int i = 0;
        int cnt = 0;
        while(cnt < k) {
            cnt += map.get(keys.get(i));
            i++;
        }
        return i;
    }
}