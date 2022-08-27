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
        int min = (int) 1e9;
        int[][] arr = new int[n][2];    // (신맛, 쓴맛)
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());   // 신맛
            arr[i][1] = Integer.parseInt(st.nextToken());   // 쓴맛
        }

        // 모든 경우의 수 = 2^n - 1
        // 재료를 모두 사용하지않는 경우의 수는 0일 때이므로 1부터 시작
        for (int i = 1; i < (1 << n); i++) {
            int a = 1;  // 신맛(*)
            int b = 0;  // 쓴맛(+)
            // 각 자리 수 검사
            for (int j = 0; j < n; j++) {
                // j번째 자리의 비트값이 1이면
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
