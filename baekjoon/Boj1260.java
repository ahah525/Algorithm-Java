package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1260 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 정점 개수
        int m = Integer.parseInt(st.nextToken());   // 간선 개수
        int start = Integer.parseInt(st.nextToken());   // 탐색 시작 번호
        boolean[] visited = new boolean[n + 1]; // 방문 여부
        // 인접 리스트
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        // graph 오름차순 정렬
        for (int i = 1; i <= n; i++) {
            Collections.sort(graph[i]);
        }
        // dfs, bfs 결과
        dfs(graph, start, visited);
        visited = new boolean[n + 1];
        System.out.println();
        bfs(graph, start, visited);
    }

    public static void dfs(ArrayList<Integer>[] graph, int v, boolean[] visited) {
        // 현재 노드 방문 처리
        visited[v] = true;
        System.out.print(v + " ");

        for (int i : graph[v]) {
            // 방문하지 않았다면
            if (!visited[i]) {
                dfs(graph, i, visited);
            }
        }
    }
    public static void bfs(ArrayList<Integer>[] graph, int start, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int v = q.poll();
            System.out.print(v + " ");

            for (int i : graph[v]) {
                // 방문하지 않은 곳이면 큐에 삽입, 방문 처리
                if (!visited[i]) {
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
    }
}
