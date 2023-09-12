package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제명] 아기 상어2
 * [풀이시간] 16분
 * [한줄평] 아주 기초적인 문제여서 더 안풀어봐도 될 것 같다.
 * 1_v1. BFS, 완전탐색(성공)
 * [풀이] 빈 칸에서 bfs를 돌려서 안전거리 값을 구하고 그 중 최댓값을 출력한다.
 * @See <a href="https://www.acmicpc.net/problem/17086">문제</a>
 */
class Boj17086 {
    static class P {
        int x;
        int y;

        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //
        int max = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 1) continue;
                // 빈칸인 곳에서 안전거리 최댓값 구하기
                max = Math.max(max, bfs(i, j));
            }
        }
        System.out.println(max);
    }

    public static int bfs(int sx, int sy) {
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
        Queue<P> q = new LinkedList<>();
        int[][] visited = new int[n][m];
        q.add(new P(sx, sy));
        visited[sx][sy] = 1;
        while(!q.isEmpty()) {
            P p = q.poll();
            for(int i = 0; i < 8; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(!inRange(nx, ny) || visited[nx][ny] != 0) continue;
                if(map[nx][ny] == 1) {
                    System.out.println(visited[p.x][p.y]);
                    return visited[p.x][p.y];
                }
                q.add(new P(nx, ny));
                visited[nx][ny] = visited[p.x][p.y] + 1;
            }
        }
        return 0;
    }

    public static boolean inRange(int x, int y) {
        if(0 <= x && x < n && 0 <= y && y < m) return true;
        return false;
    }
}