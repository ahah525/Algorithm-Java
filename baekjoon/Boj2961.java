package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2961 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());    // 재료 개수
        int min = Integer.MAX_VALUE;
        int[][] arr = new int[n][2];    // (신맛, 쓴맛)
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());   // 신맛
            arr[i][1] = Integer.parseInt(st.nextToken());   // 쓴맛
        }

        /**
         * 0 0 0
         * 재료를 적어도 1개 이상 사용하는 모든 경우의 수 = 2^n - 1
         * (a << n) = a * 2^n
         * 3 8
         * 5 8
         * -----
         * 0 0
         * 2 1
         * 0 1 <- (1)
         * 1 0
         * 1 1
         */

        for (int i = 1; i < (1 << n); i++) {
            int a = 1;  // 신맛(*)
            int b = 0;  // 쓴맛(+)
            // 각 자리 수 검사
            for (int j = 0; j < n; j++) {
                // i를 비트로 표현했을 때, j번째 자리의 비트값이 1이면
                if ((i & (1 << j)) != 0) {
                    a *= arr[j][0];
                    b += arr[j][1];
                }
            }
            min = Math.min(min, Math.abs(a - b)) ;
        }

        System.out.println(min);
    }
}
