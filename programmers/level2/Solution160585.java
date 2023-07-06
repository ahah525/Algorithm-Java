package programmers.level2;


/**
 * [문제명] 혼자서 하는 틱택토
 * [풀이시간] 2시간(34분, )
 * [한줄평]
 * 1_v1. (실패 - 1,3,4,7,8,11,14,17,19,21,25,26,29,31,32,34,36,38,39,41,43,45,46,48,49)
 * 1_v2. (실패 - 14,26,46)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/160585">문제</a>
 */
class Solution160585 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    public int solution(String[] board) {
        int[] score = new int[3];
        int[] cnt = new int[3];
        int[][] map = new int[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i].charAt(j) == '.') continue;
                int type = (board[i].charAt(j) == 'O') ? 1 : 2;
                map[i][j] = type;
                cnt[type]++;
            }
        }
        // for(int[] arr : map) {
        //     System.out.println(Arrays.toString(arr));
        // }
        // System.out.println(Arrays.toString(cnt));
        //
        // O개수보다 X개수가 많은 경우, O개수와 X개수가 2개이상 차이나면
        if(cnt[1] < cnt[2] || cnt[1] - cnt[2] > 1) return 0;

        for(int i = 0; i < 3; i++) {
            // 가로 검사
            if(map[i][0] == map[i][1] && map[i][1] == map[i][2]) {
                score[map[i][0]]++;
            }
            // 세로 검사
            if(map[0][i] == map[1][i] && map[1][i] == map[2][i]) {
                score[map[0][i]]++;
            }
        }
        // 대각선 검사
        if(map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
            score[map[1][1]]++;
        }
        if(map[2][0] == map[1][1] && map[1][1] == map[0][2]) {
            score[map[1][1]]++;
        }
        //
        // System.out.println(Arrays.toString(score));
        // O, X 모두 빙고를 만들지 못한 경우
        if(score[1] == 0 && score[2] == 0) return 1;
        if(score[1] == 1 && score[2] == 0) {
            // O가 1빙고 만들고 바로 종료한 경우
            if(cnt[1] == cnt[2] + 1) return 1;
        }
        if(score[1] == 0 && score[2] == 1) {
            // X가 1빙고 만들고 바로 종료한 경우
            if(cnt[1] == cnt[1]) return 1;
        }
        if(score[1] == 2 && score[2] == 0) {
            if(cnt[1] == cnt[2] + 1) return 1;
        }
        return 0;
    }
}