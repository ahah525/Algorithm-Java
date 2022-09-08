package baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1300 {
    private static int n, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());    // 배열 크기
        k = Integer.parseInt(br.readLine());    //

        int res = binarySearch(1, k);
        System.out.println(res);
    }

    /**
     * k 번째 수 = k 이하의 수
     */
    public static int binarySearch(int start, int end) {
        int res = 0;
        while(start <= end) {
            int mid = (start + end) / 2;
            int cnt = 0;
            // mid 이하 값을 가진 것의 개수
            for (int i = 1; i <= n; i++) {
                /**
                 * start = 1, end = 7, mid = 4 일 때
                 * 1의 배수 중에서 4이하의 값을 가진 것의 개수 = 3개 이지만
                 * (mid / i) = 4 / 1 = 4
                 * n의 배수 개수 최대값인 n과 위의 값 중 최소값을 채택해야함
                 */
                // i의 배수 중에서 mid 이하 값을 가진 것의 개수
                cnt += Math.min((mid / i), n);
            }

            if(cnt >= k) {
                // mid 값이 작아져야 함
                end = mid - 1;
                res = mid;
            } else {
                // mid 값이 커져야 함
                start = mid + 1;
            }
        }
        return res;
    }
}
