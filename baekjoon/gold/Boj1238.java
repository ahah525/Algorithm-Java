package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] 파티
 * [풀이시간] 38분
 * [한줄평] 다익스트라 알고리즘을 알고 있으면 조금 응용해서 풀 수 있는 문제였다.
 * 1_v1. 그래프/다익스트라(성공)
 * [풀이] 1~n 까지 모든 노드를 시작점으로 하여 다익스트라를 수행했을 때, 왕복 최단거리를 구한다.
 * @See <a href="https://www.acmicpc.net/problem/1238">문제</a>
 */
class Boj1238 {
    // 1_v1
    static class Node {
        int num;
        int time;
        Node(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 노드 수
        int m = Integer.parseInt(st.nextToken());   // 간선 수
        int x = Integer.parseInt(st.nextToken());   // 시작점
        // 1. 인접 리스트 초기화
        List<List<Node>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());   // 가중치
            graph.get(a).add(new Node(b, t));
        }
        int max = 0;
        // 2. x -> i 최단거리
        int[] back = dijkstra(x, n, graph);
        for(int i = 1; i <= n; i++) {
            // 3. i -> x 최단거리
            int[] go = dijkstra(i, n, graph);
            // 4. (i -> x -> i) 경로의 최단거리 합과 최댓값을 비교해 갱신
            max = Math.max(max, go[x] + back[i]);
        }
        System.out.println(max);
    }

    // 시작점이 s일 때 최단거리 배열 구하기
    public static int[] dijkstra(int s, int n, List<List<Node>> graph) {
        boolean[] visited = new boolean[n + 1];
        // 1. 최단거리 배열 초기화
        int[] d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        // 2. 시작노드 설정
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.time - o2.time); // 최단거리
        pq.add(new Node(s, 0));
        d[s] = 0;
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(visited[cur.num]) continue;
            visited[cur.num] = true;
            // cur과 연결된 노드들
            for(Node next : graph.get(cur.num)) {
                // x ~> next, x ~> cur -> next
                if(visited[next.num] || d[next.num] <= d[cur.num] + next.time) continue;
                d[next.num] = d[cur.num] + next.time;
                pq.add(new Node(next.num, d[next.num]));
            }
        }
//        System.out.println(Arrays.toString(d));
        return d;
    }
}