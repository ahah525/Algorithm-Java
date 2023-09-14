package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제명] 녹색 옷 입은 애가 젤다지?
 * [풀이시간] 30분
 * [한줄평] 다익스트라 문제인 걸 몰랐다면 단순하게 BFS로 풀어서 틀렸을 문제다. 다음에 꼭 다시 풀어봐야겠다.
 * 1_v1. 그래프/다익스트라(성공)
 * [풀이] 다익스트라 알고리즘으로 (0,0)에서 모든 좌표 (x,y)까지 최단거리를 구한다.
 * @See <a href="https://www.acmicpc.net/problem/4485">문제</a>
 */
class Boj4485 {
    static class P {
        int x;
        int y;
        int cost;

        public P(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int idx = 1;
        StringBuilder sb = new StringBuilder();
        while(true) {
            int n = Integer.parseInt(br.readLine());    // 동굴 크기
            if(n == 0) break;
            int[][] arr = new int[n][n];
            for(int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // 1. (0, 0)에서 (x, y)까지 최단거리 배열 초기화
            int[][] d = new int[n][n];
            int INF = 1000000;
            for(int[] dd : d) {
                Arrays.fill(dd, INF);
            }
            d[0][0] = arr[0][0];
            boolean[][] visited = new boolean[n][n];
            // 2. 시작 좌표 설정
            Queue<P> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
            q.add(new P(0, 0, arr[0][0]));
            //
            while(!q.isEmpty()) {
                // 3. 아직 방문하지 않은 좌표 중 출발점으로부터 최단거리에 있는 좌표 방문하기
                P p = q.poll();
                int x = p.x;
                int y = p.y;
                if(visited[x][y]) continue;
                visited[x][y] = true;
                // 4. 현재 좌표 인접 4방향 좌표 검사
                for(int i = 0; i <4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if(!inRange(nx, ny, n) || d[nx][ny] <= d[x][y] + arr[nx][ny]) continue;
                    // 5. (0,0) -> (nx,ny)의 최단거리 갱신
                    d[nx][ny] = d[x][y] + arr[nx][ny];
                    q.add(new P(nx, ny, d[nx][ny]));
                }
            }
            sb.append("Problem ").append(idx++).append(": ").append(d[n - 1][n - 1]).append("\n");
        }
        System.out.println(sb);
    }

    public static boolean inRange(int x, int y, int n) {
        if(0 <= x && x < n && 0 <= y && y < n) return true;
        return false;
    }
}