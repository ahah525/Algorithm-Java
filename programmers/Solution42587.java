package programmers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * priorities: 문서 중요도
 * location: 내가 인쇄를 요청한 문서 위치
 * answer: 내가 인쇄를 요청한 문서 순서의 실제 인쇄 순서
 * -----------------------
 * 1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
 * 2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
 * 3. 그렇지 않으면 J를 인쇄합니다.
 *
 * 반례)
 *
 */
class Solution42587 {
    public static void main(String[] args) {
        int[] priorities = {2, 1, 3, 2};
        int location = 2;

        int answer = solution(priorities, location);
        // 1
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
    public static int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Docs> q = new LinkedList<>();

        // Q 만들기
        for(int i = 0; i < priorities.length; i++) {
            q.add(new Docs(i, priorities[i]));
        }

        //
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
}