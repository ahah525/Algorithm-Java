package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [문제명] 드래곤 커브
 * [풀이시간] 35분
 * [한줄평] 구현을 어떻게 해야할 지 감을 못잡아서 결국 풀이 힌트를 보고 풀었고 꼭 다시 풀어봐야할 좋은 문제인 것 같다.
 * 1_v1. 구현(성공)
 * [풀이] i세대의 방향은 (i-1)세대의 각 방향을 반시계로 회전한 방향이다.
 * @See <a href="https://www.acmicpc.net/problem/15685">문제</a>
 * @See <a href="https://dublin-java.tistory.com/34">풀이</a>
 */
class Boj15685 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 개수
        StringTokenizer st;
        // RULD
        int[] dx = {0, -1, 0, 1};
        int[] dy = {1, 0, -1, 0};
        boolean[][] map = new boolean[101][101];    // (i, j) 꼭짓점이 드래곤 커브의 일부인지 여부
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            // 시작점
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            // 방향
            int d = Integer.parseInt(st.nextToken());
            // 세대
            int g = Integer.parseInt(st.nextToken());
            // 1. 시작점이 (x, y), 방향이 d일 때 g세대 드래곤 커브의 모든 방향 구하기
            List<Integer> dirs = new ArrayList<>();
            dirs.add(d);
            for(int j = 0; j < g; j++) {
                for(int k = dirs.size() - 1; k >= 0; k--) {
                    // 해당 방향의 반시계 회전한 방향
                    int nd = (dirs.get(k) + 1) % 4;
                    dirs.add(nd);
                }
            }
            // 2. 시작점이 (x, y)일 때 모든 방향을 탐색하여 꼭짓점(좌표) 구하기
            map[x][y] = true;
            for(int dir : dirs) {
                x += dx[dir];
                y += dy[dir];
                map[x][y] = true;
            }
        }
        // 3. 1x1 정사각형의 4 꼭짓점이 모두 드래곤 커브에 속하는 것의 개수 세기
        int cnt = 0;
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                if(map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) cnt++;
            }
        }
        System.out.println(cnt);
    }
}