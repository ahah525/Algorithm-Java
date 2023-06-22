package programmers.level1;


import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [문제명] 명예의 전당 (1)
 * [풀이시간] 7분 / 5분
 * [한줄평] 우선순위큐로 풀 수 있는 아주 기초적인 문제다. / 아주 기초적인 최소힙 문제였다.
 * 1_v1. Priority Queue(성공)
 * 2_v1. Priority Queue(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/138477">문제</a>
 */
class Solution138477 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1, 2_v1
    /**
     * @param k 명예의 전당 목록의 점수의 개수
     * @param score 1일부터 마지막 날까지 출연한 가수들의 점수
     * @return 매일 발표된 명예의 전당의 최하위 점수
     */
    public int[] solution(int k, int[] score) {
        Queue<Integer> pq = new PriorityQueue<>();
        int[] answer = new int[score.length];
        int i = 0;
        for(int n : score) {
            pq.add(n);
            if(pq.size() > k) pq.poll();
            answer[i++] = pq.peek();
        }
        return answer;
    }
}