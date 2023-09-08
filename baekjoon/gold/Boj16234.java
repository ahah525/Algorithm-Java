package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] 인구 이동
 * [풀이시간] / 46분
 * [한줄평] / 구현하는데 조금 시간이 걸리긴 했지만 bfs 구현만 할 수 있으면 못 풀 문제는 아니었고 다시 풀어봐도 좋을 문제인 것 같다.
 * 1_v1. 구현, BFS(성공)
 * 2_v1. 구현, BFS(성공)
 * [풀이] 한 점에서 BFS를 돌려서 이동할 수 있는 모든 곳의 평균값으로 갱신하고 이동이 더 이상 일어나지 않는다면 종료한다.
 * @See <a href="https://www.acmicpc.net/problem/16234">문제</a>
 */
class Boj16234 {
    // 2_v1
    static class P {
        int x;
        int y;
        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] a;
    static int n, l, r;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());   // 인구 차이 최솟값
        r = Integer.parseInt(st.nextToken());   // 인구 차이 최댓값
        a = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //
        int answer = 0; // 이동 횟수
        while(true) {
            int[][] visited = new int[n][n];    // (i, j)가 속한 연합의 번호
            List<Integer> list = new ArrayList<>(); // 연합 번호에 속한 곳의 평균값
            int idx = 1;
            boolean move = false;
            // 1. 각 나라가 속한 연합 찾기
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(visited[i][j] != 0) continue;
                    int res = bfs(i, j, idx++, visited);
                    if(res != -1) move = true;
                    list.add(res);
                }
            }
            // 2. 각 나라의 인구수 갱신
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    // 3. (i, j)에 위치한 나라가 속한 연합의 평균값이 -1이면, 인구 이동이 발생하지 않았으므로 패스
                    int res = list.get(visited[i][j] - 1);
                    if(res == -1) continue;
                    // 4. 연합의 평균값으로 갱신
                    a[i][j] = res;
                }
            }
            // 5. 인구 이동이 일어나지 않았으면 종료
            if(!move) break;
            answer++;
        }
        System.out.println(answer);
    }

    //
    public static int bfs(int sx, int sy, int num, int[][] visited) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<P> q = new LinkedList<>();
        // 1. 시작 노드 삽입
        q.add(new P(sx, sy));
        visited[sx][sy] = num;
        int sum = 0;    // 연합에 포함된 모든 나라의 인구수 총합
        int cnt = 0;    // 연합에 포함된 나라 개수
        while(!q.isEmpty()) {
            P p = q.poll();
            int x = p.x;
            int y = p.y;
            sum += a[x][y];
            cnt++;
            // 2. 인접 4방향 탐색
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 3. 범위 밖이거나 이미 방문했거나 이동할 수 없는 곳이라면, 패스
                if(!inRange(nx, ny) || visited[nx][ny] != 0 || !canMove(a[x][y], a[nx][ny])) continue;
                // 4. 인접 좌표 큐에 삽입, 방문 처리
                q.add(new P(nx, ny));
                visited[nx][ny] = num;
            }
        }
        // 5. 연합에 포함된 나라가 1개면, 갱신할 필요가 없으므로 -1리턴
        if(cnt <= 1) return -1;
        // 6. 평균값 리턴
        return sum / cnt;
    }

    // (x, y)가 범위 내의 좌표인지
    public static boolean inRange(int x, int y) {
        if(0 <= x && x < n && 0 <= y && y < n) return true;
        return false;
    }

    // 두 나라의 인구수가 l이상 r이하 인지
    public static boolean canMove(int a, int b) {
        int diff = Math.abs(a - b);
        if(l <= diff && diff <= r) return true;
        return false;
    }

    // 1_v1
//    private static int n, l, r;
//    private static int[][] graph;
//    private static int[][] visited;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        n = Integer.parseInt(st.nextToken());   // 땅 크기
//        l = Integer.parseInt(st.nextToken());   // 인구 차이 l명 이상 r명 이하
//        r = Integer.parseInt(st.nextToken());
//        graph = new int[n][n];    // 각 나라의 인구수
//        visited = new int[n][n];
//        int move = 0;
//        for (int i = 0; i < n; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < n; j++) {
//                graph[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        while (true) {
//            boolean isMoveed = false;
//            visited = new int[n][n];
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    if (visited[i][j] == 0) {
//                        // 이동되었다면
//                        if (bfs(new Position(i, j))) {
//                            isMoveed = true;
//                        }
//                    }
//                }
//            }
//            if(!isMoveed)   break;
//            move++;
//        }
//        System.out.println(move);
//    }
//    public static boolean bfs(Position start) {
//        int sum = 0;
//        int[] dx = {-1, 1, 0, 0};
//        int[] dy = {0, 0, -1, 1};
//        Queue<Position> q = new LinkedList<>();
//        List<Position> moveList = new ArrayList<>();
//        q.offer(start);
//        visited[start.x][start.y] = 1;
//
//        while (!q.isEmpty()) {
//            Position p = q.poll();
//            int x = p.x;
//            int y = p.y;
//            moveList.add(new Position(x, y));
//            sum += graph[x][y];
//
//            for (int i = 0; i < 4; i++) {
//                int nx = x + dx[i];
//                int ny = y + dy[i];
//                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
//                    if (visited[nx][ny] == 0) {
//                        int diff = Math.abs(graph[x][y] - graph[nx][ny]);
//                        if (l <= diff && diff <= r) {
//                            q.offer(new Position(nx, ny));
//                            visited[nx][ny] = visited[x][y] + 1;
//                        }
//                    }
//                }
//            }
//        }
//        if(moveList.size() == 1) {
//            return false;
//        }
//        // 평균값 반영
//        int avg = (int) (sum / moveList.size());
//        for (Position p : moveList) {
//            graph[p.x][p.y] = avg;
//        }
//        return true;
//    }
}