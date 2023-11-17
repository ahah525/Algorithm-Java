package programmers.pccp2;


import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] [PCCP 모의고사 #2] 보물 지도
 * [풀이시간] 1시간 45분 / 1시간(55분 + 5분) / 24분 / 29분
 * [한줄평] 예전에 비슷한 문제를 풀어본적이 있어서 3차원 배열로 접근해야한다는 건 알았는데 생각보다 푸는데 너무 오래걸렸고 결국 문제 풀이를 봤다.
 * / 3차원 배열로 접근해서 풀어야한다는 것은 알았는데, 막상 구현하는데 시간이 좀 걸렸다.
 * / 3차원 배열 DP와 BFS로 풀어야한다는 것을 알아서 빨리 풀 수 있었다.
 * / 점프를 사용한 경우와 사용하지 않은 경우를 구분하기 위해 DP배열을 사용했고, 최단거리를 위해 BFS로 풀었다. 다시 풀어봐도 좋을 문제다.
 * 1_v1. BFS(성공)
 * 2_v1. DP, BFS(실패 - 13, 15, 16 시간초과)
 * 2_v2. DP, BFS(성공)
 * [점화식] dp[j][x][y]: 점프를 j번 해서 (x, y)에 왔을 때 최소 이동 시간
 * [해결법] 가로, 세로가 최대 1000이길래 dp[j][x][y] 의 값을 최댓값을 2001 로 세팅했었는데, Integer.MAX_VALUE 로 초기화해서 해결함
 * 3_v1. DP, BFS(성공)
 * 4_v1. DP, BFS(성공)
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

    // 2_v1, 3_v1, 4_v1
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    int[] jx = {2, -2, 0, 0};
    int[] jy = {0, 0, 2, -2};

    public int solution2(int n, int m, int[][] hole) {
        // 1. map 초기화 0 : 길, 1 : 구멍
        int[][] map = new int[m][n];
        for(int[] h : hole) {
            map[m - h[1]][h[0] - 1] = 1;
        }
        // 2. 최소 이동 횟수 구하기
        return bfs(m - 1, 0, map, m, n);
    }

    public int bfs(int sx, int sy, int[][] map, int m, int n) {
        int[][][] dp = new int[2][m][n];    // (j,x,y): 점프를 j번 사용해서 (x,y)에 도달하기까지 최단거리
        Queue<P> q = new LinkedList<>();
        q.add(new P(sx, sy, 0));
        dp[0][sx][sy] = 0;
        dp[1][sx][sy] = 0;
        while(!q.isEmpty()) {
            P p = q.poll();
            int x = p.x;
            int y = p.y;
            int j = p.jump;
            // 목적지 도달하면 리턴
            if(x == 0 && y == n - 1) return dp[j][x][y];
            // 1칸 이동
            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                // 범위를 벗어났거나, 함정이거나, 이미 방문했던 곳이라면 -> 패스
                if(!inRange(nx, ny, n, m) || map[nx][ny] == 1 || dp[j][nx][ny] != 0) continue;
                q.add(new P(nx, ny, j));
                dp[j][nx][ny] = dp[j][x][y] + 1;
            }
            // 2칸 점프
            if(j == 0) {
                for(int i = 0; i < 4; i++) {
                    int nx = p.x + jx[i];
                    int ny = p.y + jy[i];
                    // 범위를 벗어났거나, 함정이거나, 이미 방문했던 곳이라면 -> 패스
                    if(!inRange(nx, ny, n, m) || map[nx][ny] == 1 || dp[1][nx][ny] != 0) continue;
                    q.add(new P(nx, ny, 1));
                    dp[1][nx][ny] = dp[0][x][y] + 1;
                }
            }
        }
        return -1;
    }

    public boolean inRange(int x, int y, int n, int m) {
        if(0 <= x && x < m && 0 <= y && y < n) return true;
        return false;
    }
}