package baekjoon.gold;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [문제명] 게임 개발
 * [풀이시간] 55분(35분+20분)
 * [한줄평] 조건을 제대로 읽지않아서 결국 반례를 찾기 위해 풀이를 참고했다. 단순한 위상정렬에 약간 응용을 한 좋은 문제같다.
 * 1_v1. 그래프/위상정렬(실패)
 * [반례] 동시에 건물을 지을 수 있다는 조건과 어떤 한 건물을 짓기 위해 먼저 지어야하는 건물이 여러 개일 수 있다는 조건을 고려하지 않음
 * 1_v2. 그래프/위상정렬(성공)
 * [풀이] 동시에 건물을 지을 수 있다 = 어떤 건물A를 짓기 위해 먼저 지어야하는 선행 건물(B,C,D...)가 있는 경우, 가장 늦게 지어진 건물이 끝난 이후에 건물A를 지을 수 있음
 * @See <a href="https://www.acmicpc.net/problem/1516">문제</a>
 * @See <a href="https://rldd.tistory.com/432">반례</a>
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
        int[] arr = new int[n + 1]; // 각 건물을 짓는데 걸리는 시간
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
            while(true) {
                int num = Integer.parseInt(st.nextToken()); // 선행 건물 번호
                if(num == -1) break;
                graph.get(num).add(i);
                inDegree[i]++;
            }
        }
        // 2. 진입차수가 0인 노드를 큐에 삽입, 최소시간
        int[] res = new int[n + 1]; // 각 건물을 짓는데 걸린 최소시간
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            if(inDegree[i] == 0) {
                q.add(i);
                res[i] = arr[i];
            }
        }
        // 6. 큐가 빌 때까지 반복
        while(!q.isEmpty()) {
            int cur = q.poll();
            // 3. cur과 연결된 간선 끊기
            for(int next : graph.get(cur)) {
                inDegree[next]--;
                // 4. 진입차수가 0이면 큐에 삽입
                if(inDegree[next] == 0) q.add(next);
                // 5. next건물을 짓는데 걸리는 시간 갱신(next의 선행 건물 중 제일 늦게 지어지는 건물 이후에 지을 수 있음)
                res[next] = Math.max(res[next], res[cur] + arr[next]);
            }
//            System.out.println(Arrays.toString(res));
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            sb.append(res[i]).append("\n");
        }
        System.out.println(sb);
    }
}