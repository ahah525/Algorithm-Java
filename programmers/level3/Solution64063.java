package programmers.level3;


import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 호텔 방 배정
 * [풀이시간] 52분(32분 + 20분) / (20분)
 * [한줄평] 처음에 배열로 풀려고 했는데 k의 크기가 너무 커서 힌트를 얻어 HashMap을 사용했다. 결국 시간초과 문제를 해결하지 못해서 풀이를 봤는데 재귀로 구현한 것을 이해하는 것이 너무 어려웠다.
 * 1_v1. HashMap(실패 - 효율성 테스트 1~4,6~7 시간초과)
 * 1_v2. HashMap(성공)
 * [풀이] 재귀
 * 2_v1. HashMap(실패-효율성 테스트 3,4,6 시간초과)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/64063">문제</a>
 * @See <a href="https://bcp0109.tistory.com/188">풀이 참고</a>
 */
class Solution64063 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2
    Map<Long, Long> map;    // (예약방 번호, 예약할 수 있는 방 번호의 최솟값)
    public long[] solution(long k, long[] roomNumber) {
        long[] answer = new long[roomNumber.length];
        map = new HashMap<>();
        //
        for(int i = 0; i < roomNumber.length; i++) {
            answer[i] = findEmptyRoom(roomNumber[i]);
            // System.out.println(map);
        }
        return answer;
    }

    /**
     * @param room 방 번호
     * @return 현재 방 번호 이상의 번호 중에서 비어있는 방(제일 작은 번호)
     */
    public long findEmptyRoom(long room) {
        // 1. room 이 비었다면, room 의 다음방 = (room + 1)
        if(!map.containsKey(room)) {
            map.put(room, room + 1);
            return room;
        }
        // 2. room 이 이미 배정되었다면, 다음 빈 방 찾기
        long nextRoom = map.get(room);
        long emptyRoom = findEmptyRoom(nextRoom);
        map.put(room, emptyRoom);   //
        return emptyRoom;
    }

    // 2_v1
    public long[] solution2(long k, long[] roomNumber) {
        long[] answer = new long[roomNumber.length];
        Map<Long, Long> map = new HashMap<>();
        int i = 0;
        for(long num : roomNumber) {
            long reserve = num;
            while(map.containsKey(reserve)) {
                reserve = map.get(reserve);
            }
            answer[i++] = reserve;
            //
            map.put(num, reserve + 1);
            map.put(reserve, reserve + 1);
        }
        return answer;
    }
}