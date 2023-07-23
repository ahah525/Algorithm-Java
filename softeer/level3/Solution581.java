package softeer.level3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제명] 택배 마스터 광우
 * [풀이시간] 22분
 * [한줄평] 순열로 풀어야겠다고 떠올려서 빨리 풀 수 있었던 문제였다.
 * 1_v1. 완전탐색, DFS(성공)
 * [풀이] 순열로 모든 경우의 수에 대해 택배 무게합 계산
 * @See <a href="https://softeer.ai/practice/info.do?idx=1&eid=581">문제</a>
 */
class Solution581 {
    static int[] rail;
    static int[] path;
    static int n, m, k;
    static int min = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 레일 개수
        m = Integer.parseInt(st.nextToken());   // 택배 바구니 무게
        k = Integer.parseInt(st.nextToken());   // 시행 횟수
        path = new int[n];
        rail = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            rail[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, new boolean[n]);
        System.out.println(min);
    }

    // 순열
    public static void dfs(int depth, boolean[] visited) {
        if(depth == n) {
            min = Math.min(min, calc());
            return;
        }
        for(int i = 0; i < n; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            path[depth] = rail[i];
            dfs(depth + 1, visited);
            visited[i] = false;
        }
    }

    // 현재 순서로 k번 일할 때 택배 무게 총합 계산
    public static int calc() {
        int sum = 0;
        int idx = 0;
        for(int i = 0; i < k; i++) {
            int weight = 0;
            while(weight + path[idx] <= m) {
                weight += path[idx];
                idx++;
                if(idx == n) idx = 0;
            }
            sum += weight;
        }
        return sum;
    }
}