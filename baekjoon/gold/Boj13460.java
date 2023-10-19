package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제명] 구슬 탈출 2
 * [풀이시간] 1시간 30분
 * [한줄평] 구슬을 굴렸을 때 두 구슬의 위치를 저장해서 풀어야하는 것을 몰라서 결국 풀이를 봤다. 방문처리를 하기 위해 4차원 배열을 처음 써봤고 복잡하지만 매우 좋은 문제로 다시 한 번 꼭 풀어봐야겠다.
 * 1_v1. 구현, BFS(성공)
 * [풀이] 최소 비용을 구하기 위해 BFS로 상하좌우를 탐색한다. 각각의 구슬을 해당 방향으로 이동시켰을 때 최종적으로 어느 좌표에 위치하는지 구해서 큐에 삽입하고 방문처리하면 된다.
 * - P(rx, ry, bx, by, cnt): cnt번 이동하여 빨강 구슬이 (rx, ry), 파랑 구슬이 (bx, by)에 위치함
 * - visited[rx][ry][bx][by]: (rx, ry), (bx, by)를 방문했는지 여부
 * @See <a href="https://www.acmicpc.net/problem/13460">문제</a>
 * @See <a href="https://sdesigner.tistory.com/105">풀이 참고</a>
 */
class Boj13460 {
    static class P {
        int rx;
        int ry;
        int bx;
        int by;
        int cnt;

        public P(int rx, int ry, int bx, int by, int cnt) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        P p = new P(0, 0, 0, 0, 1);
        for(int i = 0; i < n; i++) {
            String s = br.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
                // 빨강, 파랑 구슬 좌표 초기화
                if (map[i][j] == 'R') {
                    p.rx = i;
                    p.ry = j;
                } else if (map[i][j] == 'B') {
                    p.bx = i;
                    p.by = j;
                }
            }
        }
        //
        System.out.println(bfs(p));
    }

    public static int bfs(P s) {
        Queue<P> q = new LinkedList<>();
        boolean[][][][] visited = new boolean[n][m][n][m];
        // 1. 시작 좌표 삽입, 방문처리
        q.add(s);
        visited[s.rx][s.ry][s.bx][s.by] = true;
        //
        while(!q.isEmpty()) {
            P p = q.poll();
            // 2. 10회가 넘었으면 종료
            if(p.cnt > 10) return -1;
            for(int i = 0; i < 4; i++) {
                // 3. 빨강 구슬 i 방향으로 굴리기
                int[] r = roll(p.rx, p.ry, i);
                // 4. 파랑 구슬 i 방향으로 굴리기
                int[] b = roll(p.bx, p.by, i);
                // 5. 파랑 구슬이 빠진 경우, 실패
                if(map[b[0]][b[1]] == 'O') continue;
                // 6. 빨강 구슬만 빠진 경우, 성공
                if(map[r[0]][r[1]] == 'O') return p.cnt;
                // 7. 두 구슬 모두 빠지지 않은 경우, 같은 위치에 있으면 위치 조정
                if(r[0] == b[0] && r[1] == b[1]) {
                    if(i == 0) {
                        // 1. 위로 굴린 경우
                        if(p.rx < p.bx) {
                            // 빨강 구슬 x가 더 작은 경우, 파랑 구슬을 아래로 한 칸 이동
                            b[0]++;
                        } else {
                            // 파랑 구슬 x가 더 작은 경우, 빨강 구슬을 아래로 한 칸 이동
                            r[0]++;
                        }
                    } else if(i == 1) {
                        // 2. 아래로 굴린 경우
                        if(p.rx < p.bx) {
                            // 빨강 구슬 x가 더 작은 경우, 빨강 구슬을 위로 한 칸 이동
                            r[0]--;
                        } else {
                            // 파랑 구슬 x가 더 작은 경우, 파랑 구슬을 위로 한 칸 이동
                            b[0]--;
                        }
                    } else if(i == 2) {
                        // 3. 왼쪽으로 굴린 경우
                        if(p.ry < p.by) {
                            // 빨강 구슬 y가 더 작은 경우, 파랑 구슬을 오른쪽으로 한 칸 이동
                            b[1]++;
                        } else {
                            // 파랑 구슬 y가 더 작은 경우, 빨강 구슬을 오른쪽으로 한 칸 이동
                            r[1]++;
                        }
                    } else if(i == 3) {
                        // 3. 오른쪽으로 굴린 경우
                        if(p.ry < p.by) {
                            // 빨강 구슬 y가 더 작은 경우, 빨강 구슬을 왼쪽으로 한 칸 이동
                            r[1]--;
                        } else {
                            // 파랑 구슬 y가 더 작은 경우, 파랑 구슬을 왼쪽으로 한 칸 이동
                            b[1]--;
                        }
                    }
                }
                // 8. 이미 방문한 곳이면, 패스
                if(visited[r[0]][r[1]][b[0]][b[1]]) continue;
                // 9. 갱신된 두 구슬의 위치 큐에 삽입, 방문 처리
                q.add(new P(r[0], r[1], b[0], b[1], p.cnt + 1));
                visited[r[0]][r[1]][b[0]][b[1]] = true;
            }
        }
        return -1;
    }

    // (x,y)에서 d 방향으로 굴렸을 때 최종 위치
    public static int[] roll(int x, int y, int d) {
        int nx = x;
        int ny = y;
        // 1. 벽이 아닐 때까지 이동
        while(map[nx + dx[d]][ny + dy[d]] != '#') {
            nx += dx[d];
            ny += dy[d];
            // 2. 이동한 곳이 구멍이면 종료
            if(map[nx][ny] == 'O') break;
        }
        return new int[]{nx, ny};
    }
}