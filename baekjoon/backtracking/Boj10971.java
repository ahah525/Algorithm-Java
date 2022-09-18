package baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10971 {
    private static int n;
    private static int min = Integer.MAX_VALUE;
    private static boolean[] visited;
    private static int[] temp;
    private static int[][] w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());    // 도시 수
        visited = new boolean[n];
        temp = new int[n];

        w = new int[n][n];  // i -> j 로 가는 비용
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(min);
    }

    /***
     * 0 - 1 - 2 - 3 - 0
     */

    public static void dfs(int depth, int cost) {
        // 현재 비용이 최소 비용보다 크면 더 탐색할 필요X
        if(cost > min) {
            return;
        }
        if(depth == n) {
            // 시작점으로 되돌아가기
            int prev = temp[depth - 1];
            int start = temp[0];
            if(w[prev][start] != 0) {
                min = Math.min(min, cost + w[prev][temp[0]]);
            }
        }

        for(int i = 0; i < n; i++) {
            // 1. 첫번째 도시인 경우
            // 2. 이전에 방문한적 없고 해당 도시로 이동할 수 있는 경우
            if(depth == 0 || (!visited[i] && w[temp[depth - 1]][i] != 0)) {
                visited[i] = true;
                temp[depth] = i;
                int c = 0;
                if(depth != 0) {
                    c = w[temp[depth - 1]][i];
                }
                dfs(depth + 1, cost + c);
                visited[i] = false;
            }
        }
    }
}
