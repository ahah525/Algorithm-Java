package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제명] 숨바꼭질 4
 * [풀이시간] (16분)
 * [한줄평]
 * 1_v1. BFS(실패-시간초과)
 * [풀이]
 * @See <a href="https://www.acmicpc.net/problem/13913">문제</a>
 */
class Boj13913 {
    static class Node {
        int value;
        String path;

        Node(int value, String path) {
            this.value = value;
            this.path = path;
        }
    }
    static int MAX = 100001;
    static int minTime;

    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        minTime = Integer.MAX_VALUE;
        visited = new int[MAX + 1];
        String path = bfs(n, k);
        System.out.println(minTime);
        System.out.println(path);
    }

    public static String bfs(int start, int target) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, String.valueOf(start)));
        visited[start] = 1;
        //
        while(!q.isEmpty()) {
            Node cur = q.poll();
            int v = cur.value;
            String path = cur.path;
            if(v == target) {
                minTime = visited[v] - 1;
                return path;
            }
            int[] next = {v - 1, v + 1, 2 * v};
            for(int nv : next) {
                if(!isRange(nv)) continue;
                if(visited[nv] == 0) {
                    q.add(new Node(nv, path + " " + nv));
                    visited[nv] = visited[v] + 1;
                }
            }
        }
        return "";
    }

    public static boolean isRange(int n) {
        if(0 <= n && n <= MAX) return true;
        return false;
    }
}