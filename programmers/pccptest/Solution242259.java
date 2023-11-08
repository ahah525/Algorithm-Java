package programmers.pccptest;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * [문제명] [PCCP 기출문제] 2번
 * [풀이시간] 11분
 * [한줄평] DFS/BFS로 석유가 있는 곳을 모두 완전탐색해서 푸는 쉬운 문제였다.
 * 1_v1. BFS,완전탐색(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/19344/lessons/242259">문제</a>
 */
public class Solution242259 {
    class P {
        int x;
        int y;
        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    boolean[][] visited;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int n, m;
    int[] arr;  // 시추관을 설치해 뽑을 수 있는 석유량

    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        arr = new int[m];
        visited = new boolean[n][m];
        // 1. 1인 곳에서 BFS 탐색
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(land[i][j] == 0 || visited[i][j]) continue;
                bfs(i, j, land);
            }
        }
        // 2. 최댓값 탐색
        int max = 0;
        for(int num : arr) {
            max = Math.max(max, num);
        }
        return max;
    }

    public void bfs(int sx, int sy, int[][] map) {
        Queue<P> q = new LinkedList<>();
        q.add(new P(sx, sy));
        visited[sx][sy] = true;
        int cnt = 0;    // 석유량
        Set<Integer> cols = new HashSet<>();    // 열 집합
        while(!q.isEmpty()) {
            P p = q.poll();
            cols.add(p.y);
            cnt++;
            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(!inRange(nx, ny) || map[nx][ny] == 0 || visited[nx][ny]) continue;
                q.add(new P(nx, ny));
                visited[nx][ny] = true;
            }
        }
        // 각 열에 석유량 누적
        for(int col : cols) {
            arr[col] += cnt;
        }
    }

    public boolean inRange(int x, int y) {
        if(0 <= x && x < n && 0 <= y && y < m) return true;
        return false;
    }
}
