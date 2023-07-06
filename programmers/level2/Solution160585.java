package programmers.level2;


/**
 * [문제명] 혼자서 하는 틱택토
 * [풀이시간] 2시간(34분, )
 * [한줄평] 쉬운 문제라고 생각했는데, 반례를 잡는게 너무너무 어려웠다.
 * 1_v1. 구현(실패 - 1,3,4,7,8,11,14,17,19,21,25,26,29,31,32,34,36,38,39,41,43,45,46,48,49)
 * 1_v2. 구현(실패 - 14,26,46)
 * 1_v3. 구현(성공)
 * [불가능한 경우]
 * 1. O 개수 < X 개수
 * 2. O 개수와 X 개수의 차가 2이상
 * [가능한 경우]
 * 1. O, X 모두 빙고를 만들지 못한 경우
 * 2. O가 1빙고 만들고 바로 종료한 경우
 * 3. X가 1빙고 만들고 바로 종료한 경우
 * 4. O가 마지막 공격때 한 번에 2빙고 만들고 바로 종료한 경우
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/160585">문제</a>
 * @See <a href="https://inspirer9.tistory.com/443">풀이 참고</a>
 */
class Solution160585 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v3
    public int solution(String[] board) {
        // O, X 의 점수, 개수
        int[] score = new int[3];
        int[] cnt = new int[3];
        int[][] map = new int[3][3];
        // 1. 맵 초기화, OX 개수 세기
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board[i].charAt(j) == '.') continue;
                int type = (board[i].charAt(j) == 'O') ? 1 : 2;
                map[i][j] = type;
                cnt[type]++;
            }
        }
        // 2. O 개수보다 X 개수가 많은 경우, O 개수와 X 개수가 2개이상 차이나면 => 불가능
        if(cnt[1] < cnt[2] || cnt[1] - cnt[2] > 1) return 0;
        // 3. 빙고 개수 세기
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
        // 4. O, X 모두 빙고를 만들지 못한 경우 => 가능
        if(score[1] == 0 && score[2] == 0) return 1;
        if(score[1] == 1 && score[2] == 0) {
            // O가 1빙고 만들고 바로 종료한 경우 => 가능
            if(cnt[1] == cnt[2] + 1) return 1;
        }
        if(score[1] == 0 && score[2] == 1) {
            // X가 1빙고 만들고 바로 종료한 경우 => 가능
            if(cnt[1] == cnt[2]) return 1;
        }
        if(score[1] == 2 && score[2] == 0) {
            // O가 마지막 공격때 한번에 2빙고 만들고 바로 종료한 경우 => 가능
            if(cnt[1] == cnt[2] + 1) return 1;
        }
        return 0;
    }
}