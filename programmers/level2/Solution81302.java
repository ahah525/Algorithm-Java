package programmers.level2;


import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] 거리두기 확인하기
 * [풀이시간] 32분
 * [한줄평] 생각보다 쉽게 풀 수 있었던 문제였다.
 * 1_v1. BFS(성공)
 * - P 인 지점에서 매번 BFS 를 수행하며 2 이내에 P 가 있는지 검사하는 방법
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/">문제</a>
 */
class Solution81302 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    class P {
        int x;
        int y;
        int n;  // 시작 좌표와의 맨해튼 거리

        P(int x, int y, int n) {
            this.x = x;
            this.y = y;
            this.n = n;
        }
    }

    /**
     * @param places 자리에 앉아있는 응시자들의 정보와 대기실 구조를 대기실별로 담은 2차원 문자열 배열
     * @return 각 대기실별로 거리두기를 지키고 있으면 1을, 한 명이라도 지키지 않고 있으면 0을 배열에 담아 return
     */
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int i = 0; i < 5; i++) {
            // map 만들기
            char[][] map = new char[5][5];
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k < 5; k++) {
                    map[j][k] = places[i][j].charAt(k);
                }
            }
            answer[i] = isOk(map);
        }
        return answer;
    }

    //

    /**
     * @param map map 정보
     * @return 해당 map 이 거리두기를 잘 지키고 있으면 true, 아니면 false
     */
    public int isOk(char[][] map) {
        // 2. 사람(P)가 있는 곳에서 모두 BFS
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(map[i][j] == 'P') {
                    // (i, j) 에서 맨해튼 거리 2이내에 다른 사람이 있으면 바로 0 리턴
                    if(bfs(map, i, j))
                        return 0;
                }
            }
        }
        return 1;
    }

    /**
     * @param map map 정보
     * @param sx 시작 x 좌표
     * @param sy 시작 y 좌표
     * @return 시작 좌표(x,y)에서 맨해트 거리 2 이내에 다른 사람이 있으면 true, 없으면 false
     */
    public boolean bfs(char[][] map, int sx, int sy) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        Queue<P> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        // 시작위치 삽입, 방문처리
        q.add(new P(sx, sy, 0));
        map[sx][sy] = 1;
        visited[sx][sy] = true;

        while(!q.isEmpty()) {
            P p = q.poll();
            int x = p.x;
            int y = p.y;
            int n = p.n;
            if(n == 2) break;
            // 주변 탐색
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(0 <= nx && nx < 5 && 0 <= ny && ny < 5) {
                    if(map[nx][ny] == 'P')
                        // 사람이면 true 리턴
                        return true;
                    if(map[nx][ny] == 'O') {
                        // 빈 테이블일 때만 방문
                        q.add(new P(nx, ny, n + 1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return false;
    }
}