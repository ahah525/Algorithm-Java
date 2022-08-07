package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj22862 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 수열 길이
        int k = Integer.parseInt(st.nextToken());   // 최대 삭제 횟수
        int[] a = new int[n];   // n개의 수열
        int start = 0, end = 0;
        int res = 0;        // 최대 K번 원소를 삭제한 수열에서 가장 긴 짝수 연속 부분 수열의 길이
        int remove = 0;    // 삭제한 횟수

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        while (true) {
            if (remove > k) {
                // 1. 삭제한 횟수가 k번 보다 크면
                if (a[start] % 2 != 0) {
                    // start 값이 홀수면 삭제횟수 차감
                    remove--;
                }
                start++;
            } else if (end == n) {
                break;
            } else {
                // 2. 삭제한 횟수가 k번 이하면
                if (a[end] % 2 != 0) {
                    // end 값이 홀수면 삭제횟수 증가
                    remove++;
                }
                end++;
                res = Math.max(res, end - start - remove);
            }
        }
        System.out.println(res);
    }
}
