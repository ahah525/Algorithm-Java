package baekjoon.prefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2851 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 10개의 버섯 점수 입력받기
        int n = 10;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int minAbs = Integer.MAX_VALUE;
        int res = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            int abs = Math.abs(sum - 100);
            if(abs <= minAbs) {
                minAbs = abs;
                res = sum;
            } else {
                break;
            }
        }
        System.out.println(res);
    }
}
