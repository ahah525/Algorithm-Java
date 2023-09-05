package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] 최소비용 구하기
 * [풀이시간] 1시간 30분
 * [한줄평] 다익스트라 알고리즘을 구현할 수 있다면 쉽게 풀 수 있는 문제지만 몰라서 결국 풀이를 보고 해결했던 문제다.
 * 1_v1. 그래프(성공)
 * [풀이] 특정 한 노드에서 특정 한 노드까지 최소 비용을 구하기위해 "다익스트라"를 사용했다.
 * 1. 그래프 연결 관계 표현 방식 : 인접리스트
 * 2. 다익스트라에서 최단 거리 노드 찾는 방식 : 우선순위큐
 * @See <a href="https://www.acmicpc.net/problem/1916">문제</a>
 * @See <a href="https://www.acmicpc.net/problem/1916">다익스트라</a>
 */
class Boj1916 {
    static class Node {
        int idx;    // 노드 번호
        int cost;   // 비용
        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    static int n;
    static List<Node>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());    // 도시 개수
        int m = Integer.parseInt(br.readLine());    // 버스 개수
        // 1. 인접 리스트
        graph = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());    // 출발 도시 번호
            int e = Integer.parseInt(st.nextToken());    // 도착 도시 번호
            int c = Integer.parseInt(st.nextToken());    // s -> e 버스 비용
            graph[s].add(new Node(e, c));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());   // 구간 출발점의 도시 번호
        int end = Integer.parseInt(st.nextToken());     // 구간 도착점의 도시 번호
        System.out.println(dijkstra(start, end));
    }

    // start 노드에서 end 노드로 가는 최소 비용 계산
    public static int dijkstra(int start, int end) {
        // 1. 시작노드 설정
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(start, 0));
        // 2. 최단 거리 배열 초기화
        boolean[] visited = new boolean[n + 1];
        int[] d = new int[n + 1];
        int INF = Integer.MAX_VALUE;
        Arrays.fill(d, INF);
        d[start] = 0;
        while(!pq.isEmpty()) {
            // 3. 아직 방문하지 않은 노드 중에서 출발지로부터 최단 거리에 있는 노드 방문하기
            int cur = pq.poll().idx;
            if(visited[cur]) continue;
            visited[cur] = true;
            // 4. cur과 연결된 노드들 모두 검사
            for(Node next : graph[cur]) {
                // 5. 이미 next를 방문했거나, start에서 next로 가는 최소 비용 <= start에서 cur을 거쳐 next로 가는 최소 비용 이면 패스
                if(visited[next.idx] || d[next.idx] <= d[cur] + next.cost) continue;
                // 6. start에서 next로 가는 최소 비용 갱신
                // start ~> next: Math.min(start ~> next, start ~> cur -> next)
                d[next.idx] = d[cur] + next.cost;
                pq.add(new Node(next.idx, d[next.idx]));
            }
        }
        return d[end];
    }
}