package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] [HSAT 1회 정기 코딩 인증평가 기출] 로봇이 지나간 경로
 * [풀이시간] 3시간
 * [한줄평]
 * 1_v1. (실패)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=577">문제</a>
 */
class Solution577 {
    // 동서남북
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static char[][] map;
    static int h, w, total;
    static boolean[][] visited;
    static String answerPath;
    static boolean find;
    public static void main(String args[]) throws IOException {
        // 동서남북
        String dirs = "><v^";
        int a = 0, b = 0;
        int d = 0;
        find = false;
        answerPath = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        map = new char[h][w];
        total = 0;
        for(int i = 0; i < h; i++) {
            String s = br.readLine();
            for(int j = 0; j < w; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == '#') total++;
            }
        }
        //
        l1:
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(map[i][j] == '.') continue;
                for(int k = 0; k < 4; k++) {
                    visited = new boolean[h][w];
                    visited[i][j] = true;
                    dfs(i, j, k, 1, visited, "");
                    if(!isPossible(i + dx[k], j + dy[k])) continue;
                    if(find) {
                        System.out.println((i + 1) + " " + (j + 1));
                        System.out.println(dirs.charAt(d));
                        System.out.println(answerPath);
                        break l1;
                    }
                }
            }
        }
    }

    // a -> b로 회전
    public static String turn(int a, int b) {
        if(a == b) return "A";
        if(a == 0) {
            if(b == 1) return "RRA";
            if(b == 2) return "RA";
            if(b == 3) return "LA";
        } else if(a == 1) {
            if(b == 0) return "RRA";
            if(b == 2) return "LA";
            if(b == 3) return "RA";
        } else if(a == 2) {
            if(b == 0) return "LA";
            if(b == 1) return "RA";
            if(b == 3) return "RRA";
        } else {
            if(b == 0) return "RA";
            if(b == 1) return "LA";
            if(b == 2) return "RRA";
        }
        return "A";
    }

    public static void dfs(int x, int y, int d, int cnt, boolean[][] visited, String path) {
        if(find) return;
        for(int i = 0; i < 4; i++) {
            if(canGo(x, y, i)) {
                // 2칸 전진할 수 있다면
                String newPath = turn(d, i);
                go(x, y, d, i);
                dfs(x + 2 * dx[i], y + 2 * dy[i], i, cnt + 2, visited, path + newPath);
                rollback(x, y, d, i);
            } else {
                if(cnt == total) {
                    answerPath = path;
                    find = true;
                    return;
                }
            }
        }
    }

    public static void go(int x, int y, int d, int newD) {
        for(int i = 0; i < 2; i++) {
            x += dx[newD];
            y += dy[newD];
            visited[x][y] = true;
        }
    }

    public static void rollback(int x, int y, int d, int newD) {
        for(int i = 0; i < 2; i++) {
            x += dx[newD];
            y += dy[newD];
            visited[x][y] = false;
        }
    }

    public static boolean isPossible(int x, int y) {
        if(x < 0 || x >= h || y < 0 || y >= w) return false;
        if(map[x][y] == '.' || visited[x][y]) return false;
        return true;
    }

    public static boolean canGo(int x, int y, int d) {
        for(int i = 0; i < 2; i++) {
            x += dx[d];
            y += dy[d];
            if(!isPossible(x, y)) return false;
        }
        return true;
    }
}