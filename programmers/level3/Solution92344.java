package programmers.level3;


/**
 * [문제명] 파괴되지 않은 건물
 * [풀이시간] 1시간(9분 + 51분) / 30분
 * [한줄평] 풀이를 이해하는 것도 너무 어려웠던 문제였다.
 * / 결국 또 풀이를 보고 이해했던 문제로 꼭 복습이 필요하다.
 * 1_v1. 구현(실패 - 효울성 테스트 1~7 실패)
 * [시간복잡도] O(N * M * K) -> 시간초과
 * 1_v2. 누적합(성공)
 * [시간복잡도] O(K + N * M)
 * [풀이] 원본 배열 + 누적합 배열 = 결과 배열 -> 누적합 배열을 구하는 것이 핵심!
 * 2_v1. 누적합(성공)
 * [풀이] 1_v2 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/92344">문제</a>
 * @See <a href="https://tech.kakao.com/2022/01/14/2022-kakao-recruitment-round-1/">풀이 참고</a>
 */
class Solution92344 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v2, 2_v1
    /**
     * @param board 건물의 내구도를 나타내는 2차원 정수 배열
     * @param skill 적의 공격 혹은 아군의 회복 스킬을 나타내는 2차원 정수 배열
     * @return 적의 공격 혹은 아군의 회복 스킬이 모두 끝난 뒤 파괴되지 않은 건물의 개수
     */
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;
        int[][] sum = new int[n + 1][m + 1];
        for(int[] arr : skill) {
            // [type, r1, c1, r2, c2, degree]
            int type = arr[0];
            int r1 = arr[1];
            int c1 = arr[2];
            int r2 = arr[3];
            int c2 = arr[4];
            int degree = (type == 1) ? -arr[5] : arr[5];
            //
            sum[r1][c1] += degree;
            sum[r1][c2 + 1] -= degree;
            sum[r2 + 1][c1] -= degree;
            sum[r2 + 1][c2 + 1] += degree;
        }
        // 2. 각 행의 왼 -> 오른쪽으로 누적합 계산
        for(int i = 0; i < n; i++) {
            for(int j = 1; j < m; j++) {
                sum[i][j] += sum[i][j - 1];
            }
        }
        // 3. 각 열의 위 -> 아래로 누적합 계산
        for(int j = 0; j < m; j++) {
            for(int i = 1; i < n; i++) {
                sum[i][j] += sum[i - 1][j];
            }
        }
        // 4. board[i][j] + sum[i][j] = k번의 공격/회복을 거친 후 결과값
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] + sum[i][j] >= 1) answer++;
            }
        }
        return answer;
    }
}