package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제명] 뱀
 * [풀이시간] 52분
 * [한줄평] 처음 풀어보는 유형의 문제였는데, 그렇게 어렵진 않았고 나중에 다시 풀어봐도 좋을 추천할 만한 문제다.
 * 1_v1. 구현, LinkedList(성공)
 * [풀이] "꼬리에 있는 칸 = 가장 과거에 방문했던 칸" 이므로, 큐에 방문한 칸을 기록하여 꼬리 칸을 비울 때 큐에서 빼낸 좌표를 사용한다.
 * @See <a href="https://www.acmicpc.net/problem/3190">문제</a>
 */
class Boj3190 {
    static int n;
    public static void main(String[] args) throws IOException {
        // 뱀의 방향, 좌표 초기화
        int d = 0;
        int x = 0;
        int y = 0;
        // RDLU
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());        // 보드 크기
        int k = Integer.parseInt(br.readLine());    // 사과 개수
        StringTokenizer st;
        // 0: 빈칸, 1: 사과, 2: 뱀
        int[][] map = new int[n][n];
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = 1;
        }
        // 1. 시작점 방문처리
        Queue<int[]> q = new LinkedList<>();    // 뱀이 이동했던 좌표들이 담긴 큐
        q.add(new int[]{0, 0});
        map[0][0] = 2;
        int time = 0;
        boolean finish = false;
        int l = Integer.parseInt(br.readLine());    // 방향 변환 횟수
        l1:
        for(int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            String c = st.nextToken();
            int dis = t - time;    // 현재 방향에서 움직여야할 거리
            // 2. (x,y)에서 d방향으로 dis칸 이동
            for(int j = 0; j < dis; j++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                // 3. 범위를 벗어나거나 이동할 칸에 뱀이 있으면, 종료
                if(!inRange(nx, ny) || map[nx][ny] == 2) {
                    finish = true;
                    break l1;
                }
                if(map[nx][ny] == 1) {
                    // 4. 이동한 칸에 사과가 있으면 방문처리
                    q.add(new int[]{nx, ny});
                    map[nx][ny] = 2;
                } else if(map[nx][ny] == 0) {
                    // 5. 이동한 칸이 빈칸이면 방문처리,
                    q.add(new int[] {nx, ny});
                    map[nx][ny] = 2;
                    // 6. 뱀의 꼬리가 있는 칸을 비우기
                    int[] tail = q.poll();
                    map[tail[0]][tail[1]] = 0;
                }
                x = nx;
                y = ny;
                time++;
            }
            // 7. 방향 바꾸기
            if(c.equals("L")) {
                if(d == 0) d = 3;
                else d--;
            } else {
                if(d == 3) d = 0;
                else d++;
            }
        }
        // 8. 종료될 때까지 이동
        while(!finish) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            // 범위를 벗어나거나, 이동할 칸에 본인 몸이 있으면
            if(!inRange(nx, ny) || map[nx][ny] == 2) break;
            if(map[nx][ny] == 1) {
                // 이동한 칸에 사과가 있으면 방문처리
                q.add(new int[]{nx, ny});
                map[nx][ny] = 2;
            } else if(map[nx][ny] == 0) {
                // 이동한 칸이 빈칸이면 방문 처리
                q.add(new int[] {nx, ny});
                map[nx][ny] = 2;
                // 꼬리 위치 한 칸 비우기
                int[] tail = q.poll();
                map[tail[0]][tail[1]] = 0;
            }
            x = nx;
            y = ny;
            time++;
        }
        System.out.println(time + 1);
    }

    public static boolean inRange(int x, int y) {
        if(0 <= x && x < n && 0 <= y && y < n) return true;
        return false;
    }
}