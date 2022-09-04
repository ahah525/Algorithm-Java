package baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Shark {
    int x;
    int y;
    int size;   // 크기
    int cnt;    // 물고기를 먹은 횟수
    int move;   // 누적 이동 횟수

    public Shark(int x, int y, int size, int cnt, int move) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.cnt = cnt;
        this.move = move;
    }
}

public class Boj16236 {
    private static int n;
    private static Shark shark; // 상어 정보
    private static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());    // 공간의 크기
        StringTokenizer st;

        // TODO: 같은 거리로 갈 수 있는 모든 곳 검사하기(예제 4번 해결)
        // TODO: 상어가 있는 곳이면 먹지 못하게(무한루프 해결)
        /***
         * 0 : 빈칸
         * 1, 2, 3, 4, 5, 6 : 물고기 크기
         * 9 : 아기상어 위치
         */

        int[][] visited = new int[n][n];
        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                // 상어 정보 기록
                if (graph[i][j] == 9) {
                    shark = new Shark(i, j, 2, 0, 0);
                }
            }
        }

        while (true) {
            visited = new int[n][n];
            Position start = new Position(shark.x, shark.y);
            if (!bfs(start, visited)) {
                break;
            }
        }
        System.out.println(shark.move);
    }

    public static void eat() {

    }
    public static boolean bfs(Position start, int[][] visited) {
        Queue<Position> q = new LinkedList<>();
        // 시작 좌표 큐에 삽입, 방문 처리
        q.offer(start);
        visited[start.x][start.y] = 1;

        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        while (!q.isEmpty()) {
            Position p = q.poll();
            int x = p.x;
            int y = p.y;
            // 상어 크기 > 물고기 크기 -> 먹음
            // 상어가 아닐 때를 검사하지 않으면 무한루프
            if (graph[x][y] != 9 && graph[x][y] != 0 && shark.size > graph[x][y]) {
                int min = visited[x][y];
                Position minP = new Position(x, y);
                while (!q.isEmpty()) {
                    Position pos = q.poll();
                    // 빈칸이 아니면 비교
                    if(min == visited[pos.x][pos.y] && graph[pos.x][pos.y] != 0 && shark.size > graph[pos.x][pos.y]) {
                        if(x > pos.x || (x == pos.x && y > pos.y)) {
                            minP.x = pos.x;
                            minP.y = pos.y;
                        }
                    }
                }

                x = minP.x;
                y = minP.y;
                shark.x = x;
                shark.y = y;
                shark.cnt++;
                shark.move += visited[x][y] - 1;
                // 크기가 같아지면
                if (shark.cnt == shark.size) {
                    shark.size++;
                    shark.cnt = 0;
                }
                // 상어 위치 업데이트
                graph[start.x][start.y] = 0;
                graph[x][y] = 9;

//                System.out.println("shark.x = " + shark.x);
//                System.out.println("shark.y = " + shark.y);
//                System.out.println("shark.size = " + shark.size);
//                System.out.println("shark.cnt = " + shark.cnt);
//                System.out.println("shark.move = " + shark.move);
//
//                for (int i = 0; i < n; i++) {
//                    for (int j = 0; j < n; j++) {
//                        System.out.print(graph[i][j] + " ");
//                    }
//                    System.out.println();
//                }
//                System.out.println("---------------------------");
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    // 방문하지 않았고
                    if (visited[nx][ny] == 0) {
                        // 빈칸이거나 상어 크기 >= 물고기 크기 -> 이동만
                        if (shark.size >= graph[nx][ny]) {
                            q.offer(new Position(nx, ny));
                            visited[nx][ny] = visited[x][y] + 1;
                        }
                    }
                }
            }
        }
        return false;
    }
}
