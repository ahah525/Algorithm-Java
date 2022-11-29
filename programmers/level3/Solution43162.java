package programmers.level3;


import java.util.LinkedList;
import java.util.Queue;

/**
 * [문제명] 네트워크
 * [한줄평] 전형적인 DFS/BFS 로 푸는 쉬운 문제였고 더 익숙한 BFS 로 풀었다.
 * v1. BFS(성공)
 * @See <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43162/solution_groups?language=java">다른 풀이</a>
 */
class Solution43162 {
    static boolean[] visited;   // 방문 여부

    public static void main(String[] args) {
        // 2
        int n1 = 3;
        int[][] computers1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(solution(n1, computers1));

        // 1
        int n2 = 3;
        int[][] computers2 = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        System.out.println(solution(n2, computers2));
    }

    /**
     *
     * @param n 컴퓨터의 개수(1 이상 200 이하인 자연수)
     * @param computers 연결에 대한 정보가 담긴 2차원 배열
     * - 각 컴퓨터는 0부터 n-1인 정수로 표현
     * - i번 컴퓨터와 j번 컴퓨터가 연결: computers[i][j] = 1
     * - 항상 computer[i][i] = 1
     * @return 네트워크의 개수
     */
    public static int solution(int n, int[][] computers) {
        visited = new boolean[n];
        int answer = 0; // 네트워크 개수
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                bfs(i, n, computers);
                answer++;
            }
        }
        return answer;
    }

    public static void bfs(int start, int n, int[][] computers) {
        Queue<Integer> q = new LinkedList<>();
        // 시작노드 방문 처리
        q.add(start);
        visited[start] = true;
        // 인접 행렬 검사
        while(!q.isEmpty()) {
            int v = q.poll();  // 현재 노드
            // 인접 노드 검사
            for(int nv = 0; nv < n; nv++) {
                // 방문한적이 없고 연결되어 있으면 방문처리
                if(!visited[nv] && computers[v][nv] == 1) {
                    q.add(nv);
                    visited[nv] = true;
                }
            }
        }
    }
}