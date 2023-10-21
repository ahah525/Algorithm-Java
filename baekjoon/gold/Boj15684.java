package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 사다리 조작
 * [풀이시간] 1시간 7분
 * [한줄평] 가로선 선택 여부를 어떤 식으로 접근해야할지 몰라서 힌트를 보고 풀었다. 조금 복잡하긴 했지만 정말 좋은 문제고 다시 꼭 풀어봐야할 문제다!
 * 1_v1. DFS,백트래킹,구현(성공)
 * [풀이] 최솟값을 구하기 위해 가능한 모든 경우를 탐색한다.
 * 1_v2. DFS,백트래킹,구현(성공) -> 추천
 * [풀이] 조합을 사용해 0부터 3까지 가장 작은 값 중 정답을 찾으면 더 이상 탐색하지 않음
 * @See <a href="https://www.acmicpc.net/problem/15684">문제</a>
 * @See <a href="https://suhyeokeee.tistory.com/119">풀이</a>
 */
class Boj15684 {
    static int MAX = 3;
    static int n, m, h, min;
    static int[][] map;

    // 1_v1
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        n = Integer.parseInt(st.nextToken());   // 열 개수
//        m = Integer.parseInt(st.nextToken());   // 가로선 개수
//        h = Integer.parseInt(st.nextToken());   // 행 개수
//        min = MAX + 1;
//        map = new int[h + 1][n + 2];
//        for(int i = 0; i < m; i++) {
//            st = new StringTokenizer(br.readLine());
//            int a = Integer.parseInt(st.nextToken());
//            int b = Integer.parseInt(st.nextToken());
//            // (a, b)와 (a, b + 1) 연결
//            map[a][b] = 1;
//        }
//        //
//        dfs(0, 1, 0);
//        if(min == MAX + 1) min = -1;
//        System.out.println(min);
//    }
//
//    /**
//     * MAX개 이하의 가로선 선택하기
//     * @param depth 선택한 가로선의 개수
//     * @param x 이전에 선택한 가로선의 행
//     * @param start 탐색을 시작할 가로선의 값
//     */
//    public static void dfs(int depth, int x, int start) {
//        if(isPossible()) {
//            min = Math.min(min, depth);
//            return;
//        }
//        if(depth == MAX) return;
//        //
//        for(int i = x; i <= h; i++) {
//            for(int j = 1; j <= n - 1; j++) {
//                // 탐색 범위가 아니면 패스
//                if(i * n + j < start) continue;
//                // 해당 가로선이 이미 선택되었거나 왼쪽/오른쪽 가로선이 이미 선택되었으면(연속되므로), 패스
//                if(map[i][j] == 1 || map[i][j - 1] == 1 || map[i][j + 1] == 1) continue;
//                // 선택
//                map[i][j] = 1;
//                dfs(depth + 1, i, i * n + j + 1);
//                map[i][j] = 0;
//            }
//        }
//    }

    // 모든 i번 세로선의 결과가 i번이 나오는지 검사
    public static boolean isPossible() {
        for(int i = 1; i <= n; i++) {
            // 1. i열에서 시작
            int y = i;
            for(int x = 1; x <= h; x++) {
                if(map[x][y] == 1) {
                    // 2. (x,y) 오른쪽에 가로선이 있으면, 오른쪽으로 한 칸 이동
                    y++;
                } else if(map[x][y - 1] == 1) {
                    // 3. (x,y) 왼쪽에 가로선이 있으면, 왼쪽으로 한 칸 이동
                    y--;
                }
            }
            // 4. 결과가 i열이 아니면, false
            if(y != i) return false;
        }
        return true;
    }

    // 1_v2
    static boolean isFind;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 열 개수
        m = Integer.parseInt(st.nextToken());   // 가로선 개수
        h = Integer.parseInt(st.nextToken());   // 행 개수
        min = MAX + 1;
        map = new int[h + 1][n + 2];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // (a, b)와 (a, b + 1) 연결
            map[a][b] = 1;
        }
        //
        isFind = false;
        for(int i = 0; i <= MAX; i++) {
            dfs(0, i, 1, 0);
            if(isFind) break;
        }
        if(!isFind) min = -1;
        System.out.println(min);
    }

    /**
     * MAX개 이하의 가로선 선택하기
     * @param depth 선택한 가로선의 개수
     * @param x 이전에 선택한 가로선의 행
     * @param start 탐색을 시작할 가로선의 값
     */
    public static void dfs(int depth, int target, int x, int start) {
        if(depth == target) {
            if(isPossible()) {
                min = Math.min(min, depth);
                isFind = true;
            }
            return;
        }
        //
        for(int i = x; i <= h; i++) {
            for(int j = 1; j <= n - 1; j++) {
                // 탐색 범위가 아니면 패스
//                if(i * n + j < start) continue;
                // 해당 가로선이 이미 선택되었거나 왼쪽/오른쪽 가로선이 이미 선택되었으면(연속되므로), 패스
                if(map[i][j] == 1 || map[i][j - 1] == 1 || map[i][j + 1] == 1) continue;
                // 선택
                map[i][j] = 1;
                dfs(depth + 1, target, i, i * n + j + 1);
                if(isFind) return;
                map[i][j] = 0;
            }
        }
    }
}