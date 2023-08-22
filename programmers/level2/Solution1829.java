package programmers.level2;


import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] 카카오프렌즈 컬러링북
 * [풀이시간] 16분,10분
 * [한줄평] 정답률이 낮은 것에 비해 너무 쉬운 문제였다. DFS/BFS 아무 방식으로나 풀 수 있다.
 * 1_v1. 완전탐색, BFS(성공)
 * [풀이] 0이 아닌 곳에서 BFS를 돌려서 영역의 개수와 영역 크기의 최댓값을 구한다.
 * 1_v2. 완전탐색, DFS(성공)
 * [풀이] 0이 아닌 곳에서 DFS를 돌려서 영역의 개수와 영역 크기의 최댓값을 구한다.
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/1829">문제</a>
 */
class Solution1829 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    boolean[][] visited;

    class P {
        int x;
        int y;
        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int[] solution(int m, int n, int[][] picture) {
        visited = new boolean[m][n];
        int cnt = 0;
        int max = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(visited[i][j] || picture[i][j] == 0) continue;
                max = Math.max(max, bfs(i, j, m, n, picture));
                cnt++;
            }
        }
        return new int[] {cnt, max};
    }

    public int bfs(int sx, int sy, int m, int n, int[][] picture) {
        int cnt = 0;
        int target = picture[sx][sy];
        Queue<P> q = new LinkedList<>();
        q.add(new P(sx, sy));
        visited[sx][sy] = true;
        while(!q.isEmpty()) {
            P p = q.poll();
            cnt++;
            int x = p.x;
            int y = p.y;
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(0 > nx || nx >= m || 0 > ny || ny >= n) continue;
                if(visited[nx][ny] || picture[nx][ny] != target) continue;
                q.add(new P(nx, ny));
                visited[nx][ny] = true;
            }
        }
        return cnt;
    }

    // 1_v2
    public int[] solution2(int m, int n, int[][] picture) {
        visited = new boolean[m][n];
        int cnt = 0;
        int max = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(visited[i][j] || picture[i][j] == 0) continue;
                max = Math.max(max, dfs(i, j, m, n, picture));
                cnt++;
            }
        }
        return new int[] {cnt, max};
    }

    public int dfs(int x, int y, int m, int n, int[][] picture) {
        visited[x][y] = true;
        int cnt = 1;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(0 > nx || nx >= m || 0 > ny || ny >= n) continue;
            if(visited[nx][ny] || picture[nx][ny] != picture[x][y]) continue;
            cnt += dfs(nx, ny, m, n, picture);
        }
        return cnt;
    }
}