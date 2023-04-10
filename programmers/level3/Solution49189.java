package programmers.level3;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * [문제명] 가장 먼 노드
 * [풀이시간] 27분 / 11분
 * [한줄평] 오랜만에 그래프 문제를 풀어서 조금 시간이 걸리긴 했지만 어려운 문제는 아니었다. / 쉬운 bfs 문제였다.
 * 1_v1. BFS(성공)
 * 2_v1. BFS(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/49189">문제</a>
 */
class Solution49189 {
    public static void main(String[] args) {
        // 3
        System.out.println(solution(6, new int[][] {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}));
    }

    // 1_v1, 2_v1
    /**
     * @param n 노드의 개수
     * @param edge 간선에 대한 정보가 담긴 2차원 배열
     * @return 1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지
     */
    public static int solution(int n, int[][] edge) {
        // 1. 인접 리스트
        List<Integer>[] graph = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int[] e : edge) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        // 2. 시작노드 방문처리
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[n + 1];
        q.add(1);
        visited[1] = 1;

        int max = 0;    // 최장 거리(1번 노드로부터 가장 멀리 떨어진 노드의 거리)
        int cnt = 0;    // 최장 거리의 노드 개수
        while(!q.isEmpty()) {
            int v = q.poll();
            if(max < visited[v]) {
                // 3. 최장 거리 < 현재 거리 -> 최장 거리 갱신
                max = visited[v];
                cnt = 1;
            } else if(max == visited[v]) {
                // 4. 최장 거리 == 현재 거리 -> 개수 카운트
                cnt++;
            }
            for(int nv : graph[v]) {
                if(visited[nv] == 0) {
                    q.add(nv);
                    visited[nv] = visited[v] + 1;
                }
            }
        }
        return cnt;
    }
}