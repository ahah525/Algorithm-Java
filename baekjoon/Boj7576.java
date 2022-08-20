package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pos {
    int x;
    int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Boj7576 {
    private static int m, n;
    private static Queue<Pos> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());   // 가로
        n = Integer.parseInt(st.nextToken());   // 세로
        int res = 0;
        int zero = 0; // 안익은 토마토 개수
//        int[][] visited = new int[n][m];
        int[][] arr = new int[n][m];    // 토마토 정보
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                // 익은 토마토이면 모두 큐에 삽입
                if (arr[i][j] == 1) {
                    q.offer(new Pos(i, j));
                } else if (arr[i][j] == 0) {
                    // 안익은 토마토가 있으면
                    zero++;
                }
            }
        }

        // 안익은 토마토가 있으면 bfs 수행
        if (zero != 0) {
            res = bfs(arr);
            // bfs 결과 확인
            l:
            for (int i = 0; i < n; i++) {
               for (int j = 0; j < m; j++) {
                    // 안익은 토마토가 있다면
                    if (arr[i][j] == 0) {
                        res = -1;
                        break l;
                    }
                }
            }
        }
        System.out.println(res);
    }
    public static int bfs(int[][] arr) {
        int res = -1;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        while (!q.isEmpty()) {
            Pos point = q.poll();
            int x = point.x;
            int y = point.y;
            res = Math.max(res, arr[x][y]);

            // 인접 4방향
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 범위 이내이고 방문한적 없고 익지 않은 토마토이면
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (arr[nx][ny] == 0) {
                        q.offer(new Pos(nx, ny));
                        arr[nx][ny] = arr[x][y] + 1;
                    }
                }
            }
        }
        return res - 1;
    }
}
