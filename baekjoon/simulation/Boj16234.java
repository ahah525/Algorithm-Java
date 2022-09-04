package baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj16234 {
    private static int n, l, r;
    private static int[][] graph;
    private static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 땅 크기
        l = Integer.parseInt(st.nextToken());   // 인구 차이 l명 이상 r명 이하
        r = Integer.parseInt(st.nextToken());
        graph = new int[n][n];    // 각 나라의 인구수
        visited = new int[n][n];
        int move = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            boolean isMoveed = false;
            visited = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] == 0) {
                        // 이동되었다면
                        if (bfs(new Position(i, j))) {
                            isMoveed = true;
                        }
                    }
                }
            }
            if(!isMoveed)   break;
            move++;
        }
        System.out.println(move);
    }
    public static boolean bfs(Position start) {
        int sum = 0;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<Position> q = new LinkedList<>();
        List<Position> moveList = new ArrayList<>();
        q.offer(start);
        visited[start.x][start.y] = 1;

        while (!q.isEmpty()) {
            Position p = q.poll();
            int x = p.x;
            int y = p.y;
            moveList.add(new Position(x, y));
            sum += graph[x][y];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (visited[nx][ny] == 0) {
                        int diff = Math.abs(graph[x][y] - graph[nx][ny]);
                        if (l <= diff && diff <= r) {
                            q.offer(new Position(nx, ny));
                            visited[nx][ny] = visited[x][y] + 1;
                        }
                    }
                }
            }
        }
        if(moveList.size() == 1) {
            return false;
        }
        // 평균값 반영
        int avg = (int) (sum / moveList.size());
        for (Position p : moveList) {
            graph[p.x][p.y] = avg;
        }
        return true;
    }
}
