package programmers.level2;


import java.util.LinkedList;
import java.util.Queue;

/**
 * [한줄평] 문제 명에서 큐를 쓰면 된다고 친절하게 알려줘서 구현만하면 됬던 문제였다.
 * v1. -1 리턴 조건 부족(실패: 테스트 11, 28 시간초과)
 * v2. (성공)
 */
class Solution118667 {
    public static void main(String[] args) {
        // 2
        int[] queue1 = {3, 2, 7, 2};
        int[] queue2 = {4, 6, 5, 1};
        int answer1 = solution(queue1, queue2);
        System.out.println(answer1);

        // 7
        int[] queue3 = {1, 2, 1, 2};
        int[] queue4 = {1, 10, 1, 2};
        int answer2 = solution(queue3, queue4);
        System.out.println(answer2);

        // -1
        int[] queue5 = {1, 1};
        int[] queue6 = {1, 5};
        int answer3 = solution(queue5, queue6);
        System.out.println(answer3);
    }

    /**
     * 길이가 같은 두 개의 큐를 나타내는 정수 배열
     * @param queue1
     * @param queue2
     * @return 각 큐의 원소 합을 같게 만들기 위해 필요한 작업의 최소 횟수를 return
     * - 어떤 방법으로도 각 큐의 원소 합을 같게 만들 수 없는 경우, -1을 return
     */
    public static int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long sum1 = 0;    // 두 배열 원소의 총 합
        long sum2 = 0;    // 두 배열 원소의 총 합
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for(int i = 0; i < queue1.length; i++) {
            q1.add(queue1[i]);
            q2.add(queue2[i]);
            sum1 += queue1[i];
            sum2 += queue2[i];
        }

        // 원소의 합이 홀수이면 -1
        if((sum1 + sum2) % 2 != 0) {
            return -1;
        }
        //
        long s1 = sum1;    // q1의 합
        long s2 = sum2;    // q2의 합
        while(!q1.isEmpty() && ! q2.isEmpty()) {
            if(s1 == s2) {
                return answer;
            }

//            if(s1 == sum2 && s2 == sum1) {
//                return -1;
//            }

            if(s1 > s2) {
                // q1 pop -> q2 insert
                int v = q1.poll();
                s1 -= v;
                q2.add(v);
                s2 += v;
            } else if(s1 < s2) {
                // q2 pop -> q1 insert
                int v = q2.poll();
                s2 -= v;
                q1.add(v);
                s1 += v;
            }
            answer++;
        }
        return -1;
    }
}