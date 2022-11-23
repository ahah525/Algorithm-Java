package programmers.level2;


import java.util.LinkedList;
import java.util.Queue;

/**
 * [한줄평] 아주 전형적인 BFS 문제였기 때문에 쉽게 풀 수 있었다.
 * v1. BFS(성공)
 * - 방문처리를 위한 visited[][] 를 따로 사용하지 않고, maps[][] 에 값을 기록하는 방식으로 해결
 */
class Solution1844 {
    public static void main(String[] args) {
        // 11
        int[][] maps1 = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        int answer1 = solution(maps1);
        System.out.println(answer1);

        // -1
        int[][] maps2 = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}};
        int answer2 = solution(maps2);
        System.out.println(answer2);
    }

    /**
     *
     * @param maps n x m 크기의 게임 맵의 상태가 들어있는 2차원 배열(n과 m은 각각 1 이상 100 이하의 자연수)
     * - 0: 벽, 1: 길
     * - 시작위치:(1, 1), 목표위치:(n, m)
     * @return 게임 맵의 상태 maps가 매개변수로 주어질 때, 캐릭터가 상대 팀 진영에 도착하기 위해서 지나가야 하는 칸의 개수의 최솟값
     */
    public static int solution(int[][] maps) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int n = maps.length;
        int m = maps[0].length;

        Queue<Point> q = new LinkedList<>();
        // 시작위치 삽입, 방문처리
        q.add(new Point(0, 0));
        maps[0][0] = 1;

        while(!q.isEmpty()) {
            Point p = q.poll();
            int x = p.x;
            int y = p.y;
            if(x == n - 1 && y == m - 1) {
                return maps[n - 1][m - 1];
            }

            // 주변 탐색
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(0 <= nx && nx < n && 0 <= ny && ny < m) {
                    // 길인데 시작위치가 아닐 때만 이동
                    if(maps[nx][ny] == 1 && !(nx == 0 && ny == 0)) {
                        q.add(new Point(nx, ny));
                        maps[nx][ny] = maps[x][y] + 1;
                    }
                }
            }
        }
        return -1;
    }

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}