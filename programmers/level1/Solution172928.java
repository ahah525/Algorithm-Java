package programmers.level1;


import java.util.HashMap;
import java.util.Map;

/**
 * [문제명] 공원 산책
 * [풀이시간] 37분 / 30분 / 17분
 * [한줄평] 단순 구현 문제였는데, 코드를 복붙하다가 생긴 오류를 해결하느라 오래걸렸다.
 * / 쉬운 문제였는데, 방향 설정을 잘못해서 시간을 허비했다.
 * / 단순 구현 문제로 더 안풀어봐도 될 것 같은 문제다.
 * 1_v1. 구현(성공)
 * 2_v1. 구현(성공)
 * 3_v1. 구현(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/172928">문제</a>
 */
class Solution172928 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1
    class P {
        int x;
        int y;

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * @param park 공원을 나타내는 문자열 배열
     * @param routes 로봇 강아지가 수행할 명령이 담긴 문자열 배열
     * @return 로봇 강아지가 모든 명령을 수행 후 놓인 위치를 [세로 방향 좌표, 가로 방향 좌표] 순으로 배열에 담아 return
     */
    public int[] solution(String[] park, String[] routes) {
        int x = 0;
        int y = 0;
        int n = park.length;
        int m = park[0].length();
        // 통로 = 0, 장애물 = 1
        int[][] map = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(park[i].charAt(j) == 'O') {
                    // 통로
                    map[i][j] = 0;
                } else if(park[i].charAt(j) == 'X') {
                    // 장애물
                    map[i][j] = 1;
                } else {
                    map[i][j] = 0;
                    x = i;
                    y = j;
                }
            }
        }
        // N, S, W, E
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Map<String, Integer> dirMap = new HashMap<>();
        dirMap.put("N", 0);
        dirMap.put("S", 1);
        dirMap.put("W", 2);
        dirMap.put("E", 3);
        //
        for(String route : routes) {
            String[] split = route.split(" ");
            int dir = dirMap.get(split[0]); // 방향
            int dis = Integer.parseInt(split[1]);   // 이동거리
            // 이동할 수 있는지 여부 체크
            boolean move = true;
            for(int i = 1; i <= dis; i++) {
                int nx = x + dx[dir] * i;
                int ny = y + dy[dir] * i;
                if(nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == 1) {
                    move = false;
                    break;
                }
            }
            if(move) {
                x += dx[dir] * dis;
                y += dy[dir] * dis;
            }
        }
        return new int[]{x, y};
    }

    // 2_v1
    public int[] solution2(String[] park, String[] routes) {
        // NSWE
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        // (방향, 인덱스)
        Map<String, Integer> dirMap = new HashMap<>();
        dirMap.put("N", 0);
        dirMap.put("S", 1);
        dirMap.put("W", 2);
        dirMap.put("E", 3);

        int n = park.length;
        int m = park[0].length();
        char[][] map = new char[n][m];
        // 맵, 시작 좌표 초기화
        int x = 0;
        int y = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                map[i][j] = park[i].charAt(j);
                if(map[i][j] == 'S') {
                    x = i;
                    y = j;
                }
            }
        }
        //
        for(String route : routes) {
            String[] r = route.split(" ");
            int dir = dirMap.get(r[0]);
            int dis = Integer.parseInt(r[1]);
            // dir 방향으로 dis 만큼 이동했을 때 예상 좌표
            int nx = x + dx[dir] * dis;
            int ny = y + dy[dir] * dis;
            // 1. 범위를 벗어난 경우
            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            // 2. 장애물 만나는지 검사
            nx = x;
            ny = y;
            boolean isPossible = true;
            for(int i = 0; i < dis; i++) {
                nx += dx[dir];
                ny += dy[dir];
                if(map[nx][ny] == 'X') {
                    isPossible = false;
                    break;
                }
            }
            if(!isPossible) continue;
            x = nx;
            y = ny;
        }
        return new int[] {x, y};
    }

    // 3_v1
    // NSWE
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int h, w;
    char[][] map;
    Map<String, Integer> dir;
    public int[] solution3(String[] park, String[] routes) {
        // 1. 방향 인덱스 초기화
        dir = new HashMap<>();
        dir.put("N", 0);
        dir.put("S", 1);
        dir.put("W", 2);
        dir.put("E", 3);
        // 2. 맵 초기화 및 시작 좌표 저장
        h = park.length;
        w = park[0].length();
        map = new char[h][w];
        int x = 0;
        int y = 0;
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                map[i][j] = park[i].charAt(j);
                if(map[i][j] == 'S') {
                    x = i;
                    y = j;
                }
            }
        }
        //
        for(String route : routes) {
            String[] r = route.split(" ");
            int d = dir.get(r[0]);
            int n = Integer.parseInt(r[1]);
            if(canMove(x, y, d, n)) {
                x += (dx[d] * n);
                y += (dy[d] * n);
            }
        }
        return new int[] {x, y};
    }

    // (x, y)에서 d 방향으로 n칸 갈 수 있는지
    public boolean canMove(int x, int y, int d, int n) {
        int nx = x;
        int ny = y;
        for(int i = 0; i < n ; i++) {
            nx += dx[d];
            ny += dy[d];
            if(nx < 0 || h <= nx || ny < 0 || w <= ny) return false;
            if(map[nx][ny] == 'X') return false;
        }
        return true;
    }
}