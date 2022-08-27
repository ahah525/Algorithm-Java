package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj21278 {
    private static int n;
    private static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());   // 건물 수
        int m = Integer.parseInt(st.nextToken());   // 도로 수
        int[] visited = new int[n + 1]; // 방문 여부
        int min = Integer.MAX_VALUE;
        Point minP = new Point(0, 0);
        // 그래프 정보
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        // i ~ j 거리
        arr = new int[n][n];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(visited, 0);
            bfs(graph, i, visited);
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    if (k != i || k != j) {
                        sum += Math.min(arr[k][i], arr[k][j]);
                    }
                }
                if (min > sum) {
                    min = sum;
                    minP = new Point(i + 1, j + 1);
                }
            }
        }
        System.out.printf("%d %d %d", minP.x, minP.y, min);
    }
    public static void bfs(List<List<Integer>> graph, int start, int[] visited) {
        Queue<Integer> q = new LinkedList<>();
        // 현재 노드 큐에 삽입, 방문 처리
        q.add(start);
        visited[start] = 1;

        while (!q.isEmpty()) {
            int v = q.poll();
            // 현재 노드와 인접한 노드들
            for (int nv : graph.get(v)) {
                if (visited[nv] == 0) {
                    q.add(nv);
                    visited[nv] = visited[v] + 1;
                }
            }
        }
        //
        for (int i = 0; i < n; i++) {
            arr[start - 1][i] = (visited[i + 1] - 1) * 2;
        }
    }
}
