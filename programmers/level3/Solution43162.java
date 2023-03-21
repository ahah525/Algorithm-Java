package programmers.level3;


import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] 네트워크
 * [풀이시간] / 10분
 * [한줄평] 전형적인 DFS,BFS 로 푸는 쉬운 문제였고 더 익숙한 BFS 로 풀었다. / BFS 로 빨리 풀었던 기본 문제였다.
 * 1_v1. BFS(성공)
 * 2_v1. BFS(성공)
 * 2_v2. DFS(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43162">문제</a>
 */
class Solution43162 {

    public static void main(String[] args) {
        // 2
        System.out.println(solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));

        // 1
        System.out.println(solution(3, new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
    }

    // 1_v1, 2_v1
    /**
     * @param n 컴퓨터의 개수(1 이상 200 이하인 자연수)
     * @param computers 연결에 대한 정보가 담긴 2차원 배열
     * - 각 컴퓨터는 0부터 n-1인 정수로 표현
     * - i번 컴퓨터와 j번 컴퓨터가 연결: computers[i][j] = 1
     * - 항상 computer[i][i] = 1
     * @return 네트워크의 개수
     */
    public static int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n]; // 방문여부
        int answer = 0; // 네트워크 개수
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                bfs(i, n, computers, visited);
                answer++;
            }
        }
        return answer;
    }

    public static void bfs(int start, int n, int[][] map, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        // 시작노드 방문 처리
        q.add(start);
        visited[start] = true;
        // 인접 행렬 검사
        while(!q.isEmpty()) {
            int v = q.poll();  // 현재 노드
            // 인접 노드 검사
            for(int i = 0; i < n; i++) {
                // 방문한적이 없고 연결되어 있으면 방문처리
                if(map[v][i] == 1 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    // 2_v2
    public static int solution2(int n, int[][] computers) {
        boolean[] visited = new boolean[n]; // 방문여부
        int answer = 0; // 네트워크 개수
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(i, n, computers, visited);
                answer++;
            }
        }
        return answer;
    }

    public static void dfs(int v, int n, int[][] map, boolean[] visited) {
        // 현재 노드 방문 처리
        visited[v] = true;
        // 인접 노드 검사
        for(int i = 0; i < n; i++) {
            if(map[v][i] == 1 && !visited[i]) {
                dfs(i, n, map, visited);
            }
        }
    }
}