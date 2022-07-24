package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj11727 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 가로 길이
        // d[i]: 길이가 i인 직사각형을 채우는 방법의 수
        int[] d = new int[n + 1];
        d[1] = 1;
        if(n >= 2) {
            d[2] = 3;
            for (int i = 3; i <= n; i++) {
                // 1. 마지막 타일이 2 * 1 1개
                // 2. 마지막 타일이 1 * 2 2개
                // 3. 마지막 타일이 2 * 2 1개
                d[i] = (d[i - 1] +  2 * d[i - 2]) % 10007;
            }
        }
        System.out.println(d[n]);
    }
}
