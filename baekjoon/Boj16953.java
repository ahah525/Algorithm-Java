package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16953 {
    private static long a;  // 시작걊
    private static long b;  // 목표값

    static class Node {
        long value; // 값
        int depth;  // 깊이

        public Node(long value, int depth) {
            this.value = value;
            this.depth = depth;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        bfs();
    }
    public static void bfs() {
        Queue<Node> q = new LinkedList<>();
        // 시작 노드 삽입
        q.add(new Node(a, 1));
        // 큐가 비지않을 때까지 반복
        while (!q.isEmpty()) {
            Node node = q.poll();
            long v = node.value;
            int d = node.depth;
            long v1 = v * 2;    // * 2
            long v2 = v * 10 + 1;   // 뒤에 1 붙이기
            if(v1 == b || v2 == b) {
                System.out.println(d + 1);
                return;
            }
            // 목표값보다 작을때만 삽입
            if(v1 < b) {
                q.add(new Node(v1, d + 1));
            }
            if (v2 < b) {
                q.add(new Node(v2, d + 1));
            }
        }
        System.out.println(-1);
    }
}
