package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class P {
    int x;
    int y;
    int skill;  // 스킬 사용 횟수

    public P(int x, int y, int skill) {
        this.x = x;
        this.y = y;
        this.skill = skill;
    }
}

public class Boj1600 {
    private static int k, w, h;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());    // 말 이동 횟수

        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());   // 가로
        h = Integer.parseInt(st.nextToken());   // 세로

        int res = (int) 1e9;
        int[][][] visited = new int[h][w][k + 1];    // (h, w)에 k번 사용해서 오는 이동 횟수
        // 0: 평지, 1: 장애물(이동X)
        int[][] arr = new int[h][w];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(arr, new P(0, 0, 0), visited);

        for (int i = 0; i <= k; i++) {
            if (visited[h - 1][w - 1][i] != 0) {
                res = Math.min(res, visited[h - 1][w - 1][i]);
            }
        }

        if(res == (int) 1e9)
            res = 0;
        System.out.println(res - 1);
    }
    public static void bfs(int[][] arr, P start, int[][][] visited) {
        // 반시계 방향
        int[] hx = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] hy = {-1, -2, -2, -1, 1, 2, 2, 1};
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        Queue<P> q = new LinkedList<>();
        // 시작 좌표 큐에 삽입, 방문 처리
        q.offer(start);
        visited[start.x][start.y][start.skill] = 1;

        while (!q.isEmpty()) {
            P p = q.poll();
            int x = p.x;
            int y = p.y;
            int skill = p.skill;

            // 현재 좌표에 오는데 k번보다 작게 스킬을 사용했으면 말 스킬 사용 가능
            if (skill < k) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + hx[i];
                    int ny = y + hy[i];
                    // 범위 내이고 (방문한적 없거나 / 방문한적 있는데 현재값 + 1보다 더 큰값이라면)
                    if (0 <= nx && nx < h && 0 <= ny && ny < w) {
                        // 1. 빈 곳이고 방문한적 없으면
                        if (arr[nx][ny] == 0 && visited[nx][ny][skill + 1] == 0) {
                            q.offer(new P(nx, ny, skill + 1));
                            visited[nx][ny][skill + 1] = visited[x][y][skill] + 1;
                        }
                    }
                }
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 범위 내이고 (방문한적 없거나 / 방문한적 있는데 현재값 + 1보다 더 큰값이라면)
                if (0 <= nx && nx < h && 0 <= ny && ny < w) {
                    // 1. 빈 곳이고 방문한적 없으면
                    if (arr[nx][ny] == 0 && visited[nx][ny][skill] == 0) {
                        q.offer(new P(nx, ny, skill));
                        visited[nx][ny][skill] = visited[x][y][skill] + 1;
                    }
                }
            }
        }
    }
}
