package programmers.level2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] 프린터
 * [풀이시간] / 16분
 * [한줄평] 큐에서 값을 꺼내지 않고 탐색하기 위해 Iterator 사용법을 검색해서 풀었던 문제였다. / 2번쨰 풀다보니 쉽게 풀 수 있었다.
 * 1_v1. Queue, Iterator(성공)
 * 2_v1. Queue, 향상된 for 문(성공) -> 추천
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42587">문제</a>
 */
class Solution42587 {
    public static void main(String[] args) {
        // 1
        int[] priorities = {2, 1, 3, 2};
        int location = 2;
        int answer = solution(priorities, location);
        System.out.println(answer);
    }

    public static class Docs {
        int location;   // 원래 위치
        int priority;   // 중요도

        public Docs(int location, int priority) {
            this.location = location;
            this.priority = priority;
        }

        public int getLocation() {
            return location;
        }

        public int getPriority() {
            return priority;
        }
    }

    /**
     * @param priorities 문서 중요도
     * @param location 내가 인쇄를 요청한 문서 위치
     * @return 내가 인쇄를 요청한 문서 순서의 실제 인쇄 순서
     */
    public static int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Docs> q = new LinkedList<>();

        // Q 만들기
        for(int i = 0; i < priorities.length; i++) {
            q.add(new Docs(i, priorities[i]));
        }

        while(true) {
            // 1. 맨앞 문서 1개 꺼내기
            Docs now = q.poll();
            // 2. 현재 문서보다 더 중요한 문서가 있는지 검사
            // Iterator 로 큐에 담긴 모든 값 확인하기
            boolean isAdd = false;  // 현재 문서를 다시 넣었는지 여부
            Iterator<Docs> iterator = q.iterator();
            while(iterator.hasNext()) {
                Docs next = iterator.next();
                // 현재 큐에 방금 꺼낸 문서보다 더 중요한 문서가 있으면 맨 뒤로 넣기
                if(now.getPriority() < next.priority) {
                    q.add(now);
                    isAdd = true;
                    break;
                }
            }
            // 3. 현재 문서를 뒤에 다시 넣지 않았으면 빼낸 개수 카운팅
            if(!isAdd) {
                answer++;
                // 몇 번째로 꺼냈는지 체킹해야되는 문서를 찾으면 바로 리턴
                if(now.getLocation() == location) {
                    return answer;
                }
            }
        }
    }

    /**
     * 2번 풀이
     */
    class Doc {
        int index;   // 몇번째 문서인지
        int priority;// 중요도

        Doc(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }

    public int solution2(int[] priorities, int location) {
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
    public boolean canPrint(Queue<Doc> q, int standard) {
        for(Doc d : q) {
            // 대기목록에서 더 중요도가 높은 문서가 1개라도 있으면 false
            if(standard < d.priority) {
                return false;
            }
        }
        return true;
    }
}