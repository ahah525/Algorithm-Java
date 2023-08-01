package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 징검다리
 * [풀이시간] (10분+20분)
 * [한줄평]
 * 1_v1. (실패)
 * 1_v2. DFS, 완전탐색(실패 - 시간초과)
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=390">문제</a>
 */
class Solution390 {
    // 1_v2
    static int max;
    // static int[] path;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //
        max = 1;
        // path = new int[n];
        // Arrays.fill(path, -1);
        dfs(0, -1, 0, n, arr);

        System.out.println(max);
    }

    public static void dfs(int depth, int prevIdx, int prev, int n, int[] arr) {
        // System.out.println(Arrays.toString(path));
        max = Math.max(max, depth);
        if(prevIdx == n - 1) return;
        for(int i = prevIdx + 1; i < n; i++) {
            if(prev < arr[i]) {
                // path[depth] = i;
                dfs(depth + 1, i, arr[i], n, arr);
                // path[depth] = -1;
            }
        }
    }
}