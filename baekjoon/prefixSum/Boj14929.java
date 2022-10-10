package baekjoon.prefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        int[] sum = new int[n + 1];
        long res = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        sum[n] = arr[n];
        for(int i = n - 1; i >= 2; i--) {
            sum[i] = sum[i + 1] + arr[i];
        }

        for(int i = 1; i <= n - 1; i++) {
            res += arr[i] * sum[i + 1];
        }

        System.out.println(res);
    }
}
