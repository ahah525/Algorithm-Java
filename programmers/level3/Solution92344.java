package programmers.level3;


/**
 * [문제명] 파괴되지 않은 건물
 * [풀이시간] 9분
 * [한줄평]
 * 1_v1. 구현(실패 - 효울성 테스트 실패)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/92344">문제</a>
 */
class Solution92344 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        //
        for(int[] arr : skill) {
            // [type, r1, c1, r2, c2, degree]
            if(arr[0] == 1) {
                for(int i = arr[1]; i <= arr[3]; i++) {
                    for(int j = arr[2]; j <= arr[4]; j++) {
                        board[i][j] -= arr[5];
                    }
                }
            } else {
                for(int i = arr[1]; i <= arr[3]; i++) {
                    for(int j = arr[2]; j <= arr[4]; j++) {
                        board[i][j] += arr[5];
                    }
                }
            }
        }
        //
        for(int[] row : board) {
            for(int c : row) {
                if(c >= 1) answer++;
            }
        }
        return answer;
    }
}