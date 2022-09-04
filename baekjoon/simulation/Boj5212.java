package baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Boj5212 {
    private static int r, c;
    private static int[][] graph;
    private static int[][] visited;
    private static List<Position> mountainList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        /**
         * 0 : 땅(X)
         * 1 : 바다(.)
         * 2 : 침몰되서 바다로 변함
         */
        graph = new int[r][c];
        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                if (s.charAt(j) == '.') {
                    graph[i][j] = 1;
                } else {
                    graph[i][j] = 0;
                    mountainList.add(new Position(i, j));
                }
            }
        }

        for (Position p : mountainList) {
            int x = p.x;
            int y = p.y;
            int cnt = 0;
            // 인접한 4칸 바다 개수 세기
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 <= nx && nx < r && 0 <= ny && ny < c) {
                    if (graph[nx][ny] == 1) {
                        cnt++;
                    }
                } else {
                    cnt++;
                }
            }
            if(cnt >= 3) {
                // 바다로 바뀜
                graph[x][y] = 2;
            }
        }

        int minX = 10, minY = 10;
        int maxX = -1, maxY = -1;

        for(Position p : mountainList) {
            if (graph[p.x][p.y] == 0) {
                minX = Math.min(minX, p.x);
                minY = Math.min(minY, p.y);
                maxX = Math.max(maxX, p.x);
                maxY = Math.max(maxY, p.y);
            }
        }

        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                if (graph[i][j] == 0) {
                    System.out.print("X");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
