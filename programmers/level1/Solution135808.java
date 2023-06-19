package programmers.level1;


import java.util.Arrays;

/**
 * [문제명] 과일 장수
 * [풀이시간] 12분 / 6분
 * [한줄평] 정렬하는 것만 떠올린다면 쉽게 풀 수 있는 문제였다. / 정렬을 하면 쉽게 풀 수 있는 문제였다.
 * 1_v1. 정렬(성공)
 * 2_v1. 정렬(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/135808">문제</a>
 */
class Solution135808 {
    public static void main(String[] args) {
        // 8
        int[] score1 = {1, 2, 3, 1, 2, 3, 1};
        System.out.println(solution(3, 4, score1));

        // 33
        int[] score2 = {4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2};
        System.out.println(solution(4, 3, score2));
    }

    // 1_v1
    /**
     * @param k 사과의 최대 점수
     * @param m 한 상자에 들어가는 사과의 수
     * @param score 사과들의 점수
     * @return 과일 장수가 얻을 수 있는 최대 이익
     */
    public static int solution(int k, int m, int[] score) {
        int answer = 0;
        // 내림차순하고 m 개씩 분리했을 때 최솟값 찾아서 더하기
        Arrays.sort(score);
//        Arrays.sort(score, Collections.reverseOrder()); // 불가능
        for(int i = score.length - m; i >= 0; i-= m) {
            answer += score[i] * m;
        }
        return answer;
    }

    // 2_v1
    public int solution2(int k, int m, int[] score) {
        int answer = 0;
        Arrays.sort(score);
        int i = score.length - m;
        while(i >= 0) {
            answer += score[i] * m;
            i -= m;
        }
        return answer;
    }
}