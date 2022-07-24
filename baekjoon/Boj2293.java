package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 동전의 종류
        int k = Integer.parseInt(st.nextToken());   // 동전 가치의 합
        // a[i]: i번째 동전의 가치
        int[] a = new int[n + 1];
        // d[i]: i원을 만들 수 있는 경우의 수
        int[] d = new int[k + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        d[0] = 1;   // a[i]원 1개로 만드는 경우의 수
        // 동전의 종류만큼 반복
        for (int j = 1; j <= n; j++) {
            // 마지막 동전을 a[j]로 쓸 때, i원을 만들 수 있는 경우의 수 더해줌
            for (int i = a[j]; i <= k; i++) {
                d[i] += d[i - a[j]];
            }
        }
        System.out.println(d[k]);
    }
}
