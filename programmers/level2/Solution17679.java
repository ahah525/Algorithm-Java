package programmers.level2;


/**
 * [문제명] [1차] 프렌즈4블록
 * [풀이시간] 1시간 / 1시간 10분(1시간 + 10분) / 35분
 * [한줄평] 생각보다 푸는데 시간이 좀 더 걸리긴했지만 어려운 구현 문제는 아니었다.
 * / 그냥 구현문제였는데 결국 반례를 찾지 못해서 1번 방식을 참고해 풀었다.
 * / 복잡하긴 했지만 구현만 하면 되는 문제였다.
 * 1_v1. 구현(성공)
 * 2_v1. 구현(실패 - 5, 10, 11 실패)
 * 2_v2. 구현(성공)
 * [풀이] tmp 에 1,2차 저장을 수행함
 * - map[][] : 원본
 * - tmp[][] : 원본에서 블록 삭제한 결과 저장(1차) -> 빈칸이 있으면 블록을 아래로 이동한 결과를 저장(2차)
 * 2_v3. 구현(성공)
 * [풀이] tmp 에 1차 저장을 수행함
 * - map[][] : 원본
 * - removed[][] : 블록 삭제 여부
 * - tmp[][] : 원본에서 블록 삭제 여부를 고려해 블록을 아래로 이동한 결과를 저장(1차)
 * 3_v1. 구현(성공)
 * [풀이] 2_v3 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/17679">문제</a>
 */
class Solution17679 {
    public static void main(String[] args) {
        // 14
        String[] board1 = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
        System.out.println(solution2(4, 5, board1));

        // 15
        String[] board2 = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        System.out.println(solution2(6, 6, board2));
    }

    // 1_v1
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

    // 2_v2
    public static int solution2(int m, int n, String[] board) {
        int answer = 0;
        int[] dx = {0, 0, 1, 1};
        int[] dy = {0, 1, 0, 1};
        // 원본 맵
        char[][] map = new char[m][n];
        for(int i = 0; i < m; i++) {
            char[] arr = board[i].toCharArray();
            for(int j = 0; j < n; j++)
                map[i][j] = arr[j];
        }
        //
        while(true) {
            int cnt = 0;
            boolean[][] removed = new boolean[m][n];
            for(int x = 0; x < m - 1; x++) {
                for(int y = 0; y < n - 1; y++) {
                    if(map[x][y] != '\0') {
                        // 4칸 검사
                        boolean remove = true;
                        for(int i = 0; i < 4; i++) {
                            int nx = x + dx[i];
                            int ny = y + dy[i];
                            if(map[x][y] != map[nx][ny]) {
                                remove = false;
                                break;
                            }
                        }
                        // 복사본에 지우기
                        if(remove) {
                            for(int i = 0; i < 4; i++) {
                                int nx = x + dx[i];
                                int ny = y + dy[i];
                                if(!removed[nx][ny]) cnt++;
                                removed[nx][ny] = true;
                            }
                        }
                    }
                }
            }
            // 제거한게 없으면 종료
            if(cnt == 0) break;
            // 아래로 옮기기
            char[][] tmp = new char[m][n];
            for(int y = 0; y < n; y++) {
                int i = m - 1;
                for(int x = m - 1; x >= 0; x--) {
                    // 삭제된 칸이면
                    if(removed[x][y]) continue;
                    // 삭제되지 않은 칸이면
                    tmp[i--][y] = map[x][y];
                }
            }
            //
            answer += cnt;
            map = tmp;
        }
        return answer;
    }

    // 2_v3(2_v1 풀이 반례 잡기)
    public static int solution3(int m, int n, String[] board) {
        int answer = 0;
        int[] dx = {0, 0, 1, 1};
        int[] dy = {0, 1, 0, 1};

        char[][] map = new char[m][n];
        for(int i = 0; i < m; i++) {
            char[] arr = board[i].toCharArray();
            for(int j = 0; j < n; j++) {
                map[i][j] = arr[j];
            }
        }
        //
        while(true) {
            int cnt = 0;
            char[][] tmp = new char[m][n];
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    tmp[i][j] = map[i][j];
                }
            }
            for(int x = 0; x < m - 1; x++) {
                for(int y = 0; y < n - 1; y++) {
                    if(map[x][y] != '-') {
                        // 4칸 검사
                        boolean remove = true;
                        for(int i = 0; i < 4; i++) {
                            int nx = x + dx[i];
                            int ny = y + dy[i];
                            if(map[x][y] != map[nx][ny]) {
                                remove = false;
                                break;
                            }
                        }
                        // 복사본에 지우기
                        if(remove) {
                            for(int i = 0; i < 4; i++) {
                                int nx = x + dx[i];
                                int ny = y + dy[i];
                                if(tmp[nx][ny] != '-') cnt++;
                                tmp[nx][ny] = '-';
                            }
                        }
                    }
                }
            }
            // 제거한게 없으면 종료
            if(cnt == 0) break;
            for(int y = 0; y < n; y++) {
                int blank = -1;
                for(int x = m - 1; x >= 0; x--) {
                    // 첫번째로 빈칸을 찾았으면 인덱스 기록
                    if(blank == -1 && tmp[x][y] == '-') {
                        blank = x;
                    }
                    // 빈칸을 찾은 상태에서 문자를 발견하면 아래로
                    else if(blank != -1 && tmp[x][y] != '-') {
                        tmp[blank--][y] = tmp[x][y];
                        tmp[x][y] = '-';
                    }
                }
            }
            //
            answer += cnt;
            map = tmp;
        }
        return answer;
    }

    // 3_v1
    int[] dx = {0, 1, 0, 1};
    int[] dy = {0, 0, 1, 1};
    int delete; // 이번 턴에서 삭제된 블록의 개수
    public int solution4(int m, int n, String[] board) {
        int answer = 0;
        char[][] map = new char[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }
        //
        while(true) {
            delete = 0;
            map = play(m, n, map);
            // 이번 턴에 삭제된 블록이 없으면 종료
            if(delete == 0) break;
            answer += delete;
        }
        return answer;
    }

    public char[][] play(int m, int n, char[][] map) {
        // 삭제
        boolean[][] deleted = new boolean[m][n];
        for(int x = 0; x < m - 1; x++) {
            for(int y = 0; y < n - 1; y++) {
                if(canDelete(x, y, map)) {
                    for(int i = 0; i < 4; i++) {
                        int nx = x + dx[i];
                        int ny = y + dy[i];
                        if(deleted[nx][ny]) continue;
                        deleted[nx][ny] = true;
                        delete++;
                    }
                }
            }
        }
        // 아래로 이동
        char[][] tmp = new char[m][n];
        for(int j = 0; j < n; j++) {
            int x = m - 1;
            for(int i = m - 1; i >= 0; i--) {
                if(map[i][j] == '\0') break;
                if(deleted[i][j]) continue;
                tmp[x--][j] = map[i][j];
            }
        }
        return tmp;
    }

    // (x,y)에서 4칸 삭제할 수 있는지
    public boolean canDelete(int x, int y, char[][] map) {
        if(map[x][y] == '\0') return false;
        for(int i = 1; i <= 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(map[nx][ny] != map[x][y]) return false;
        }
        return true;
    }
}