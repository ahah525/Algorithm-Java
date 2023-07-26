package programmers.level3;


import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 호텔 방 배정
 * [풀이시간] (32분)
 * [한줄평]
 * 1_v1. HashMap(실패 - 효율성 테스트 1~4,6~7 시간초과)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/64063">문제</a>
 */
class Solution64063 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    public long[] solution(long k, long[] roomNumber) {
        long[] answer = new long[roomNumber.length];
        // (예약방 번호, 예약할 수 있는 방 번호의 최솟값)
        Map<Long, Long> map = new HashMap<>();
        //
        int i = 0;
        for(long n : roomNumber) {
            if(!map.containsKey(n)) {
                map.put(n, n + 1);
                answer[i++] = n;
                // System.out.println(map);
                continue;
            }
            long next = map.get(n);
            while(true) {
                if(!map.containsKey(next)) {
                    answer[i++] = next;
                    map.put(n, next + 1);
                    map.put(next, next + 1);
                    break;
                }
                next++;
            }
            // System.out.println(map);
        }
        return answer;
    }
}