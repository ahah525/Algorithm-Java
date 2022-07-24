package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1106 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());   // 늘려야하는 최소 고객수
        int n = Integer.parseInt(st.nextToken());   // 홍보가능한 도시 개수
        int[] cost = new int[n + 1];    // cost[i]: i번째 도시 홍보 비용
        int[] num = new int[n + 1];     // num[i]: i번째 도시 홍보 고객수
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            num[i] = Integer.parseInt(st.nextToken());
        }
        // d[i][j]: i번째 도시까지 최소 c명을 얻기 위해 필요한 최소 비용
        int[][] d = new int[n + 1][c + 1];
        // 0행 1e9로 초기화(최소 비용을 구해야하므로 최댓값으로 초기화)
        for (int i = 0; i <= c; i++) {
            d[0][i] = (int) 1e9;
        }
        // n번째 도시까지 c명을 얻기 위해 필요한 최소 비용 계산
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= c; j++) {
                // 1. i번째 도시를 선택X
                int c1 = d[i - 1][j];
                // 2. i번째 도시를 선택O
                int c2 = cost[i] + (j > num[i] ? d[i][j - num[i]] : 0);
                d[i][j] = Math.min(c1, c2);
            }
        }
        System.out.println(d[n][c]);
    }
}
