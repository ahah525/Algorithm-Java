package programmers;

import java.util.ArrayList;
import java.util.List;

class Solution87390 {
    public static void main(String[] args) {
        int n = 1000000;
        long left = 100000;
        long right = 199999;

        List<Integer> answer = solution(n, left, right);
        // [3,2,2,3]
        System.out.println(answer);
    }

    public static List<Integer> solution(int n, long left, long right) {
        List<Integer> answer = new ArrayList<>();

        for(long i = left; i <= right; i++) {
            // 행, 열 값 중 최댓값 + 1
            answer.add((int) (Math.max(i / n, i % n) + 1));
        }

        return answer;
    }
}