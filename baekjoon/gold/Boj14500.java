package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 테트로미노
 * [풀이시간] / 45분
 * [한줄평]
 * / 어려운 문제는 아니었는데, 블록을 좌표상의 값으로 나타낼 때 잘못된 숫자를 넣어 19개 경우을 다 테스트해봐야 반례를 확인할 수 있었다.
 * 1_v1. 구현, 완전탐색(실패)
 * 1_v2. 구현, 완전탐색(성공)
 * [풀이] 각 좌표에서 19개의 모든 블록을 놓는 경우를 테스트한다.
 * 2_v1. 구현, 완전탐색(실패)
 * [반례] block[][][] 배열의 숫자를 잘못 기록함
 * 2_v2. 구현, 완전탐색(성공)
 * [풀이] 1_v2 동일
 * @See <a href="https://www.acmicpc.net/problem/14500">문제</a>
 * @See <a href="https://www.acmicpc.net/board/view/61597">반례</a>
 */
class Boj14500 {
    // 2_v2
    static int[][][] block = {
            // 1번(2개)
            {{0, 0, 0, 0}, {0, 1, 2, 3}},
            {{0, 1, 2, 3}, {0, 0, 0, 0}},
            // 2번(1개)
            {{0, 0, 1, 1}, {0, 1, 0, 1}},
            // 3번(8개)
            {{0, 1, 2, 2}, {0, 0, 0, 1}},
            {{0, 1, 2, 2}, {1, 1, 1, 0}},
            {{0, 0, 0, 1}, {0, 1, 2, 0}},
            {{0, 0, 0, 1}, {0, 1, 2, 2}},
            {{0, 0, 1, 2}, {0, 1, 1, 1}},
            {{0, 0, 1, 2}, {0, 1, 0, 0}},
            {{0, 1, 1, 1}, {2, 0, 1, 2}},
            {{0, 1, 1, 1}, {0, 0, 1, 2}},
            // 4번(4개)
            {{0, 1, 1, 2}, {0, 0, 1, 1}},
            {{0, 1, 1, 2}, {1, 0, 1, 0}},
            {{0, 0, 1, 1}, {1, 2, 0, 1}},
            {{0, 0, 1, 1}, {0, 1, 1, 2}},
            // 5번(4개)
            {{0, 0, 0, 1}, {0, 1, 2, 1}},
            {{0, 1, 1, 2}, {1, 0, 1, 1}},
            {{0, 1, 1, 1}, {1, 0, 1, 2}},
            {{0, 1, 2, 1}, {0, 0, 0, 1}}
    };

    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int max = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                // (i,j)에서 놓을 수 있는 블록 모두 검사
                for (int[][] d : block) {
                    int sum = 0;
                    for (int k = 0; k < 4; k++) {
                        int x = i + d[0][k];
                        int y = j + d[1][k];
                        if (!inRange(x, y)) {
                            sum = 0;
                            break;
                        }
                        sum += arr[x][y];
                    }
                    max = Math.max(max, sum);
                }
            }
        }
        System.out.println(max);
    }

    public static boolean inRange(int x, int y) {
        if(0 <= x && x < n && 0 <= y && y < m) return true;
        return false;
    }

    // 1_v2
//    private static int n, m, max;
//    private static int[][] arr;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        n = Integer.parseInt(st.nextToken());   // 세로
//        m = Integer.parseInt(st.nextToken());   // 가로
//        max = 0;    //
//        arr = new int[n][m];
//        for (int i = 0; i < n; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < m; j++) {
//                arr[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//        check();
//        System.out.println(max);
//    }
//    public static void check() {
//        int[][][] dx = {
//                {{0, 0, 0, 0}, {0, 1, 2, 3}},
//                {{0, 0, 1, 1}},
//                {{0, 1, 2, 2}, {0, 0, 0, 1}, {0 ,0 ,1, 2}, {0, 1, 1, 1},
//                        {0, 1, 2, 2}, {0, 1, 1, 1}, {0, 0, 1, 2}, {0, 0, 0, 1}},
//                {{0, 1, 1, 2}, {0, 0, 1, 1}, {0, 1, 1, 2}, {0, 0 ,1, 1}},
//                {{0, 0, 0, 1}, {0, 1, 1, 2}, {0, 1, 1, 1}, {0, 1, 1, 2}}
//        };
//        int[][][] dy = {
//                {{0, 1, 2, 3}, {0, 0, 0, 0}},
//                {{0, 1, 0, 1}},
//                {{0, 0, 0, 1}, {0, 1, 2, 0}, {0, 1, 1, 1}, {2, 0, 1, 2},
//                        {1, 1, 0, 1}, {0, 0, 1, 2}, {0, 1, 0, 0}, {0, 1, 2, 2}},
//                {{0, 0, 1, 1}, {1, 2, 0, 1}, {1, 0, 1, 0}, {0, 1, 1, 2}},
//                {{0, 1, 2, 1}, {1, 0, 1, 1}, {1, 0, 1, 2}, {0, 0, 1, 0}}
//        };
//        int[] cnt = {2, 1, 8, 4, 4};    // 경우의 수
//        for (int x = 0; x < n; x++) {
//            for (int y = 0; y < m; y++) {
//                //
//                for (int i = 0; i < 5; i++) {
//                    for (int j = 0; j < cnt[i]; j++) {
//                        int sum = 0;
//                        boolean valid = true;
//                        for (int k = 0; k < 4; k++) {
//                            int nx = x + dx[i][j][k];
//                            int ny = y + dy[i][j][k];
//                            // 범위를 벗어나면
//                            if(nx < 0 || n <= nx || ny < 0 || m <= ny) {
//                                valid = false;
//                                break;
//                            }
//                            sum += arr[nx][ny];
//                        }
//                        // 의도적 탈출하지 않았을 때만 최댓값 업데이트
//                        if(valid) {
//                            max = Math.max(max, sum);
//                        }
//                    }
//                }
//            }
//        }
//    }
}