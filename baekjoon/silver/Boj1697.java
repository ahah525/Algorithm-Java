package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제명] 숨바꼭질
 * [풀이시간] 11분
 * [한줄평] 너무 쉬운 기초 문제라 더이상 풀어볼 필요가 없는 문제다.
 * 1_v1. BFS(성공)
 * [풀이] BFS로 3가지 연산
 * @See <a href="https://www.acmicpc.net/problem/1697">문제</a>
 */
class Boj1697 {
    static int MAX = 100000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        System.out.println(bfs(n, k));
    }

    public static int bfs(int s, int e) {
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[MAX + 1];
        q.add(s);
        visited[s] = 1;
        while(!q.isEmpty()) {
            int x = q.poll();
            if(x == e) return visited[e] - 1;
            if(isRange(x - 1) && visited[x - 1] == 0) {
                q.add(x - 1);
                visited[x - 1] = visited[x] + 1;
            }
            if(isRange(x + 1) && visited[x + 1] == 0) {
                q.add(x + 1);
                visited[x + 1] = visited[x] + 1;
            }
            if(isRange(2 * x) && visited[2 * x] == 0) {
                q.add(2 * x);
                visited[2 * x] = visited[x] + 1;
            }
        }
        return 0;
    }

    public static boolean isRange(int n) {
        if(0 <= n && n <= MAX) return true;
        return false;
    }
}