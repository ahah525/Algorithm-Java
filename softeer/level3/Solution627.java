package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제명] [21년 재직자 대회 예선] 이미지 프로세싱
 * [풀이시간] 15분
 * [한줄평] DFS나 BFS로 푸는 아주 기초적인 문제였다.
 * 1_v1. BFS(성공)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=627">문제</a>
 */
class Solution627 {
    static class P {
        int x;
        int y;
        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[][] map;
    static int h, w;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        map = new int[h][w];
        for(int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int q = Integer.parseInt(br.readLine());
        for(int k = 0; k < q; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            bfs(i, j, c);
        }
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                System.out.print(map[i][j]);
                if(j == w - 1) break;
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void bfs(int sx, int sy, int c) {
        int target = map[sx][sy];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        boolean[][] visited = new boolean[h][w];
        Queue<P> q = new LinkedList<>();
        q.add(new P(sx, sy));
        visited[sx][sy] = true;
        while(!q.isEmpty()) {
            P p = q.poll();
            int x = p.x;
            int y = p.y;
            map[x][y] = c;
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(0 > nx || nx >= h || 0 > ny || ny >= w) continue;
                if(visited[nx][ny] || map[nx][ny] != target) continue;
                q.add(new P(nx, ny));
                visited[nx][ny] = true;
            }
        }
    }
}