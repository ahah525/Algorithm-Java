package softeer.level2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] 장애물 인식 프로그램
 * [풀이시간] 18분
 * [한줄평] DFS나 BFS 로 풀 수 있는 아주 기초적인 문제였다.
 * 1_v1. BFS, 완전탐색(성공)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=409">문제</a>
 */
class Solution409 {
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
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        // 1. map 초기화
        map = new int[n][n];
        for(int i = 0; i < n; i++) {
            String s = br.readLine();
            for(int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }
        List<Integer> list = new ArrayList<>();
        // 2.
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] == 0) continue;
                list.add(bfs(i, j));
            }
        }
        System.out.println(list.size());
        Collections.sort(list);
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    // (sx, sy)와 이어진 장애물 블록 개수 조회
    public static int bfs(int sx, int sy) {
        int cnt = 0;
        Queue<P> q = new LinkedList<>();
        q.add(new P(sx, sy));
        map[sx][sy] = 0;
        while(!q.isEmpty()) {
            cnt++;
            P p = q.poll();
            int x = p.x;
            int y = p.y;
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(0 > nx || nx >= n || 0 > ny || ny >= n || map[nx][ny] == 0) continue;
                q.add(new P(nx, ny));
                map[nx][ny] = 0;
            }
        }
        return cnt;
    }
}