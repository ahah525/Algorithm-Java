package programmers.pccp2;


import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] [PCCP 모의고사 #2] 카페 확장
 * [풀이시간] 38분 / 20분 / 42분(20분 + 22분) / 13분
 * [한줄평] CPU 스케줄링과 비슷한 문제였는데 쉬운듯 약간 복잡했던 문제였다. 다시 풀어보면 좋을 것 같은 문제다.
 * / 다시 풀어봐도 좋을 문제다.
 * /
 * / 문제 설명대로 구현만 하면 쉽게 풀 수 있는 문제다.
 * 1_v1. LinkedList(성공)
 * [풀이] 큐에 각 음료의 제조 완료 시각을 저장함
 * 2_v1. LinkedList(성공)
 * [풀이] 1_v1 동일
 * 3_v1. LinkedList(실패 - 4,~5,7~8,10 실패)
 * [풀이] 큐에 각 음료 제조 시간을 저장함
 * 3_v2. LinkedList(성공)
 * [풀이] 1_v1 동일
 * 4_v1. LinkedList(성공)
 * [풀이] 1_v1 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/15009/lessons/121689">문제</a>
 */
class Solution121689 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1, 2_v1, 3_v2
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
        // 1. 음료를 k초 마다 큐에 넣기
        for(int i : order) {
            // 2. t초 기준 큐에 제조 완료된 음료들이 있으면 빼기
            while(!q.isEmpty()) {
                // 큐에 담긴 음료의 제조 완료시각 > 현재 시각(t)이면 -> 아직 제조 중인 음료는 큐에서 뺄 수 없으므로 break
                if(q.peek() > t) break;
                // 큐에 담긴 음료의 제조 완료시각 <= 현재 시각(t)이면 -> 이미 제조된 음료이므로 큐에서 빼기
                q.poll();
            }
            // 3. 현재 음료의 제조 완료시각을 계산해 큐에 넣기
            if(q.isEmpty()) {
                // t초 에 이전 음료들이 모두 제조 완료된 상태라면 -> 현재 음료 제조완료시각 = t + 음료 제조시간
                end = t + menu[i];
            } else {
                // t초 에 아직 제조 중인 음료가 있다면 -> 현재 음료제조 완료시각 = 마지막 음료 제조완료시각 + 음료제조시간
                end += menu[i];
            }
            q.add(end);
            // 4. 큐에 넣고나서 대기중인 음료(사람) 개수로 최댓값 갱신
            max = Math.max(max, q.size());
            // System.out.println("t=" + t);
            // System.out.println(q);
            t += k;
        }
        return max;
    }

    // 4_v1
    public int solution2(int[] menu, int[] order, int k) {
        int answer = 0;
        // 예상 종료시각
        int t = 0;
        int prevEnd = 0;    // 이전 음료 완료 시각
        Queue<Integer> q = new LinkedList<>();
        for(int num : order) {
            // 1. 대기하는 사람 중 이미 음료 제조가 완료된 사람은 out
            while(!q.isEmpty()) {
                if(q.peek() > t) break;
                q.poll();
            }
            // 2. 현재 음료의 예상 종료 시각 계산, 큐에 넣기, 이전 음료 완료 시각 갱신
            int nowEnd = Math.max(prevEnd, t) + menu[num];
            q.add(nowEnd);
            prevEnd = nowEnd;
            answer = Math.max(answer, q.size());
            t += k;
        }
        return answer;
    }
}