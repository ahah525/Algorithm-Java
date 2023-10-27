package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [문제명] 게리맨더링 2
 * [풀이시간] 3시간
 * [한줄평] 그대로 구현하는 문제라 쉽다고 생각했는데, 각 구역의 인구수를 구하는 부분이 까다로웠다. 다른 풀이에서 경계선을 먼저 체크하는 것을 보고 해결할 수 있었다. 다시 한번 꼭 풀어봐야겠다.
 * 1_v1. 구현,완전탐색(성공)
 * [풀이] 먼저 5구역의 경계선을 체크하고 나머지 구역의 인구수를 차례로 구한다.
 * @See <a href="https://www.acmicpc.net/problem/17779">문제</a>
 * @See <a href="https://bcp0109.tistory.com/218">풀이</a>
 */
class Boj17779 {
    // 1_v1
    static int n, total;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        total = 0;  // 전체 인구수
        map = new int[n][n];
        int min = Integer.MAX_VALUE;
        // 1. map 초기화, 전체 인구수 계산
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                total += map[i][j];
            }
        }
        // 2. 가능한 모든 경우의 (x, y, d1, d2)에 대해 검사
        for(int x = 0; x < n; x++) {
            for(int y = 0; y < n; y++) {
                for(int d1 = 1; d1 < n; d1++) {
                    for(int d2 = 1; d2 < n; d2++) {
                        // 3. 조건 검사
                        if(x + d1 + d2 >= n) continue;
                        if(y < d1 || y + d2 >= n) continue;
                        // 4. 인구 차이의 최솟값 갱신
                        min = Math.min(min, getDiff(x, y, d1, d2));
                    }
                }
            }
        }
        System.out.println(min);
    }

    // 인구가 가장 많은 선거구와 가장 적은 선거구의 인구 차이
    public static int getDiff(int x, int y, int d1, int d2) {
        int[] cnt = new int[5]; // 각 구역의 인구수
        boolean[][] border = new boolean[n][n];
        // 1. 경계선 구하기
        for(int i = 0; i <= d1; i++) {
            border[x + i][y - i] = true;
            border[x + d2 + i][y + d2 - i] = true;
        }
        for(int i = 0; i <= d2; i++) {
            border[x + i][y + i] = true;
            border[x + d1 + i][y - d1 + i] = true;
        }
        // 2. 1번 선거구 인구수 구하기
        for(int i = 0; i < x + d1; i++) {
            for(int j = 0; j <= y; j++) {
                if(border[i][j]) break;
                cnt[0] += map[i][j];
            }
        }
        // 3. 2번 선거구 인구수 구하기
        for(int i = 0; i <= x + d2; i++) {
            for(int j = n - 1; j > y; j--) {
                if(border[i][j]) break;
                cnt[1] += map[i][j];
            }
        }
        // 4. 3번 선거구 인구수 구하기
        for(int i = x + d1; i < n; i++) {
            for(int j = 0; j < y - d1 + d2; j++) {
                if(border[i][j]) break;
                cnt[2] += map[i][j];
            }
        }
        // 5. 4번 선거구 인구수 구하기
        for(int i = x + d2 + 1; i < n; i++) {
            for(int j = n - 1; j >= y - d1 + d2; j--) {
                if(border[i][j]) break;
                cnt[3] += map[i][j];
            }
        }
        // 6. 5번 선거구 인구수 구하기
        cnt[4] = total;
        for(int i = 0; i < 4; i++) {
            cnt[4] -= cnt[i];
        }
        // 7. 최다 인구수와 최저 인구수의 차이 리턴
        Arrays.sort(cnt);
        return cnt[4] - cnt[0];
    }
}