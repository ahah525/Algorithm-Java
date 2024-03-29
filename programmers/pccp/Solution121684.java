package programmers.pccp;


/**
 * [문제명] [PCCP 모의고사 #2] 체육대회
 * [풀이시간] 40분 / 8분
 * [한줄평] n 개에서 r 개를 순서있게 뽑는 순열 문제였는데, 처음에 조합이라고 착각해서 풀다가 시간을 허비했다. / 순열 기초 문제였다.
 * 1_v1. 순열(성공)
 * [접근법] 무엇이 뽑혔는지는 기록할 필요가 없고 합만 필요하기 때문에 path 대신 sum 을 사용
 * 2_v1. DFS, 완전탐색(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/15008/lessons/121684">문제</a>
 */
class Solution121684 {
    public static void main(String[] args) {
        // 210
        int[][] ability1 = {{40, 10, 10}, {20, 5, 0}, {30, 30, 30}, {70, 0, 70}, {100, 100, 100}};
        System.out.println(solution(ability1));

        // 60
        int[][] ability2 =  {{20, 30}, {30, 20}, {20, 30}};
        System.out.println(solution(ability2));
    }

    // 1_v1
    static int max = 0;

    /**
     * @param ability 반 학생들의 각 종목에 대한 능력치를 나타내는 2차원 정수 배열
     * @return 선발된 대표들의 해당 종목에 대한 능력치 합의 최대값
     */
    public static int solution(int[][] ability) {
        max = 0;
        boolean[] visited = new boolean[ability.length];
        dfs(0, visited, 0, ability, ability[0].length, ability.length);

        return max;
    }

    /**
     * n개에서 r개 순서있게 뽑기(순열)
     * @param depth 선택한 개수
     * @param visited 선택 여부
     * @param sum 선택한 능력치의 합
     * @param ability 반 학생들의 능력치
     * @param n 종목수
     * @param r 학생수
     */
    public static void dfs(int depth, boolean[] visited, int sum, int[][] ability, int n, int r) {
        if(depth == n) {
            max = Math.max(max, sum);
            return;
        }
        for(int i = 0; i < r; i++) {
            if(!visited[i]) {
                visited[i] = true;  // 방문 처리
                dfs(depth + 1, visited, sum + ability[i][depth], ability, n, r);
                visited[i] = false; // 방문 처리 취소
            }
        }
    }
}