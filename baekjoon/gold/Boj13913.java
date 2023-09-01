package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * [문제명] 숨바꼭질 4
 * [풀이시간] 45분(16분+9분+20분)
 * [한줄평] 경로를 기록하는데 시간초과 문제를 해결하지 못해서 결국 풀이를 참고했고 꼭 복습이 필요하다.
 * 1_v1. BFS(실패-시간초과)
 * [풀이] String으로 경로 저장
 * 1_v2. BFS(실패-시간초과)
 * [풀이] StringBuilder로 경로 저장
 * 1_v3. BFS, Stack(성공)
 * [풀이] 현재노드의 부모노드를 배열에 저장하는 것이 핵심이다. k->n 방향으로 부모 노드를 stack에 push하고 pop하면서 기록한다.
 * @See <a href="https://www.acmicpc.net/problem/13913">문제</a>
 * @See <a href="https://void2017.tistory.com/247">문제 힌트</a>
 */
class Boj13913 {
    // 1_v3
    static int MAX = 100001;
    static int minTime;
    static int[] visited;
    static int[] parent;    // parent[i]: i의 부모 노드

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        minTime = Integer.MAX_VALUE;
        visited = new int[MAX + 1];
        parent = new int[MAX + 1];
        bfs(n, k);
        System.out.println(minTime);
        System.out.println(getPath(n, k));
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
                return;
            }
            int[] next = {v - 1, v + 1, 2 * v};
            for(int nv : next) {
                if(!isRange(nv)) continue;
                if(visited[nv] == 0) {
                    q.add(nv);
                    visited[nv] = visited[v] + 1;
                    // 다음노드의 부모노드 = 현재노드
                    parent[nv] = v;
                }
            }
        }
    }

    public static boolean isRange(int n) {
        if(0 <= n && n <= MAX) return true;
        return false;
    }

    // n -> k 경로 구하기
    public static String getPath(int n, int k) {
        // 1. 시작 노드 = k
        Stack<Integer> stack = new Stack<>();
        int p = k;
        stack.push(k);
        // 2. 해당 노드의 부모 노드를 반복적으로 찾아서 k -> n 경로 구하기
        while(p != n) {
            p = parent[p];
            stack.push(p);
        }
        // 3. n -> k 경로로 바꾸기
        StringBuilder path = new StringBuilder();
        while(!stack.isEmpty()) {
            path.append(stack.pop()).append(" ");
        }
        return path.toString();
    }
}