package programmers.level1;


/**
 * [문제명] 삼총사
 * [풀이시간] 13분
 * [한줄평] n 개 중에 3개를 뽑는 아주 쉬운 조합 문제였으나 변수 하나를 잘못써서 조금 오래걸렸다. 사실 3중 for 문으로도 풀 수 있지만 재귀 풀이를 연습하고자 재귀로 풀었다.
 * v1. 조합-재귀(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/131705">문제</a>
 */
class Solution131705 {
    public static void main(String[] args) {
        // 2
        int[] number1 = {-2, 3, 0, 2, -5};
        System.out.println(solution(number1));

        // 5
        int[] number2 = {-3, -2, -1, 0, 1, 2, 3};
        System.out.println(solution(number2));

        // 0
        int[] number3 = {-1, 1, -1, 1};
        System.out.println(solution(number3));
    }

    static int answer;

    /**
     * @param number 한국중학교 학생들의 번호를 나타내는 정수 배열
     * @return 학생들 중 삼총사를 만들 수 있는 방법의 수
     * - 이 학교 학생 3명의 정수 번호를 더했을 때 0이 되면 3명의 학생은 삼총사라고 합니다.
     */
    public static int solution(int[] number) {
        answer = 0;
        dfs(0, 0, 0, number, number.length);
        return answer;
    }

    /**
     * @param depth 깊이
     * @param start 탐색 시작인덱스
     * @param sum 누적합
     * @param number 학생 번호 배열
     * @param n 학생수
     */
    public static void dfs(int depth, int start, int sum, int[] number, int n) {
        if(depth == 3) {
            if(sum == 0)
                answer++;
            return;
        }
        for(int i = start; i < n; i++) {
            dfs(depth + 1, i + 1, sum + number[i], number ,n);
        }
    }
}