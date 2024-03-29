package programmers.level3;


import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] [카카오 인턴] 경주로 건설
 * [풀이시간] 1시간 50분 / 27분 / 47분(37분 + 10분)
 * [한줄평] 생각보다 너무 어려웠던 문제였고 결국 다른 풀이를 보고 해결했다.. 비슷한 문제를 몇번 풀어본 기억이 있는데 여전히 어렵다ㅠㅠ
 * / DP를 적용하지 않아도 최소비용을 구할 수 있다고 생각해서 틀렸던 문제였다.
 * 1_v1. DP, BFS(성공)
 * - dp[d][x][y]: (x, y)좌표용 d 방향일 때 최소 비용
 * 2_v1. DP, BFS(성공)
 * 3_v1. BFS(실패 - 3~4,6~11,13~23,25 실패)
 * 3_v2. DP, BFS(실패 - 3~4,6~11,13~23,25 실패) -> 빠름
 * [해결법] 최단거리 문제가 아니기 때문에 visited[i][x][y] 에 방문했던 곳이더라도 최소비용이면 다시 방문한다.
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/67259">문제</a>
 * @See <a href="https://girawhale.tistory.com/86">다른 풀이</a>
 */
class Solution67259 {
    public static void main(String[] args) {
        //
        int[][] board1 = {{0,0,0},{0,0,0},{0,0,0}};
        System.out.println(solution(board1));
    }

    // 1_v1, 2_v1
    static class P {
        int d;
        int x;
        int y;

        public P(int d, int x, int y) {
            this.d = d;
            this.x = x;
            this.y = y;
        }
    }

    /**
     * @param board 도면의 상태(0은 비어 있음, 1은 벽)을 나타내는 2차원 배열
     * @return 경주로를 건설하는데 필요한 최소 비용
     */
    public static int solution(int[][] board) {
        return bfs(board.length, board);
    }

    public static int bfs(int n, int[][] board) {
        // 상하좌우
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        // 1. 최댓값으로 초기화
        int INF = Integer.MAX_VALUE;
        int[][][] dp = new int[4][n][n];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    dp[i][j][k] = INF;
                }
            }
        }
        // 2. 시작점 4방향 방문처리
        Queue<P> q = new LinkedList<>();
        for(int i = 0; i < 4; i++) {
            q.add(new P(i, 0, 0));
            dp[i][0][0] = 0;
        }
        int min = INF;  // 최소 비용
        while(!q.isEmpty()) {
            // 3. 큐에서 꺼내기
            P p = q.poll();
            int x = p.x;
            int y = p.y;
            int d = p.d;
            // 4. (n - 1, n - 1) 도달하면 최소비용 갱신
            if(x == n - 1 && y == n - 1) {
                min = Math.min(min, dp[d][x][y]);
                continue;
            }
            // 5. 인접 4칸에 대해 검사
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int cost = ((d == i) ? 100 : 600);  // (x,y) d 방향 -> (nx, ny) i 방향으로 가기 위해 드는 비용
                // 좌표내 길이면
                if(0 <= nx && nx < n && 0 <= ny && ny < n && board[nx][ny] == 0) {
                    // (nx, ny) i 방향으로 오는 현재 최소 비용 > (x, y) d 방향으로 오는 현재 최소 비용 + (x,y) d 방향 -> (nx, ny) i 방향으로 가기 위해 드는 비용
                    if(dp[i][nx][ny] > dp[d][x][y] + cost) {
                        // 방문처리 + 최소비용 갱신
                        q.add(new P(i, nx, ny));
                        dp[i][nx][ny] = dp[d][x][y] + cost;
                    }
                }
            }
        }
        return min;
    }

    // 3_v1
    public int solution2(int[][] board) {
        int n = board.length;
        int[][][] visited = new int[4][n][n];
        int MAX = Integer.MAX_VALUE;
        int answer = MAX;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    visited[i][j][k] = MAX;
                }
            }
        }
        // 상하좌우
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<P> q = new LinkedList<>();
        // 하,우 방향으로만 큐에 넣기
        q.add(new P(1, 0, 0));
        q.add(new P(3, 0, 0));
        visited[1][0][0] = 0;
        visited[3][0][0] = 0;
        while(!q.isEmpty()) {
            P p = q.poll();
            int x = p.x;
            int y = p.y;
            int d = p.d;
            // (n-1, n-1)이면 최솟값 갱신
            if(x == n - 1 && y == n - 1) {
                answer = Math.min(answer, visited[d][n - 1][n - 1]);
                continue;
            }
            int dd = (d % 2 == 0) ? d + 1 : d - 1;  // 반대방향
            for(int i = 0; i < 4; i++) {
                if(i == dd) continue;   // 반대방향은 이미 지나왔던 곳이므로 건너뛰기
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if(board[nx][ny] == 0) {
                        int cost = visited[d][x][y] + ((d == i) ? 100 : 600);
                        if(visited[i][nx][ny] > cost) {
                            q.add(new P(i, nx, ny));
                            visited[i][nx][ny] = cost;
                        }
                    }
                }
            }
        }
        return answer;
    }
}