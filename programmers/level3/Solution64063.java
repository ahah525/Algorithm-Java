package programmers.level3;


import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 호텔 방 배정
 * [풀이시간] 52분(32분 + 20분) / 27분(20분+7분)
 * [한줄평] 처음에 배열로 풀려고 했는데 k의 크기가 너무 커서 힌트를 얻어 HashMap을 사용했다. 결국 시간초과 문제를 해결하지 못해서 풀이를 봤는데 재귀로 구현한 것을 이해하는 것이 너무 어려웠다.
 * / 이전에 풀었던 아이디어가 떠올랐고 구현은 간단했기 떄문에 저번보다 쉽게 풀었다.
 * 1_v1. HashMap(실패 - 효율성 테스트 1~4,6~7 시간초과)
 * 1_v2. HashMap(성공)
 * [풀이] 재귀로 어떤 방에 배정할 수 있는지 구한다. 거쳐갔던 모든 방들에 대해 (거쳐간 방, 배정된 방 + 1)으로 갱신한다.
 * 2_v1. HashMap(실패-효율성 테스트 3,4,6 시간초과)
 * [풀이] while문으로 어떤 방에 배정할 수 있는지 구한다. (원했던 방, 배정된 방 + 1), (배정된 방, 배정된 방 + 1) 2가지만 갱신했다.
 * 2_v2. HashMap(성공)
 * [풀이] 1_v2 동일
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
        // 1. room 이 비었다면, room 에 바로 배정
        if(!map.containsKey(room)) {
            // 2. room 의 다음 방 번호 갱신 -> room에 요청이 들어오면 (room + 1)번 방을 확인해야 함을 기록
            map.put(room, room + 1);
            return room;
        }
        // 3. room 이 이미 배정되었다면, 다음 빈 방 찾기
        long reserve = findEmptyRoom(map.get(room));
        // 4. room 의 다음 방 번호 갱신 -> room에 요청이 들어오면 (reserve + 1)번 방을 확인해야 함을 기록
        map.put(room, reserve + 1);
        return reserve;
    }

    // 2_v1
//    Map<Long, Long> map;
//    long reserve;
//
//    public long[] solution2(long k, long[] roomNumber) {
//        long[] answer = new long[roomNumber.length];
//        map = new HashMap<>();
//        int i = 0;
//        for(long num : roomNumber) {
//            findRoom(num);
//            answer[i++] = reserve;
//        }
//        return answer;
//    }
//
//    public void findRoom(long num) {
//        if(!map.containsKey(num)) {
//            reserve = num;
//            map.put(reserve, reserve + 1);
//            return;
//        }
//        //
//        findRoom(map.get(num));
//        map.put(num, reserve + 1);
//    }
}