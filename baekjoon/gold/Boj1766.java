package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] 문제집
 * [풀이시간] 10분
 * [한줄평] 위상정렬 알고리즘만 알면 쉽게 풀 수 있는 문제였다.
 * 1_v1. 그래프(성공)
 * [풀이] 위상정렬
 * @See <a href="https://www.acmicpc.net/problem/1766">문제</a>
 */
class Boj1766 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 문제 수
        int m = Integer.parseInt(st.nextToken());
        // 1. 인접 리스트
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        // 2. 인접차수 배열
        int[] inDegree = new int[n + 1];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            // a -> b
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            inDegree[b]++;
            graph.get(a).add(b);
        }
        // 3. 인접차수가 0인 노드 큐에 삽입
        Queue<Integer> q = new PriorityQueue<>();   // 가능하면 쉬운 문제부터 풀어야 한다는 조건
        for(int i = 1; i <= n; i++) {
            if(inDegree[i] == 0) q.add(i);
        }
        //
        List<Integer> res = new ArrayList<>();
        while(!q.isEmpty()) {
            // 4. 큐에서 빼고 간선 끊기
            int cur = q.poll();
            res.add(cur);
            for(int next : graph.get(cur)) {
                inDegree[next]--;
                if(inDegree[next] == 0) q.add(next);
            }
        }
        //
        StringBuilder sb = new StringBuilder();
        for(int node : res) {
            sb.append(node).append(" ");
        }
        System.out.println(sb);
    }
}