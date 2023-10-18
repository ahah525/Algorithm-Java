package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] 연구소
 * [풀이시간] 50분 / 40분
 * [한줄평] 구현이 조금 복잡하긴 했지만 각 알고리즘을 구현만 할 줄 알면 어렵지 않게 풀 수 있는 문제였다. 여러 알고리즘을 복습할 수 있는 좋은 문제같다.
 * / 여러가지 알고리즘을 써서 푸는 좋은 문제였다. 두번째는 빈칸 좌표를 리스트에 저장하고 활용했는데 첫번째 풀이보다는 조금 느렸다.
 * 1_v1. DFS,BFS,완전탐색(성공) -> 추천
 * [풀이] n*m개의 좌표 중에서 3개를 골라 벽으로 만드는 모든 경우의 수에 대해 안전영역 크기를 계산한다. 바이러스 좌표를 리스트에 저장하고 BFS를 돌린다.
 * 2_v1. DFS, BFS, 완전탐색(성공)
 * [풀이] 1_v1 동일, 빈칸 좌표를 리스트에 저장하고 DFS에서 활용한다.
 * @See <a href="https://www.acmicpc.net/problem/14502">문제</a>
 */
class Boj14502 {
    // 1_v1
    static class P {
        int x;
        int y;
        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m, blank, max;
    static int[][] map;
    static List<P> virus;   // 바이러스 좌표 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        blank = 0;  // 빈 칸 개수
        max = 0;    // 안전영역 최댓값
        map = new int[n][m];    // 0: 빈칸, 1: 벽, 2: 바이러스
        virus = new ArrayList<>();
        // 1. 맵 초기화, 빈칸 개수 세기, 바이러스 좌표 저장
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j< m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) blank++;
                else if(map[i][j] == 2) virus.add(new P(i, j));
            }
        }
        // 2. 벽 3개를 세울 것이므로 빈칸 개수 조정
        blank -= 3;
        dfs(0, 0);
        System.out.println(max);
    }

    // n * m 좌표 중에서 3개 고르기
    public static void dfs(int depth, int start) {
        if(depth == 3) {
            // 1. 원래 빈칸이었던 곳 중에 바이러스가 퍼진 칸의 개수 구하기
            boolean[][] visited = new boolean[n][m];
            int cnt = 0;
            for(P p : virus) {
                cnt += bfs(p.x, p.y, visited);
            }
            // 2. 안전영역 크기 = 초기 빈칸 개수 - 바이러스가 퍼져서 더이상 빈칸이 아닌 칸의 개수
            max = Math.max(max, blank - cnt);
            return;
        }
        //
        for(int i = start; i < n * m; i++) {
            int x = i / m;
            int y = i % m;
            if(map[x][y] != 0) continue;
            // 3. 빈칸을 벽으로 바꿈
            map[x][y] = 1;
            dfs(depth + 1, i + 1);
            map[x][y] = 0;
        }
    }

    // (sx, sy)칸에 있는 바이러스가 퍼진 칸의 개수 조회
    public static int bfs(int sx, int sy, boolean[][] visited) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int cnt = 0;    // 바이러스가 퍼진 칸의 개수
        Queue<P> q = new LinkedList<>();
        q.add(new P(sx, sy));
        visited[sx][sy] = true;
        while(!q.isEmpty()) {
            P p = q.poll();
            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                // 범위를 벗어난 경우, 빈칸이 아닌 경우, 이미 방문한 경우는 패스
                if(!inRange(nx, ny) || map[nx][ny] != 0 || visited[nx][ny]) continue;
                q.add(new P(nx, ny));
                visited[nx][ny] = true;
                cnt++;
            }
        }
        return cnt;
    }

    public static boolean inRange(int x, int y) {
        if(0 <= x && x < n && 0 <= y && y < m) return true;
        return false;
    }

    // 2_v1
//    static class P {
//        int x;
//        int y;
//        P(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//    }
//
//    static int n, m, max;
//    static int[][] map;
//    static List<P> blanks;    // 빈칸 좌표
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        n = Integer.parseInt(st.nextToken());
//        m = Integer.parseInt(st.nextToken());
//        // 0:빈칸, 1:벽, 2:바이러스
//        map = new int[n][m];
//        max = 0;
//        blanks = new ArrayList<>();
//        for(int i = 0; i < n; i++) {
//            st = new StringTokenizer(br.readLine());
//            for(int j = 0; j < m; j++) {
//                map[i][j] = Integer.parseInt(st.nextToken());
//                if(map[i][j] == 0) {
//                    blanks.add(new P(i, j));
//                }
//            }
//        }
//        dfs(0, 0);
//        System.out.println(max);
//    }
//
//    public static void dfs(int depth, int start) {
//        if(depth == 3) {
//            int safe = blanks.size() - 3;
//            boolean[][] visited = new boolean[n][m];
//            for(int i = 0; i < n; i++) {
//                for(int j = 0; j < m; j++) {
//                    if(map[i][j] != 2) continue;
//                    // 감염시킨 수
//                    safe -= bfs(i, j, visited);
//                }
//            }
//            max = Math.max(max, safe);
//            return;
//        }
//        //
//        for(int i = start; i < blanks.size(); i++) {
//            P p = blanks.get(i);
//            map[p.x][p.y] = 1;
//            dfs(depth + 1, i + 1);
//            map[p.x][p.y] = 0;
//        }
//    }
//
//    public static int bfs(int sx, int sy, boolean[][] visited) {
//        int[] dx = {-1, 1, 0, 0};
//        int[] dy = {0, 0, -1, 1};
//        Queue<P> q = new LinkedList<>();
//        q.add(new P(sx, sy));
//        visited[sx][sy] = true;
//        //
//        int cnt = 0;
//        while(!q.isEmpty()) {
//            P p = q.poll();
//            for(int i = 0; i < 4; i++) {
//                int nx = p.x + dx[i];
//                int ny = p.y + dy[i];
//                if(!inRange(nx, ny) || visited[nx][ny] || map[nx][ny] != 0) continue;
//                q.add(new P(nx, ny));
//                visited[nx][ny] = true;
//                cnt++;
//            }
//        }
//        return cnt;
//    }
//
//    public static boolean inRange(int x, int y) {
//        if(0 <= x && x < n && 0 <= y && y < m) return true;
//        return false;
//    }
}