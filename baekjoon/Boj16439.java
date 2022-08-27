package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj16439 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 회원 수
        int m = Integer.parseInt(st.nextToken());   // 치킨 종류 수
        int max = 0;    // 만족도 합의 최댓값
        int[][] arr = new int[n][m];    // 치킨 선호도
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 경우의 수 = mC3
        for (int i = 0; i <= m - 3; i++) {
            for (int j = i + 1; j <= m - 2; j++) {
                for (int k = j + 1; k <= m - 1; k++) {
                    int res = 0;    // 만족도
                    // 모든 회원의 만족도
                    for (int a = 0; a < n; a++) {
                        res += Math.max(Math.max(arr[a][i], arr[a][j]), arr[a][k]);
                    }
                    max = Math.max(max, res);
                }
            }
        }
        System.out.println(max);
    }
}
