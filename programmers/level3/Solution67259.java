package programmers.level3;


import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] [카카오 인턴] 경주로 건설
 * [풀이시간] 1시간 50분
 * [한줄평] 생각보다 너무 어려웠던 문제였고 결국 다른 풀이를 보고 해결했다.. 비슷한 문제를 몇번 풀어본 기억이 있는데 여전히 어렵다ㅠㅠ
 * 1_v1. DP + BFS(성공)
 * - dp[d][x][y]: (x, y)좌표용 d 방향일 때 최소 비용
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/67259">문제</a>
 * @See <a href="https://girawhale.tistory.com/86">다른 풀이</a>
 */
class Solution67259 {
    public static void main(String[] args) {
        //
        int[][] board1 = {{0,0,0},{0,0,0},{0,0,0}};
        System.out.println(solution(board1));
    }

    static class P {
        int x;
        int y;
        int d;
        int c;

        public P(int x, int y, int d, int c) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.c = c;
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
        int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
        //   최댓값으로 초기화
        int[][][] dp = new int[4][n][n];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        // 시작점(0, 0) 모든방향 방문처리
        Queue<P> q = new LinkedList<>();
        for(int i= 0; i < 4; i++) {
            q.add(new P(0, 0, i, 0));
            dp[i][0][0] = 0;
        }
        while(!q.isEmpty()) {
            P p = q.poll();
            int x = p.x;
            int y = p.y;
            int d = p.d;
            int c = p.c;
            for(int i = 0; i < 4; i++) {
                // (nx, ny) 에 nc 방향으로 도착했을 때 최소비용 계산
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 직진 = 100, 회전+직전 = 600
                int nc = c + ((d == i) ? 100 : 600);
                // 갈 수 있는 길이면
                if(0 <= nx && nx < n && 0 <= ny && ny < n && board[nx][ny] == 0) {
                    // 이전에 (nx, ny) 에 nc 방향으로 도착했을 때 최소비용보다 현재 비용이 더 작다면 갱신
                    if(dp[i][nx][ny] > nc) {
                        dp[i][nx][ny] = nc;
                        q.add(new P(nx, ny, i, nc));
                    }
                }
            }
        }
        //        for(int i = 0; i < 4; i++) {
//            for(int j = 0; j < n ;j++) {
//                System.out.println(Arrays.toString(dp[i][j]));
//            }
//        }
        // 도착지점에서 4방향 중 최소 비용 구하기
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < 4; i++) {
            min = Math.min(min, dp[i][n - 1][n - 1]);
        }
        return min;
    }
}