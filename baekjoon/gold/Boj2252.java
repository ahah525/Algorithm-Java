package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] 줄 세우기
 * [풀이시간] 1시간
 * [한줄평] 매번 조건에 따라 노드를 넣었다 뺐다하는건 시간초과가 날 것 같아 고민하다가 결국 해답을 못찾아서 풀이를 보고 이해했던 문제다.
 * 1_v1. 그래프(성공)
 * [풀이] 위상정렬
 * @See <a href="https://www.acmicpc.net/problem/2252">문제</a>
 * @See <a href="https://bcp0109.tistory.com/21">위상정렬1</a>
 * @See <a href="https://www.youtube.com/watch?v=qzfeVeajuyc">위상정렬2</a>
 */
class Boj2252 {
    // 1_v1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 학생수
        int m = Integer.parseInt(st.nextToken());   // 키 비교 횟수
        // 1. 인접 리스트
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        // 2. 진입차수 배열
        int[] inDegree = new int[n + 1];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // a -> b
            graph.get(a).add(b);
            inDegree[b]++;
        }
        List<Integer> res = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();  // 위상
        // 3. 진입차수가 0인 노드를 큐에 삽입
        for(int i = 1; i <= n; i++) {
            if(inDegree[i] == 0) q.add(i);
        }
        // 5. 큐가 빌 때까지 반복
        while(!q.isEmpty()) {
            // 2. 큐에서 꺼낸 노드를 결과 리스트에 추가
            int cur = q.poll();
            res.add(cur);
            // 3. 꺼낸 노드와 연결된 모든 간선 제거
            for(int next : graph.get(cur)) {
                inDegree[next]--;   // next의 진입차수 1개 감소
                // 4. 감소한 진입차수가 0이면 큐에 삽입
                if(inDegree[next] == 0) q.add(next);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int node : res) {
            sb.append(node).append(" ");
        }
        System.out.println(sb);
    }
}