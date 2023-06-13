package programmers.level2;


import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [문제명] 디펜스 게임
 * [풀이시간] 1시간 20분
 * [한줄평] 처음에 DP로 접근했다가 점화식 세우는데 실패해서 결국 풀이를 보고 해결했는데, 답을 봐도 어려운 문제였다.
 * 1_v1. PriorityQueue(성공)
 * [접근법] 실제로 몇번째 라운드에 무족권을 사용했는지는 중요하지 않다!
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/142085">문제</a>
 * @See <a href="https://yoo-dev.tistory.com/20">풀이 참고</a>
 */
class Solution142085 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    /**
     * @param n 준호가 처음 가지고 있는 병사의 수
     * @param k 사용 가능한 무적권의 횟수
     * @param enemy 매 라운드마다 공격해오는 적의 수가 순서대로 담긴 정수 배열
     * @return 준호가 몇 라운드까지 막을 수 있는지 return
     */
    public int solution(int n, int k, int[] enemy) {
        int cnt = n;    // 병사수
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < enemy.length; i++) {
            // 1. 이번 라운드의 적의 수 만큼 병사 사용
            cnt -= enemy[i];
            pq.add(enemy[i]);
            // 2. 병사수가 0보다 작으면 무족권 사용
            if(cnt < 0) {
                if(k > 0) {
                    // 3. 무족권이 남아있으면 적의 수가 가장 많은 라운드에 사용(해당 라운드의 병사 복원)
                    cnt += pq.poll();
                    k--;
                } else {
                    // 4. 무족권이 없으면 종료
                    return i;
                }
            }
        }
        return enemy.length;
    }
}