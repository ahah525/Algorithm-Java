package baekjoon.divideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 0 : 하얀색
 * 1 : 파란색
 * 잘라진 햐얀색 색종이의 개수
 * 잘라진 파란색 색종이의 개수
 */
public class Boj2630 {
    private static int[][] arr;
    private static int blue, white = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 종이 한 변의 길이
        arr = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(0, 0, n);

        System.out.println(white);
        System.out.println(blue);
    }

    public static void divide(int r, int c, int size) {
        int color = arr[r][c];
        // 종료 조건
        if(size == 1 || isSameColor(r, c, size, color)) {
            if(color == 0) {
                white++;
            } else {
                blue++;
            }
            return;
        }

        // 분할
        size /= 2;

        divide(r, c, size);
        divide(r, c + size, size);
        divide(r + size, c, size);
        divide(r + size, c + size, size);
    }

    // 전체 종이가 모두 같은색으로 칠해져있는지 검사
    public static boolean isSameColor(int r, int c, int size, int color) {
        for(int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if(arr[i][j] != color) {
                    return false;
                }
            }
        }
        return true;
    }
}
