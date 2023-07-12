package programmers.level2;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] 거리두기 확인하기
 * [풀이시간] 32분 / 47분 / 27분
 * [한줄평] 생각보다 쉽게 풀 수 있었던 문제였다.
 * / BFS로 풀면 어렵지 않게 풀 수 있었다.
 * / 풀이 방식이 바로 떠올라서 빨리 풀 수 있었다. 다시 한 번 더 복습하면 좋을 문제다.
 * 1_v1. BFS(성공)
 * [풀이] P 인 지점에서 매번 BFS 를 수행하며 2 이내에 P 가 있는지 검사하는 방법
 * 2_v1. BFS(성공)
 * 3_v1. BFS(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/81302">문제</a>
 */
class Solution81302 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    class P {
        int x;
        int y;
        int d;  // 시작 좌표와의 맨해튼 거리

        P(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
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
            int n = p.d;
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

    // 2_v1
    public int[] solution2(String[][] places) {
        int[] answer = new int[5];
        Arrays.fill(answer, 1);
        for(int k = 0; k < 5; k++) {
            char[][] map = change(places[k]);
            boolean[][] visited = new boolean[5][5];
            l1:
            for(int i = 0; i < 5; i++) {
                for(int j = 0; j < 5; j++) {
                    if(map[i][j] != 'P') continue;
                    if(!bfs(i, j, map, visited)) {
                        answer[k] = 0;
                        break l1;
                    }
                }
            }
        }
        return answer;
    }

    public char[][] change(String[] place) {
        char[][] map = new char[5][5];
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                map[i][j] = place[i].charAt(j);
            }
        }
        return map;
    }

    public boolean bfs(int startX, int startY, char[][] map, boolean[][] visited) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<P> q = new LinkedList<>();
        q.add(new P(startX, startY, 0));
        visited[startX][startY] = true;
        while(!q.isEmpty()) {
            P p = q.poll();
            int x = p.x;
            int y = p.y;
            int d = p.d;
            if(d == 2) break;
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(0 <= nx && nx < 5 && 0 <= ny && ny < 5) {
                    if(visited[nx][ny]) continue;
                    if(map[nx][ny] == 'P') return false;
                    if(map[nx][ny] == 'O') {
                        q.add(new P(nx, ny, d + 1));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return true;
    }

    // 3_v1
//    class P {
//        int x;
//        int y;
//        P(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//    }
//    public int[] solution(String[][] places) {
//        int[] answer = new int[5];
//        char[][][] map = new char[5][5][5];
//        for(int i = 0; i < 5; i++) {
//            for(int j = 0; j < 5; j++) {
//                for(int k = 0; k < 5; k++) {
//                    map[i][j][k] = places[i][j].charAt(k);
//                }
//            }
//        }
//        //
//        for(int i = 0; i < 5; i++) {
//            //
//            int flag = 1;
//            int[][] visited = new int[5][5];
//            l1:
//            for(int x = 0; x < 5; x++) {
//                for(int y = 0; y < 5; y++) {
//                    if(map[i][x][y] == 'P') {
//                        if(!bfs(x, y, map[i], visited)) {
//                            flag = 0;
//                            break l1;
//                        }
//                    }
//                }
//            }
//            answer[i] = flag;
//        }
//
//        return answer;
//    }
//
//    // (sx, sy) 좌표 사람이 응시 규칙을 만족했는지(맨해튼 거리가 2 이하인 곳에 다른 응시생이 없는지)
//    public boolean bfs(int sx, int sy, char[][] map, int[][] visited) {
//        int[] dx = {-1, 1, 0 ,0};
//        int[] dy = {0, 0, -1, 1};
//        Queue<P> q = new LinkedList<>();
//        q.add(new P(sx, sy));
//        visited[sx][sy] = 1;
//        while(!q.isEmpty()) {
//            P p = q.poll();
//            int x = p.x;
//            int y = p.y;
//            // 거리가 2이상 이면 인접 좌표 탐색X
//            if(visited[x][y] > 2) break;
//            for(int i = 0; i < 4; i++) {
//                int nx = x + dx[i];
//                int ny = y + dy[i];
//                if(!canMove(nx, ny) || map[nx][ny] == 'X' || visited[nx][ny] != 0) continue;
//                if(map[nx][ny] == 'P') return false;
//                q.add(new P(nx, ny));
//                visited[nx][ny] = visited[x][y] + 1;
//            }
//        }
//        return true;
//    }
//
//    public boolean canMove(int x, int y) {
//        if(0 > x || x >= 5 || 0 > y || y >= 5) return false;
//        return true;
//    }
}