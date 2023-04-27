package programmers.level1;


import java.util.ArrayList;
import java.util.List;

/**
 * [문제명] 모의고사
 * [풀이시간] 15분 / 20분
 * [한줄평] 있는 그대로 구현하면 쉽게 풀 수 있는 문제였다. / for문으로 모든 경우의 수를 탐색하면 해결되는 문제였다.
 * 1_v1. 구현(성공)
 * 2_v1. 완전탐색(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42840">문제</a>
 */
class Solution42840 {
    public static void main(String[] args) {
        // [1]
        int[] answers1 = {1,2,3,4,5};
        System.out.println(solution(answers1));

        // [1,2,3]
        int[] answers2 = {1,3,2,4,2};
        System.out.println(solution(answers2));
    }

    // 1_v1, 2_v1
    /**
     * @param answers 1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열
     * @return 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return(가장 높은 점수를 받은 사람이 여럿일 경우, return하는 값을 오름차순 정렬)
     */
    public static int[] solution(int[] answers) {
        int[] a = {1, 2, 3, 4, 5};                  // 5
        int[] b = {2, 1, 2, 3, 2, 4, 2, 5};         // 8
        int[] c = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};   // 10
        // 1. 3명의 정답 맞힌 개수 구하기
        int[] scores = new int[3];
        for(int i = 0; i < answers.length; i++) {
            // 1번
            if(a[i % 5] == answers[i]) scores[0]++;
            // 2번
            if(b[i % 8] == answers[i]) scores[1]++;
            // 3번
            if(c[i % 10] == answers[i]) scores[2]++;
        }
        // 2. 최댓값을 가진 사람을 찾아 리스트로 반환
        List<Integer> list = new ArrayList<>();
        int max = Math.max(Math.max(scores[0], scores[1]), scores[2]);
        for(int i = 0; i < 3; i++) {
            if(scores[i] == max)
                list.add(i + 1);
        }
        // 3. 리스트로 배열로 변환
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}