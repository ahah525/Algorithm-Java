package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj18427 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 학생수
        int m = Integer.parseInt(st.nextToken());   // 최대 블록수
        int h = Integer.parseInt(st.nextToken());   // 탑의 높이
        // a[i]: i번째 학생의 블럭 높이
        List<Integer>[] a = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = new ArrayList<>();
            while (st.hasMoreTokens()) {
                a[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        // d[i][j] : i번째 학생까지 블록을 사용해 높이가 j인 블록을 만드는 경우의 수
        long[][] d = new long[n + 1][h + 1];
        // 초기화
        for (int i = 0; i <= n; i++) {
            // 높이가 h인 블록 1개를 사용하는 경우의 수 = 1
            d[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= h; j++) {
                // 1. 현재 블록 선택X
                d[i][j] = d[i - 1][j];
                // i번째 학생이 갖고있는 각 블록에 대한 경우의 수 계산
                for (int blockH : a[i]) {
                    // 2. 현재 블록 선택O
                    if (j >= blockH) {
                        d[i][j] = (d[i][j] + d[i - 1][j - blockH]) % 10007;
                    }
                }
            }
        }
        System.out.println(d[n][h] % 10007);
    }
}