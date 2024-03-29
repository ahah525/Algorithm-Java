package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] 낚시왕
 * [풀이시간] 1시간 20분(1시간 15분+5분)
 * [한줄평] 문제 그대로 구현하면 되는 문제였는데 1)땅에서 가장 가까운 상어를 구하고 2)모든 상어를 이동시킬 때, 효율적인 방식을 사용하려고 여러 방법을 시도하다가 결국 2차원 배열을 완전탐색하는 것으로 풀었다.
 * 1_v1. 구현(실패-시간초과)
 * [반례] 속력은 최대 1000이기 때문에, 최악의 상황에 1000칸을 매번 이동하면 시간초과가 발생한다.
 * 1_v2. 구현(성공)
 * [해결] 상하로 이동할 때는 (높이-1)*2 만큼 나머지 연산을 하고, 좌우로 이동할 때는 (너비-1)*2 만큼 나머지 연산을 한다.
 * @See <a href="https://www.acmicpc.net/problem/17143">문제</a>
 * @See <a href="https://minhamina.tistory.com/65">풀이</a>
 */
class Boj17143 {
    // 1_v1
    static class Shark {
        int x;
        int y;
        int s;  // 속력
        int d;  // 이동방향
        int z;  // 크기

        public Shark(int x, int y, int s, int d, int z) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    static int r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());   // 상어수
        Shark[][] map = new Shark[r][c];
        // 상하우좌
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        // 1. m개의 상어
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken()) - 1;  // 행
            int sy = Integer.parseInt(st.nextToken()) - 1;  // 열
            int s = Integer.parseInt(st.nextToken());   // 속력
            int d = Integer.parseInt(st.nextToken()) - 1;   // 이동 방향
            int z = Integer.parseInt(st.nextToken());   // 크기
            map[sx][sy] = new Shark(sx, sy, s, d, z);
        }
        int sum = 0;    // 상어 크기의 합
        // 2. 0열부터 마지막 열까지 반복
        for(int y = 0; y < c; y++) {
            // 3. y열에 있는 상어 중 땅과 제일 가까운 상어 잡기
            for(int x = 0; x < r; x++) {
                if(map[x][y] != null) {
                    sum += map[x][y].z;
                    map[x][y] = null;
                    break;
                }
            }
            // 4. 나머지 상어 1초간 이동
            Shark[][] tmp = new Shark[r][c];    // 이동 결과
            for(int i = 0; i < r; i++) {
                for(int j = 0; j < c; j++) {
                    if(map[i][j] == null) continue;
                    int nx = map[i][j].x;
                    int ny = map[i][j].y;
                    int nd = map[i][j].d;
                    int ns = map[i][j].s;
                    // 5. 이동해야하는 칸 수 최솟값으로 갱신 -> 시간초과 문제 해결!!
                    // 5.1. 상하로 움질일 때는 (r-1)*2 가 넘어가면 제자리로 돌아오므로 나머지 연산 수행
                    if(nd <= 1) ns %= (r - 1) * 2;
                    // 5.2. 좌우로 움질일 때는 (c-1)*2 가 넘어가면 제자리로 돌아오므로 나머지 연산 수행
                    else ns %= (c - 1) * 2;
                    // 6. ns칸 만큼 이동
                    for(int k = 0; k < ns; k++) {
                        // 7. 다음 칸이 범위 밖이면 반대 방향으로 바꾸기
                        if(!inRange(nx + dx[nd], ny + dy[nd])) {
                            if(nd % 2 == 0) nd++;
                            else nd--;
                        }
                        // 8. 다음 칸으로
                        nx += dx[nd];
                        ny += dy[nd];
                    }
                    // 9. 상어가 없거나/현재 상어가 크기가 더 크다면,
                    if(tmp[nx][ny] == null || (tmp[nx][ny] != null && map[i][j].z > tmp[nx][ny].z)) {
                        tmp[nx][ny] = new Shark(nx, ny, map[i][j].s, nd, map[i][j].z);
                    }
                }
            }
            // 10. 이동 결과 갱신
            map = tmp;
        }
        System.out.println(sum);
    }

    public static boolean inRange(int x, int y) {
        if(0 <= x && x < r && 0 <= y && y < c) return true;
        return false;
    }
}