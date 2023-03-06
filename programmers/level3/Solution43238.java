package programmers.level3;


import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [문제명] 입국심사
 * [풀이시간] (30분)
 * [한줄평]
 * 1_v1. PriorityQueue(실패 - 6~9 시간초과)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43238">문제</a>
 */
class Solution43238 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    class Test {
        int play;// 심사 시간
        long end; // 심사 끝나는 시각

        Test(int play, long end) {
            this.play = play;
            this.end = end;
        }
    }

    public long solution(int n, int[] times) {
        long answer = 0;
        // 1. (심사 끝나는 시각 + 심사 시간) 오름차순 정렬
        Queue<Test> pq = new PriorityQueue<>((o1, o2) -> ((o1.end + o1.play) - (o2.end + o2.play)) > 0 ? 1 : -1);
        for(int t : times) {
            pq.add(new Test(t, 0));
        }
        // 2.
        for(int i = 0; i < n; i++) {
            Test t = pq.poll();
            pq.add(new Test(t.play, t.end + t.play));
            answer = Math.max(answer, t.end + t.play);
        }
        return answer;
    }
}