package programmers.level2;


import java.util.*;

/**
 * [문제명] 무인도 여행
 * [풀이시간] 16분 / 23분, 13분 / 13분
 * [한줄평] DFS/BFS 로 풀 수 있는 쉬운 문제였다.
 * / BFS로 쉽게 풀 수 있는 문제였다.
 * / 아주 기초적인 DFS/BFS 문제로 더 이상 안풀어봐도 될 것 같다.
 * 1_v1. BFS(성공)
 * 2_v1. BFS(성공)
 * 2_v2. DFS(성공) -> 빠름
 * 3_v1. DFS(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/154540">문제</a>
 */
class Solution154540 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 1_v1, 2_v1
    class P {
        int x;
        int y;
        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * @param maps 지도를 나타내는 문자열 배열
     * @return 각 섬에서 최대 며칠씩 머무를 수 있는지 배열에 오름차순으로 담아 return
     */
    public int[] solution(String[] maps) {
        List<Integer> list = new ArrayList<>();
        // 1. map 정보를 int[] 로 저장
        int n = maps.length;
        int m = maps[0].length();
        int[][] map = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(maps[i].charAt(j) == 'X') {
                    map[i][j] = 0;
                } else {
                    map[i][j] = maps[i].charAt(j) - '0';
                }
            }
        }
        // 2. 0 이 아닌 좌표를 시작점으로 bfs
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] != 0)
                    list.add(bfs(new P(i, j), n, m, map));
            }
        }
        // 3. 무인도가 없다면 {-1} 리턴
        if(list.size() == 0) return new int[] {-1};
        // 4. 각 섬에서 머무를 수 있는지 오름차순 정렬 후 리턴
        int[] answer = new int[list.size()];
        Collections.sort(list);
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    // 무인도에서 머무를 수 있는 일 수 조회
    public int bfs(P start, int n, int m, int[][] map) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        // 1. 시작 좌표 방문처리
        Queue<P> q = new LinkedList<>();
        q.add(start);
        int sum = map[start.x][start.y]; // 시작 좌표의 식량
        map[start.x][start.y] = 0;
        //
        while(!q.isEmpty()) {
            P p = q.poll();
            int x = p.x;
            int y = p.y;
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if(map[nx][ny] != 0) {
                        q.add(new P(nx, ny));
                        sum += map[nx][ny];
                        map[nx][ny] = 0;
                    }
                }
            }
        }
        return sum;
    }

    // 2_v2
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    public int[] solution2(String[] maps) {
        List<Integer> list = new ArrayList<>();
        int n = maps.length;
        int m = maps[0].length();
        int[][] map = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(maps[i].charAt(j) == 'X') map[i][j] = 0;
                else map[i][j] = maps[i].charAt(j) - '0';
            }
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] != 0) {
                    list.add(dfs(i, j, n, m, map));
                }
            }
        }
        if(list.size() == 0) return new int[] {-1};
        int[] answer = new int[list.size()];
        Collections.sort(list);
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    public int dfs(int x, int y, int n, int m, int[][] map) {
        int sum = map[x][y];
        map[x][y] = 0;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(0 <= nx && nx < n && 0 <= ny && ny < m && map[nx][ny] != 0) {
                sum += dfs(nx, ny, n, m, map);
            }
        }
        return sum;
    }

    // 3_v1
//    int[] dx = {-1, 1, 0, 0};
//    int[] dy = {0, 0, -1, 1};
//    char[][] map;
//    int cnt;
//    int n, m;
//    public int[] solution3(String[] maps) {
//        n = maps.length;
//        m = maps[0].length();
//        // 1. map 초기화
//        map = new char[n][m];
//        for(int i = 0; i < n; i++) {
//            for(int j = 0; j < m; j++) {
//                map[i][j] = maps[i].charAt(j);
//            }
//        }
//        // 2.
//        List<Integer> list = new ArrayList<>();
//        for(int i = 0; i < n; i++) {
//            for(int j = 0; j < m; j++) {
//                if(map[i][j] != 'X') {
//                    cnt = 0;
//                    dfs(i, j);
//                    list.add(cnt);
//                }
//            }
//        }
//        // 3. -1 반환
//        if(list.size() == 0) return new int[] {-1};
//        // 4. 오름차순 정렬
//        Collections.sort(list);
//        // 5. 리스트를 배열로 변환
//        int[] answer = new int[list.size()];
//        for(int i = 0; i < list.size(); i++) {
//            answer[i] = list.get(i);
//        }
//        return answer;
//    }
//
//    public void dfs(int x, int y) {
//        cnt += map[x][y] - '0';
//        map[x][y] = 'X';
//        for(int i = 0; i < 4; i++) {
//            int nx = x + dx[i];
//            int ny = y + dy[i];
//            if(0 <= nx && nx < n && 0 <= ny && ny < m && map[nx][ny] != 'X') {
//                dfs(nx, ny);
//            }
//        }
//    }
}