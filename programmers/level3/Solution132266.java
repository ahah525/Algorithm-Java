package programmers.level3;


import java.util.*;

/**
 * [문제명] 부대복귀
 * [풀이시간] 14분 / 18분(10분+8분)
 * [한줄평] 레벨3 문제였지만 너무 기초적인 BFS 문제여서 쉽게 풀었다.
 * / 처음에 풀이했던 방법이 시간초과가 났다. 기초 문제지만 복습이 필요하다.
 * 1_v1. BFS(성공)
 * [풀이] 인접리스트
 * 2_v1. BFS(실패 - 12,13 시간초과)
 * [풀이] 입력되는 출발지마다 bfs 실행 -> 최대 500번 수행
 * 2_v2. BFS(성공)
 * [풀이] 도착지를 출발지로 가정하고 bfs 1번만 수행
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/132266">문제</a>
 */
class Solution132266 {
    public static void main(String[] args) {
        //
        System.out.println();
    }

    // 2_v2
    List<Integer>[] graph;
    int[] visited;  // visited[i]: destination 에서 i까지 최단거리

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        // 1. 최단거리 -1로 초기화
        visited = new int[n + 1];
        Arrays.fill(visited, -1);
        // 2. 인접리스트 초기화
        graph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] r : roads) {
            graph[r[0]].add(r[1]);
            graph[r[1]].add(r[0]);
        }
        // 3. destination이 출발지일 때 다른 모든 노드들까지 최단거리 계산
        bfs(destination);
        //
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++) {
            answer[i] = visited[sources[i]];
        }
        return answer;
    }

    public void bfs(int s) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = 0;
        while(!q.isEmpty()) {
            int v = q.poll();
            for(int nv : graph[v]) {
                if(visited[nv] != -1) continue;
                q.add(nv);
                visited[nv] = visited[v] + 1;
            }
        }
    }
}