package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제명] A -> B
 * [풀이시간] (7분)
 * [한줄평]
 * 1_v1. (성공)
 * 2_v1. BFS(실패-메모리 초과)
 * @See <a href="https://www.acmicpc.net/problem/16953">문제</a>
 */
class Boj16953 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        System.out.println(bfs(a, b));
    }

    public static int bfs(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[end + 1];
        q.add(start);
        visited[start] = 1;
        while(!q.isEmpty()) {
            int v = q.poll();
            if(v == end) return visited[end];
            if(2 * v <= end && visited[2 * v] == 0) {
                q.add(2 * v);
                visited[2 * v] = visited[v] + 1;
            }
            if(v * 10 + 1 <= end && visited[v * 10 + 1] == 0) {
                q.add(v * 10 + 1);
                visited[v * 10 + 1] = visited[v] + 1;
            }
        }
        return -1;
    }
}