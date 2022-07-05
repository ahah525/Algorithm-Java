package baekjoon;

import java.io.*;
import java.util.*;

public class Boj11725 {
    static int n;   // 노드 개수
    static int[] parent;    //  부모 노드
    static boolean[] visited;   // 방문 여부
    static ArrayList<Integer>[] graph;  // 인접 리스트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        parent = new int[n + 1];  // 부모 노드
        graph = new ArrayList[n + 1];    // 인접 리스트
        visited = new boolean[n + 1]; // 방문 여부
        // 인접 리스트 초기화
        for(int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        // (n-1)개의 연결된 두 정점 정보로 인접 리스트 만들기
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);    // a노드와 인접한 노드 추가
            graph[b].add(a);    // b노드와 인접한 노드 추가
        }
        bfs(graph, 1, visited);
        // 부모 노드 출력
        for (int i = 2; i <= n; i++) {
            bw.write(parent[i] + "\n");
        }
        bw.flush();
        bw.close();
    }
    static public void bfs(ArrayList<Integer>[] graph, int start, boolean[] visited) {
        // 큐 구현을 위해 LinkedList 사용
        Queue<Integer> q = new LinkedList<>();
        // 시작 노드 삽입, 방문 처리
        q.offer(start);
        visited[start] = true;
        // 큐가 빌 때까지 반복
        while (!q.isEmpty()) {
            // 큐에서 노드 한 개 추출
            int v = q.poll();   // 현재 노드
            // 해당 노드와 연결된 노드들을 큐에 삽입
            for (int i : graph[v]) {
                // 해당 노드들을 방문한적이 없으면
                if (!visited[i]) {
                    // 해당 노드 삽입, 방문 처리
                    visited[i] = true;
                    q.offer(i);
                    parent[i] = v;  // 부모 노드 저장
                }
            }
        }
    }
}
