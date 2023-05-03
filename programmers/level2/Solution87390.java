package programmers.level2;

import java.util.ArrayList;
import java.util.List;

/**
 * [문제명] n^2 배열 자르기
 * [풀이시간] / 20분(15분 + 5분) / (17분)
 * [한줄평] 두번째 풀 때는 바로 어떻게 풀어야겠다고 접근 방법을 떠올리긴했으나 반례를 잡지 못해 한번에 못풀었다. 1번 방법이 더 간결한 풀이다.
 * /
 * 1_v1. (성공)
 * 2_v1. (실패 - 2, 3, 15~20 실패)
 * 2_v2. (성공)
 * [반례] a == c 일 경우, 범위 밖의 값들이 추가되는 문제 발생
 * 3_v1. (실패 - 2~3,15~20 런타임 에러)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/87390">문제</a>
 */
class Solution87390 {
    public static void main(String[] args) {
        int n = 1000000;
        long left = 100000;
        long right = 199999;

        List<Integer> answer = solution(n, left, right);
        // [3,2,2,3]
        System.out.println(answer);
    }

    /**
     * @param n 1 ≤ n ≤ 107
     * @param left 0 ≤ left ≤ right < n2
     * @param right 0 ≤ left ≤ right < n2
     * @return 주어진 과정대로 만들어진 1차원 배열
     */
    public static List<Integer> solution(int n, long left, long right) {
        List<Integer> answer = new ArrayList<>();

        for(long i = left; i <= right; i++) {
            // 행, 열 값 중 최댓값 + 1
            answer.add((int) (Math.max(i / n, i % n) + 1));
        }

        return answer;
    }

    public List<Integer> solution2(int n, long left, long right) {
        List<Integer> answer = new ArrayList<>();
        int a = (int) (left / n);
        int b = (int) (left % n);
        int c = (int) (right / n);
        int d = (int) (right % n);

        if(a == c) {
            for(int i = b; i <= d; i++) {
                answer.add(Math.max(a, i) + 1);
            }
        } else {
            // a행 b열 이후
            for(int i = b; i < n; i++) {
                answer.add(Math.max(a, i) + 1);
            }
            // (a + 1)행 ~ (c - 1)행
            for(int i = a + 1; i <= c - 1; i++) {
                for(int j = 0; j < n; j++) {
                    answer.add(Math.max(i, j) + 1);
                }
            }
            // c행 d열 이전
            for(int i = 0; i <= d; i++) {
                answer.add(Math.max(c, i) + 1);
            }
        }
        return answer;
    }
}