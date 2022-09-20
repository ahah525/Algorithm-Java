package baekjoon.backtracking;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj9663 {
    private static int n;
    private static boolean[][] visited;   // 열 선택여부
    private static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());    // 체스판 크기
        visited = new boolean[n][n];
        // n * n 중에서 n 개 뽑기(조합)
        // 각 행, 열에 무조건 퀸 한개 있어야 함
        dfs(0);
        System.out.println(cnt);

    }
    public static void dfs(int depth) {
        // depth = 행, i = 열
        if(depth == n) {
            cnt++;
            return;
        }

        // 첫번째 행은 아무데나 위치
        for(int i = 0; i < n; i++) {
            boolean possible = true;
            // 현재 좌표 (depth, i)
            // j번째 행 유망성 검사
            for(int j = 1; j <= depth; j++) {
                // 위쪽 행을 대상으로 왼쪽 대각선, 수직 오른쪽 대각선에 방문한 좌표가 있는지 체크
                if((i - j >= 0 && i - j < n && visited[depth - j][i - j])
                        || (visited[depth - j][i])
                        || (i + j >= 0 && i + j < n && visited[depth - j][i + j])
                ) {
                    possible = false;
                    break;
                }
            }
            if(depth == 0 || possible) {
                Point p = new Point(depth, i);
                visited[depth][i] = true;
                dfs(depth + 1);
                visited[depth][i] = false;
            }
        }
    }
}
