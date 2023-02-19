package programmers.level2;


/**
 * [문제명] 멀쩡한 사각형
 * [풀이시간] 23분
 * [한줄평] 그냥 수학문제라고 생각하고 풀었던 문제였다.
 * 1_v1. 수학(성공)
 * - 밑변의 길이를 1, 2, ... w 로
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/62048">문제</a>
 */
class Solution62048 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    /**
     * @param w 가로의 길이
     * @param h 세로의 길이
     * @return 사용할 수 있는 정사각형의 개수
     */
    public long solution(int w, int h) {
        long answer = 0;
        // 밑변의 길이(i)가 1~w 일 때, 높이의 길이(j) 개수 만큼 뺀다
        for(int i = 1; i <= w; i++) {
            double j = (double) h / w * i;
            answer += Math.floor(h - j);
        }
        return answer * 2;
    }
}