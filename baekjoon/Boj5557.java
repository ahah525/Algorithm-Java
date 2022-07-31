package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5557 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 숫자 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n - 1; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        int target = Integer.parseInt(st.nextToken());
        // d[i][j]: i번째 수까지 j가 되는 경우의 수
        long[][] d = new long[n - 1][21];
        d[0][a[0]] = 1;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (j + a[i] <= 20) {
                    d[i][j + a[i]] += d[i - 1][j];
                }
                if (j - a[i] >= 0) {
                    d[i][j - a[i]] += d[i - 1][j];
                }
            }
        }
        System.out.println(d[n - 2][target]);
     }
}
