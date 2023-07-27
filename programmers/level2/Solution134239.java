package programmers.level2;


import java.util.ArrayList;
import java.util.List;

/**
 * [문제명] 우박수열 정적분
 * [풀이시간] 36분 / 21분
 * [한줄평] 문제 자체는 어렵지 않았으나 문제에서 offset 의미를 이해해지 못해서 문제 설명을 찾아보고 풀 수 있었다.
 * / 문제에서 말하는 오프셋의 의미만 빨리 이해하면 쉽게 풀 수 있는 문제였다.
 * 1_v1. 누적합(성공)
 * [풀이] [a, b] 구간합 = [0, b] 누적합 - [0, a - 1] 누적합
 * 2_v1. 누적합(성공)
 * [풀이] 1_v1 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/134239">문제</a>
 * @See <a href="https://ksb-dev.tistory.com/214">문제 이해하기</a>
 */
class Solution134239 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1, 2_v1
    /**
     * @param k 우박수의 초항
     * @param ranges 정적분을 구하는 구간들의 목록
     * @return 정적분의 결과 목록
     */
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        double sum = 0; // 누적합
        int prev = k; // 이전 값
        // 1. x=0을 기준으로 y=1이 될 때까지의 누적합 계산
        List<Double> list = new ArrayList<>();
        list.add(sum);
        while(k > 1) {
            if(k % 2 == 0) k /= 2;
            else k = k * 3 + 1;
            sum += (double) (prev + k) / 2;
            list.add(sum);
            prev = k;
        }
        int max = list.size() - 1; // y=1인 점의 x좌표
        for(int i = 0; i < ranges.length; i++) {
            // 2. 시작점, 끝점 계산
            int s = ranges[i][0];
            int e = max + ranges[i][1];
            // 3. 시작점 > 끝점 이면 -1
            if(s > e) {
                answer[i] = -1;
                continue;
            }
            // 4. 시작점~끝점 면적 구하기
            answer[i] = list.get(e) - list.get(s);
        }
        return answer;
    }
}