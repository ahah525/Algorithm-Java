package programmers.pccp2;


import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] [PCCP 모의고사 #2] 카페 확장
 * [풀이시간] 38분
 * [한줄평] CPU 스케줄링과 비슷한 문제였는데 쉬운듯 약간 복잡했던 문제였다. 다시 풀어보면 좋을 것 같은 문제다.
 * 1_v1. Queue(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/15009/lessons/121689">문제</a>
 */
class Solution121689 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param menu 각 음료들의 제조 시간을 담은 정수 배열
     * @param order 오늘 손님들이 주문한 음료가 순서대로 적힌 배열
     * @param k 새로운 한 명의 손님이 방문하는데 걸리는 시간
     * @return 오늘 카페에 동시에 존재한 손님 수의 최댓값
     */
    public static int solution(int[] menu, int[] order, int k) {
        int max = 0;
        // 음료 제조 끝나는 시각
        Queue<Integer> q = new LinkedList<>();
        int t = 0;      // 현재 시각
        int end = 0;    // 큐의 마지막값(현재 대기중인 음료중 가장 마지막에 끝나는 시각)
        for(int i : order) {
            // 1. 제조 완료된 음료들 큐에서 먼저 빼기
            while(!q.isEmpty()) {
                // 큐에 담긴 음료의 제조 완료시각 > 현재 시각(t)이면 -> 큐에서 뺄 음료 없음
                if(q.peek() > t)
                    break;
                // 큐에 담긴 음료의 제조 완료시각 <= 현재 시각(t)이면 -> 큐에서 음료 빼기
                q.poll();
            }
            // 2. 현재 음료의 제조 완료시각을 계산해 큐에 넣기
            if(q.isEmpty()) {
                // t초 에 이전 음료들이 모두 제조 완료된 상태라면 -> 현재 음료 제조완료시각 = t + 음료 제조시간
                q.add(t + menu[i]);
                end = t + menu[i];
            } else {
                // t초 에 아직 제조 중인 음료가 있다면 -> 현재 음료제조 완료시각 = 마지막 음료 제조완료시각 + 음료제조시간
                q.add(end + menu[i]);
                end = end + menu[i];
            }
            // System.out.println("t=" + t);
            // System.out.println(q);
            // 3. 큐에 넣고나서 대기중인 음료(사람) 개수로 최댓값 갱신
            max = Math.max(max, q.size());
            t += k;
        }
        return max;
    }
}