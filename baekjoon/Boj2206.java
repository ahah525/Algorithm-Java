package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2206 {
    private static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 세로
        m = Integer.parseInt(st.nextToken());   // 가로
        int res = (int) 1e9;
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }

        // 벽 부순 경우, 부수지 않은 경우
        int[][][] visited = new int[n][m][2];

        bfs(arr, new P(0, 0, 0), visited);

        for (int i = 0; i < 2; i++) {
            if (visited[n - 1][m - 1][i] != 0) {
                res = Math.min(res, visited[n - 1][m - 1][i]);
            }
        }
        if(res == (int) 1e9)
            res = -1;
        System.out.println(res);
    }
    private static void bfs(int[][] arr, P start, int[][][] visited) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        Queue<P> q = new LinkedList<>();
        q.offer(start);
        visited[start.x][start.y][start.cnt] = 1;

        while (!q.isEmpty()) {
            P p = q.poll();
            int x = p.x;
            int y = p.y;
            int cnt = p.cnt;

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 범위내이면
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (arr[nx][ny] == 0 && visited[nx][ny][cnt] == 0) {
                        // 길이고 방문한적 없으면
                        q.offer(new P(nx, ny, cnt));
                        visited[nx][ny][cnt] = visited[x][y][cnt] + 1;
                    } else if (arr[nx][ny] == 1 && cnt == 0 && visited[nx][ny][cnt + 1] == 0) {
                        // 벽인데 아직 벽 부순적 없고 방문한적도 없으면
                        q.offer(new P(nx, ny, cnt + 1));
                        visited[nx][ny][cnt + 1] = visited[x][y][cnt] + 1;
                    }
                }
            }
        }
    }
}
