package programmers.pccp2;


import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] [PCCP 모의고사 #2] 보물 지도
 * [풀이시간] 1시간 45분 / (55분)
 * [한줄평] 예전에 비슷한 문제를 풀어본적이 있어서 3차원 배열로 접근해야한다는 건 알았는데 생각보다 푸는데 너무 오래걸렸고 결국 문제 풀이를 봤다.
 * 1_v1. BFS(성공)
 * 2_v1. DP, BFS(실패 - 13, 15, 16 시간초과)
 * @See <a href="https://school.programmers.co.kr/learn/courses/15009/lessons/121690">문제</a>
 * @See <a href="https://www.acmicpc.net/problem/1600">유사 문제</a>
 */
class Solution121690 {
    public static void main(String[] args) {
        // 5
        int[][] hole1 = {{2, 3}, {3, 3}};
        System.out.println(solution(4, 4, hole1));

        // -1
        int[][] hole2 = {{1, 4}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {3, 3}, {4, 1}, {4, 3}, {5, 3}};
        System.out.println(solution(5, 4, hole2));
    }

    // 1_v1
    static class P {
        int x;
        int y;
        int jump;   // (x, y)점에 오기까지 점프사용횟수

        P(int x, int y, int jump) {
            this.x = x;
            this.y = y;
            this.jump = jump;
        }
    }

    public static int solution(int n, int m, int[][] hole) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int[] jx = {2, -2, 0, 0};
        int[] jy = {0, 0, 2, -2};
        // m * n(0: 길, 1: 구멍)
        int[][] map = new int[m][n];
        for(int[] h : hole) {
            map[m - h[1]][h[0] - 1] = 1;
        }
        // visited[i][x][y] = (x,y)에 오기까지 점프 i번 사용했을 때 이동횟수
        int[][][] visited = new int[2][m][n];
        Queue<P> q = new LinkedList<>();
        q.add(new P(m - 1, 0, 0));
        visited[0][m - 1][0] = 1;
        while(!q.isEmpty()) {
            P p = q.poll();
            int x = p.x;
            int y = p.y;
            int jump = p.jump;
            // 2칸 점프했을 때
            if(jump == 0) {
                for (int i = 0; i < 4; i++) {
                    int nx = x + jx[i];
                    int ny = y + jy[i];
                    if (0 <= nx && nx < m && 0 <= ny && ny < n) {
                        if (map[nx][ny] == 0 && visited[jump + 1][nx][ny] == 0) {
                            q.add(new P(nx, ny, jump + 1));
                            visited[jump + 1][nx][ny] = visited[jump][x][y] + 1;
                        }
                    }
                }
            }
            // 1칸 점프했을 때
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(0 <= nx && nx < m && 0 <= ny && ny < n) {
                    if(map[nx][ny] == 0 && visited[jump][nx][ny] == 0) {
                        q.add(new P(nx, ny, jump));
                        visited[jump][nx][ny] = visited[jump][x][y] + 1;
                    }
                }
            }
        }
        if(visited[0][0][n - 1] == 0) {
            if(visited[1][0][n - 1] == 0) {
                return -1;
            }
            return visited[1][0][n - 1] - 1;
        }
        return Math.min(visited[0][0][n - 1], visited[1][0][n - 1]) - 1;
    }

    // 2_v1
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    int[] jx = {2, -2, 0, 0};
    int[] jy = {0, 0, 2, -2};
    int MAX_VALUE = 2001;
    public int solution2(int n, int m, int[][] hole) {
        int answer = 0;
        // 초기화 0 : 길, 1 : 구멍
        int[][] map = new int[m][n];
        for(int[] h : hole) {
            map[m - h[1]][h[0] - 1] = 1;
        }
        // for(int[] arr : map) {
        //     System.out.println(Arrays.toString(arr));
        // }
        int[][][] dp = new int[2][m][n];
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < m; j++) {
                for(int k = 0; k < n; k++) {
                    dp[i][j][k] = MAX_VALUE;
                }
            }
        }

        bfs(m - 1, 0, map, dp, m, n);
        // for(int[][] arr : dp) {
        //     for(int[] a : arr) {
        //         System.out.println(Arrays.toString(a));
        //     }
        //     System.out.println();
        // }
        if(dp[1][0][n - 1] == MAX_VALUE) return -1;
        return dp[1][0][n - 1];
    }

    public void bfs(int startX, int startY, int[][] map, int[][][] dp, int m, int n) {
        Queue<P> q = new LinkedList<>();
        q.add(new P(startX, startY, 0));
        dp[0][startX][startY] = 0;
        dp[1][startX][startY] = 0;
        while(!q.isEmpty()) {
            // for(int[] a : dp[0]) {
            //     System.out.println(Arrays.toString(a));
            // }
            // System.out.println();
            P p = q.poll();
            if(p.x == 0 && p.y == n - 1) continue;
            //
            int x = p.x;
            int y = p.y;
            int j = p.jump;
            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(0 <= nx && nx < m && 0 <= ny && ny < n
                        && map[nx][ny] == 0 && dp[j][nx][ny] == MAX_VALUE) {
                    q.add(new P(nx, ny, j));
                    dp[j][nx][ny] = Math.min(dp[j][nx][ny], dp[j][x][y] + 1);
                }
            }
            //
            if(p.jump == 0) {
                for(int i = 0; i < 4; i++) {
                    int nx = p.x + jx[i];
                    int ny = p.y + jy[i];
                    if(0 <= nx && nx < m && 0 <= ny && ny < n
                            && map[nx][ny] == 0 && dp[1][nx][ny] == MAX_VALUE) {
                        q.add(new P(nx, ny, 1));
                        dp[1][nx][ny] = Math.min(dp[1][nx][ny], dp[0][x][y] + 1);
                    }
                }
            }
        }
    }
}