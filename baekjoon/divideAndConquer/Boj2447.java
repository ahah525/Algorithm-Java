package baekjoon.divideAndConquer;

import java.io.*;

/**
 * n = 3의 거듭제곱(1~8)
 */
public class Boj2447 {
    private static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        arr = new char[n][n];
        divide(0, 0, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j] == '\0') {
                    bw.write(' ');
                } else {
                    bw.write('*');
                }
            }
            bw.write('\n');
        }
        bw.flush();
        bw.close();
    }
    public static void divide(int r, int c, int size) {
        if(size == 3) {
            for(int i = r; i < r + 3; i++) {
                for (int j = c; j < c + 3; j++) {
                    if(i != r + 1 || j != c + 1) {
                        arr[i][j] = '*';
                    }
                }
            }
            return ;
        }

        size /= 3;
        // 9개
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(i != 1 || j != 1) {
                    divide(r + i * size, c + j * size, size);
                }
            }
        }
    }
}
