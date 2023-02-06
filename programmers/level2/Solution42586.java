package programmers.level2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * [문제명] 기능개발
 * [풀이시간] 25분
 * [한줄평] 큐를 이용해야겠다는 것은 쉽게 떠올렸지만 int 끼리 연산한 결과는 int 라는 것을 간과해서 틀렸던 문제였다.
 * 1_v1. 소요일수 구할 때 / 연산 결과가 int -> 올림 적용 안됨(실패)
 * 1_v2. 소요일수 구할 때 / 연산 결과가 double -> 올림 적용 성공(성공)
 * 2_v1. (성공)
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
        // 1. 각 기능 소요일수 계산해서 큐에 넣기
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < progresses.length; i++) {
            int days = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]);   // 소요 일수
            q.add(days);
        }
        // 2. 배포할 때마다 완성된 기능 개수 구하기
        while(!q.isEmpty()) {
            // 1. 맨 앞에서 배포해야하는 기능 한 개 빼기
            int first = q.poll();   // 맨앞 기능의 소요일수
            int cnt = 1;        // 배포한 기능 개수
            // 2. 뒤에 기능이 이미 완료되었다면 같이 배포하기
            while(!q.isEmpty()) {
                // 2-1. 맨앞 기능 소요일 수 < 뒤에 있는 기능 소요일수 -> 같이 배포X
                if(first < q.peek())
                    break;
                // 2-2. 맨앞 기능 소요일 수 >= 뒤에 있는 기능 소요일수 -> 같이 배포O
                q.poll();
                cnt++;
            }
            // 3. 이번에 배포되는 기능 수 추가
            answer.add(cnt);
        }
        return answer;
    }
}