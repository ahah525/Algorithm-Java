package programmers.level1;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [문제명] 명예의 전당 (1)
 * [풀이시간] 7분
 * [한줄평] 우선순위큐로 풀 수 있는 아주 기초적인 문제다.
 * 1_v1. Priority Queue(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/138477">문제</a>
 */
class Solution138477 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param k 명예의 전당 목록의 점수의 개수
     * @param score 1일부터 마지막 날까지 출연한 가수들의 점수
     * @return 매일 발표된 명예의 전당의 최하위 점수
     */
    public List<Integer> solution(int k, int[] score) {
        List<Integer> answer = new ArrayList<>();
        Queue<Integer> q = new PriorityQueue<Integer>();
        for(int n : score) {
            // 1. 이번 점수 넣기
            q.add(n);
            // 2. 개수가 k 보다 많으면 최하위 1개 빼기
            if(q.size() > k) {
                q.poll();
            }
            // 3. 최하위 점수 리스트에 추가
            answer.add(q.peek());
        }
        return answer;
    }
}