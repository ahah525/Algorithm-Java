package programmers.level2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * [문제명] 기능개발
 * [한줄평] 큐를 이용해야겠다는 것은 쉽게 떠올렸지만 int 끼리 연산한 결과는 int 라는 것을 간과해서 틀렸던 문제였다.
 * v1. 소요일수 구할 때 / 연산 결과가 int -> 올림 적용 안됨(실패)
 * v2. 소요일수 구할 때 / 연산 결과가 double -> 올림 적용 성공(성공)
 */
class Solution42586 {
    public static void main(String[] args) {
        // [2, 1]
        int[] progresses1 = {93, 30, 55};
        int[] speeds1 = {1, 30, 5};
        List<Integer> answer1 = solution(progresses1, speeds1);
        System.out.println(answer1);

        // 2(반례)
        int[] progresses2 = {95, 94};
        int[] speeds2 = {3, 3};
        List<Integer> answer2 = solution(progresses2, speeds2);
        System.out.println(answer2);
    }

    /**
     *
     * @param progresses 각 작업의 진도
     * @param speeds 각 작업의 개발 속도
     * - 배포되어야 하는 순서대로 작업이 주어짐
     * - 배포는 하루에 한 번만 가능
     * @return 각 배포마다 배포되는 기능의 수
     */
    public static List<Integer> solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList();
        int n = progresses.length;  // 작업 개수

        // 1. 각 작업 끝내는데 걸리는 일 수 계산해서 큐에 넣기
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            // int days = (int) Math.ceil((100 - progresses[i]) / speeds[i]);   // / 결과가 이미 int 값이라 올림을 해도 소용이 없었던 오류
            int days = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]);   // 소요 일수
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