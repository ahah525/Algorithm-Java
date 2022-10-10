package baekjoon.prefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());   // 부분합
        int cnt = 0;

        int[] arr = new int[n + 1];
        int[] sum = new int[n + 1];
        st = new StringTokenizer(br.readLine());

        arr[1] = Integer.parseInt(st.nextToken());
        for(int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1]  + Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= n; i++) {
            for(int j = i; j <= n; j++) {
                int res = arr[j] - arr[i - 1];
                if(res == k)
                    cnt++;
            }
        }

        System.out.println(cnt);
    }
}
