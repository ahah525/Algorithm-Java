package programmers.level2;


/**
 * [문제명] [1차] 프렌즈4블록
 * [풀이시간] 1시간
 * [한줄평] 생각보다 푸는데 시간이 좀 더 걸리긴했지만 어려운 구현 문제는 아니었다.
 * v1. 구현(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/17679">문제</a>
 */
class Solution17679 {
    public static void main(String[] args) {
        // 14
        String[] board1 = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
        System.out.println(solution(4, 5, board1));

        // 15
        String[] board2 = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        System.out.println(solution(6, 6, board2));
    }

    /**
     * @param m 판의 높이
     * @param n 판의 너비
     * @param board 판의 배치 정보
     * @return 지워지는 블록은 모두 몇 개인지
     */
    public static int solution(int m, int n, String[] board) {
        int answer = 0; // 지워진 총 블록의 개수
        // 2 * 2 탐색 좌표
        int[] dx = {0, 0, 1, 1};
        int[] dy = {0, 1, 0, 1};
        // 1. 판 정보를 2차원 char 배열로 변환
        char[][] map = new char[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }

        while(true) {
            boolean[][] visited = new boolean[m][n];    // 해당 칸의 블록 삭제 여부
            int cnt = 0;    // 이번 턴에서 삭제된 블록 개수
            // 2. 삭제할 블록 탐색하기(삭제할 블록은 visited 에 체크)
            for(int i = 0; i < m - 1; i++) {
                for(int j = 0; j < n - 1; j++) {
                    // 2-1. 현재 칸을 기준으로 2 * 2 칸이 모두 같은지 = 삭제해야할 블록인지 검사
                    char target = map[i][j];    // 현재 블록
                    boolean isDel = true;       // 삭제 여부
                    if(target == '\0') {
                        isDel = false;
                    } else {
                        // 4칸이 모두 같으면 삭제
                        for(int k = 1; k < 4; k++) {
                            if(target != map[i + dx[k]][j + dy[k]]) {
                                isDel = false;
                                break;
                            }
                        }
                    }
                    // 2-2. 삭제해야하면 4칸 블록 모두 삭제, 삭제 개수 카운팅
                    if(isDel) {
                        for(int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            // 이미 삭제 처리된 칸은 카운팅하면 안됨
                            if(!visited[nx][ny]) {
                                visited[nx][ny] = true;
                                cnt++;
                            }
                        }
                    }
                }
            }
            // 3. 삭제할 블록이 없으면 탈출
            if(cnt == 0)
                break;
            // 4. 블록 삭제 후 위에 있는 블록은 아래로 이동시키기
            char[][] tmp = new char[m][n];
            for(int j = 0; j < n; j++) {
                int x = m - 1;
                for(int i = m - 1; i >= 0; i--) {
                    if(!visited[i][j]) {
                        tmp[x][j] = map[i][j];
                        x--;
                    }
                }
            }
            map = tmp;
            // 5. 이번 턴에서 삭제한 블록 개수를 총 개수에 더해주기
            answer += cnt;
        }
        return answer;
    }
}