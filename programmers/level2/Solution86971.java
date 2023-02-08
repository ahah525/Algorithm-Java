package programmers.level2;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * [문제명] 전력망을 둘로 나누기
 * [풀이시간] 30분
 * [한줄평] n 의 크기를 보니 완전탐색으로 풀 수 있겠다는 생각이 들어서 BFS 를 반복문을 돌려 풀었다. BFS 로 풀었지만 DFS 로 풀어도 되는 문제다.
 * 1_v1. 인접리스트, BFS(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/86971">문제</a>
 */
class Solution86971 {
    public static void main(String[] args) {
        // 3
        int[][] wires1 = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
        System.out.println(solution1(9, wires1));
    }

    /**
     * @param n 송전탑의 개수
     * @param wires 전선 정보
     * @return 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때, 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)
     */
    public static int solution1(int n, int[][] wires) {
        int min = 100;
        // 1. 인접리스트
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for(int[] wire : wires) {
            graph.get(wire[0]).add(wire[1]);
            graph.get(wire[1]).add(wire[0]);
        }
        // 2. 각 전선을 끊었을 때 경우의 수 모두 비교
        for(int[] wire: wires) {
            // 해당 전선을 끊었을 때 각 트리의 탑 개수의 차 구하기
            int v1 = bfs1(wire[0], wire[1], n, graph);
            int v2 = bfs1(wire[1], wire[0], n, graph);
            min = Math.min(min, Math.abs(v1 - v2));
        }
        return min;
    }

    public static int bfs1(int start, int impossible, int n, List<List<Integer>> graph) {
        // start -> impossible 는 끊어져서 갈 수 없음
        int cnt = 0;    // 탑개수
        boolean[] visited = new boolean[n + 1]; // 탑 방문 여부
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        while(!q.isEmpty()) {
            int v = q.poll();
            cnt++;
            // 인접노드
            for(int i : graph.get(v)) {
                // 끊긴 간선이면 패스
                if(v == start && i == impossible || i == start && v == impossible)
                    continue;
                if(!visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
        return cnt;
    }
}