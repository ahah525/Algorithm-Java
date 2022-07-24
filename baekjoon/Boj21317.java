package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj21317 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 돌의 개수
        int[] small = new int[n];   // small[i]: i->(i+1)로 이동하는 작은 점프 에너지
        int[] big= new int[n];      // big[i]: i->(i+2)로 이동하는 큰 점프 에너지
        StringTokenizer st;
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            small[i] = Integer.parseInt(st.nextToken());
            big[i] = Integer.parseInt(st.nextToken());
        }
        int k = Integer.parseInt(br.readLine());    // i->(i+3)로 이동하는 매우 큰 점프 에너지
        // d[i][0]: i번째 돌까지 이동하는데 매우 큰 점프를 사용하지 않았을 때, 필요한 최소 에너지
        // d[i][1]: i번째 돌까지 이동하는데 매우 큰 점프를 1번 사용했을 때, i번째 돌까지 이동하는데 필요한 최소 에너지
        int[][] d = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            d[i][0] = (int) 1e9;
            d[i][1] = (int) 1e9;
        }

        d[1][0] = 0;    // 점프X
        if(n >= 2)
            d[2][0] = small[1];  // 작은 점프
        if(n >= 3)
            // 작은 점프 2번 or 큰 점프 1번 중 최솟값
            d[3][0] = Math.min(small[1] + small[2], big[1]);
        for (int i = 4; i <= n; i++) {
            d[i][0] = Math.min(d[i - 1][0] + small[i - 1], d[i - 2][0] + big[i - 2]);
            d[i][1] = Math.min(d[i - 3][0] + k, Math.min(d[i - 1][1] + small[i - 1], d[i - 2][1] + big[i - 2]));
        }
        // 매우 큰 점프 사용하지 않았을 때 or 사용했을 때 중 최솟값
        System.out.println(Math.min(d[n][0], d[n][1]));
    }
}
