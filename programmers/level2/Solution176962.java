package programmers.level2;


import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * [문제명] 과제 진행하기
 * [풀이시간] 2시간 35분(2시간 25분 + 10분) / 2시간 20분
 * [한줄평] 너무 어려운 구현 문제여서 꼭 한번 다시 풀어봐야겠다.
 * / 다시 푸는데도 너무 복잡해서 어려웠다.
 * 1_v1. 구현(실패 - 1~10,12,18,20 실패)
 * 1_v2. 구현(성공)
 * [반례] 스택에 있는 과제를 꺼내서 종료했을 경우에도 기록해야 함
 * 2_v1. 구현(성공)
 * [풀이] 1_v2 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/176962">문제</a>
 * @See <a href="https://school.programmers.co.kr/questions/46698">참고</a>
 */
class Solution176962 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    class Assignment {
        String name;
        int start;
        int play;

        Assignment(String name, int start, int play) {
            this.name = name;
            this.start = start;
            this.play = play;
        }

        public String toString() {
            return String.format("[%s, %d, %d]", name, start, play);
        }
    }

    /**
     * @param plans 과제 계획을 담은 이차원 문자열 배열
     * @return 과제를 끝낸 순서대로 이름을 배열에 담아 return
     */
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        // 1. 과제 시작 시각 오름차순 정렬
        Queue<Assignment> pq = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);
        for(String[] plan : plans) {
            pq.add(new Assignment(plan[0], getTime(plan[1]), Integer.parseInt(plan[2])));
        }
        Stack<Assignment> stack = new Stack<>();    // 잠시 멈춘 과제 스택
        // 2. 첫번째 과제로 초기화
        Assignment cur = pq.poll(); // 진행중인 과제
        int curS = cur.start; // 진행중인 과제의 시작 시각
        int idx = 0; // 완료한 과제 개수
        // 3. 모든 과제를 시작할 때까지 반복
        while(!pq.isEmpty()) {
            // 4. 큐에서 과제 1개 꺼내기
            Assignment a = pq.poll();
            // 5. 현재 과제를 시작하기 전에 처리할 일 수행
            if(curS + cur.play <= a.start) {
                // 진행 중인 과제 종료 시각 <= 현재 과제 시작 시각
                // 1. 진행 중인 과제 종료
                answer[idx++] = cur.name;
                // 2. (진행 중인 과제 종료 시각 ~ 현재 과제 시작 시각) 사이 남은 시간 계산
                int remain = a.start - curS - cur.play;
                // 3. 현재 과제 시작까지 남은 시간 동안 스택에 있는 과제 진행
                while(!stack.isEmpty() && remain > 0) {
                    cur = stack.pop();
                    if(remain < cur.play) {
                        // 남은 시간 < 진행 시간, 남은 시간 동안 스택에서 꺼낸 과제 진행, 남은 과제 시간 갱신
                        cur.play -= remain;
                        stack.push(cur);
                        break;
                    }
                    // 남은 시간 >= 진행 시간 -> 스택에서 꺼낸 과제 종료, 남은 시간 갱신
                    answer[idx++] = cur.name;
                    remain -= cur.play;
                }
            } else {
                // 진행 중인 과제가 끝난 시각 > 현재 과제 시작 시각
                // 1. 진행 중인 과제 남은 시각 갱신, 스택으로 이동
                cur.play = (curS + cur.play - a.start);
                stack.push(cur);
            }
            // 6. 현재 과제 시작
            cur = a;
            curS = a.start;
        }
        // 7. 마지막 과제 완료 처리
        answer[idx++] = cur.name;
//        stack.push(cur);
        // 8. 스택에 남은 과제 완료 처리
        while(!stack.isEmpty()) {
            answer[idx++] = stack.pop().name;
        }
        return answer;
    }

    // 시각을 분으로 환산
    public int getTime(String s) {
        String[] t = s.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}