package programmers.level3;


import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [문제명] 야근 지수
 * [풀이시간] 45분(30분 + 15분)
 * [한줄평] 아이디어 자체는 빨리 떠올렸는데, 구현부분에서 조금 오래걸렸던 것 같다.
 * v1. 단순 반복문(실패 - 정확성 9, 13 만 성공, 효율성 모두 실패)
 * - while 문 내에서 Arrays.sort() 로 매번 정렬을 하다보니 시간초과가 난 것 같다.
 * v2. PriorityQueue(성공)
 * - 이 문제의 핵심은 최댓값이 최소가 되도록 최댓값을 계속 1개씩 줄여주는 것이다!!
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/">문제</a>
 */
class Solution12927 {
    public static void main(String[] args) {
        // 12
        int[] works1 = {4, 3, 3};
        System.out.println(solution(4, works1));

        // 6
        int[] works2 = {2, 1, 2};
        System.out.println(solution(1, works2));

        // 0
        int[] works3 = {1, 1};
        System.out.println(solution(3, works3));
    }

    /**
     * @param n 퇴근까지 남은 시간
     * @param works 각 일에 대한 작업량
     * @return 야근 피로도를 최소화한 값
     */
    public static long solution(int n, int[] works) {
        long answer = 0;
        // 1. 내림차순 정렬
        Queue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        for(int w : works) {
            q.add(w);
        }
        // 2. 최댓값이 최소가 되게 만들기(남은 시간을 다 쓰거나 최댓값이 0 인 경우 종료)
        while(n > 0) {
            int max = q.poll();
            if(max == 0) break;
            q.offer(max - 1);
            n--;
        }
        // 3. 야근 피로도 계산해서 리턴
        for(int w : q) {
            answer += Math.pow(w, 2);
        }
        return answer;
    }
}