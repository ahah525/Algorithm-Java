package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 미세먼지 안녕!
 * [풀이시간] 1시간 10분
 * [한줄평] 빡 구현 문제여서 생각보다 푸는데 오래걸렸다.
 * 1_v1. (성공)
 * [풀이] 미세먼지 확산 로직에서는 새로운 배열에 결과를 누적하고 이를 갱신했고, 공기청정기 작동 로직에서는 기존 배열에 바로 값을 갱신했다.
 * @See <a href="https://www.acmicpc.net/problem/17144">문제</a>
 */
class Boj17144 {
    static int r, c, robot;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        // -1: 공기 청정기, 미세먼지의 양
        map = new int[r][c];
        for(int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            // 공기청정기
            if(map[i][0] == -1 && robot == 0) robot = i;
        }
        //
        for(int i = 0; i < t; i++) {
            // 1. 미세먼지 확산
            move();
            // 2. 공기청정기 작동
            clean();
        }
        // 3. 미세먼지 합 계산
        int sum = 0;
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(map[i][j] == -1) continue;
                sum += map[i][j];
            }
        }
        System.out.println(sum);
    }

    public static void move() {
        // 상하좌우
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int[][] res = new int[r][c];
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                res[i][j] += map[i][j];
                if(map[i][j] == 0 || map[i][j] == -1) continue;
                int v = map[i][j] / 5;  // 확산되는 양
                for(int k = 0; k < 4; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    // 범위 밖이거나 공기 청정기인 경우는 패스
                    if(!inRange(x, y) || map[x][y] == -1) continue;
                    res[x][y] += v;
                    res[i][j] -= v;
                }
            }
        }
        map = res;
    }

    public static void clean() {
        // 1. (0 ~ robot)행 반시계 방향
        // 0열 위 -> 아래
        for(int i = robot - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        // 0행 오 -> 왼
        for(int j = 0; j < c - 1; j++) {
            map[0][j] = map[0][j + 1];
        }
        // (c-1)열 아래 -> 위
        for(int i = 0; i < robot; i++) {
            map[i][c - 1] = map[i + 1][c - 1];
        }
        // (robot)행 왼 -> 오
        for(int j = c - 1; j > 1; j--) {
            map[robot][j] = map[robot][j - 1];
        }
        map[robot][1] = 0;  // 공청에서 불어온 바람은 미먼X
        // 2. (robot+1 ~ r-1)행 시계 방향
        // 0열 아래 -> 위
        for(int i = robot + 2; i < r - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        // (r-1)행 오 -> 왼
        for(int j = 0; j < c - 1; j++) {
            map[r - 1][j] = map[r - 1][j + 1];
        }
        // (c-1)열 위 -> 아래
        for(int i = r - 1; i >= robot + 2; i--) {
            map[i][c - 1] = map[i - 1][c - 1];
        }
        // (robot+1)행 왼 -> 오
        for(int j = c - 1; j > 1; j--) {
            map[robot + 1][j] = map[robot + 1][j - 1];
        }
        map[robot + 1][1] = 0;  // 공청에서 불어온 바람은 미먼X
    }

    public static boolean inRange(int x, int y) {
        if(0 <= x && x < r && 0 <= y && y < c) return true;
        return false;
    }
}