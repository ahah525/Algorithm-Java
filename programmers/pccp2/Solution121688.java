package programmers.pccp2;


import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [문제명] [PCCP 모의고사 #2] 신입사원 교육
 * [풀이시간] 4분 / 3분
 * [한줄평] 보자마자 로직이 떠올라서 우선순위 큐로 바로 풀었던 쉬운 문제였다. / 우선순위 큐로 푸는 아주 쉬운 기초 문제다.
 * 1_v1. Priority Queue(성공)
 * 2_v1. Priority Queue(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/15009/lessons/121688">문제</a>
 */
class Solution121688 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    public static int solution(int[] ability, int number) {
        // 오름차순 정렬
        Queue<Integer> q = new PriorityQueue<>();
        for(int n : ability) {
            q.add(n);
        }
        // 최솟값 2개를 빼서 더한 값을 넣기 반복
        for(int i = 0; i < number; i++) {
            int sum = q.poll() + q.poll();
            q.add(sum);
            q.add(sum);
        }
        // 총합
        int answer = 0;
        for(int n : q) {
            answer += n;
        }
        return answer;
    }
}