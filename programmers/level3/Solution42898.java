package programmers.level3;


import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] 등굣길
 * [풀이시간] (40분)
 * [한줄평]
 * 1_v1. DP, BFS(실패- 1, 5 시간초과)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42898">문제</a>
 */
class Solution42898 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    public class P {
        int x;
        int y;

        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * @param m 격자의 가로 길이
     * @param n 격자의 세로 길이
     * @param puddles 물이 잠긴 지역의 좌표를 담은 2차원 배열
     * @return 오른쪽과 아래쪽으로만 움직여 집에서 학교까지 갈 수 있는 최단경로의 개수를 1,000,000,007로 나눈 나머지
     */
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n][m];
        for(int[] p : puddles) {
            map[p[1] - 1][p[0] - 1] = 1;
        }
        return bfs(n, m, map);
    }

    public int bfs(int n, int m, int[][] map) {
        int[] dx = {1, 0};
        int[] dy = {0, 1};
        long[][] visited = new long[n][m];
        Queue<P> q = new LinkedList<>();
        q.add(new P(0, 0));
        visited[0][0] = 1;
        while(!q.isEmpty()) {
            P p = q.poll();
            int x = p.x;
            int y = p.y;
            for(int i = 0; i < 2; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if(map[nx][ny] == 0) {
                        if(visited[nx][ny] == 0) {
                            q.add(new P(nx, ny));
                        }
                        visited[nx][ny] = (visited[nx][ny] + visited[x][y]) % 1000000007 ;
                    }
                }
            }
        }
        // for(int i = 0; i < n; i++) {
        //     for(int j = 0; j < m; j++) {
        //         System.out.print(visited[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        return (int) (visited[n - 1][m - 1] % 1000000007);
    }
}