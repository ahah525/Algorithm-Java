package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제명] 숨바꼭질 2
 * [풀이시간] 55분 / 30분
 * [한줄평] 처음에 DP 문제라고 생각해서 풀다가 결국 풀이 힌트를 보고 이해했던 문제였다.
 * / BFS 문제라는 것을 알고 풀었지만 최소 비용인 방법의 개수를 구하는 것이 조금 어려웠던 것 같다. 다시 풀어봐도 좋을 문제다.
 * 1_v1. BFS(성공)
 * [풀이]
 * 2_v1. BFS(성공)
 * @See <a href="https://www.acmicpc.net/problem/12851">문제</a>
 */
class Boj12851 {
    // 1_v1
    static int MAX = 100000;
    static int[] visited = new int[MAX + 1];
    static int minTime;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        minTime = Integer.MAX_VALUE;
        cnt = 0;
        // n -> k
        bfs(n, k);
        System.out.println(minTime);
        System.out.println(cnt);
    }

    public static void bfs(int start, int target) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = 1;
        //
        while(!q.isEmpty()) {
            int v = q.poll();
            if(v == target) {
                minTime = visited[v] - 1;
                cnt++;
            }
            if(minTime < visited[v] - 1) return;
            int[] next = {v - 1, v + 1, 2 * v};
            for(int nv : next) {
                if(!isRange(nv)) continue;
                // nv에 처음 방문한 경우 or 이전에 nv에 방문했을 때 걸린 시간과 이번에 nv에 방문할 때 걸린 시간이 같은 경우
                if(visited[nv] == 0 || visited[nv] == visited[v] + 1) {
                    q.add(nv);
                    visited[nv] = visited[v] + 1;
                }
            }
        }
    }

    public static boolean isRange(int n) {
        if(0 <= n && n <= MAX) return true;
        return false;
    }

    // 2_v1
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(st.nextToken());
//        int k = Integer.parseInt(st.nextToken());
//        int MAX = 100000;
//        int[] visited = new int[MAX + 1];
//        int cnt = 0;
//        //
//        Queue<Integer> q = new LinkedList<>();
//        q.add(n);
//        visited[n] = 1;
//        while(!q.isEmpty()) {
//            int x = q.poll();
//            if(x == k) {
//                cnt++;
//                while(!q.isEmpty()) {
//                    if(q.poll() == k) cnt++;
//                }
//                break;
//            }
//            int[] next = {x - 1, x + 1, 2 * x};
//            for(int nx : next) {
//                if(nx < 0 || nx > MAX) continue;
//                if(visited[nx] == 0 || visited[x] + 1 == visited[nx]) {
//                    q.add(nx);
//                    visited[nx] = visited[x] + 1;
//                }
//            }
//        }
//        //
//        System.out.println(visited[k] - 1);
//        System.out.println(cnt);
//    }
}