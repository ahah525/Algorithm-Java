package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 수열 길이
        int s = Integer.parseInt(st.nextToken());   // 최소 부분합
        int[] a = new int[n];
        int start = 0, end = 0;
        int res = n + 1;    // 부분합이 s이상인 최단 연속 부분 수열
        int sum = 0;        // 부분합

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        while (true) {
            if (sum >= s) {
                // 1. 부분합이 s 이상이면 start++
                sum -= a[start];
                res = Math.min(res, end - start);
                start++;
            } else if (end == n) {
                break;
            } else {
                // 2. 부분합이 s 미만이면 end++
                sum += a[end];
                end++;
            }
        }
        // s이상인 부분합을 만들 수 없는 경우 0
        if(res == n + 1)
            res = 0;
        System.out.println(res);
    }
}
