package programmers.level2;


import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * [문제명] 과제 진행하기
 * [풀이시간] (2시간 25분)
 * [한줄평]
 * 1_v1. 구현(실패 - 1~10,12,18,20 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/">문제</a>
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

    public String[] solution(String[][] plans) {

        String[] answer = new String[plans.length];
        // 1. 과제 시작 시각 오름차순 정렬
        Queue<Assignment> pq = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);
        for(String[] plan : plans) {
            pq.add(new Assignment(plan[0], getTime(plan[1]), Integer.parseInt(plan[2])));
        }
        System.out.println(pq);
        // 잠시 멈춘 과제 스택
        Stack<Assignment> stack = new Stack<>();
        Assignment cur = pq.poll(); // 진중인 과제
        int t = cur.start; // 진행중인 과제의 시작 시각
        int idx = 0; // 완료한 과제 개수

        // 모든 과제를 완료할 때까지 반복
        while(!pq.isEmpty()) {
            Assignment a = pq.poll();
            // System.out.println("진행중:" + cur);
            // System.out.println("잠시대기:" + stack);
            // System.out.println("t:" + t);
            // System.out.println("선택:" + a);
            // System.out.println("정답:" + Arrays.toString(answer));

            if(t + cur.play < a.start) {
                System.out.println("1");
                // 진행 중인 과제 종료 시각 < 현재 과제 시작 시각
                // 1. 진행 중인 과제 종료
                answer[idx++] = cur.name;
                // 2. 현재 과제 시작까지 남은 시간 동안 스택에 있는 과제 진행
                int remain = a.start - t - cur.play;
                while(!stack.isEmpty() && remain > 0) {
                    Assignment pop = stack.pop();
                    cur = pop;
                    if(remain < pop.play) {
                        // 남은 시간 < 진행 시간, 남은 시간 동안 진행
                        pop.play -= remain;
                        stack.push(pop);
                        break;
                    }
                    remain -= pop.play;
                }
                // 3. 현재 과제 시작
                cur = a;
                t = cur.start;
            } else if(t + cur.play == a.start) {
                System.out.println("2");
                // 진행 중인 과제가 끝난 시각 == 현재 과제 시작 시각
                // 1. 진행 중인 과제 종료
                answer[idx++] = cur.name;
                // 2. 현재 과제 시작
                cur = a;
                t = a.start;
            } else {
                System.out.println("3");
                // 진행 중인 과제가 끝난 시각 > 현재 과제 시작 시각
                // 1. 진행 과제 남은 시각 갱신, 스택으로 이동
                cur.play = (t + cur.play - a.start);
                stack.push(cur);
                // 2. 현재 과제 시작
                cur = a;
                t = a.start;
            }
            // System.out.println();
        }

        answer[idx++] = cur.name;
        while(!stack.isEmpty()) {
            answer[idx++] = stack.pop().name;
        }
        return answer;
    }

    public int getTime(String s) {
        String[] t = s.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}