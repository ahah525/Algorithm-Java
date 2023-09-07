package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 점프
 * [풀이시간] (30분)
 * [한줄평]
 * 1_v1. 완전탐색, DFS(실패-시간초과)
 * [풀이] (0, 0) -> (n-1, n-1) 갈 수 있는 모든 경로 탐색
 * @See <a href="https://www.acmicpc.net/problem/1890">문제</a>
 */
class Boj1890 {
    static int n;
    static int cnt = 0;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());    // 크기
        map = new int[n][n];
        visited = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //
        dfs(0, 0);
        System.out.println(cnt);
    }

    public static void dfs(int x, int y) {
        if(map[x][y] == 0) {
            cnt++;
            return;
        }
        if(inRange(x + map[x][y])) {
            visited[x + map[x][y]][y] = true;
            dfs(x + map[x][y], y);
            visited[x + map[x][y]][y] = false;
        }
        if(inRange(y + map[x][y])) {
            visited[x][y + map[x][y]] = true;
            dfs(x, y + + map[x][y]);
            visited[x][y + map[x][y]] = false;
        }
    }

    public static boolean inRange(int x) {
        if(0 <= x && x < n) return true;
        return false;
    }
}