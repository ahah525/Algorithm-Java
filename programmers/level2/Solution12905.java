package programmers.level2;


/**
 * [문제명] 가장 큰 정사각형 찾기
 * [풀이시간] (15분 + 5분 + )
 * [한줄평]
 * 1_v1. (실패 - 정확성 테스트 8, 14 실패, 효율성 테스트 모두 실패)
 * 2_v1. (실패 - 효율성 테스트 모두 실패)
 * - 오직 1로 이루어진 가장 큰 정사각형을 구해야 함!!
 * [반례] 모두 0으로 이루어진 경우
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12905">문제</a>
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12905">반례</a>
 */
class Solution12905 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    public int solution(int [][]board) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                for(int k = 1; k <= Math.min(n, m); k++) {
                    if(board[i][j] == 0 || !isOk(i, j, k, board, n, m))
                        break;
                    answer = Math.max(answer, k);
                }
            }
        }
        return answer * answer;
    }

    // (x, y)에서 변의 길이가 size 인 정사각형을 만들수 있는지 여부
    public boolean isOk(int x, int y, int size, int[][] board, int n, int m) {
        // 범위를 벗어나면
        if(x + size - 1 >= n || y + size - 1 >= m) {
            return false;
        }
        // 세로축 검사
        for(int i = 0; i < size; i++) {
            if(board[x + i][y + size - 1] != 1) {
                return false;
            }
        }
        // 가로축 검사
        for(int i = 0; i < size - 1; i++) {
            if(board[x + size - 1][y + i] != 1) {
                return false;
            }
        }
        return true;
    }
}