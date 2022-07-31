package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Boj15724 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 세로
        int m = Integer.parseInt(st.nextToken());   // 가로
        int[][] a = new int[n][m];    // 사람수
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] d = new int[n][m];
        d[0][0] = a[0][0];
        for (int i = 1; i < n; i++) {
            d[i][0] = d[i - 1][0] + a[i][0];
        }
        for (int i = 1; i < m; i++) {
            d[0][i] = d[0][i - 1] + a[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                d[i][j] = a[i][j] + d[i - 1][j] + d[i][j - 1] - d[i - 1][j - 1];
            }
        }
        int k = Integer.parseInt(br.readLine());    // 직사각형 범위 개수
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            Point s = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
            Point e = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
            int res = d[e.x][e.y];

            if (s.x == 0 && s.y > 0) {
                res -= d[e.x][s.y - 1];
            } else if (s.x > 0 && s.y == 0) {
                res -= d[s.x - 1][e.y];
            } else if(s.x > 0 && s.y > 0) {
                res = res - d[e.x][s.y - 1] - d[s.x - 1][e.y] + d[s.x - 1][s.y - 1];
            }
            //int res = d[e.x][e.y] - d[e.x][s.y - 1] - d[s.x - 1][e.y] + d[s.x - 1][s.y - 1];
            System.out.println(res);
        }
    }
}
