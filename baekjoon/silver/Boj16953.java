package baekjoon.silver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제명] A -> B
 * [풀이시간] / 25분(7분+18분)
 * [한줄평]
 * / 너무 쉬운 문제라고 생각했는데, 메모리 초과가 난다는 생각을 못해서 힌트를 보고 해결했다.
 * 1_v1. BFS(성공)
 * 2_v1. BFS, DP(실패-메모리 초과)
 * [풀이] visited 배열에 i를 만들기 위한 최소 횟수를 기록했다. A, B가 최대 10^9로 해당 크기 만큼 배열을 만들 수 없어 메모리 초과가 되었다.
 * 2_v2. BFS(성공)
 * [풀이] 첫번째 연산은 무조건 짝수만 나오고, 두번째 연산은 무조건 홀수만 나온다. 즉, 두 연산으로 인해 같은 값이 또 나올일은 없으므로 visited 없이 풀 수 있다.
 * @See <a href="https://www.acmicpc.net/problem/16953">문제</a>
 */
class Boj16953 {
    // 1_v1, 2_v2
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        System.out.println(bfs(a, b));
    }

    static class Node {
        long value;
        int depth;

        public Node(long value, int depth) {
            this.value = value;
            this.depth = depth;
        }
    }

    public static int bfs(int start, int end) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 0));
        while(!q.isEmpty()) {
            Node cur = q.poll();
            long v = cur.value;
            int d = cur.depth;
            if(v == end) return d + 1;
            // 1. 2 곱하기
            if(2 * v <= end) q.add(new Node(2 * v, d + 1));
            // 2. 가장 오른쪽에 1 추가하기
            if(v * 10 + 1 <= end) q.add(new Node(v * 10 + 1, d + 1));
        }
        return -1;
    }
}