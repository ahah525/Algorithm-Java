package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제명] 아기상어
 * [풀이시간] 1시간 15분(44분+31분)
 * [한줄평] 결국 반례 힌트를 보고 해결하긴 했지만 이해가 안갈정도로 어려운 문제는 아니었고 다시 꼭 복습하면 좋을 문제다.
 * 1_v1. 구현, BFS(실패)
 * [풀이] 먹을 수 있는 물고기 = 1)거리가 가장 가까움, 2) 가장 위쪽에 있음, 3) 가장 왼쪽에 있음 이라고 해서 ULRD 방향으로 탐색해서 처음 찾은 물고기를 바로 리턴했다.
 * [반례] ULRD 방향으로 탐색해서 가장 먼저 찾은 물고기가 2) 가장 위쪽, 3) 가장 왼쪽에 있다고 보장할 수 없다.
 * 1_v2. 구현, BFS(성공)
 * [해결] 먹을 수 있는 물고기의 좌표, 거리를 저장하고 3가지 조건을 만족하는 경우에만 먹을 수 있는 물고기 값을 갱신한다.
 * @See <a href="https://www.acmicpc.net/problem/16236">문제</a>
 * @See <a href="https://bellog.tistory.com/109">반례</a>
 */
class Boj16236 {
    // 1_v1
    // ULRD
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static class P {
        int x;
        int y;
        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int n;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        int fish = 0;   // 물고기 수
        int sx = 0; // 아기상어 x좌표
        int sy = 0; // 아기상어 y좌표
        int ss = 2; // 아기상어 크기
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) continue;
                if(map[i][j] == 9) {
                    sx = i;
                    sy = j;
                    map[i][j] = 0;
                } else {
                    fish++;
                }
            }
        }
        int time = 0;   // 엄마 상어에게 도움을 요청하지 않고 물고기를 잡어먹을 수 있는 시간
        int eat = 0;    // 아기상어가 현재 크기에서 먹은 물고기 개수
        // 물고기가 남아있을 때까지 반복
        while(fish > 0) {
            // 1. 잡아먹을 수 있는 물고기 중 우선순위가 가장 높은 물고기 찾기
            int[] f = bfs(sx, sy, ss);
            int x = f[0];
            int y = f[1];
            int t = f[2];
//            System.out.println(x + "," + y + "," + t);
            // 2. 먹을 수 있는 물고기가 없으면 종료
            if(t == -1) break;
            // 3. 물고기 먹기
            map[x][y] = 0;
            sx = x;
            sy = y;
            time += t;
            eat++;
            // 4. 현재 크기만큼 물고기를 먹으면, 물고기 크기 1증가
            if(ss == eat) {
                ss++;
                eat = 0;
                fish--;
            }
        }
        //
        System.out.println(time);
    }

    // 가장 가까운 물고기 위치
    public static int[] bfs(int sx, int sy, int ss) {
        boolean find = false;
        Queue<P> q = new LinkedList<>();
        int[][] visited = new int[n][n];
        q.add(new P(sx, sy));
        visited[sx][sy] = 1;
        //
        int minX = n;
        int minY = n;
        int minD = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            P p = q.poll();
            int x = p.x;
            int y = p.y;
            // 1. 먹을 수 있는 물고기이면
            if(map[x][y] != 0 && map[x][y] < ss) {
                find = true;
                // 2.
                if(visited[x][y] - 1 < minD) {
                    minX = x;
                    minY = y;
                    minD = visited[x][y] - 1;
                } else if(visited[x][y] - 1 == minD) {
                    if(x < minX) {
                        minX = x;
                        minY = y;
                    } else if(x == minX) {
                        if(y < minY) {
                            minX = x;
                            minY = y;
                        }
                    }
                }
            }
            if(find) continue;
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 범위 밖이거나 크기가 더 큰 물고기이거나 이미 방문했으면, 패스
                if(!isRange(nx, ny) || map[nx][ny] > ss || visited[nx][ny] != 0) continue;
                q.add(new P(nx, ny));
                visited[nx][ny] = visited[x][y] + 1;
            }
        }
//        System.out.println(pq);
        if(minD == Integer.MAX_VALUE) return new int[]{-1, -1, -1};
        return new int[]{minX, minY, minD};
    }

    public static boolean isRange(int x, int y) {
        if(0 <= x && x < n && 0 <= y && y < n) return true;
        return false;
    }
}