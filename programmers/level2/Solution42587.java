package programmers.level2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [문제명] 프린터
 * [풀이시간] / 16분 / 28분 / 11분
 * [한줄평] Iterator 사용법을 검색해서 풀었던 문제였다.
 * / 2번째라서 쉽게 풀었지만 우선순위큐를 이용한 풀이방법이 있다는 것도 알게되었다.
 * / 쉬웠는데 배열을 잘못봐서 문제를 해결하는데 오래걸렸다.
 * / 그냥 있는대로 구현하기만 하면 되는 아주 쉬운 문제로 다시 풀어볼 필요는 없을 것 같다.
 * 1_v1. LinkedList, Iterator(성공)
 * 2_v1. LinkedList, for-each(성공)
 * [풀이] LinkedList에 (인덱스, 우선순위)를 저장하고 큐의 맨 앞에 있는 프로세스보다 우선순위가 더 큰 것이 있는지를 for-each로 하나씩 확인한다.
 * 2_v2. PriorityQueue(성공)
 * [풀이]
 * 3_v1. LinkedList(성공)
 * [풀이] 2_v1 동일
 * 4_v1. LinkedList(성공)
 * [풀이] LinkedList에 (우선순위)만 저장하고 큐의 맨 앞에 있는 프로세스보다 우선순위가 더 큰 것이 있는지를 for-each로 하나씩 확인한다.
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42587">문제</a>
 * @See <a href="https://fbtmdwhd33.tistory.com/223">다른 풀이 참고</a>
 */
class Solution42587 {
    public static void main(String[] args) {
        // 1
        System.out.println(solution(new int[]{2, 1, 3, 2}, 2));

        // 5
        System.out.println(solution(new int[]{1, 1, 9, 1, 1, 1}, 0));
    }

    // 2_v1, 3_v1
    static class J {
        int idx;      // 문서 원래 순서
        int priority; // 중요도

        J(int idx, int priority) {
            this.idx = idx;
            this.priority = priority;
        }
    }

    public static int solution(int[] priorities, int location) {
        int answer = 0; // 인쇄한 문서 개수
        // 1. 대기큐 초기화
        Queue<J> q = new LinkedList<>();
        for(int i = 0; i < priorities.length; i++) {
            q.add(new J(i, priorities[i]));
        }
        // 큐가 비어있지 않을 동안 반복
        while(!q.isEmpty()) {
            // 2. 가장 앞에 있는 문서 꺼내기
            J first = q.poll();
            // 3. 해당 문서를 프린트 할 수 있는지 검사
            boolean canPrint = true;
            for(J j : q) {
                if(first.priority < j.priority) {
                    canPrint = false;
                    break;
                }
            }
            if(canPrint) {
                // 4. 프린트할 수 있으면 개수 카운팅
                answer++;
                if(first.idx == location) break;    // 답을 찾았으면 바로 종료
            } else {
                // 5. 프린트할 수 없으면 뒤에 넣기
                q.add(new J(first.idx, first.priority));
            }
        }
        return answer;
    }

    // 2_v3
    public int solution2(int[] priorities, int location) {
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

    // 4_v1
    public int solution3(int[] priorities, int location) {
        int answer = 1;
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < priorities.length; i++) {
            q.add(i);
        }
        while(true) {
            int now = priorities[q.peek()];
            boolean canPlay = true;
            for(int loc : q) {
                if(now < priorities[loc]) {
                    canPlay = false;
                    break;
                }
            }
            int loc = q.poll();
            if(canPlay) {
                if(loc == location) break;
                answer++;
            } else {
                q.add(loc);
            }
        }
        return answer;
    }
}