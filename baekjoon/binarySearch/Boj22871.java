package baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj22871 {
    private static int n;
    private static int[] a;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());    // 돌 개수
        a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(binarySearch(0, n - 1));
    }
    public static int binarySearch(int start, int end) {
        if (start == end) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (int mid = start + 1; mid <= end; mid++) {
            // start -> mid로 한번에 이동하는데 드는 힘
            int p1 = (mid - start) * (1 + Math.abs(a[start] - a[mid]));
            // mid -> end로 이동하는데 드는 최대 힘
            int p2 = binarySearch(mid, end);
            // start -> end로 이동하는데 드는 최대 힘
            int maxP = Math.max(p1, p2);
            res = Math.min(res, maxP);
        }
        return res;
    }
}
