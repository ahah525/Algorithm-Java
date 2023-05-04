package programmers.level2;


/**
 * [문제명] 가장 큰 정사각형 찾기
 * [풀이시간] 1시간(15분 + 5분 + 40분) / 26분
 * [한줄평] DP 로 풀어야함을 알아도 못풀어서 결국 문제 풀이를 보고 이해했던 문제였다.
 * 1_v1. (실패 - 정확성 테스트 8, 14 실패, 효율성 테스트 모두 실패)
 * 1_v2. (실패 - 효율성 테스트 모두 실패)
 * - 오직 1로 이루어진 가장 큰 정사각형을 구해야 함!!
 * [반례] 모두 0으로 이루어진 경우
 * 1_v3. DP(성공)
 * - d[i][j]: (i, j)가 포함될 수 있는 가장 큰 정사각형 한 변의 길이
 * [점화식] d[i][j] = Math.min(Math.min(d[i][j - 1], d[i - 1][j - 1]), d[i - 1][j]) + 1
 * 2_v1. DP(실패 - 정확성 테스트 1 실패)
 * 2_v2. DP(성공)
 * [반례] 행/열의 길이가 1인 경우 최댓값 갱신 안되는 문제 해결
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12905">문제</a>
 * @See <a href="https://school.programmers.co.kr/questions/12304">반례</a>
 * @See <a href="https://small-stap.tistory.com/40">풀이 참고</a>
 */
class Solution12905 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v3
    /**
     * @param board 1와 0로 채워진 표
     * @return 표에서 1로 이루어진 가장 큰 정사각형을 찾아 넓이를 return
     */
    public int solution(int [][]board) {
        int answer = 0; // 1로 이루어진 가장 큰 정사각형 한 변의 길이
        int n = board.length;
        int m = board[0].length;
        int[][] d = new int[n + 1][m + 1];  // 따로 초기화하지 않기 위해 (n + 1) * (m + 1) 로 생성
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                // 1. 0인 경우 패스
                if(board[i - 1][j - 1] == 0) continue;
                // 2. 1인 경우 (←, ↖, ︎↑) 좌표 중 최솟값 + 1
                d[i][j] = Math.min(Math.min(d[i][j - 1], d[i - 1][j - 1]), d[i - 1][j]) + 1;
                answer = Math.max(answer, d[i][j]);
            }
        }
        return answer * answer;
    }

    // 2_v1
    public int solution2(int[][] board) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] != 1) {
                    board[i][j] = 0;
                    continue;
                }
                if(i != 0 && j != 0) {
                    int min = Math.min(board[i][j - 1], Math.min(board[i - 1][j - 1], board[i - 1][j]));
                    board[i][j] += min;
                }
                answer = Math.max(answer, board[i][j]);
            }
        }
        return answer * answer;
    }
}