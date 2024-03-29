package baekjoon.divideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1. 행렬을 2×2 정사각형으로 나눈다.
 * 2. 각 정사각형에서 2번째로 큰 수만 남긴다.
 * 출력 : 마지막에 남은 수
 */
public class Boj17829 {
    private static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int res = divide(0, 0, n);
        System.out.println(res);
    }
    public static int divide(int r, int c, int size) {
        int[] list = new int[4];
        if(size == 2) {
            int idx = 0;
            for (int i = r; i < r + 2; i++) {
                for (int j = c; j < c + 2; j++) {
                    list[idx++] = arr[i][j];
                }
            }
            // 내림차순 정렬 후 2번째로 큰 수 리턴
            Arrays.sort(list);
            return list[2];
        }

        size /= 2;
        list[0] = divide(r, c, size);
        list[1] = divide(r, c + size, size);
        list[2] = divide(r + size, c, size);
        list[3] = divide(r + size, c + size, size);
        // 내림차순 정렬 후 2번째로 큰 수 리턴
        Arrays.sort(list);
        return list[2];
    }
}
