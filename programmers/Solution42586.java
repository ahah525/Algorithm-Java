package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * progresses: 각 작업의 진도
 * speeds: 각 작업의 개발 속도
 * answer: 각 배포마다 배포되는 기능의 수
 * -----------------------
 * - 배포되어야 하는 순서대로 작업이 주어짐
 * - 배포는 하루에 한 번만 가능
 *
 * 반례)
 * 95, 94
 * 3, 3
 * 2
 */
class Solution42586 {
    public static void main(String[] args) {
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};

        List<Integer> answer = solution(progresses, speeds);
        // [2, 1]
        System.out.println(answer);
    }

    public static List<Integer> solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList();
        int n = progresses.length;  // 작업 개수

        // 1. 각 작업 끝내는데 걸리는 일 수 계산해서 큐에 넣기
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            int days = (int) Math.ceil((100 - progresses[i]) / speeds[i]);   // 소요 일수
            q.add(days);
        }

        // 2. 배포할 때마다 완성된 기능 개수 구하기
        while(true) {
            if(q.isEmpty()) {
                break;
            }
            // 1. 맨 앞에서 배포해야하는 작업 한 개 빼기
            int v = q.poll();
            int cnt = 1;
            // 2. 뒤에 작업이 이미 완료되었다면 같이 배포하기
            while(true) {
                if(q.isEmpty()) {
                    break;
                }
                int nv = q.peek();
                if(v < nv) {
                    break;
                }
                q.poll();
                cnt++;
            }
            answer.add(cnt);
        }
        return answer;
    }
}