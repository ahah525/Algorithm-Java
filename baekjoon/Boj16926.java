package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;


public class Boj16926 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());   // 회전 수
        // n * m 배열
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int num = Math.min(n, m) / 2;   // 반복 횟수
        int[] cnt = new int[num];
        for (int i = 0; i < num; i++) {
            cnt[i] = r % ((n + m - 2 - 4 * i) * 2);
        }

        int[][] res = new int[n][m];
        // r번 회전

        for (int i = 0; i < num; i++) {
            for (int k = 0; k < cnt[i]; k++) {
                int now = arr[i][i];
                int next = 0;

                // 1. 좌
                for (int j = i; j <= n - 2 - i; j++) {
                    next = arr[j + 1][i];
                    arr[j + 1][i] = now;
                    now = next;
                }
                // 2. 하
                for (int j = i; j <= m - 2 - i; j++) {
                    next = arr[n - 1 - i][j + 1];
                    arr[n - 1 - i][j + 1] = now;
                    now = next;
                }
                // 3. 우
                for (int j = n - 1 - i; j > i; j--) {
                    next = arr[j - 1][m - 1 - i];
                    arr[j - 1][m - 1 - i] = now;
                    now = next;
                }
                // 4. 상
                for (int j = m - 1 - i; j > i; j--) {
                    next = arr[i][j - 1];
                    arr[i][j - 1] = now;
                    now = next;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
