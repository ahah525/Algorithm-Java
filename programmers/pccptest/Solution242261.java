package programmers.pccptest;


/**
 * [문제명] [PCCP 기출문제] 4번
 * [풀이시간] 40분
 * [한줄평] 조건 처리하는 부분이 조금 까다로웠고 다시 풀어봐도 좋을 문제다.
 * 1_v1. DFS,백트래킹(성공)
 * [풀이] 빨강/파랑 수레 시작점을 기준으로 DFS 탐색하여 아래 조건을 만족하는 경우에만 DFS 호출한다.
 * >> 수레 이동 조건
 * 1. 수레는 벽이나 범위 밖으로 이동할 수 없다.
 * 2. 수레는 자신이 방문했던 칸으로 이동할 수 없다.
 * 3. 도착 칸에 위치한 수레는 더 이상 움직일 수 없다.
 * 4. 동시에 두 수레가 같은 칸으로 이동할 수 없다.
 * 5. 수레끼리 자리를 바꾸면서 이동할 수 없다.
 * @See <a href="https://school.programmers.co.kr/learn/courses/19344/lessons/242261">문제</a>
 */
class Solution242261 {
    int n, m, min, INF;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int solution(int[][] maze) {
        INF = Integer.MAX_VALUE;
        min = INF;
        n = maze.length;    //  가로
        m = maze[0].length; // 세로
        // 1. 빨강/파랑 수레 시작 좌표 초기화
        int rx = 0;
        int ry = 0;
        int bx = 0;
        int by = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(maze[i][j] == 1) {
                    rx = i;
                    ry = j;
                } else if(maze[i][j] == 2) {
                    bx = i;
                    by = j;
                }
            }
        }
        // 2. 빨강/파랑 수레 시작 좌표 방문처리
        boolean[][] red = new boolean[n][m];    // 빨강 수레 방문 여부
        boolean[][] blue = new boolean[n][m];   // 파랑 수레 방문 여부
        red[rx][ry] = true;
        blue[bx][by] = true;
        // 3. 빨강/파랑 수레 시작 좌표에서 DFS
        dfs(0, rx, ry, bx, by, red, blue, maze);
        // 4. 빨강/파랑 수레가 각 도착칸에 도달하지 못하면, 0 리턴
        if(min == INF) return 0;
        return min;
    }

    public void dfs(int depth, int rx, int ry, int bx, int by, boolean[][] red, boolean[][] blue, int[][] maze) {
        if(maze[rx][ry] == 3 && maze[bx][by] == 4) {
            // 1. 빨강 수레와 파랑 수레가 모두 도착했으면, 최솟값 갱신
            min = Math.min(min, depth);
            return;
        }
        if(maze[rx][ry] == 3 && maze[bx][by] != 4) {
            // 2. 빨강 수레만 도착했으면, 파랑 수레만 움직이기
            for(int i = 0; i < 4; i++) {
                int nbx = bx + dx[i];
                int nby = by + dy[i];
                if(!inRange(nbx, nby) || maze[nbx][nby] == 5 || blue[nbx][nby]) continue;
                // 파랑 수레가 이동할 곳에 빨강 수레가 있으면, 패스
                if(nbx == rx && nby == ry)  continue;
                blue[nbx][nby] = true;
                dfs(depth + 1, rx, ry, nbx, nby, red, blue, maze);
                blue[nbx][nby] = false;
            }
        } else if(maze[rx][ry] != 3 && maze[bx][by] == 4) {
            // 3. 파랑 수레만 도착했으면, 빨강 수레만 움직이기
            for(int i = 0; i < 4; i++) {
                int nrx = rx + dx[i];
                int nry = ry + dy[i];
                if(!inRange(nrx, nry) || maze[nrx][nry] == 5 || red[nrx][nry]) continue;
                // 빨강 수레가 이동할 곳에 파랑 수레가 있으면, 패스
                if(nrx == bx && nry == by)  continue;
                red[nrx][nry] = true;
                dfs(depth + 1, nrx, nry, bx, by, red, blue, maze);
                red[nrx][nry] = false;
            }
        } else {
            // 4. 둘 다 도착안했으면, 둘 다 움직이기
            for(int i = 0; i < 4; i++) {
                int nrx = rx + dx[i];
                int nry = ry + dy[i];
                if(!inRange(nrx, nry) || maze[nrx][nry] == 5 || red[nrx][nry]) continue;
                for(int j = 0; j < 4; j++) {
                    int nbx = bx + dx[j];
                    int nby = by + dy[j];
                    if(!inRange(nbx, nby) || maze[nbx][nby] == 5 || blue[nbx][nby]) continue;
                    // 빨강 수레가 이동할 칸과 파랑 수레가 이동할 칸이 같으면, 패스
                    if(nrx == nbx && nry == nby) continue;
                    // 서로 위치를 바꾸면서 이동할 수X
                    if(nrx == bx && nry == by && nbx == rx && nby == ry) continue;
                    red[nrx][nry] = true;
                    blue[nbx][nby] = true;
                    dfs(depth + 1, nrx, nry, nbx, nby, red, blue, maze);
                    red[nrx][nry] = false;
                    blue[nbx][nby] = false;
                }
            }
        }
    }

    public boolean inRange(int x, int y) {
        if(0 <= x && x < n && 0 <= y && y < m) return true;
        return false;
    }
}