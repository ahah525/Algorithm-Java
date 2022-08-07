package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 수열 길이
        int k = Integer.parseInt(st.nextToken());
        int[] a = new int[n];   // 수열
        int max = 0;    // 수열 원소 중 최댓값
        int res = 0;    // 같은 원소가 k개 이하로 들어있는 최장 연속 부분 수열의 길이
        int start = 0, end = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, a[i]);
        }

        int[] cnt = new int[max + 1];   // cnt[i]: 부분 수열에서 i의 개수
        while (true) {
            // end가 끝에 도달하면 종료(start를 오른쪽으로 옮겨봤자 최댓값 아님)
            if (end == n) {
                break;
            }
            if (cnt[a[end]] < k) {
                // 1. 현재 끝 숫자의 개수가 k 보다 작으면
                cnt[a[end++]] += 1;
            } else {
                // 2. 현재 끝 숫자의 개수가 k 이상이면
                cnt[a[start++]] -= 1;
            }
            // 현재 부분 수열 = s ~ (e-1)
            // 부분 수열 길이 = (e-1) - s + 1 = e - s
            res = Math.max(res, end - start);
        }
        System.out.println(res);
    }
}
