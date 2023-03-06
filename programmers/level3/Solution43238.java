package programmers.level3;


import java.util.Arrays;

/**
 * [문제명] 입국심사
 * [풀이시간] 1시간(30분 + 30분)
 * [한줄평] 이분탐색 문제라는 것을 알아도 어떻게 풀어야할지 감이 안잡혀서 결국 풀이를 보고 이해했던 문제다.
 * 1_v1. PriorityQueue(실패 - 6~9 시간초과)
 * 2_v2. BinarySearch(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43238">문제</a>
 * @See <a href="https://school.programmers.co.kr/questions/7919">풀이 힌트</a>
 * @See <a href="https://swycha.tistory.com/122">풀이 참고</a>
 */
class Solution43238 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param n 입국심사를 기다리는 사람 수
     * @param times 각 심사관이 한 명을 심사하는데 걸리는 시간이 담긴 배열
     * @return 모든 사람이 심사를 받는데 걸리는 시간의 최솟값
     */
    public long solution(int n, int[] times) {
        long answer = 0;
        // 1. 심사 소요 시간 오름차순 정렬
        Arrays.sort(times);
        int m = times.length;   // 심사위원 수
        // 2. 모든 사람이 심사를 받는데 걸리는 시간의 최솟값, 최댓값 초기화
        long start = 1;
        long end = n * (long) times[times.length - 1]; // 최댓값 = 심사 소요 시간이 가장 긴 심사위원이 n명을 심사하는 경우
        // start, end 엇갈릴 때까지 반복
        while(start <= end) {
            long mid = (start + end) / 2;
            // 3. mid 시간에 심사할 수 있는 사람수의 총합 구하기
            long sum = 0;
            for(int time : times) {
                // 해당 심사위원이 mid 시간에 심사할 수 있는 사람수
                sum += mid / time;
            }
            if(sum < n) {
                // 4. mid 시간에서 n명을 모두 심사할 수 없으면, 오른쪽 범위 탐색
                start = mid + 1;
            } else {
                // 5. mid 시간에서 n명 이상을 심사할 수 있으면, 왼쪽 범위 탐색
                end = mid - 1;
                answer = mid;   // mid 값 기록해두기
            }
        }
        return answer;
    }
}