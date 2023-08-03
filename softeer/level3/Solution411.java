package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제명] 동계 테스트 시점 예측
 * [풀이시간] 36분
 * [한줄평] 어디에서 bfs를 호출해야하는지 힌트를 보고 풀어서 다음에 꼭 다시 한번 풀어봐야할 문제다.
 * 1_v1. BFS(성공)
 * [풀이] 얼음이 아닌 곳에서 BFS를 수행한다. 얼음인 곳을 만나면 해당 좌표
 * - air[x][y]: 외부 공기와 만난 횟수
* @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=411">문제</a>
 */
class Solution411 {
    static class P {
        int x;
        int y;
        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] map;
    static int n, m, ice;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m]; // 0:땅, 1:얼음
        ice = 0;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) ice++;
            }
        }
        int time = 0;
        while(ice != 0) {
            ice -= bfs();
            time++;
        }
        // for(int[] arr : map) {
        //     System.out.println(Arrays.toString(arr));
        // }
        // System.out.println();
        // for(int[] arr : air) {
        //     System.out.println(Arrays.toString(arr));
        // }
        System.out.println(time);
    }

    public static int bfs() {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int[][] air = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        Queue<P> q = new LinkedList<>();
        q.add(new P(0, 0));
        visited[0][0] = true;
        while(!q.isEmpty()) {
            P p = q.poll();
            int x = p.x;
            int y = p.y;
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(0 > nx || nx >= n || 0 > ny || ny >= m) continue;
                if(map[nx][ny] == 1) {
                    air[nx][ny]++;
                } else {
                    if(visited[nx][ny]) continue;
                    q.add(new P(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
        //
        return remove(air);
    }

    public static int remove(int[][] air) {
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(air[i][j] < 2) continue;
                map[i][j] = 0;
                cnt++;
            }
        }
        return cnt;
    }
}