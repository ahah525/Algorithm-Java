package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14500 {
    private static int n, m, max;
    private static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 세로
        m = Integer.parseInt(st.nextToken());   // 가로
        max = 0;    //
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        check();
        System.out.println(max);
    }
    public static void check() {
        int[][][] dx = {
                {{0, 0, 0, 0}, {0, 1, 2, 3}},
                {{0, 0, 1, 1}},
                {{0, 1, 2, 2}, {0, 0, 0, 1}, {0 ,0 ,1, 2}, {0, 1, 1, 1},
                        {0, 1, 2, 2}, {0, 1, 1, 1}, {0, 0, 1, 2}, {0, 0, 0, 1}},
                {{0, 1, 1, 2}, {0, 0, 1, 1}, {0, 1, 1, 2}, {0, 0 ,1, 1}},
                {{0, 0, 0, 1}, {0, 1, 1, 2}, {0, 1, 1, 1}, {0, 1, 1, 2}}
        };
        int[][][] dy = {
                {{0, 1, 2, 3}, {0, 0, 0, 0}},
                {{0, 1, 0, 1}},
                {{0, 0, 0, 1}, {0, 1, 2, 0}, {0, 1, 1, 1}, {2, 0, 1, 2},
                        {1, 1, 0, 1}, {0, 0, 1, 2}, {0, 1, 0, 0}, {0, 1, 2, 2}},
                {{0, 0, 1, 1}, {1, 2, 0, 1}, {1, 0, 1, 0}, {0, 1, 1, 2}},
                {{0, 1, 2, 1}, {1, 0, 1, 1}, {1, 0, 1, 2}, {0, 0, 1, 0}}
        };
        int[] cnt = {2, 1, 8, 4, 4};    // 경우의 수
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                //
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < cnt[i]; j++) {
                        int sum = 0;
                        boolean valid = true;
                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[i][j][k];
                            int ny = y + dy[i][j][k];
                            // 범위를 벗어나면
                            if(nx < 0 || n <= nx || ny < 0 || m <= ny) {
                                valid = false;
                                break;
                            }
                            sum += arr[nx][ny];
                        }
                        // 의도적 탈출하지 않았을 때만 최댓값 업데이트
                        if(valid) {
                            max = Math.max(max, sum);
                        }
                    }
                }
            }
        }
    }
}
