package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] 게임 개발
 * [풀이시간] (35분)
 * [한줄평]
 * 1_v1. (실패)
 * [풀이]
 * @See <a href="https://www.acmicpc.net/problem/1516">문제</a>
 */
class Boj1516 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 건물수
        StringTokenizer st;
        // 1. 인접리스트, 진입차수 배열 초기화
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inDegree = new int[n + 1];
        int[] arr = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());   // 건물을 짓는데 걸리는 시간
            int num = Integer.parseInt(st.nextToken()); // 건물을 짓기 위해 먼저 지어야하는 건물 번호
            if(num == -1) continue;
            graph.get(num).add(i);
            inDegree[i]++;
        }
        // 진입차수가 0인 노드를 큐에 삽입
        int[] res = new int[n + 1];
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            if(inDegree[i] == 0) {
                q.add(i);
                res[i] = arr[i];
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();
            // cur과 연결된 간선 끊기
            for(int next : graph.get(cur)) {
                inDegree[next]--;
                if(inDegree[next] == 0) {
                    res[next] = res[cur] + arr[next];
                    q.add(next);
                }
            }
        }

//        while(!q.isEmpty()) {
//            int cur = q.poll();
//            for(int next : graph.get(cur)) {
//                q.add(next);
//                res[next] = res[cur] + arr[next];
//            }
//        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            sb.append(res[i]).append("\n");
        }
        System.out.println(sb);
    }
}