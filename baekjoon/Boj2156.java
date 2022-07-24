package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 포도주 잔의 개수
        // a[i]: i번째 잔의 포두주의 양
        int[] a = new int[n + 1];
        // d[i]: i번째 포도주까지 최대로 마실 수 있는 포도주의 양
        int[] d = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        d[1] = a[1];    // O
        if(n >= 2)
            d[2] = a[1] + a[2]; // OO
        for (int i = 3; i <= n; i++) {
            int d1 = d[i - 3] + a[i - 1] + a[i];    // 1. ~XOO
            int d2 = d[i - 2] + a[i];               // 2. ~~XO
            int d3 = d[i - 1];                      // 3. ~~~X
            d[i] = Math.max(Math.max(d1, d2), d3);
        }
        System.out.println(d[n]);
    }
}
