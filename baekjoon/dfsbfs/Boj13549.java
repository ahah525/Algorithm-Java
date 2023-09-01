package baekjoon.dfsbfs;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제명] 숨바꼭질 3
 * [풀이시간] 21분
 * [한줄평] BFS 문제인 것만 안다면 어렵지 않게 풀 수 있었던 문제였다.
 * 1_v1. BFS(성공)
 * [풀이]
 * @See <a href="https://www.acmicpc.net/problem/13549">문제</a>
 */
class Boj13549 {
    static int minTime; // n->k 최소 이동 시간
    static int MAX = 100000;
    static int[] visited = new int[MAX + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        minTime = Integer.MAX_VALUE;
        bfs(n, k);
        System.out.println(minTime);
    }

    public static void bfs(int start, int target) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = 1;
        //
        while(!q.isEmpty()) {
            int v = q.poll();
            // 1. v로 이동 시간이 target로 최소 이동 시간보다 더 크면, 패스(다음 노드에 방문할 필요X)
            if(visited[v] - 1 > minTime) continue;
            // 2. v가 target이면 최솟값 갱신
            if(v == target) {
                minTime = visited[v] - 1;
                continue;
            }
            int[] next = {v - 1, v + 1, 2 * v};
            int[] time = {1, 1, 0};
            for(int i = 0; i < 3; i++) {
                int nv = next[i];   // 다음 노드
                int t = time[i];    // 다음 노드로 이동 시간
                // 3. 범위 벗어나면 패스
                if(!isRange(nv)) continue;
                // 4. 아직 방문한 적 없는 경우 or 방문했지만 현재 이동 시간이 더 최소인 경우
                if(visited[nv] == 0 || visited[nv] > visited[v] + t) {
                    q.add(nv);
                    visited[nv] = visited[v] + t;
                }
            }
        }
    }

    public static boolean isRange(int n) {
        if(0 <= n && n <= MAX) return true;
        return false;
    }
}