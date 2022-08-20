package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj2606 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int v = Integer.parseInt(br.readLine());    // 노드 수
        int e = Integer.parseInt(br.readLine());    // 간선 수
        boolean[] visited = new boolean[v + 1];     // 방문여부
        int res = 0;

        // 인접 리스트
        ArrayList<Integer>[] graph = new ArrayList[v + 1];
        for (int i = 0; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        res = bfs(graph, 1, visited);
        System.out.println(res);
    }

    public static int bfs(ArrayList<Integer>[] graph, int start, boolean[] visited) {
        int res = 0;
        Queue<Integer> q = new LinkedList<>();
        // 시작 노드 큐에 삽입, 방문 처리
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int v = q.poll();
            res++;
            // 인접 리스트
            for (int i : graph[v]) {
                // 방문한적이 없다면 큐에 삽입, 방문처리
                if (!visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
        return res - 1; //  1번을 제외한 개수
    }
}
