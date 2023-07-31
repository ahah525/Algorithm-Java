package programmers.level2;


import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] 리코쳇 로봇
 * [풀이시간] 1시간 10분 / 32분
 * [한줄평] 문제 자체를 이해하지 못해서 힌트를 봤다. 처음에는 3차원 배열로 방문처리를 해야된다고 생각했는데 그럴 필요가 없었다. 다시 한번 꼭 풀어봐야할 문제다.
 * / 문제를 이해해서인지 쉽게 풀 수 있었다.
 * 1_v1. BFS(성공)
 * [풀이] 장애물을 만나거나 맨 끝에 부딪힐 때까지 이동한 경로는 방문처리를 하면 안된다!!
 * 2_v1. BFS(성공)
 * [풀이] 1_v1 동일
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/169199">문제</a>
 * @See <a href="https://ksb-dev.tistory.com/291">문제 이해</a>
 */
class Solution169199 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1, 2_v1
    class P {
        int x;
        int y;
        P(int x, int y) {
            this.x = x;
            this.y = y;
        }

//        @Override
//        public String toString() {
//            return String.format("(%d,%d)", x, y);
//        }
    }

    char[][] map;
    int n, m;

    /**
     * @param board 게임판의 상태를 나타내는 문자열 배열
     * @return 말이 목표위치에 도달하는데 최소 몇 번 이동해야 하는지(만약 목표위치에 도달할 수 없다면 -1을 return)
     */
    public int solution(String[] board) {
        int sx = 0;
        int sy = 0;
        n = board.length;
        m = board[0].length();
        // 1. map, 시작점 초기화
        map = new char[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                map[i][j] = board[i].charAt(j);
                if(map[i][j] == 'R') {
                    sx = i;
                    sy = j;
                }
            }
        }
        // 상하좌우
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int[][] visited = new int[n][m];
        Queue<P> q = new LinkedList<>();
        // 2. 시작점 방문 처리
        q.add(new P(sx, sy));
        visited[sx][sy] = 1;
        //
        while(!q.isEmpty()) {
            P p = q.poll();
            int x = p.x;
            int y = p.y;
            //  3. 도착점에 도달하면 리턴
            if(map[x][y] == 'G') return visited[x][y] - 1;
            // 4. 인접 4방향 탐색
            for(int i = 0; i < 4; i++) {
                // 5. (x, y)에서 i 방향으로 쭉 이동했을 때 최종적으로 도달하는 지점 구하기
                int nx = x;
                int ny = y;
                while(true) {
                    nx += dx[i];
                    ny += dy[i];
                    // 해당 지점이 갈 수 없는 곳이면 이전 지점이 최종적으로 방문해야하는 지점이 된다.
                    if (!canMove(nx, ny)) {
                        nx -= dx[i];
                        ny -= dy[i];
                        break;
                    }
                }
                // 6. 해당 지점을 이전에 방문한적 없으면 방문처리
                if(visited[nx][ny] == 0) {
                    q.add(new P(nx, ny));
                    visited[nx][ny] = visited[x][y] + 1;
                }
            }
        }
        return -1;
    }

    // (x, y)로 이동 가능한지
    public boolean canMove(int x, int y) {
        // 범위 밖을 벗어나거나 장애물인 곳은 이동 불가
        return (0 <= x && x < n && 0 <= y && y < m && map[x][y] != 'D') ? true : false;
    }
}