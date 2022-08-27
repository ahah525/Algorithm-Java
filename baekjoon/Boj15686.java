package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj15686 {
    private static int min = Integer.MAX_VALUE;
    private static int n, m, h, c;
    private static int[][] arr;
    private static List<Point> chicken = new ArrayList<>(); // 치킨집 좌표 리스트
    private static List<Point> home = new ArrayList<>();    // 집 좌표 리스트
    private static boolean[] visit;     // 방문여부
    private static int[][] dis;    //  // (i번째 집 ~ j번째 치킨집) 거리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 도시 크기
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][n];    //
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) {
                    // 치킨집 위치 저장
                    chicken.add(new Point(i, j));
                } else if (arr[i][j] == 1) {
                    // 집 위치 저장
                    home.add(new Point(i, j));
                }
            }
        }
        h = home.size();    // 집 수
        c = chicken.size(); // 치킨집 수
        visit = new boolean[c]; // 치킨집 선택 여부
        dis = new int[h][c];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < c; j++) {
                int dx = Math.abs(home.get(i).x - chicken.get(j).x);
                int dy = Math.abs(home.get(i).y - chicken.get(j).y);
                dis[i][j] = dx + dy;
            }
        }

        dfs(0, 0);
        System.out.println(min);
    }

    //
    public static void calcSum() {
        int sum = 0;
        // 거리 계산
        for (int i = 0; i < h; i++) {
            int d = Integer.MAX_VALUE;
            for (int j = 0; j < c; j++) {
                // 선택된 치킨집이면
                if (visit[j]) {
                    d = Math.min(d, dis[i][j]);
                }
            }
            sum += d;
        }
        min = Math.min(min, sum);
    }
    // 치킨집 순서, 현재까지 고른 치킨집 수
    public static void dfs(int depth, int cnt) {
        if (depth == c) {
            if(0 < cnt && cnt <= m) {
                calcSum();
            }
            return;
        }
        //
        if (cnt == m) {
            calcSum();
            return;
        }

        // 1. 현재 치킨집 방문 O
        visit[depth] = true;
        dfs(depth + 1, cnt + 1);


        // 2. 현재 치킨집 방문 X
        visit[depth] = false;
        dfs(depth + 1, cnt);
    }
}
