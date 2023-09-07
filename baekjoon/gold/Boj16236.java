package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제명] 아기상어
 * [풀이시간] (44분)
 * [한줄평]
 * 1_v1. BFS(실패)
 * [풀이]
 * @See <a href="https://www.acmicpc.net/problem/16236">문제</a>
 */
class Boj16236 {
    // 1_v1
    // ULRD
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static class P {
        int x;
        int y;
        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int n;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        int fish = 0;
        // 아기상어
        int sx = 0;
        int sy = 0;
        int ss = 2;
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) continue;
                if(map[i][j] == 9) {
                    sx = i;
                    sy = j;
                    map[i][j] = 0;
                } else {
                    fish++;
                }
            }
        }
        //
        int time = 0;
        int eat = 0;
        while(fish > 0) {
            int[] arr = bfs(sx, sy, ss);
            int x = arr[0];
            int y = arr[1];
            int t = arr[2];
            System.out.println(x + "," + y + "," + t);
            if(t == -1) break;
            map[x][y] = 0;
            sx = x;
            sy = y;
            time += t;
            eat++;
            if(ss == eat) {
                ss++;
                eat = 0;
                fish--;
            }
        }
        //
        System.out.println(time);
    }

    // 가장 가까운 물고기 위치
    public static int[] bfs(int sx, int sy, int ss) {
        Queue<P> q = new LinkedList<>();
        int[][] visited = new int[n][n];
        q.add(new P(sx, sy));
        visited[sx][sy] = 1;
        //
        while(!q.isEmpty()) {
            P p = q.poll();
            int x = p.x;
            int y = p.y;
            // 먹을 수 있는 물고기이면
            if(map[x][y] != 0 && map[x][y] < ss) return new int[]{x, y, visited[x][y] - 1};
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 범위 밖이거나 크기가 더 큰 물고기이거나 이미 방문했으면, 패스
                if(!isRange(nx, ny) || map[nx][ny] > ss || visited[nx][ny] != 0) continue;
                q.add(new P(nx, ny));
                visited[nx][ny] = visited[x][y] + 1;
            }
        }
        return new int[]{-1, -1, -1};
    }

    public static boolean isRange(int x, int y) {
        if(0 <= x && x < n && 0 <= y && y < n) return true;
        return false;
    }
}