package baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj22871 {
    private static int n;
    private static long[] a;
    private static long[][] d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());    // 돌 개수
        a = new long[n];
        d = new long[n][n]; // i->j로 가는데 드는 최대힘
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i++) {
            Arrays.fill(d[i], -1);
        }
        System.out.println(binarySearch(0, n - 1));
    }
    public static long binarySearch(int start, int end) {
        if (start == end) {
            return 0;
        }
        if(d[start][end] != -1) {
            return d[start][end];
        }

        long res = Long.MAX_VALUE;
        for (int mid = start + 1; mid <= end; mid++) {
            // start -> mid로 한번에 이동하는데 드는 힘
            long p1 = (mid - start) * (1 + Math.abs(a[start] - a[mid]));
            // mid -> end로 이동하는데 드는 최대 힘
            long p2 = binarySearch(mid, end);
            d[mid][end] = p2;
            // start -> end로 이동하는데 드는 최대 힘
            long maxP = Math.max(p1, p2);
            res = Math.min(res, maxP);
        }
        return res;
    }
}
