package programmers.level2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [문제명] 프린터
 * [풀이시간] / 16분
 * [한줄평] Iterator 사용법을 검색해서 풀었던 문제였다. / 2번째라서 쉽게 풀었지만 우선순위큐를 이용한 풀이방법이 있다는 것도 알게되었다.
 * 1_v1. Queue, Iterator(성공)
 * 2_v1. Queue, 향상된 for 문(성공)
 * 2_v2. PriorityQueue(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42587">문제</a>
 * @See <a href="https://fbtmdwhd33.tistory.com/223">다른 풀이 참고</a>
 */
class Solution42587 {
    public static void main(String[] args) {
        // 1
        int[] priorities = {2, 1, 3, 2};
        int location = 2;
        int answer = solution2(priorities, location);
        System.out.println(answer);
    }

    /**
     * 2_v1
     */
    static class Doc {
        int index;   // 몇번째 문서인지
        int priority;// 중요도

        Doc(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }

    public static int solution2(int[] priorities, int location) {
        int answer = 0; // 인쇄한 문서 개수
        // 1. 대기큐 초기화
        Queue<Doc> q = new LinkedList<>();
        for(int i = 0; i < priorities.length; i++) {
            q.add(new Doc(i, priorities[i]));
        }
        while(!q.isEmpty()) {
            // 2. 가장 앞에 있는 문서 꺼내기
            Doc j = q.poll();
            if(canPrint(q, j.priority)) {
                // 3. 해당 문서를 앤쇄할 수 있으면 카운팅
                answer++;
                // 4. 인쇄 요청한 문서면 탈출
                if(j.index == location)
                    break;
            } else {
                // 5. 해당 문서를 인쇄할 수 없으면 대기큐 마지막에 넣기
                q.add(j);
            }
        }
        return answer;
    }

    /**
     * @param q 문서 대기목록
     * @param standard 가장 앞에 있는 문서의 중요도
     * @return 가장 앞에 있는 문서를 프린트할 수 있는지 여부
     */
    public static boolean canPrint(Queue<Doc> q, int standard) {
        for(Doc d : q) {
            // 대기목록에서 더 중요도가 높은 문서가 1개라도 있으면 false
            if(standard < d.priority) {
                return false;
            }
        }
        return true;
    }

    /**
     * 2_v3
     */
    public int solution3(int[] priorities, int location) {
        int answer = 0;
        // 1. 문서 중요도 내림차순 정렬
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int p : priorities) {
            pq.add(p);
        }
        l1:
        while(!pq.isEmpty()) {
            for(int i = 0; i < priorities.length; i++) {
                // 2. 대기큐에서 꺼낸 중요도값과 같은 값을 가진 문서를 찾으면 큐에서 삭제, 카운팅
                if(pq.peek() == priorities[i]) {
                    // System.out.println(i);
                    pq.poll();
                    answer++;
                    // 3. 꺼내서 삭제한 문서가 내가 앤쇄를 요청한 문서면 탈출
                    if(i == location) {
                        break l1;
                    }
                }
            }
        }
        return answer;
    }
}