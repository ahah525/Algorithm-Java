package baekjoon.divideAndConquer;

import java.io.*;

/**
 * n = 3 * 2^k (k = 0 ~ 10)
 */
public class Boj2448 {
    private static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int m = n * 2;
        arr = new char[m][m];
        divide(0, n - 1, n);    // 시작점 열을 n으로 하면 틀림(길이가 짝수이므로)

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
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
            int[] dx = {0, 1, 1, 2, 2, 2, 2, 2};
            int[] dy = {0, -1, 1, -2, -1, 0, 1, 2};
            for (int i = 0; i < 8; i++) {
                arr[r + dx[i]][c + dy[i]] = '*';
            }
            return ;
        }

        size /= 2;
        // 3개
        divide(r, c, size);
        divide(r + size, c - size, size);
        divide(r + size, c + size, size);
    }
}
