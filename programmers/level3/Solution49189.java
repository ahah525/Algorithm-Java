package programmers.level3;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * [문제명] 가장 먼 노드
 * [풀이시간] 27분
 * [한줄평] 오랜만에 그래프 문제를 풀어서 조금 시간이 걸리긴 했지만 어려운 문제는 아니었다.
 * v1. BFS(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/49189">문제</a>
 */
class Solution49189 {
    public static void main(String[] args) {
        // 3
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(solution(6, edge));
    }

    /**
     * @param n 노드의 개수
     * @param edge 간선에 대한 정보가 담긴 2차원 배열
     * @return 1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지
     */
    public static int solution(int n, int[][] edge) {
        // 인접리스트 만들기
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] arr : edge) {
            graph.get(arr[0]).add(arr[1]);
            graph.get(arr[1]).add(arr[0]);
        }
        return bfs(n, graph);
    }

    public static int bfs(int n, List<List<Integer>> graph) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int[] route = new int[n + 1];
        int max = 0;
        int cnt = 0;
        // 첫번째 노드 삽입
        q.add(1);
        visited[1] = true;
        //
        while(!q.isEmpty()) {
            int v = q.poll();
            if(max < route[v]) {
                max = route[v];
                cnt = 1;
            } else {
                cnt++;
            }
            // 인접노드 탐색
            for(int i : graph.get(v)) {
                if(!visited[i]) {
                    // 방문처리
                    q.add(i);
                    visited[i] = true;
                    route[i] = route[v] + 1;
                }
            }
        }
        return cnt;
    }
}