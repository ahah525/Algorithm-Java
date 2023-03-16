package programmers.level2;


/**
 * [문제명] 하노이의 탑
 * [풀이시간] 1시간(45분 + 15분)
 * [한줄평] 접근 자체를 어떻게 해야할지 막막해서 풀이를 보고 이해하는데 더 오랜시간을 썼던 문제였다.
 * 1_v1. 재귀(실패)
 * 1_v2. 재귀(성공)
 * [해결] 2번 과정(from 에 있는 1개의 선반을 to 로 옮기기)는 재귀를 돌릴 필요가 없다
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12946">문제</a>
 * @See <a href="https://shoark7.github.io/programming/algorithm/tower-of-hanoi">하노이의 탑 원리</a>
 */
class Solution12946 {
    public static void main(String[] args) {
        //
        System.out.println(solution(3));
    }

    // 1_v2
    static int idx;

    /**
     * @param n 1번 기둥에 있는 원판의 개수
     * @return n개의 원판을 3번 원판으로 최소로 옮기는 방법
     */
    public static int[][] solution(int n) {
        idx = 0;
        int cnt = (int) Math.pow(2, n) - 1; // 최소 이동 횟수 = 2^n - 1
        int[][] answer = new int[cnt][2];

        hanoi(n, 1, 3, 2, answer);

        return answer;
    }

    // n 개의 선반을 via 를 거쳐 from -> to 로 옮기는 방법
    public static void hanoi(int n, int from , int to, int via, int[][] answer) {
        if(n == 1) {
            // 옮겨야 하는 선반이 1개일 때 (from, to) 기록
            answer[idx++] = new int[]{from, to};
            return;
        }
        // 1. (n-1)개의 선반을 to 를 거쳐 from -> via 로 옮기기
        hanoi(n - 1, from, via, to, answer);
        // 2. from 에 있는 1개의 선반을 to 로 옮기기
        answer[idx++] = new int[]{from, to};
        // 3. (n-1)개의 선반을 from 을 거쳐 via -> to 로 옮기기
        hanoi(n - 1, via, to, from, answer);
    }
}