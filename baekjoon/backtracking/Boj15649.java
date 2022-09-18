package baekjoon.backtracking;

import java.io.*;
import java.util.StringTokenizer;

public class Boj15649 {
    private static int n, m;
    private static boolean[] visited;
    private static int[] arr;
    private static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        arr = new int[m];

        // 1~n 자연수 중에서 중복없이 m개를 고른 수열

        dfs(0);
        bw.flush();
        bw.close();
    }

    public static void dfs(int depth) throws IOException {
        if(depth == m) {
            for (int val : arr) {
                bw.write(val + " ");
//                System.out.print(val + " ");
            }
            bw.write("\n");
//            System.out.println();
            return;
        }

        for(int i = 0; i < n; i++) {
            // 노드 방문하지 않은 경우
            if(!visited[i]) {
                // 방문 처리
                visited[i] = true;
                arr[depth] = i + 1;
                dfs(depth + 1);
                // 상태 원상복귀
                visited[i] = false;
            }
        }
    }
}
