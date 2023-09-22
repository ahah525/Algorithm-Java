package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 경사로
 * [풀이시간] 1시간
 * [한줄평] 그대로 구현만 하면 되는 문제였는데 생각보다 복잡해서 결국 풀이를 보고 해결했던 문제로 꼭 다시 풀어봐야겠다.
 * 1_v1. 구현(성공)
 * [풀이]
 * @See <a href="https://www.acmicpc.net/problem/14890">문제</a>
 * @See <a href="https://moonsbeen.tistory.com/282">풀이</a>
 */
class Boj14890 {
    static int n, l;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   //
        l = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        // i행, i열이 갈 수 있는 길인지 검사
        for(int i = 0; i < n; i++) {
            if(checkRow(i)) cnt++;
            if(checkCol(i)) cnt++;
        }
        System.out.println(cnt);
    }

    // r행 검사
    public static boolean checkRow(int r) {
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n - 1; i++) {
            int diff = map[r][i] - map[r][i + 1];   // 현재 칸과 다음 칸의 높이차
            // 1. 현재 칸과 다음 칸의 높이가 같으면, 패스
            if(diff == 0) continue;
            // 2. 현재 칸과 다음 칸의 높이차가 1보다 큰 경우, 지나갈 수 없음
            if(diff > 1 || diff < -1) return false;
            if(diff == -1) {
                // 3. 다음 칸이 현재 칸보다 1칸 높다면, 현재 칸을 기준으로 왼쪽 칸 l개 검사
                for(int j = 0; j < l; j++) {
                    if(i - j < 0 || visited[i - j] || map[r][i - j] != map[r][i]) return false;
                    visited[i - j] = true;
                }
            } else if(diff == 1) {
                // 4. 다음 칸이 현재 칸보다 1칸 낮다면, 다음 칸을 기준으로 오른쪽 칸 l개 검사
                for(int j = 1; j <= l; j++) {
                    if(i + j >= n || visited[i + j] || map[r][i + j] != map[r][i + 1]) return false;
                    visited[i + j] = true;
                }
            }
        }
        return true;
    }

    // c열 검사
    public static boolean checkCol(int c) {
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n - 1; i++) {
            int diff = map[i][c] - map[i + 1][c];   // 현재 칸과 다음 칸의 높이차
            // 1. 현재 칸과 다음 칸의 높이가 같으면, 패스
            if(diff == 0) continue;
            // 2. 현재 칸과 다음 칸의 높이차가 1보다 큰 경우, 지나갈 수 없음
            if(diff > 1 || diff < -1) return false;
            if(diff == -1) {
                // 3. 다음 칸이 현재 칸보다 1칸 높다면, 현재 칸을 기준으로 왼쪽 칸 l개 검사
                for(int j = 0; j < l; j++) {
                    if(i - j < 0 || visited[i - j] || map[i - j][c] != map[i][c]) return false;
                    visited[i - j] = true;
                }
            } else if(diff == 1) {
                // 4. 다음 칸이 현재 칸보다 1칸 낮다면, 다음 칸을 기준으로 오른쪽 칸 l개 검사
                for(int j = 1; j <= l; j++) {
                    if(i + j >= n || visited[i + j] || map[i + j][c] != map[i + 1][c]) return false;
                    visited[i + j] = true;
                }
            }
        }
        return true;
    }
}