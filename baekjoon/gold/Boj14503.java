package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 로봇 청소기
 * [풀이시간] 33분
 * [한줄평] 구현은 어렵지 않았는데, 문제 조건에 대한 설명이 부족해서 푸는데 조금 오래걸렸다.
 * 1_v1. 구현(성공)
 * [풀이]
 * 1. 현재 칸이 청소되지 않은 칸(0)이라면, 해당 칸을 청소(2)한다.
 * 2. 현재 칸의 주변 4칸을 반시계 방향으로 검사하며 청소되지 않은 칸(0)이면, 해당 방향으로 한 칸 전진한다. 1번으로 돌아간다.
 * 3. 현재 칸의 주변 4칸 중 청소되지 않은 칸(0)이 없으면, 방향을 유지한 채로 한 칸 후진한다. 1번으로 돌아간다.
 * 4. 후진할 수 없다면(해당 칸이 범위 밖이거나 벽(1)이라면), 종료한다.
 * @See <a href="https://www.acmicpc.net/problem/14503">문제</a>
 */
class Boj14503 {
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];    // 0: 청소안된 칸, 1: 벽, 2: 청소한 칸
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // URDL
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int cnt = 0;    // 청소한 칸 개수
        while(true) {
            // 1. 현재 칸이 아직 청소되지 않은 경우,
            if(map[x][y] == 0) {
                map[x][y] = 2;
                cnt++;
            }
            boolean find = false;   // 주변 4칸 중 청소되지 않은 빈칸이 있는지 여부
            int dd = d;
            for(int i = 0; i < 4; i++) {
                int nd = (dd + 3) % 4;   // 반시계 90도 회전한 방향
                int nx = x + dx[nd];
                int ny = y + dy[nd];
                // 2. 범위 밖이거나 청소되지 않은 빈 칸이 아니면, 다음 방향 탐색
                if(!inRange(nx, ny) || map[nx][ny] != 0) {
                    dd = nd;
                    continue;
                }
                // 3. 청소되지 않은 빈 칸이면, 한 칸 전진 후 1번으로 이동
                x = nx;
                y = ny;
                d = nd;
                find = true;
                break;
            }
            if(find) continue;
            // 4. 현재 칸의 주변 4칸 중 청소되지 않은 빈칸이 없는 경우, 후진 방향 탐색
            int nd = (d + 2) % 4;   // 후진 방향
            int nx = x + dx[nd];
            int ny = y + dy[nd];
            // 5. 범위 밖이거나 벽이라 후진할 수 없다면, 작동 멈춤
            if(!inRange(nx, ny) || map[nx][ny] == 1) break;
            // 6. 바라보는 방향을 유지한 채로 한 칸 후진
            x = nx;
            y = ny;
        }
        System.out.println(cnt);
    }
    public static boolean inRange(int x, int y) {
        if(0 <= x && x < n && 0 <= y && y < m) return true;
        return false;
    }
}